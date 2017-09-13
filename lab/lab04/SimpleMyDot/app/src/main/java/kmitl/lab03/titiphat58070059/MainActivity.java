package kmitl.lab03.titiphat58070059;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import kmitl.lab03.titiphat58070059.model.Dot;
import kmitl.lab03.titiphat58070059.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.DotChangedListener, View.OnClickListener {

    private Dot dot;
    private DotView dotView;
    private int radius = 15;
    private int touchRadius = 30;
    private ArrayList<Dot> dotList;
    private Button btnUndo;
    private ShareActionProvider mShareAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dot = new Dot(100, 100, 20, this);
        dotList = new ArrayList<>();
        dotView = (DotView) findViewById(R.id.dotView);
        dot.setListener(this);

        btnUndo = (Button) findViewById(R.id.btnUndo);
        btnUndo.setOnClickListener(this);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        float centerX = random.nextInt(this.dotView.getWidth());
        float centerY = random.nextInt(this.dotView.getHeight());
        radius = 15;
        addDot(centerX, centerY, radius);
    }

    public void addDot(float centerX, float centerY, int radius) {
        this.dot = new Dot(centerX, centerY, radius, this);
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
        this.dot.setRadius(radius);
        this.dot.setR();
        this.dot.setG();
        this.dot.setB();
        dotList.add(dot);
//        dotView.setDot(dot);
    }

    public void onDotChanged(Dot dot) {
        dotView.setDotList(dotList);
//        dotView.setDot(dot);
        dotView.invalidate();
    }

    public void onDotChanged(ArrayList<Dot> dotList) {
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
        float touchY = event.getY() - 190;
        radius = 30;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (dot.findDot(touchX, touchY, dotList, radius) == -1) {
                    addDot(touchX, touchY, radius);
                    return true;
                } else {
                    dotList.remove(dot.findDot(touchX, touchY, dotList, radius));
                    this.onDotChanged(dotList);
                    return true;
                }
            default:
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnUndo) {
            if (dotList.size() > 0) {
                dotList.remove(dotList.size() - 1);
                this.onDotChanged(dotList);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.acion_share);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.acion_share) {
            mShareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
            takeScreenshot();
//            Toast.makeText(MainActivity.this , "Menu : " + item.getItemId() ,Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setShareIntent(File image) {
//        mShareAction.setShareIntent(shareIntent);
        Uri uri = Uri.fromFile(image);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sendIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(sendIntent, "Share screenshot to .."));
    }

    public void takeScreenshot() {
        View view = getWindow().getDecorView().getRootView();
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots" + now + ".jpg";
        File image = new File(path);
        try {
            FileOutputStream fileOut = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setShareIntent(image);
    }
}
