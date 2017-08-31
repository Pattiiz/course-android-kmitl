package kmitl.lab03.titiphat58070059;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.titiphat58070059.model.Dot;
import kmitl.lab03.titiphat58070059.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.DotChangedListener {

    private Dot dot;
    private DotView dotView;
    private int touchRadius = 30;
    private int radius = 15;
    private ArrayList<Dot> dotList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dot = new Dot(100, 100, 20, this);
        dotList = new ArrayList<>();
        dotView = (DotView) findViewById(R.id.dotView);
        dot.setListener(this);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        float centerX = random.nextInt(this.dotView.getWidth());
        float centerY = random.nextInt(this.dotView.getHeight());
        addDot(centerX, centerY, radius);
    }

    public void addDot(float centerX, float centerY, int radius) {
//        int radius = (int) (30 + (Math.random() * 200));
        this.dot = new Dot(centerX, centerY, radius, this);
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
        this.dot.setRadius(radius);
        dotList.add(dot);
//        dotView.setDot(dot);
    }

    public void onDotChanged(Dot dot) {
        dotView.setDotList(dotList);
//        dotView.setDot(dot);
        dotView.invalidate();
    }

    public void onClearDot(View view) {
        dotList.clear();
        dotView.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY() - 200;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                addDot(touchX, touchY, touchRadius);
                return true;
        }
        return false;
    }
}
