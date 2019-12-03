package com.example.connormonson.assignment1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class view extends AppCompatActivity {
    DatabaseHelper myDatabaseHelper;
    ListView lv;

    ArrayList<String> newlist = new ArrayList<String>();

    private static final String FILE_NAME = "example.txt";
    ArrayList<String> list2 = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    //ListView lv;
    TextView text;
    String newstring;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //somewhat works
        lv = (ListView) findViewById(R.id.list1);
        ArrayList<String> list2 = enterInfo.list;

        myDatabaseHelper = new DatabaseHelper(this);

        insertIntoListView();

        /* NOT WORKING :(

        SharedPreferences settings = getSharedPreferences("prefs", MODE_PRIVATE);
        newstring = settings.getString("name", "");
        String[] viewlist = newstring.split(",");
        for (int i=0; i<viewlist.length; i++){
            newlist.add(viewlist[i]);
        }
        */




    /* was working but not saving data


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lv.requestLayout();
     */








        /*
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        String str = settings.getString("message", "");

        list.add(str);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lv.requestLayout();
        */


    //-------------------------------------------------------------------

        //text = (TextView) findViewById(R.id.txt);
        //SharedPreferences settings = getSharedPreferences("prefs", 0);
        //String FILE_NAME = settings.getString("message", "");


        //lv = (ListView) findViewById(R.id.list1);
        //Intent intent = getIntent();
        //String str = intent.getStringExtra("message");

        //SharedPreferences settings = getSharedPreferences("prefs", 0);
        //String str = settings.getString("message", "");
        //String[] entries = str.split(",");
        //for (int i=0; i<entries.length; i++){
            //list.add(entries[i]);
        //}
        //list.add("connor is cool test");
    /*
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(str.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String str2;

            while ((str2 = br.readLine()) != null){
                sb.append(str2).append("\n");
            }

            text.setText(sb.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    */
        /*
        list.add(str);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lv.requestLayout();
        */

        //lv.setAdapter(adapter);

        //text = (TextView) findViewById(R.id.txt);
        //Intent intent = getIntent();
        //String str = intent.getStringExtra("message");
        //text.setText(str);


        //SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", MODE_PRIVATE);

    }

    private void insertIntoListView(){
        Cursor data = myDatabaseHelper.getListContents();
        ArrayList<String> listData = new ArrayList<String>();
        while(data.moveToNext()){
            //get values from db in first column and add
            listData.add(data.getString(1));
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listData);
        lv.setAdapter(adapter);
    }

}
