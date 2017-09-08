package kmitl.lab03.titiphat58070059;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBack;
    int x;
    TextView tvNum, tvX, tvY, tvRadius;
    DotSerializable DotSerialize;
    DotParcelable DotParcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        x = getIntent().getIntExtra("xValue", 0);
        tvNum = (TextView) findViewById(R.id.tvNum);
        tvNum.setText(String.valueOf(x));

        DotSerialize = (DotSerializable) getIntent().getSerializableExtra("DotSerialize");
        tvX = (TextView) findViewById(R.id.tvX);
        tvX.setText(String.valueOf(DotSerialize.getCenterX()) + " | " + DotSerialize.getCenterY());

        DotParcel = (DotParcelable) getIntent().getParcelableExtra("DotParcelable");
        tvY = (TextView) findViewById(R.id.tvY);
        tvY.setText(String.valueOf(DotParcel.getCenterX() + " | " + DotParcel.getCenterY()));
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
