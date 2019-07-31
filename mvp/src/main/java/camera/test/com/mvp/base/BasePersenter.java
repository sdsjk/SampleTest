package camera.test.com.mvp.base;

import java.lang.ref.WeakReference;

public class BasePersenter<T extends BaseView> {


    //试图的引用对象
//   public WeakReference<T> mView;
   public WeakReference<T> mView;

    public void  attachView(T view){
        this.mView=new WeakReference<T>(view);
    }

    public T getView() {
        return mView.get();
    }

    //在persenter中释放掉view的引用避免内存泄漏
    public void dettachView(){
        if(mView!=null) {
            mView.clear();
            mView=null;
        }
    }

    public boolean  isAttachView(){
        return mView==null;
    }

}
