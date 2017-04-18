package com.bubblepop.bubblepop;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CountDown extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        // COUNT DOWN (6 SECONDS)
        new CountDownTimer(6000, 1000) {

            TextView timer = (TextView)findViewById(R.id.timer);

            // CALLED EVERY SECOND/TICK
            public void onTick(long millisUntilFinished) {
                timer.setText("Please start popping bubbles in " + millisUntilFinished / 1000 + " seconds.");
            }

            // AFTER COUNTDOWN IS FINISHED, GO TO POPPING ACTIVITY TO POP BUTTONS
            public void onFinish() {
                Intent poppingIntent = new Intent(CountDown.this, PoppingActivity.class);
                startActivity(poppingIntent);
            }

        }.start();
    }
}
