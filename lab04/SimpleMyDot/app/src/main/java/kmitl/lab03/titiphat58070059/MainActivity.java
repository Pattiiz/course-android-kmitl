package kmitl.lab03.titiphat58070059;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.titiphat58070059.model.Dot;
import kmitl.lab03.titiphat58070059.DotSerializable;
import kmitl.lab03.titiphat58070059.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.DotChangedListener, View.OnClickListener {

    private Dot dot;
    private DotView dotView;
    private int touchRadius = 30;
    private int radius = 15;
    private ArrayList<Dot> dotList;
    Button btnOpenActivity;
    DotSerializable DotSerialize;
    DotParcelable DotParcelable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dot = new Dot(100, 100, 20, this);
        dotList = new ArrayList<>();
        dotView = (DotView) findViewById(R.id.dotView);
        dot.setListener(this);

        //Open Second Activity
        btnOpenActivity = (Button) findViewById(R.id.btnOpenActivity);
        btnOpenActivity.setOnClickListener(this);

        //Serializable
        Random random = new Random();
        int centerX = random.nextInt(255);
        int centerY = random.nextInt(255);
        DotSerialize = new DotSerializable();
        DotSerialize.setCenterX(centerX);
        DotSerialize.setCenterY(centerY);
        DotSerialize.setRadius(radius);

        //Parcelable
        DotParcelable = new DotParcelable();
        DotParcelable.setCenterX(centerX);
        DotParcelable.setCenterY(centerY);
        DotParcelable.setRadius(radius);

    }

    public void onRandomDot(View view) {
        Random random = new Random();
        float centerX = random.nextInt(this.dotView.getWidth());
        float centerY = random.nextInt(this.dotView.getHeight());
        addDot(centerX, centerY, radius);
    }

    public void addDot(float centerX, float centerY, int radius) {
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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("xValue", 30);
        intent.putExtra("DotSerialize", DotSerialize);
        intent.putExtra("DotParcelable", DotParcelable);
        startActivity(intent);
    }

}
