package camera.test.com.activitylifcly;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
   public ActivityStackManager activityStackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityStackManager=ActivityStackManager.getStackManager();

    }
}
