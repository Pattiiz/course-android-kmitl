package kmitl.lab03.titiphat58070059.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Printer;
import android.view.View;

/**
 * Created by student on 8/25/2017 AD.
 */

public class DotView extends View {
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dot != null) {
            paint.setColor(Color.RED);
            canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius, paint);
        }
    }

    public void setDot(Dot dot) {
        this.dot = dot;
    }

    private Paint paint;

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
