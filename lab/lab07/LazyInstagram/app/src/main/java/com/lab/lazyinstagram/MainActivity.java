package com.lab.lazyinstagram;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lab.lazyinstagram.adapter.PostAdapter;
import com.lab.lazyinstagram.api.LazyInstagramApi;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button btnSwitch, btnFollow;
    private int viewType = 0;
    private int btnPosition = 0;
    private List<Integer> flwFlag = new ArrayList<>();
    private String user;
    private Spinner mUser;
    private ArrayList<String> listUser = new ArrayList<String>();
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#207ab1"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        getScreenSize();

        flwFlag.add(0);
        flwFlag.add(0);
        flwFlag.add(0);

        user = "nature";
        getUserProfile(user);

        btnSwitch = (Button) findViewById(R.id.btnSwitch);
        btnSwitch.setOnClickListener(this);

        btnFollow = (Button) findViewById(R.id.btnFollow);
        btnFollow.setOnClickListener(this);

        mUser = (Spinner) findViewById(R.id.spnUser);
        listUser.add("android");
        listUser.add("nature");
        listUser.add("cartoon");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listUser);
        mUser.setAdapter(arrayAdapter);
        mUser.setOnItemSelectedListener(this);
    }

    public void getUserProfile(String userName) {

        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(LazyInstagramApi.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LazyInstagramApi lazyIgApi = retrofit.create(LazyInstagramApi.class);

        Call<UserProfile> call = lazyIgApi.getProfile(userName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    setDisplay(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void setDisplay(UserProfile userProfile) {

        ImageView ivProfile = (ImageView) findViewById(R.id.ivProfile);
        Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(ivProfile);

        TextView tvPost = (TextView) findViewById(R.id.tvPost);
        tvPost.setText(userProfile.getPost() + "\nPosts");

        TextView tvFollowing = (TextView) findViewById(R.id.tvFlwing);
        tvFollowing.setText(userProfile.getFollowing() + "\nFollowing");

        TextView tvFollower = (TextView) findViewById(R.id.tvFlwer);
        tvFollower.setText(userProfile.getFollower() + "\nFollowers");

        TextView tvBio = (TextView) findViewById(R.id.tvBio);
        tvBio.setText(userProfile.getBio());

        PostAdapter postAdapter = new PostAdapter(MainActivity.this);
        postAdapter.setSrc(userProfile.getItemPost(), viewType, width);

        RecyclerView rv = (RecyclerView) findViewById(R.id.list);
        if (viewType == 0) {
            rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        } else {
            rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
        rv.setAdapter(postAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSwitch) {
            if (viewType == 0) {
                btnSwitch.setText("GridView");
                viewType = 1;
                getUserProfile(user);
            } else {
                btnSwitch.setText("ListView");
                viewType = 0;
                getUserProfile(user);
            }
        }
        if (v.getId() == R.id.btnFollow) {

            if (flwFlag.get(btnPosition) == 0) {
                dialogProgress();
                flwFlag.set(btnPosition, 1);
                setBtnStatus(btnPosition);
            } else {
                dialogProgress();
                flwFlag.set(btnPosition, 0);
                setBtnStatus(btnPosition);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            user = "android";
            setBtnStatus(position);
        } else if (position == 1) {
            user = "nature";
            setBtnStatus(position);
        } else {
            user = "cartoon";
            setBtnStatus(position);
        }
        getUserProfile(user);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void dialogProgress() {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progress.dismiss();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 1000);
    }

    public void setBtnStatus(int position) {
        btnPosition = position;

        if (flwFlag.get(position) == 0) {
            btnFollow.setText("Follow");
        } else {
            btnFollow.setText("Following");
        }
    }

    public void getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
    }
}
