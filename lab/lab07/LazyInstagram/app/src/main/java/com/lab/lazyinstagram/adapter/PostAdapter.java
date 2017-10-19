package com.lab.lazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lab.lazyinstagram.Posts;
import com.lab.lazyinstagram.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 10/6/2017 AD.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {

    private List<Posts> src;
    private Context context;
    private int viewFlag = 0, width = 0;
    private View view;

    public PostAdapter(Context context) {
        this.context = context;
        this.src = new ArrayList<>();
    }

    public void setSrc(List<Posts> src, int viewFlag, int width) {
        this.src = src;
        this.viewFlag = viewFlag;
        this.width = width;
    }

    public class Holder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView tvLike, tvComment;

        public Holder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.ivImg);
            tvLike = (TextView) itemView.findViewById(R.id.tvLike);
            tvComment = (TextView) itemView.findViewById(R.id.tvComment);

            image.setMaxWidth(width);
            image.setMaxHeight(width);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewFlag == 0) {
            view = inflater.inflate(R.layout.post_item, null, false);
        } else {
            view = inflater.inflate(R.layout.post_single, null, false);
        }
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView imageView = holder.image;
        String link = src.get(position).getUrl();
        Glide.with(context).load(link).into(imageView);

        TextView like = holder.tvLike;
        TextView comment = holder.tvComment;
        like.setText(String.valueOf(src.get(position).getLike()));
        comment.setText(String.valueOf(src.get(position).getComment()));
    }

    @Override
    public int getItemCount() {
        return src.size();
    }
}
