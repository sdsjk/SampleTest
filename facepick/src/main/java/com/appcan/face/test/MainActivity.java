package com.appcan.face.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        SimpleDateFormat createTimeSdf1 = new SimpleDateFormat("yyyy-MM-dd");
        tv=findViewById(R.id.tv);
        List<String> labels=new ArrayList<>();
        labels.add("用户名：张三");
        labels.add("日期："+ createTimeSdf1.format(new Date()));
        labels.add("不可扩散");
        tv.setBackground(new WaterMarkBg(this,labels,-30,16));
//        tv.setBackground(new MyWaterBg());
    }
}
