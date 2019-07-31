package com.appcan.face.test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyWaterBg extends Drawable {
    private List<String> labels = new ArrayList<>();
    private String waterString = "AppCan";
    private Paint paint;

    /**
     * 右间距
     */
    private int paddingLeft = 10;
    /**
     * 水印多少行
     */
    private int warterLines = 10;
    /**
     * 水印多少列
     */
    private int waterRows = 3;
    /**
     * 行之间的间距
     */
    private int RowSpance = 20;

    private int lineSpance = 30;

    public MyWaterBg() {
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
//        设置画笔颜色
        paint.setColor(Color.parseColor("#50AEAEAE"));
//        设置抗锯齿，开启后绘制变慢
        paint.setAntiAlias(true);
//        设置画笔的宽度
        paint.setStrokeWidth(5);
//        设置样式为描边
//        paint.setStyle(Paint.Style.STROKE);
//           设置字体大小
        paint.setTextSize(60);
//        文字对齐方式针对绘制文字的起点，默认是left
        paint.setTextAlign(Paint.Align.LEFT);


    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#40F3F5F9"));
        canvas.rotate(-30);
        canvas.save();
//        文字的长度
        float textLenght = paint.measureText(waterString);

        int left = getBounds().left;
        int top = getBounds().top;
        int right = getBounds().right;
        int bottom = getBounds().bottom;
        Log.e("TAG", "left:::::" + left + "====top::::" + top + "::::::right:::::" + right + "::::bottom::::" + bottom);

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        //可绘制最顶线
        float top1 = fontMetrics.top;
        //当前绘制最顶线
        float ascent = fontMetrics.ascent;
        //绘制基线
        float leading = fontMetrics.leading;
        //当前绘制最低线
        float descent = fontMetrics.descent;
        //可绘制最低线
        float bottom1 = fontMetrics.bottom;
        Log.e("TAG", "top1:::::" + top1 + "====ascent::::" + ascent + "::::::leading:::::" + leading + "::::descent::::" + descent + "::::bottom1::::" + bottom1);


        //计算每行的高度
        int lineHeight = bottom / warterLines;
        //计算每列的宽度
        int rowWidth = right / waterRows;
        int nextStartX = paddingLeft;
        int fristY = lineHeight / 2;
        int nexStartY = 0;
        //循环绘制文字
        for (int line = 0; line < warterLines; line++) {
            nexStartY = (nexStartY + lineHeight);
            nextStartX = paddingLeft;
            if ((line + 1) % 2 == 0) {
                nextStartX = paddingLeft * 10;
            }
            for (int row = 0; row < waterRows; row++) {
                Log.e("TAG", "nextStartX:::" + nextStartX + "nextStartY:::::::::" + nexStartY);
                canvas.drawText(waterString, nextStartX, nexStartY, paint);
                nextStartX = nextStartX + rowWidth + paddingLeft;
            }
        }

//        canvas.drawText(waterString,paddingLeft,500,paint);
//        paint.setColor(Color.RED);
//        canvas.drawLine(paddingLeft,top1+500,paddingLeft+textLenght,top1+500,paint);
//        paint.setColor(Color.GREEN);
//        canvas.drawLine(paddingLeft,ascent+500,paddingLeft+textLenght,ascent+500,paint);
//        paint.setColor(Color.BLACK);
//        canvas.drawLine(paddingLeft,descent+500,paddingLeft+textLenght,descent+500,paint);
//        paint.setColor(Color.GREEN);
//        canvas.drawLine(paddingLeft,bottom1+500,paddingLeft+textLenght,bottom1+500,paint);
//        paint.setColor(Color.BLUE);
//        canvas.drawLine(paddingLeft,leading+500,paddingLeft+textLenght,leading+500,paint);
//        paint.setColor(Color.BLUE);
        canvas.restore();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}
