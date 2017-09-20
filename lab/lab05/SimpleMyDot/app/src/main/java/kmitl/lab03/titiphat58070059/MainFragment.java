package kmitl.lab03.titiphat58070059;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.titiphat58070059.model.Dot;
import kmitl.lab03.titiphat58070059.view.DotView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements Dot.DotChangedListener, View.OnClickListener, View.OnTouchListener {

    private Dot dot;
    private DotView dotView;
    private int radius = 15;
    private int touchRadius = 30;
    private ArrayList<Dot> dotList;
    private Button btnRandom;
    private Button btnClear;
    private Button btnUndo;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        dot = new Dot();
        dot.setListener(this);

        dotList = new ArrayList<>();

        dotView = (DotView) rootView.findViewById(R.id.dotView);
        dotView.setOnTouchListener(this);

        btnRandom = (Button) rootView.findViewById(R.id.btnRandom);
        btnRandom.setOnClickListener(this);

        btnClear = (Button) rootView.findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        btnUndo = (Button) rootView.findViewById(R.id.btnUndo);
        btnUndo.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnRandom) {
            onRandomDot(v);
        }
        if (v.getId() == R.id.btnClear) {
            onClearDot(v);
        }
        if (v.getId() == R.id.btnUndo) {
            onUndo(v);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
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
    }

    public void onDotChanged(Dot dot) {
        dotView.setDotList(dotList);
        dotView.invalidate();
    }

    public void onDotChanged(ArrayList<Dot> dotList) {
        dotView.setDotList(dotList);
        dotView.invalidate();
    }

    public void onClearDot(View view) {
        dotList.clear();
        dotView.invalidate();
    }

    public void onUndo(View view) {
        if (dotList.size() > 0) {
            dotList.remove(dotList.size() - 1);
            this.onDotChanged(dotList);
        }
    }


}
