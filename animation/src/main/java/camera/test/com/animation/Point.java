package camera.test.com.animation;

public class Point {
    private float X;
    private float Y;

    public Point(float x, float y) {
        X = x;
        Y = y;
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    @Override
    public String toString() {
        return "X==="+getX()+"=====Y"+getY();
    }
}
