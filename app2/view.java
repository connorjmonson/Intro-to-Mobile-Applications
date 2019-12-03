package com.example.connormonson.assignment2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class view extends AppCompatActivity {

    DatabaseHelper myDatabaseHelper;
    ListView lv;
    //ArrayAdapter<String> adapter;


    DatabaseHelper myDB;
    ArrayList<User> userList;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        /* (!) list view that WORKS (!)
        lv = (ListView) findViewById(R.id.list1);
        myDatabaseHelper = new DatabaseHelper(this);

        insertIntoListView();
        */

        //new stuff





        ListView listView = (ListView) findViewById(R.id.list1);
        myDB = new DatabaseHelper(this);

        //ArrayList<String> theList = new ArrayList<>();
        userList = new ArrayList<>();
        Cursor data = myDB.getListContents();
            int i = 1;
            while (data.moveToNext()){
                user = new User("#" +i+ " ID=  " + data.getString(0), data.getString(1), data.getString(2));
                userList.add(user);
                i++;
            }
            ThreeColumn_ListAdapter adapter =  new ThreeColumn_ListAdapter(this,R.layout.list_adapter_view, userList);
            listView = findViewById(R.id.list1);
            listView.setAdapter(adapter);


    }

    private void insertIntoListView() {
        Cursor data = myDatabaseHelper.getListContents();
        ArrayList<String> listData = new ArrayList<String>();
        int i = 1;
        while(data.moveToNext()){
            //get values from db in first column and add
            listData.add(i + ". ID =" + data.getString(0) + "\n Title: " + data.getString(2) + "\n URL: " + data.getString(1));
            i++;
        }

        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listData);
        //lv.setAdapter(adapter);
    }
}
