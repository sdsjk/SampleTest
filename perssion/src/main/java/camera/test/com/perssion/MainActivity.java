package camera.test.com.perssion;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import camera.test.com.perssion.perssionUtils.PermissionUtils;
import camera.test.com.perssion.perssionUtils.RequestPermissionsCallBcak;

import static camera.test.com.perssion.perssionUtils.PermissionUtils.REQUESTFLAGDENIED;
import static camera.test.com.perssion.perssionUtils.PermissionUtils.REQUESTFLAGDENIEDNOASK;

public class MainActivity extends AppCompatActivity implements RequestPermissionsCallBcak {

    private static final String CHANNEL_ID = "push";
    private static final String CHANEL_NAME = "pushchanle";
    private NotificationManager notificationManager;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }





    public  void getImageOriention(View view){

    }
    public static int getExifRotation(File imageFile) {
        if (imageFile == null) return 0;
        try {
            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            // We only recognize a subset of orientation tag values
            switch (exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return 90;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return 180;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return 270;
                default:
                    return ExifInterface.ORIENTATION_UNDEFINED;
            }
        } catch (IOException e) {
            return 0;
        }
    }
    public void requsetMoreCallByUtils(View view) {
        PermissionUtils.requestPermissions(this, perssions, 3, this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        parseIntent();
    }

    public void showNotification(View view) {
        String text = "这里是内容";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
        Intent intent = new Intent(MainActivity.this,MainActivity.this.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,100,intent,PendingIntent.FLAG_ONE_SHOT);
        builder.setTicker("有一条通知")//设置在第一个通知到达时显示在状态栏的文本
                .setSmallIcon(R.mipmap.ic_launcher)//设置小图标
                .setContentTitle("内容的标题")//设置内容标题
                .setStyle(new NotificationCompat.BigPictureStyle())//设置显示的样式
                .setContentText(text)//设置显示的内容
                .setNumber(100)//在通知的右侧设置一个数字
                .setOngoing(false)//设置是否是一个可持续的通知
                .setContentInfo("dsada")//设置右侧显示的文本，
                .setAutoCancel(true)//设置此标志将使它以便当用户点击它在面板中的通知被自动取消
                .setContentIntent(pendingIntent)
                .setColor(Color.BLUE);

        Notification notification = builder.build();
        NotificationManagerCompat.from(MainActivity.this).notify(0,notification);

//        showNotification();
    }

    private void showNotification() {
        Notification notification;
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             builder=new Notification.Builder(this,CHANNEL_ID);
        }else {
             builder=new Notification.Builder(this);
        }
        //设置标题
        builder.setContentTitle("设置标题");
        //设置内容
        builder.setContentText("内容是............");
        //设置状态栏显示的图标，建议图标颜色透明
        builder.setSmallIcon(R.mipmap.ic_launcher);
        // 设置通知灯光（LIGHTS）、铃声（SOUND）、震动（VIBRATE）、（ALL 表示都设置）
        builder.setDefaults(Notification.DEFAULT_ALL);
        //灯光三个参数，颜色（argb）、亮时间（毫秒）、暗时间（毫秒）,灯光与设备有关
        builder.setLights(Color.RED, 200, 200);
        // 铃声,传入铃声的 Uri（可以本地或网上）我这没有铃声就不传了
        builder.setSound(Uri.parse("")) ;
        // 震动，传入一个 long 型数组，表示 停、震、停、震 ... （毫秒）
        builder.setVibrate(new long[]{0, 200, 200, 200, 200, 200});
        // 通知栏点击后自动消失
        builder.setAutoCancel(true);
        // 简单通知栏设置 Intent
        builder.setContentIntent(setPendingIntent("简单通知"));
        builder.setPriority(Notification.PRIORITY_HIGH);

        //设置下拉之后显示的图片
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);//是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.GREEN);//小红点颜色
            channel.setShowBadge(false); //是否在久按桌面图标时显示此渠道的通知
            notificationManager.createNotificationChannel(channel);
        }
        notification=builder.build();
        notificationManager.notify(1,notification);
    }

    // 设置 pendingIntent
    private PendingIntent setPendingIntent(String msg) {
        // 普通的 Intent，可以传值
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("notification", msg);
        // 获取 pendingIntent，传入四个参数，这些参数大家都能看懂，第四个是一些 Flag，表示你这个通知栏会怎么变化，一般使用这个
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    // 解析 Intent
    private void parseIntent() {
        String msg = getIntent().getStringExtra("notification");
        if (msg != null) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * 申请打电话权限
     */
    int requestCodes = 1;
    int requestCodeAll = 2;

    public void requsetCall(View view) {
        //判断权限是否申请过

        //系统运行环境小于6.0不需要权限申请
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Toast.makeText(this, "目标版本不是23不需要申请权限，去做想做的事情BA！", Toast.LENGTH_LONG).show();
            return;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, requestCodes);
        } else {
            Toast.makeText(this, "权限申请通过去做想做的事情把！", Toast.LENGTH_LONG).show();
        }
    }

    public String[] perssions = new String[]{
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public void requsetMoreCall(View view) {
        List<String> perssionsList = new ArrayList<>();
        perssionsList.clear();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Toast.makeText(this, "目标版本不是23不需要申请权限，去做想做的事情BA！", Toast.LENGTH_LONG).show();
            return;
        }

        for (int i = 0; i < perssions.length; i++) {
            if (ActivityCompat.checkSelfPermission(this, perssions[i]) != PackageManager.PERMISSION_GRANTED) {
                perssionsList.add(perssions[i]);
            }
        }
        if (!perssionsList.isEmpty()) {
            ActivityCompat.requestPermissions(this, perssionsList.toArray(new String[perssionsList.size()]), requestCodeAll);
        } else {
            Toast.makeText(this, "权限申请全部申请通过去做想做的事情把！", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == requestCodes) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "权限申请通过去做想做的事情把！", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "权限没有申请通过！", Toast.LENGTH_LONG).show();
//                权限没有申请通过判断拒绝权限的情况
//                1.上次选择禁止并勾选：下次不在询问	false
//                2.第一次打开App时	 false
//                3.上次弹出权限点击了禁止（但没有勾选“下次不在询问”）	 true
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                    //没有勾选不在询问的选项
                    Toast.makeText(this, "没有勾选不在询问的选项！重新去请求权限", Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(this, new String[]{permissions[0]}, requestCodes);
                } else {
                    //勾选不在询问的选项
                    Toast.makeText(this, "勾选不在询问的选项！", Toast.LENGTH_LONG).show();
                }
            }

        } else if (requestCode == requestCodeAll) {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    result.add(permissions[i]);
                }
            }
            if (!result.isEmpty()) {
                //权限没有申请通过
                String[] requestperssions = result.toArray(new String[result.size()]);
                List<String> reRequestPerssion = new ArrayList<>();
                for (int i = 0; i < requestperssions.length; i++) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, requestperssions[i])) {
                        reRequestPerssion.add(requestperssions[i]);
                    }
                }
                if (!reRequestPerssion.isEmpty()) {
                    ActivityCompat.requestPermissions(this, result.toArray(new String[result.size()]), requestCodeAll);
                } else {
                    Toast.makeText(this, "勾选不在询问的选项111111！", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "权限申请全部通过去做想做的事情把！", Toast.LENGTH_LONG).show();
            }

        } else if (requestCode == 3) {
            PermissionUtils.onRequestPermissionsResults(this, requestCode, permissions, grantResults, this);
        }
    }

    @Override
    public void requestPermissionsSucesss(int requestCode) {
        Log.e("TAG", "=================权限申请通过");
    }

    @Override
    public void requestPermissionfailure(int errorCode, Object requestCode) {
        if (errorCode == REQUESTFLAGDENIED) {
            if (requestCode instanceof List) {

                String[] requestAgin = (String[]) ((List) requestCode).toArray(new String[((List) requestCode).size()]);
                PermissionUtils.requestPermissions(this, requestAgin, 3, this);
            }
            Log.e("TAG", "=================普通的权限拒绝");
        } else if (errorCode == REQUESTFLAGDENIEDNOASK) {
            Log.e("TAG", "=================勾选的权限拒绝");
        }
    }
}
