package kmitl.lab03.titiphat58070059.model;

/**
 * Created by student on 8/25/2017 AD.
 */

public class Dot {

    private int centerX;
    private int centerY;
    private int radius;

    public interface DotChangedListener {
        void onDotChanged(Dot dot);
    }

    private DotChangedListener listener;

    public void setListener(DotChangedListener listener) {
        this.listener = listener;
    }

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
