package com.tabian.firebasereaddata;

import android.*;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tabian.firebasereaddata.adapter.UserDetailsadapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by swati on 3/14/2018.
 */

public class ReadDatafromFB extends AppCompatActivity {
    FirebaseDatabase mUserdatabase;
    DatabaseReference mUserDatabaseRef;
    List<UserInformation> userslist;
    ArrayList<UserInformation> contactsList;
    private Cursor cursor;
    String name;
    String mobile;
    ArrayAdapter<String> arrayAdapter;
    ListView contactListview;
    ArrayList<String> mobilelist;
    ArrayList<String> fbcontacts;
    ArrayList<String> storecontacts;
    public static final int RequestPermissionCode = 1;
    public static ArrayList<String> fbMobilelist;
    ArrayList<String > comparelist;
    private boolean result=false;
    private UserDetailsadapter userAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_database_layout);
        userslist = new ArrayList<>();
        mobilelist = new ArrayList<String>();
        storecontacts = new ArrayList<String>();
        fbMobilelist = new ArrayList<String>();
        fbcontacts = new ArrayList<String>();
        contactsList = new ArrayList<UserInformation>();
        comparelist=new ArrayList<String>();
        instilitize();
        enablePermission();
        getAllContacts();

    }

    private void instilitize() {
        mUserdatabase = FirebaseDatabase.getInstance();
        mUserDatabaseRef = mUserdatabase.getReference().child("users");
        RecyclerView recylerview;
        recylerview= (RecyclerView) findViewById(R.id.recycler_view);
        userAdapter=new UserDetailsadapter(contactsList);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recylerview.setLayoutManager(mLayoutManager);
        recylerview.setAdapter(userAdapter);
        }

    @Override
    protected void onStart() {
        super.onStart();
        mUserDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("in On Strart" + dataSnapshot.getValue().toString());
                for (DataSnapshot db : dataSnapshot.getChildren()) {
                    UserInformation userInformation = db.getValue(UserInformation.class);
                    contactsList.add(userInformation);
                    compareData();
                }
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void compareData() {
        for (UserInformation userinfo : contactsList) {
            System.out.println("Mobile in contactlist" + userinfo.getMobile());
            String result = String.valueOf(userinfo.getMobile());
            fbMobilelist.add("Mobile Numbsers" + result);
        }

        for(int i=0;i<mobilelist.size();i++){

            for(int j=0;j<fbMobilelist.size();j++){
              result=  PhoneNumberUtils.compare(this,fbMobilelist.get(j),mobilelist.get(i));
                if(result==true){
                        comparelist.add(fbMobilelist.get(j));

                }


                 }
        }

        LinkedHashSet<String > linkedHashSet=new LinkedHashSet<String >();
        linkedHashSet.addAll(comparelist);
        comparelist.clear();
        comparelist.addAll(linkedHashSet);
        Collections.sort(comparelist);
        System.out.println("Afer remove"+comparelist.toString());
    }



    private void getAllContacts(){
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);

        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//                System.out.println(name);
            mobile =(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

            storecontacts.add(name);
            mobilelist.add(mobile);
           // System.out.println(contactsList.toString());
        }

        cursor.close();
       }
    private void enablePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                ReadDatafromFB.this,
                android.Manifest.permission.READ_CONTACTS))
        {

            Toast.makeText(ReadDatafromFB.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(ReadDatafromFB.this,new String[]{
                    android.Manifest.permission.READ_CONTACTS}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(ReadDatafromFB.this,"Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(ReadDatafromFB.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


}
