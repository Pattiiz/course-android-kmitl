package com.thitiphat.espressotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBack, btnClear;
    List<String> nameList, ageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nameList = getIntent().getStringArrayListExtra("nameList");
        ageList = getIntent().getStringArrayListExtra("ageList");

        setDisplay();

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnClear) {
            nameList.clear();
            ageList.clear();
            setDisplay();
        } else {
            finish();
        }
    }

    public void setDisplay() {

        ItemAdapter itemAdapter = new ItemAdapter();
        itemAdapter.setItem(nameList, ageList);

        RecyclerView rv = (RecyclerView) findViewById(R.id.itemList);
        rv.setLayoutManager(new LinearLayoutManager(SecondActivity.this));
        rv.setAdapter(itemAdapter);
    }
}
