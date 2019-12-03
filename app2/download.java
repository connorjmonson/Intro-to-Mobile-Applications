package com.example.connormonson.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class download extends AppCompatActivity {

    private Button backandstoreButton;
    EditText at1;
    EditText at2;
    DatabaseHelper myDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        at1 = (EditText) findViewById(R.id.edittext1);
        at2 = (EditText) findViewById(R.id.edittext2);
        myDatabaseHelper = new DatabaseHelper(this);

        //back and store info button
        backandstoreButton = (Button) findViewById(R.id.buttonDone);
        backandstoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(at1.length()!= 0 && at2.length()!= 0){
                    String str1 = at1.getText().toString();
                    String str2 = at2.getText().toString();
                    AddData(str1, str2);
                    at1.setText("");
                    at2.setText("");
                    finish();
                }
                else{
                    toastMessage("No Internet Connection/Enter all fields");
                }
                //Toast.makeText(, "No Internet Connection", Toast.LENGTH_SHORT).show();
                //finish();

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        ((EditText) findViewById(R.id.edittext1)).getText().clear();
        ((EditText) findViewById(R.id.edittext2)).getText().clear();
    }

    public void AddData(String newEntry1, String newEntry2){
        boolean insertData = myDatabaseHelper.addData(newEntry1, newEntry2);
        if (insertData == true){
            toastMessage("success");
        }else{
            toastMessage("No Internet Connection/something went wrong");
        }
    }

    private void toastMessage(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
