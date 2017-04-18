package com.bubblepop.bubblepop;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class PoppingActivity extends AppCompatActivity {

    int popCnt, numX, numY, rNumX, rNumY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popping);


        // GET DIMENSIONS OF THE SCREEN (use this later -- in bubblePopping function)
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        numX = size.x;
        numY = size.y;


        // START TIMER (11000 <-- 11 seconds)
        new CountDownTimer(11000, 1000) {
            TextView timer = (TextView)findViewById(R.id.timer);
            public void onTick(long millisUntilFinished) {
                timer.setText(millisUntilFinished / 1000 + " seconds remaining.");
            }

            // SHOW RESULT AND RESTART BUTTON AFTER TIMER IS DONE
            public void onFinish() {

                Button restart = (Button) findViewById(R.id.restartButton);
                ImageView bubble = (ImageView) findViewById(R.id.bubbleImg);

                // HIDES THE BUBBLE
                bubble.setVisibility(View.INVISIBLE);

                // DISPLAYS # OF POPS AFTER TIMER IS DONE
                timer.setText("You have popped " + popCnt + " bubbles.");

                // DISPAYS THE RESTART BUTTON (set to invisible in XML file)
                restart.setVisibility(View.VISIBLE);
            }
        }.start();

    }


    // DISPLAYS BUBBLE IN A RANDOM SPOT
    public void showBubble(View v) {

        // INCREMENTS NUMBER OF BUBBLES POPPED
        popCnt++;
        ImageView img = (ImageView) findViewById(R.id.bubbleImg);
        Random r = new Random();

        // SELECTS RANDOM X & Y
        // SELECTS RANDOM x BETWEEN 0 AND WIDTH OF THE SCREEN MINUS THE WIDTH OF THE BUBBLE IMAGE MINUS MARGINS
        // SELECTS RANDOM y BETWEEN 0 AND HEIGHT OF THE SCREEN MINUS THE HEIGHT OF THE BUBBLE IMAGE MINUS MARGINS
        rNumX = r.nextInt(numX - 350);
        rNumY = r.nextInt(numY - 500);

        // SET THE IMAGE AT THE RANDOM X & Y COORDINATE
        img.setX(rNumX);
        img.setY(rNumY);
    }

    // RESTARTS THIS ACTIVITY (CALLS ONCREATE AGAIN)
    public void restart(View v) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
