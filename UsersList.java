package com.tabian.firebasereaddata;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.UserInfo;

import java.util.List;

/**
 * Created by swati on 3/14/2018.
 */

public class UsersList extends ArrayAdapter<UserInformation> {
private Activity context;
    List<UserInformation> mUserLists;
    public UsersList(Activity context, List<UserInformation> mUsers) {
        super(context, R.layout.view_database_layout,mUsers);
        this.context = context;
        this.mUserLists = mUsers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.view_database_layout,null,true);
        TextView textView= (TextView) convertView.findViewById(R.id.tvUserInfo);
        UserInformation userInformation=mUserLists.get(position);
        textView.setText(userInformation.getName());
        return listViewItem;
    }
}
