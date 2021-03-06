package com.example.armcurl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startActivity(View view){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void getInstructions(View view){
        Intent intent = new Intent(this, Instructions.class);
        startActivity(intent);
    }
}
