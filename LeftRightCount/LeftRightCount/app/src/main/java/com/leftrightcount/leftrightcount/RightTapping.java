package com.leftrightcount.leftrightcount;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class RightTapping extends AppCompatActivity {

    public static int rightCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_tapping);

        rightCount = 0;
        new CountDownTimer(11000, 1000) {

            TextView timer = (TextView)findViewById(R.id.timer);
            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                TappingActivity.rightCountDisplay.setText(rightCount + " right taps");
                timer.setText(Integer.toString(rightCount));
            }

        }.start();

    }

    public void incrementRight(View v){ this.rightCount++; }
}
