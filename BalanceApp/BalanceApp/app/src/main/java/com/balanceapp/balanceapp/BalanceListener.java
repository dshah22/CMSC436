package com.balanceapp.balanceapp;

/**
 * Created by Travis on 4/5/2017.
 */

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import static android.R.attr.x;
import static android.view.View.X;

public class BalanceListener implements SensorEventListener {

    int score;
    float prevX;

    public BalanceListener(){
        score = 0;
        prevX = 0;
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType() == Sensor.TYPE_GRAVITY){
            updateScore(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    public String getScoreString(){
        Integer scoreString = score;
        return scoreString.toString();
    }

    // Balance Score Manager
    public void updateScore(SensorEvent event){
        float x = event.values[1];
        if (prevX == 0)
            prevX = x;
        else
            score += (Math.abs(x - prevX) * 5);


    }
}