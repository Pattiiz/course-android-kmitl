package com.thitiphat.espressotest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.thitiphat.espressotest.model.UserInfo;
import com.thitiphat.espressotest.model.UserList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAdd, btnView;
    private EditText etName, etAge;
    private Intent intent;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private UserList userList = new UserList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);

        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);

        //Get data from Internal Storage
        sharedPref = this.getSharedPreferences("list", MODE_PRIVATE);
        editor = sharedPref.edit();

        String user = sharedPref.getString("userList", null);
        if (user == null) {
            return;
        } else {
            userList = new Gson().fromJson(user, UserList.class);
        }

//        editor.apply();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            if ((etName.getText().toString().equals("")) || (etAge.getText().toString().equals(""))) {
                Toast.makeText(getApplicationContext(), "Please Enter user info", Toast.LENGTH_SHORT).show();
            } else {
                UserInfo userInfo = new UserInfo();
                userInfo.setName(etName.getText().toString());
                userInfo.setAge(etAge.getText().toString());

                userList.addUserList(userInfo);

                etName.setText("");
                etAge.setText("");
                Toast.makeText(getApplicationContext(), "Complete!!!", Toast.LENGTH_SHORT).show();
            }

        } else {
            if (userList.getUserList().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Not Found", Toast.LENGTH_SHORT).show();
            } else {
                String user = new Gson().toJson(userList);
                editor.putString("userList", user);
                editor.apply();

                intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
