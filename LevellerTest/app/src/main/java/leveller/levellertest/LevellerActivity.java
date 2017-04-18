package leveller.levellertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.widget.FrameLayout;
import android.widget.TextView;


public class LevellerActivity extends Activity implements SensorEventListener {

    FrameLayout mainView;

    private SensorManager sensorManager;
    private Ball ball;

    // Center of circles and radius of big, medium, and small circles.
    protected static float cx, cy, bigRadius, midRadius, smallRadius, xOrient, yOrient;
    protected static float[] orientation;

    // Rotation Matrix
    private final float[] MAG = new float[] {1f, 1f, 1f};
    private final float[] I = new float[16];
    private final float[] r = new float[16];

    // Score
    TextView scoreView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        cx = 0;
        cy = 0;
        bigRadius = 0;
        midRadius = 0;
        smallRadius = 0;
        orientation = new float[4];
        getMiddle();
        ball = new Ball(this);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        setContentView(R.layout.activity_leveller);
        mainView = (android.widget.FrameLayout)findViewById(R.id.activity_leveller);
        mainView.addView(ball);


        // Timer
        new CountDownTimer(11000, 1000) {

            TextView timer = (TextView)findViewById(R.id.timer);
            public void onTick(long millisUntilFinished) {
                timer.setText("Seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                Intent intent = new Intent(LevellerActivity.this, ScoreDisplayActivity.class);
                intent.putExtra("final_score", ((Integer) ball.ballScore).toString());
                startActivity(intent);
            }

        }.start();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] rotationMatrix = new float[16];
        SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);
        float[] orientationValues = new float[3];
        SensorManager.getOrientation(rotationMatrix, orientationValues);

        // Get pitch and roll of device
        xOrient = (float) Math.toDegrees(orientationValues[1]);
        yOrient = (float) Math.toDegrees(orientationValues[2]);

        // Update the ball
        if(ball != null) {
            ball.update();

            // Update the score
            scoreView = (TextView) findViewById(R.id.score);
            scoreView.setText("Score: " + ball.ballScore);
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
                SensorManager.SENSOR_DELAY_GAME );
    }


    @Override
    protected void onStop(){
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    /**
     * Gets the middle of the screen and the usable radius for the ball.
     */
    protected void getMiddle(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        bigRadius = Math.min(width, height) / 2;
        midRadius = bigRadius/((float) (Math.sqrt(2)));
        smallRadius = bigRadius/(float) (2*(Math.sqrt(2)));
        cx = width/2;
        cy = height/2;
    }
}

