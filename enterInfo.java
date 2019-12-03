package com.example.connormonson.assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArraySet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class enterInfo extends AppCompatActivity {

    /*
    I got help from many different youtube videos!
    needed lots of help in creating a databaseHelper
    Obtained help in coding and giving credit to this youtuber:
    https://www.youtube.com/watch?annotation_id=annotation_3065812653&feature=iv&src_vid=SK98ayjhk1E&v=aQAIMY-HzL8
    in reguards to using SQLite
    */

    DatabaseHelper myDatabaseHelper;

    static final String FILE_NAME = "example.txt";
    //static String[] viewlist = new String[100];
    int i = 0;

    //private static final ArrayList<String> list = new ArrayList<String>();
    private Button backandstoreButton;
    static ArrayList<String> list = new ArrayList<String>();

    EditText at1;
    //Spinner at2;
    EditText at3;
    String entry;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);

        //data
        at1 = (EditText) findViewById(R.id.edittext1);
        //at2 = (Spinner) findViewById(R.id.spinner1);
        at3 = (EditText) findViewById(R.id.edittext3);
        myDatabaseHelper = new DatabaseHelper(this);

        //spinner time
        spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);




        //back and store info button
        backandstoreButton = (Button) findViewById(R.id.buttonDone);
        backandstoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "Photo: " + at1.getText().toString() + " By: " + at3.getText().toString() + " " + spinner.getSelectedItem().toString();
                //list.add(str);

                if(at1.length()!= 0 && at3.length()!= 0){
                    AddData(str);
                    //reset text
                }


                //testing NOT WORKING
                /*
                SharedPreferences settings = getSharedPreferences("prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                StringBuilder sb = new StringBuilder();
                sb.append(list);
                editor.putString("name", sb.toString());
                editor.apply();
                */




            /*
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fos.write(str.getBytes());

                    at1.getText().clear();
                    at3.getText().clear();

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
            */

             /*
                SharedPreferences settings = getSharedPreferences("prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("message", str);
                editor.apply();
            */

        /*
                Intent intent = new Intent(getApplicationContext(), view.class);
                intent.putExtra("message", str);
                startActivityForResult(intent, 1);
        */

                //old stuff
                //openActivityMainActivity();
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        ((EditText) findViewById(R.id.edittext1)).getText().clear();
        ((EditText) findViewById(R.id.edittext3)).getText().clear();
    }

    public void AddData(String newEntry){
        boolean insertData = myDatabaseHelper.addData(newEntry);
    }
}
