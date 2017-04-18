package com.example.armcurl;


import android.hardware.SensorEvent;

public abstract class CurlManager {
    protected abstract void onRepIncrease();

    boolean state = false;

    public void updateEvent(SensorEvent event){
        float value = currentPos(event);

        if(!state){
            if(value > 6.5f){
                onRepIncrease();
                state = !state;
            }
        }else {
            if(value < 0.0f){
                state = !state;
            }
        }
    }

    private float currentPos(SensorEvent event){
        float x = event.values[0];

        return x;
    }

}
