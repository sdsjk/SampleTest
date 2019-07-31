package camera.test.com.networkdata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.JsonObject;

import java.util.HashMap;

import camera.test.com.networkdata.Api.NetWorkApi;
import camera.test.com.networkdata.bean.NewListBean;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rt);
        initData();
    }

    private void initData() {
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.apiopen.top/")
                .build();
        NetWorkApi netWorkApi = retrofit.create(NetWorkApi.class);
        Call<NewListBean> video = netWorkApi.getNewList("1", "2", "video");

        video.enqueue(new Callback<NewListBean>() {
            @Override
            public void onResponse(Call<NewListBean> call, Response<NewListBean> response) {

                Log.e("TAG", "response code::"+response.code());
                Log.e("TAG", "response::::::"+response.body().getMessage());
            }

            @Override
            public void onFailure(Call<NewListBean> call, Throwable t) {

            }
        });


        HashMap<String, Object> hashMap=new HashMap<>();
        hashMap.put("page","1");
        hashMap.put("count","2");
        hashMap.put("type","video");

        Call<NewListBean> newList2 = netWorkApi.getNewList2(hashMap);
        newList2.enqueue(new Callback<NewListBean>() {
            @Override
            public void onResponse(Call<NewListBean> call, Response<NewListBean> response) {
                Log.e("TAG", "newList2:::::::::"+response.body().getResult());
            }

            @Override
            public void onFailure(Call<NewListBean> call, Throwable t) {

            }
        });

        Call<JsonObject> newList3 = netWorkApi.getNewList3(hashMap);
        newList3.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("TAG", "newList3:::::::::::::"+response.body());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("TAG", "newList3onFailure:::::::::::::"+t);
            }
        });


    }
}
