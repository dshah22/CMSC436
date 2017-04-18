package com.leftrightcount.leftrightcount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class TappingActivity extends AppCompatActivity {


    public static TextView leftCountDisplay;
    public static TextView rightCountDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tapping);

        leftCountDisplay = (TextView) findViewById(R.id.leftCountDisplay);
        rightCountDisplay = (TextView) findViewById(R.id.rightCountDisplay);
    }

    public void clickLeft(View v) {
        Intent intent = new Intent("com.leftrightcount.leftrightcount.LeftTapping");
        startActivity(intent);
    }

    public void clickRight(View v) {
        Intent intent = new Intent("com.leftrightcount.leftrightcount.RightTapping");
        startActivity(intent);
    }

}
