package com.thitiphat.espressotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Serializable {

    Button btnAdd, btnView;
    List<String> nameList = new ArrayList<>(), ageList = new ArrayList<>();
    EditText etName, etAge;
    Intent intent;

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


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            if ((etName.getText().toString().equals("")) || (etAge.getText().toString().equals(""))) {
                Toast.makeText(getApplicationContext(), "Please Enter user info", Toast.LENGTH_SHORT).show();
            }
            else {
                nameList.add(etName.getText().toString());
                ageList.add(etAge.getText().toString());
                etName.setText("");
                etAge.setText("");
                Toast.makeText(getApplicationContext(), "Complete!!!", Toast.LENGTH_SHORT).show();
            }

        } else {
            if (nameList.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Not Found", Toast.LENGTH_SHORT).show();
            }
            else {
                intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("nameList", (Serializable) nameList);
                intent.putExtra("ageList", (Serializable) ageList);
                startActivity(intent);
            }

        }
    }
}
