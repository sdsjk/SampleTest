package camera.test.com.camera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;

import camera.test.com.camera.permissionUtils.PermissionUtils;
import camera.test.com.camera.permissionUtils.RequestPermissionsCallBcak;
import camera.test.com.camera.utils.CommomUtils;
import camera.test.com.testutils.UtilsCommon;

public class MainActivity extends AppCompatActivity implements RequestPermissionsCallBcak{

    private static final int LOCATIONREQUESTCODE = 2;
    public    String FILE_PATH = "";
    private TextView pic_src_tv, vedio_src_tv;
    private final int REQUESTPERSSIONCAMERACODE = 1;
    private CommomUtils commonUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UtilsCommon.LogDebug("343434343");
        initView();
    }

    private void initView() {
        commonUtils = CommomUtils.getUtilsInstance();
        pic_src_tv = (TextView) findViewById(R.id.pic_src_tv);
        vedio_src_tv = (TextView) findViewById(R.id.vedio_src_tv);
        FILE_PATH=Environment.getExternalStorageDirectory()+"/cameraTest/"+ System.currentTimeMillis()+".3gp";

    }

    public  void openSystemPic(View view){
//        Intent intent=new Intent();
//        intent.setAction(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent,1001);

//        Intent intent1 = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent1, 1001);

        Intent intent = new Intent();
        String packageName = getPackageName();
        intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
        intent.setData(Uri.parse("package:" + packageName));
        startActivityForResult(intent, 10001);


    }

    

    public void vedio(View view) {
        vedio_src_tv.setText(FILE_PATH);
        Intent intent = new Intent();
        intent.setAction("android.media.action.VIDEO_CAPTURE");
        intent.addCategory("android.intent.category.DEFAULT");

        File file = new File(FILE_PATH);
        Uri uri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //设置视频最大允许录制的时长，单位为毫秒
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,5000);
        //设置视频录制的质量，0为低质量，1为高质量
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,0);
        startActivityForResult(intent, 0);
    }

    /**
     * 打开相机去捕捉图片
     *
     * @param view
     */
    public void camera(View view) {
        //首先去判断是否有相关的权限
        getCameraPerssions();
    }

    private void getCameraPerssions() {
        //要申请的权限
        String perssions[] = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO,Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS};
        requestPermissions(perssions, REQUESTPERSSIONCAMERACODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUESTPERSSIONCAMERACODE:
                //判断权限是否开启
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //权限通过去做你想做的事情
                    pickPicByCamera();
                } else {
                    //权限申请失败，请到相关的设置页面手动去申请开启
                }
                break;
            case 10001:
                Log.d("Hello World!","开启省电模式成功");
                break;
            case LOCATIONREQUESTCODE:
                    PermissionUtils.onRequestPermissionsResults(this,requestCode,permissions,grantResults,this);
                break;
            default:
                break;
        }
    }

    /**
     * 打开相机去采集图像
     */
    private void pickPicByCamera() {
        if (commonUtils.checkCameraHardware(this)) {
            //创建打开系统相机的意图
            Intent openCameraIntent = new Intent();
            //设置打开系统相机的action
            openCameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            // 照片输出路径
            String outPath = Environment.getExternalStorageDirectory() + "/cameraTest";
            String fileName = "zhang" + getRandomNum() + ".jpg";
            File file = new File(outPath, fileName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            pic_src_tv.setText(file.getAbsolutePath());
            //文件输出路径转为Uri
            Uri uri = Uri.fromFile(file);
            //intent携带路径，照片就会输出的到指定的目录下
            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            //打开系统相机去照相，如果采用自定义路径去作为图片存储路径，在onActivityResult中Intent就为null
            startActivityForResult(openCameraIntent, 1);
        }

    }

    private String getRandomNum() {
        return System.currentTimeMillis() + "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                Log.e("TAG", "返回的来数据是:" + data);
                break;
            case 1001:
                Log.e("TAG", "=================="+data.getData());
                break;
        }
    }


    public void  requestPermission(View view){
        PermissionUtils.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CAMERA},LOCATIONREQUESTCODE,this);
//        PermissionUtils.requestPermissions(this,Manifest.permission.WRITE_EXTERNAL_STORAGE,LOCATIONREQUESTCODE,this);
    }


    @Override
    public void requestPermissionsSucesss(int requestCode) {
        Log.e("TAG", "requestPermissionsSucesss=================="+requestCode);
    }

    @Override
    public void requestPermissionfailure(int errorCode,Object requestCode) {
        Log.e("TAG", errorCode+"requestPermissionfailure=================="+requestCode.toString());

        final CustomDiaLog dialog=new CustomDiaLog(this,R.style.customDialog);
        dialog.show();
    }

}
