package camera.test.com.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

public class MyView extends View {
    private float radius=70;
    private Paint paint=new Paint();
    private Point currentPoint;
    private String color;
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSetting();
    }

    private void initSetting() {
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
    }

    public void setColor(String color) {
        this.color = color;
        paint.setColor(Color.parseColor(color));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(currentPoint==null){
            currentPoint=new Point(radius,radius);
            drawCricle(canvas);
            startAnimation();
        }else {
            drawCricle(canvas);
        }

    }

    private void startAnimation() {
        Point start=new Point(getWidth()/2,radius);
        Point end=new Point(getWidth()/2,getHeight());
        ValueAnimator valueAnimator=ValueAnimator.ofObject(new PointEvaluator(),start,end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point animatedValue = (Point) animation.getAnimatedValue();
                currentPoint=animatedValue;
                invalidate();
            }
        });
        ObjectAnimator colorAnimator=ObjectAnimator.ofObject(this,"color",new ColorEvaluator(),"#ff00ff","#00ff22");
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.setDuration(2000);
        animatorSet.play(valueAnimator).with(colorAnimator);
        animatorSet.start();
    }

    private void drawCricle(Canvas canvas) {
        canvas.drawCircle(currentPoint.getX(),currentPoint.getY(),radius,paint);
    }
}
