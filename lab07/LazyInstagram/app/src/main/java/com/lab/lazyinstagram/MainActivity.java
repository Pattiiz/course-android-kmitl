package com.lab.lazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lab.lazyinstagram.adapter.PostAdapter;
import com.lab.lazyinstagram.api.LazyInstagramApi;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserProfile("nature");

        PostAdapter postAdapter = new PostAdapter(this);
        RecyclerView rv = (RecyclerView) findViewById(R.id.list);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(postAdapter);

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
                    UserProfile userProfile = response.body();

                    TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
                    tvUsername.setText(userProfile.getUser());

                    ImageView ivProfile = (ImageView) findViewById(R.id.ivProfile);
                    Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(ivProfile);

                    TextView tvPost = (TextView) findViewById(R.id.tvPost);
                    tvPost.setText(userProfile.getPost());
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });

    }
}
