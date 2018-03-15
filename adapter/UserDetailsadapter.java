package com.tabian.firebasereaddata.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tabian.firebasereaddata.R;
import com.tabian.firebasereaddata.UserInformation;

import java.util.List;

/**
 * Created by swati on 3/15/2018.
 */

public class UserDetailsadapter extends RecyclerView.Adapter<UserDetailsadapter.UsersHolder> {
    List<UserInformation> usersList;
    class UsersHolder extends RecyclerView.ViewHolder{
        public TextView mobile,name,email;

        public UsersHolder(View itemView) {
            super(itemView);
            mobile= (TextView) itemView.findViewById(R.id.mobile);
            name= (TextView) itemView.findViewById(R.id.name);
            email= (TextView) itemView.findViewById(R.id.email_text);
        }
    }

    public UserDetailsadapter(List<UserInformation> usersList) {
        this.usersList = usersList;
    }

    @Override
    public UsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_content,parent,false);

        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersHolder holder, int position) {
        UserInformation userInformation=usersList.get(position);
        holder.mobile.setText(String.valueOf(userInformation.getMobile()));
        holder.name.setText(userInformation.getName());
        holder.email.setText(userInformation.getEmail());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
