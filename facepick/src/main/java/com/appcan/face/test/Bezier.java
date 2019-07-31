package com.appcan.face.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class Bezier extends View {
    public Bezier(Context context) {
        this(context,null);
    }

    public Bezier(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Bezier(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        Path path=new Path();
        path.moveTo(100,400);
        path.quadTo(300,100,500,800);
        canvas.drawPath(path,paint);

    }
}
