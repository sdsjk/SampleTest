package camera.test.com.activitylifcly;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Stack;

public class ActivityStackManager {
    public static Stack<Activity> store = new Stack<>();
    public static int numActivity = 0;

    private ActivityStackManager() {}

    /**
     * 饿汉模式获取单例模式
     */
    private static  ActivityStackManager activityStackManager = new ActivityStackManager();

    public static ActivityStackManager getStackManager() {

        return activityStackManager;
    }

    public  static ActivityStackManager getActivityStackmanagerByLazy(){
        if(activityStackManager==null){
            synchronized (ActivityStackManager.class){
                if(activityStackManager==null) {
                    activityStackManager=new ActivityStackManager();
                }
            }
        }

        return activityStackManager;
    }

    private static class ActivityStackManagerHodle{
        private static final ActivityStackManager activityStackManager=new ActivityStackManager();
    }

    public static ActivityStackManager getActivityStackManagerInstance(){
        return ActivityStackManagerHodle.activityStackManager;
    }

    public void registerActivityLifecycleCallbacks(Application application) {
        application.registerActivityLifecycleCallbacks(new SwitchBackgroundCallbacks());
    }

    class SwitchBackgroundCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.e("TAG", "onActivityCreated........." + activity.getLocalClassName());
            numActivity++;
            Log.e("TAG", numActivity + "::onActivityResumed........." + activity.getLocalClassName());
            if (store.indexOf(activity) < 0) {
                store.add(activity);
            }
            Log.e("TAG", "store=====" + store.size());
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.e("TAG", "onActivityStarted........." + activity.getLocalClassName());
        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
            numActivity--;
            Log.e("TAG", numActivity + "::onActivityStopped........." + activity);
            if(numActivity==0) {
                Toast.makeText(activity,"APP进入后台",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            store.remove(activity);
        }
    }

    //返回栈顶activity
    public Activity getStackTopActivity() {
        return store.lastElement();
    }

    public int getStackNum() {
        return store.size();
    }

    public Activity getBottomActivity() {
        return store.firstElement();
    }


}
