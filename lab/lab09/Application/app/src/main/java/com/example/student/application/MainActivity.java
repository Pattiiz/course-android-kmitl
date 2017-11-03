package com.example.student.application;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    final MessageInfoDB messageInfoDB = Room.databaseBuilder(getApplicationContext(), MessageInfoDB.class, "DEMOINFO").build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        new AsyncTask<Void, Void, MessageInfo>() {
            @Override
            protected MessageInfo doInBackground(Void... params) {

                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setText("Hello");
                messageInfo.setTime(new Date().toString());

                messageInfoDB.getMessageInfo().insert(messageInfo);
                return null;
            }

            @Override
            protected void onPostExecute(MessageInfo messageInfo) {
                super.onPostExecute(messageInfo);
            }
        }.execute();

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new AsyncTask<Void, Void, List<MessageInfo>>() {
            @Override
            protected List<MessageInfo> doInBackground(Void... params) {
                return messageInfoDB.getMessageInfo().getAll();
            }

            @Override
            protected void onPostExecute(List<MessageInfo> messageInfos) {
                ArrayAdapter<MessageInfo> adapter = new ArrayAdapter<MessageInfo>(MainActivity.this, R.layout.activity_main);
            }
        }.execute();
    }
}
