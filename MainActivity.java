package com.example.connormonson.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //first button on home page
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityEnterInfo();
            }
        });

        //second button on home page
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityView();
            }
        });

        //third button on home page
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0); //work this part out
                //openActivityExit();
            }
        });
    }

    //open Enter Info
    public void openActivityEnterInfo(){
        Intent intent = new Intent(this, enterInfo.class);
        startActivity(intent);
    }

    //open view
    public void openActivityView(){
        //make a new activity called view and change enterInfo.class -> view.class
        Intent intent = new Intent(this, view.class);
        startActivity(intent);
    }

    //open exit
    public void openActivityExit(){
        //make a new activity called view and change enterInfo.class -> exit.class

    }

}
