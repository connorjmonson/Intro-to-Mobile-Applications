package com.example.connormonson.assignment2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class rangeView extends AppCompatActivity {

    DatabaseHelper myDatabaseHelper;
    ListView lv;
    //ArrayAdapter<String> adapter;

    DatabaseHelper myDB;
    ArrayList<User> userList;
    ArrayList<User> userList2;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_view);

        Intent intent = getIntent();
        int number1 = intent.getIntExtra(range.EXTRA_NUMBER1, 0);
        int number2 = intent.getIntExtra(range.EXTRA_NUMBER2, 0);
        ListView listView = (ListView) findViewById(R.id.list1);
        myDB = new DatabaseHelper(this);

        //ArrayList<String> theList = new ArrayList<>();
        userList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int j = 1;
        while (data.moveToNext()){
            user = new User("#" +j+ " ID=  " +data.getString(0), data.getString(1), data.getString(2));
            userList.add(user);
            j++;
        }

        userList2 = new ArrayList<>();
        for (int i = number1-1; i <= number2-1; i++){
            userList2.add(userList.get(i));
        }

        ThreeColumn_ListAdapter adapter =  new ThreeColumn_ListAdapter(this,R.layout.list_adapter_view, userList2);
        listView = findViewById(R.id.list1);
        listView.setAdapter(adapter);


        //insertIntoListView(number1, number2);
    }

    private void insertIntoListView(int number1, int number2) {
        lv = (ListView) findViewById(R.id.list1);
        myDatabaseHelper = new DatabaseHelper(this);
        Cursor data = myDatabaseHelper.getListContents();
        ArrayList<String> listData = new ArrayList<String>();
        int j = 1;
        while(data.moveToNext()){
            //get values from db in first column and add
            listData.add(j + ". ID =" + data.getString(0) + "\n Title: " + data.getString(2) + "\n URL: " + data.getString(1));
            j++;
        }

        ArrayList<String> listData2 = new ArrayList<String>();

        for (int i = number1-1; i <= number2-1; i++){
            listData2.add(listData.get(i));
        }
            //listData2.add(listData.get(i));
        //}

        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listData2);
        //lv.setAdapter(adapter);
    }

    private void toastMessage(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
