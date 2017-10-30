package com.thitiphat.espressotest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.thitiphat.espressotest.adapter.ItemAdapter;
import com.thitiphat.espressotest.model.UserList;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnBack, btnClear;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private UserList userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        //Get data from Internal Storage
        sharedPref = this.getSharedPreferences("list", MODE_PRIVATE);
        editor = sharedPref.edit();

        String user = sharedPref.getString("userList", null);
        if (user == null) {
            return;
        } else {
            userList = new Gson().fromJson(user, UserList.class);
        }

        setDisplay();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnClear) {
            userList.getUserList().clear();
            setPersistentData();
            setDisplay();
        } else {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void setDisplay() {
        ItemAdapter itemAdapter = new ItemAdapter();
        itemAdapter.setList(userList);

        RecyclerView rv = (RecyclerView) findViewById(R.id.itemList);
        rv.setLayoutManager(new LinearLayoutManager(SecondActivity.this));
        rv.setAdapter(itemAdapter);
    }

    public void setPersistentData() {
        String user = new Gson().toJson(userList);
        editor.putString("userList", user);
        editor.apply();
    }
}
