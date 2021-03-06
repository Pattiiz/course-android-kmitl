package com.thitiphat.espressotest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thitiphat.espressotest.R;
import com.thitiphat.espressotest.model.UserList;

import java.util.List;

/**
 * Created by phatm on 10/23/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Holder> {

    private List<String> nameList, ageList;
    private UserList userList;

    public class Holder extends RecyclerView.ViewHolder {

        public TextView tvName, tvAge;

        public Holder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvAge = (TextView) itemView.findViewById(R.id.tvAge);
        }
    }

    public ItemAdapter() {
        userList = new UserList();
    }

    public void setItem(List<String> nameList, List<String> ageList) {
        this.nameList = nameList;
        this.ageList = ageList;
    }

    public void setList(UserList userList) {
        this.userList = userList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_item, null, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        TextView name = holder.tvName;
        TextView age = holder.tvAge;
//        name.setText(nameList.get(position));
//        age.setText(ageList.get(position));
        name.setText(userList.getUserList().get(position).getName().toString());
        age.setText(userList.getUserList().get(position).getAge().toString());
    }

    @Override
    public int getItemCount() {
        //return nameList.size();
        return userList.getUserList().size();
    }

}
