package kmitl.lab03.titiphat58070059;

import java.io.Serializable;

/**
 * Created by student on 9/8/2017 AD.
 */

public class DotSerializable implements Serializable {

    private int centerX;
    private int centerY;
    private int radius;

    public DotSerializable() {


    }

    public DotSerializable(int centerX, int centerY, int radius) {

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
