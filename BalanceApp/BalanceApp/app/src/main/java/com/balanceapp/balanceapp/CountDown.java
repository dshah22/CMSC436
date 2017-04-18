package com.balanceapp.balanceapp;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.data;
import static com.balanceapp.balanceapp.R.id.timer;

/**
 * Created by OJ on 4/4/17.
 */

public class CountDown extends AppCompatActivity {

    Button restartButton;
    boolean testCanceled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        restartButton = (Button) findViewById(R.id.restartButton);
        testCanceled = false;

        // COUNT DOWN (6 SECONDS)
        new CountDownTimer(7000, 1000) {

            TextView timer = (TextView)findViewById(R.id.timer);

            // CALLED EVERY SECOND/TICK
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished/1000 == 1){
                    timer.setText("Test begins in " + millisUntilFinished / 1000 + " second.");
                } else {
                    timer.setText("Test begins in " + millisUntilFinished / 1000 + " seconds.");
                }
            }


            // AFTER COUNTDOWN IS FINISHED, GO TO POPPING ACTIVITY TO POP BUTTONS
            public void onFinish() {
                if(!testCanceled) {
                    timer.setText("BEGIN TEST!");

                    // VIBRATE
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(500); // Vibrate for 500 milliseconds

                    // Make sound
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();

                    // Begin balancing activity
                    beginTest();

                    restartButton.setVisibility(View.VISIBLE);
                }
            }

        }.start();
    }

    // Start activity intending to get a result from it
    public void beginTest(){
        Intent balanceIntent = new Intent(this, BalanceActivity.class);
        startActivityForResult(balanceIntent, 1);
    }


    // Get the resulting score from the activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        System.out.println("Activity Result done");
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data != null) {
            String text = data.getStringExtra("score");
            TextView timer = (TextView)findViewById(R.id.timer);
            timer.setText("Score: " + text);

        }
    }


    // Restarts the current activity
    public void restart(View v) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }

    @Override
    public void onBackPressed(){
        testCanceled = true;
        finish();
        super.onBackPressed();
        startActivity(new Intent(this ,MainActivity.class));
    }
}
