package kmitl.lab03.titiphat58070059.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Printer;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.titiphat58070059.model.Dot;

/**
 * Created by student on 8/25/2017 AD.
 */

public class DotView extends View {

    private Paint paint;
    private Dot dot;

    public ArrayList<Dot> getDotList() {
        return dotList;
    }

    public void setDotList(ArrayList<Dot> dotList) {
        this.dotList = dotList;
    }

    private ArrayList<Dot> dotList;
    Random random = new Random();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dotList != null) {
            for(Dot dot : dotList){
                if (dot != null) {
                    paint.setColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
                }
            }
        }

    }

    public void setDot(Dot dot) {
        this.dot = dot;
    }


    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }


    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }
}
