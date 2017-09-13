package kmitl.lab03.titiphat58070059.model;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.titiphat58070059.MainActivity;

/**
 * Created by student on 8/25/2017 AD.
 */

public class Dot {

    private float centerX;
    private float centerY;
    private float radius;
    private int r;
    private int g;
    private int b;

    Random random = new Random();
    private DotChangedListener listener;

    public void setListener(DotChangedListener listener) {
        this.listener = listener;
    }

    public interface DotChangedListener {
        void onDotChanged(Dot dot);
    }

    public Dot() {

    }

    public Dot(float centerX, float centerY, float radius, DotChangedListener listener) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.listener = listener;

    }

    public int findDot(float x, float y, ArrayList<Dot> dotList, int radius) {
        for (int i = 0; i < dotList.size(); i++) {
            float centerX = dotList.get(i).getCenterX();
            float centerY = dotList.get(i).getCenterY();
            double distance = Math.sqrt(Math.pow(centerX - x, 2)) + Math.sqrt(Math.pow(centerY - y, 2));
            if (distance <= radius) {
                return i;
            }
        }
        return -1;
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

    public int getR() {
        return r;
    }

    public void setR() {
        this.r = random.nextInt(255);
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG() {
        this.g = random.nextInt(255);
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB() {
        this.b = random.nextInt(255);
    }

    public void setB(int b) {
        this.b = b;
    }
}