package kmitl.lab03.titiphat58070059;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab03.titiphat58070059.model.Dot;
import kmitl.lab03.titiphat58070059.view.DotView;

public class MainActivity extends AppCompatActivity {

    private Dot dot;
    private DotView dotView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dot = new Dot(0,0,20);
        dot.setDotChangedListener(this);
        dotView = (DotView) findViewById(R.id.dotView);

    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(500);
        int centerY = random.nextInt(500);
        Dot dot = new Dot(centerX, centerY, 50);

        TextView tvX = (TextView) findViewById(R.id.tvX);
        tvX.setText(String.valueOf(centerX));
        TextView tvY = (TextView) findViewById(R.id.tvY);
        tvY.setText(String.valueOf(centerY));
    }

    public void onDotChanged(Dot dot) {
        dotView.setDot(dot);
        dotView.invalidate();
    }
}
