package com.leftrightcount.leftrightcount;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class LeftTapping extends AppCompatActivity {

    public static int leftCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_tapping);


        leftCount = 0;
        new CountDownTimer(11000, 1000) {

            TextView timer = (TextView)findViewById(R.id.timer);
            public void onTick(long millisUntilFinished) {
                timer.setText("Seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                TappingActivity.leftCountDisplay.setText(leftCount + " left taps");
                timer.setText(Integer.toString(leftCount));
            }

        }.start();

    }

    public void incrementLeft(View v) {
        this.leftCount++;
    }


}
