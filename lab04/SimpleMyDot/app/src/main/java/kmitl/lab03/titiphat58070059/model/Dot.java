package kmitl.lab03.titiphat58070059.model;

import kmitl.lab03.titiphat58070059.MainActivity;

/**
 * Created by student on 8/25/2017 AD.
 */

public class Dot {

    private float centerX;
    private float centerY;
    private float radius;

    private DotChangedListener listener;

    public void setListener(DotChangedListener listener){
        this.listener = listener;
    }

    public interface DotChangedListener {
        void onDotChanged(Dot dot);
    }

    public Dot(float centerX, float centerY, float radius, DotChangedListener listener) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.listener = listener;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
        this.listener.onDotChanged(this);
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
        this.listener.onDotChanged(this);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
