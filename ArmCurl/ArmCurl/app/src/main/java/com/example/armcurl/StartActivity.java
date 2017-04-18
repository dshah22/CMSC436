package com.example.armcurl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    static final int START_COUNT_RIGHT = 1, START_COUNT_LEFT =2;
    TextView rightTextView, leftTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        rightTextView = (TextView) findViewById(R.id.right_text_view);
        leftTextView = (TextView) findViewById(R.id.left_text_view);
    }

    public void startCurlRight(View view){
        Intent intent = new Intent(this, CurlActivity.class);
        startActivityForResult(intent, START_COUNT_RIGHT);
    }

    public void startCurlLeft(View view){
        Intent intent = new Intent(this, CurlActivity.class);
        startActivityForResult(intent, START_COUNT_LEFT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null) {
            String text = data.getStringExtra("score");
            if(requestCode == START_COUNT_RIGHT){
                rightTextView.append(text);
            }else if(requestCode == START_COUNT_LEFT){
                leftTextView.append(text);
            }
        }
    }
}
