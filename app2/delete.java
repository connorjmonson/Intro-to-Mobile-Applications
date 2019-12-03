package com.example.connormonson.assignment2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delete extends AppCompatActivity {

    private Button backandstoreButton;
    EditText at1;
    EditText at2;
    DatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        at1 = (EditText) findViewById(R.id.edittext1);
        at2 = (EditText) findViewById(R.id.edittext2);
        myDatabaseHelper = new DatabaseHelper(this);

        //back and store info button
        backandstoreButton = (Button) findViewById(R.id.buttonDone);
        backandstoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(at1.length()>0){
                    String str1 = at1.getText().toString();
                    Integer row = myDatabaseHelper.deleteID(str1);
                    if(row>0){
                        toastMessage("Deleted");
                        finish();
                    }
                    else{
                        toastMessage("Error! Does not exist");
                    }
                }

                if(at2.length()>0){
                    String str2 = at2.getText().toString();
                    Integer row = myDatabaseHelper.deleteName(str2);
                    if(row>0){
                        toastMessage("Deleted");
                        finish();
                    }
                    else{
                        toastMessage("Error! Does not exist");
                    }
                }
                /*
                if(at1.length()!= 0 && at2.length()!= 0){
                    String str1 = at1.getText().toString();
                    int value = Integer.parseInt(str1);
                    String str2 = at2.getText().toString();

                    Cursor data = myDatabaseHelper.nameExists(str1, str2);
                    int itemID = -1;
                    while(data.moveToNext()){
                        itemID = data.getInt(0);
                    }
                    if (itemID > -1){
                        myDatabaseHelper.deleteName(str1, str2);
                        toastMessage("Deleted");
                        finish();
                    }
                    else{
                        toastMessage("Error! Does not exist");
                        //finish();
                    }


                    /*
                    //boolean check = myDatabaseHelper.nameExists(value, str2);
                    if ( check == false){
                        toastMessage("Error! Does not exist");
                    }
                    else {
                        //not recognizing integer value as primary key
                        myDatabaseHelper.deleteName(value, str2);
                        finish();
                    }

                }
                */
                else{
                   // toastMessage("Error! Enter all fields");
                }
                //finish();

            }
        });

    }

    private void toastMessage(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
