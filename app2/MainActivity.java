package com.example.connormonson.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //first button on home page
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityDownload();
            }
        });

        //second button on home page
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityDelete();
            }
        });

        //third button on home page
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityView();
            }
        });

        //fourth button on home page
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityRange();
            }
        });
    }

    //open Activity Download
    public void openActivityDownload(){
        Intent intent = new Intent(this, download.class);
        startActivity(intent);
    }

    //open Activity Delete
    public void openActivityDelete(){
        Intent intent = new Intent(this, delete.class);
        startActivity(intent);
    }

    //open Activity View
    public void openActivityView(){
        Intent intent = new Intent(this, view.class);
        startActivity(intent);
    }

    //open Activity Range
    public void openActivityRange(){
        Intent intent = new Intent(this, range.class);
        startActivity(intent);
    }
}
