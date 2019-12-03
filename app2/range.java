package com.example.connormonson.assignment2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class range extends AppCompatActivity {
    //public static int EXTRA_NUMBER1 = 0;
    //public static int EXTRA_NUMBER2 = 0;
    public static final String EXTRA_NUMBER1 = "com.example.connormonson.assignment2.EXTRA_NUMBER1";
    public static final String EXTRA_NUMBER2 = "com.example.connormonson.assignment2.EXTRA_NUMBER2";

    //back and store info button
    private Button backandstoreButton;
    EditText at1;
    EditText at2;
    DatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range);

        at1 = (EditText) findViewById(R.id.edittext1);
        at2 = (EditText) findViewById(R.id.edittext2);

        //back and store info button
        backandstoreButton = (Button) findViewById(R.id.buttonDone);
        backandstoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(at1.length()!= 0 && at2.length()!= 0){
                    continueOn();
                }
                //finish();

            }
        });
    }

    public void continueOn(){
        at1 = (EditText) findViewById(R.id.edittext1);
        String str1 = at1.getText().toString();
        int value1 = Integer.parseInt(str1);

        at2 = (EditText) findViewById(R.id.edittext2);
        String str2 = at2.getText().toString();
        int value2 = Integer.parseInt(str2);

        if(value1 > value2 || value1 == 0 || value2 == 0){
            toastMessage("Error! invalid range");
            finish();
        }
        else openActivityRangeView(value1, value2);
    }

    //open Activity RangeView
    public void openActivityRangeView(int value1, int value2){
        myDatabaseHelper = new DatabaseHelper(this);
        Cursor data = myDatabaseHelper.getListContents();
        ArrayList<String> listData = new ArrayList<String>();
        while(data.moveToNext()){
            //get values from db in first column and add
            listData.add(data.getString(1) + ", " + data.getString(2));
        }

        if (value2-1 > listData.size()-1){
            toastMessage("Error! invalid range");
            finish();
        }
        else {
            Intent intent = new Intent(this, rangeView.class);

            intent.putExtra(EXTRA_NUMBER1, value1);
            intent.putExtra(EXTRA_NUMBER2, value2);
            startActivity(intent);
        }
    }

    private void toastMessage(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

}
