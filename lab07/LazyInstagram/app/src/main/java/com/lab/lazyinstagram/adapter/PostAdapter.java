package com.lab.lazyinstagram.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lab.lazyinstagram.MainActivity;
import com.lab.lazyinstagram.R;

/**
 * Created by student on 10/6/2017 AD.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {

    String[] src = {"http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png"};
    private Context context;

    public PostAdapter(Context context) {

        this.context = context;

    }

    @Override
    public PostAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.post_item, null, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PostAdapter.Holder holder, int position) {
        ImageView iv = holder.image;
        Glide.with(context).load(src[position]).into(iv);

    }

    @Override
    public int getItemCount() {
        return src.length;
    }

    public class Holder extends RecyclerView.ViewHolder {

        public ImageView image;

        public Holder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.ivImg1);
        }
    }

}
