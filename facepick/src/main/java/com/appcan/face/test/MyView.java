package com.appcan.face.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
    private Paint paint;
    private Paint xyPaint;
    int gridWidth=50;
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSetting();
    }

    private void initSetting() {
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLUE);

        xyPaint=new Paint();
        xyPaint.setColor(Color.BLACK);
        xyPaint.setStrokeWidth(4);
        xyPaint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        int hafWidth = getWidth() / 2;
        int hafHeght = getHeight() / 2;

        canvas.translate(hafWidth,hafHeght);
        int curWidth = gridWidth;

        while (curWidth<hafHeght){
            //绘制垂直右边线
            canvas.drawLine(curWidth,-hafHeght,curWidth,hafHeght,paint);
            //绘制垂直左边线
            canvas.drawLine(-curWidth,-hafHeght,-curWidth,hafHeght,paint);

            canvas.drawLine(0,curWidth,curWidth/2,curWidth,xyPaint);

            curWidth=curWidth+gridWidth;
        }
        //绘制Y坐标线
        canvas.drawLine(0,-hafHeght,0,hafHeght,xyPaint);
        //沪指X坐标线
        canvas.drawLine(-hafWidth,0,hafWidth,0,xyPaint);
        canvas.restore();
    }
}
