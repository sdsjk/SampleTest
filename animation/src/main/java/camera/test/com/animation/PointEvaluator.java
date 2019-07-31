package camera.test.com.animation;

import android.animation.TypeEvaluator;
import android.util.Log;

public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {

        float x = startValue.getX();
        float y = startValue.getY();
        float x1 = endValue.getX();
        float y1 = endValue.getY();

        Log.e("TAG", "x====:"+x+"=====:y======:"+y+"========:"+x1+"========:"+y1+"===:fraction"+fraction);
        float finalX=x+fraction*(x1-x);
        float finalY=y+fraction*(y1-y);
        Log.e("TAG", "finalX===="+finalX+"====:finalY"+finalY);
        Point point=new Point(finalX,finalY);
        return point;
    }
}
