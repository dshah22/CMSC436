package com.spiraltrace.spiraltrace;

import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CanvasView canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvasView = (CanvasView) findViewById(R.id.canvas);
    }

    public void clearCanvas(View v) {
        CanvasView.xCoord = new float[1000000];
        CanvasView.yCoord = new float[1000000];
        canvasView.clearCanvas();

        TextView diff = (TextView) findViewById(R.id.lengthDiff);
        diff.setText("");
    }


    public void submitDrawing(View v) {
        PathMeasure pm = new PathMeasure(CanvasView.mPath, false);
        Float length = pm.getLength();
        while(pm.nextContour()){
            length += pm.getLength();
        }

        TextView diff = (TextView) findViewById(R.id.lengthDiff);
        diff.setText("Difference: " + Math.abs(7800 - length));
    }
}
