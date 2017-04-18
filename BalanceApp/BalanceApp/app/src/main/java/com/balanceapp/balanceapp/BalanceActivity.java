package com.balanceapp.balanceapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class BalanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        SensorManager sensorService = (SensorManager) getApplicationContext().getSystemService(getApplicationContext().SENSOR_SERVICE);
        Sensor sensor = sensorService.getDefaultSensor(Sensor.TYPE_GRAVITY);

        //Balance Listener
        final BalanceListener listener = new BalanceListener();

        sensorService.registerListener(listener, sensorService.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_FASTEST);

        // Set Timer for 10 seconds
        new CountDownTimer(11000, 1000) {

            TextView timer = (TextView)findViewById(R.id.test_timer);
            public void onTick(long millisUntilFinished) {
                timer.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                String score = listener.getScoreString();

                Intent intent = new Intent();
                intent.putExtra("score", score);
                setResult(RESULT_OK, intent);

                // VIBRATE
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(500); // Vibrate for 500 milliseconds

                // Make sound
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();

                finish();
            }

        }.start();

    }

    @Override
    public void onBackPressed(){

    }
}
