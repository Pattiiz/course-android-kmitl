package kmitl.lab03.titiphat58070059;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 9/8/2017 AD.
 */

public class DotParcelable implements Parcelable {

    private int centerX;
    private int centerY;
    private int radius;

    public DotParcelable() {

    }

    public DotParcelable(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    protected DotParcelable(Parcel in) {
        centerX = in.readInt();
        centerY = in.readInt();
        radius = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(centerX);
        dest.writeInt(centerY);
        dest.writeInt(radius);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DotParcelable> CREATOR = new Creator<DotParcelable>() {
        @Override
        public DotParcelable createFromParcel(Parcel in) {
            return new DotParcelable(in);
        }

        @Override
        public DotParcelable[] newArray(int size) {
            return new DotParcelable[size];
        }
    };

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
