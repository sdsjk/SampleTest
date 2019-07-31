package camera.test.com.networkdata.okutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import camera.test.com.networkdata.R;
import camera.test.com.networkdata.exception.MyException;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        initView();
    }


    public void RX2concatDelayError(View view){
        Observable.concatArrayDelayError(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onError(new MyException("测试异常"));
            }
        }), Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(6);
            }
        })).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.e("TAG", "concat============="+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void RX2mergeArray(View view){
        Observable.mergeArray(
                Observable.intervalRange(1, 2, 3, 1,TimeUnit.SECONDS),
                Observable.intervalRange(5, 3, 3, 1,TimeUnit.SECONDS),
                Observable.just(5l, 6l, 7l, 8l),
                Observable.just(9l, 10l),
                Observable.just(11l)).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long integer) throws Exception {
                Log.e("TAG", "concat============="+integer);
            }
        });
    }

    public void RX2merge(View view){
        Observable.merge(
                Observable.intervalRange(1, 2, 3, 1,TimeUnit.SECONDS),
                Observable.just(5l, 6l, 7l, 8l),
                Observable.just(9l, 10l),
                Observable.just(11l)).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long integer) throws Exception {
                Log.e("TAG", "concat============="+integer);
            }
        });
    }

    public void RX2ContatArray(View view) {
        Observable.concatArray(Observable.just(1, 2, 3),
                Observable.just(4, 5, 6),
                Observable.just(7, 8, 9),
                Observable.just(10, 11, 12),
                Observable.just(13, 14, 15)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e("TAG", "concat============="+integer);
            }
        });
    }
    public void RX2Contat(View view) {
        Observable.merge(
                Observable.just(5l, 6l, 7l, 8l),
                Observable.just(9l, 10l),
                Observable.just(11l)).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long integer) throws Exception {
                Log.e("TAG", "concat============="+integer);
            }
        });
    }

    public void RX2Buffer(View view) {
        Integer arr[] = {1, 2, 3, 4};
        Observable.fromArray(arr).buffer(2, 1).subscribe(new Observer<List<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Integer> value) {
                for (Integer num : value) {
                    Log.e("TAG", "value:::::::" + num);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


    public void RX2ConcatMap(View view) {
        Integer arr[] = {1, 2, 3, 4};
        Observable.fromArray(arr).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("A" + integer + "::::::" + i);
                }
                return Observable.fromIterable(list);
//                return Observable.just("A"+integer,"B"+integer);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("TAG", "flatMap::::::::::" + s);
            }
        });
    }

    public void RX2flatMap(View view) {
        Integer arr[] = {1, 2, 3, 4};
        Observable.fromArray(arr).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("A" + integer + ";;;;" + i);
                }
                return Observable.fromIterable(list);
//                return Observable.just("A"+integer,"B"+integer);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("TAG", "flatMap::::::::::" + s);
            }
        });
    }

    public void RX2map(View view) {
        Observable.just(1, 2, 3, 4, 5).map(new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) throws Exception {
                return "事件传递修改参数" + 1 + "为字符串";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("TAG", "accept:::::::::::::" + s);
            }
        });
    }

    public void Rx2range(View view) {
        Observable.range(1, 5).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.e("TAG", "onNext:::::::::::" + value + "::range:::::::::::::" + Thread.currentThread());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void Rx2IntervalRange(View view) {
        Observable.intervalRange(1, Integer.MAX_VALUE, 0, 1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long value) {
                Log.e("TAG", "onNext:::::::::::" + value + "::intervalRange:::::::::::::" + Thread.currentThread());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void Rx2Interval(View view) {
        Observable.interval(2, 2, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long value) {
                Log.e("TAG", "onNext:::::::::::" + value + ":::::::::::::" + Thread.currentThread());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void Rx2timer(View view) {
        Observable.timer(3, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long value) {
                Log.e("TAG", "onNext::::timer" + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    Integer num = 10;

    public void Rx2defer(View view) {

        Observable<Integer> defer = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(num);
            }
        });
        num = 90;
        defer.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.e("TAG", "onNext::::defer" + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


    public void Rx2Test(View view) {
        Observable.empty().subscribe(new Observer<Object>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.e("TAG", "empty::::onSubscribe");
            }

            @Override
            public void onNext(Object value) {
                Log.e("TAG", "onNext::::empty");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "onError::::empty");
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "onComplete::::empty");
            }
        });

        Observable.error(new MyException("读书出错")).subscribe(new Observer<Object>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.e("TAG", "onSubscribe::::error");
            }

            @Override
            public void onNext(Object value) {
                Log.e("TAG", "onNext::::error");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "onError::::error" + e);
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "onComplete::::error");
            }
        });

        Observable.never().subscribe(new Observer<Object>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.e("TAG", "onSubscribe::::never");
            }

            @Override
            public void onNext(Object value) {
                Log.e("TAG", "onNext::::never");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "onError::::never");
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "onComplete::::never");
            }
        });
    }


    public void Rx2fromIterable(View view) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        Observable.fromIterable(arrayList).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.e("TAG", "Rx2fromIterable::::onNext" + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e("TAG", "Rx2fromIterable::::onComplete");
            }
        });
    }


    public void Rx2FromArray(View view) {
        Integer age[] = {1, 2, 3, 4};
        Observable.fromArray(age).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("TAG", "Rx2FromArray最先执行的方法......onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.e("TAG", "Rx2FromArray::::onNext" + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e("TAG", "Rx2FromArray::::onComplete");
            }
        });
    }

    public void Rx2Just(View view) {
        Observable.just("A", "b").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("TAG", "最先执行的方法......onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.e("TAG", "事件接收者::::" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "onError::::" + e);
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "onComplete::::");
            }
        });
    }


    public void Rx1(View view) {
        //被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("A");
                e.onNext("B");
                e.onComplete();
            }
        });
//        观察者
        Observer<String> observer = new Observer<String>() {
            Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
                Log.d("TAG", "开始采用subscribe连接");
            }

            @Override
            public void onNext(String value) {
                if ("A".equals(value)) {
                    mDisposable.dispose();
                }
                Log.d("TAG", "对Next事件作出响应" + value);

            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG", "onError事件" + e);
            }

            @Override
            public void onComplete() {
                Log.d("TAG", "onComplete事件");
            }
        };

        observable.subscribe(observer);
    }


    private void initView() {


    }
}
