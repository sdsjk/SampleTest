package camera.test.com.camera;

import android.app.Application;
import android.os.StrictMode;

/**
 * Created by zhang on 2018/11/30.
 */

public class MyApplications extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //在7.0之后就会检测file路径，需要加相关代码，否则不能采集图片成功
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }
}
