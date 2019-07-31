package camera.test.com.camera.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by zhang on 2018/11/30.
 */

public class CommomUtils {

    private CommomUtils(){};

    private static CommomUtils commomUtils=new CommomUtils();

    public static  CommomUtils getUtilsInstance(){
        return commomUtils;
    }

    /**
     * 检查摄像机是否可用
     *
     * @param context
     * @return
     */
    public boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

}
