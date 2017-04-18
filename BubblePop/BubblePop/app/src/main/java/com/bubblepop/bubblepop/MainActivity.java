package com.bubblepop.bubblepop;

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

    // REDIRECTS USER TO THE COUNTDOWN SCREEN BEFORE ACTUALLY STARTING THE BUBBLE POPPING
    protected void startPopping(View v) {
        Intent poppingIntent = new Intent(MainActivity.this, CountDown.class);
        startActivity(poppingIntent);
    }


}
