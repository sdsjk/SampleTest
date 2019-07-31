package camera.test.com.activitylifcly;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        注册监听acticity生命周期方法
        ActivityStackManager.getStackManager().registerActivityLifecycleCallbacks(this);
    }


}
