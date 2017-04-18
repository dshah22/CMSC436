package leveller.levellertest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


import static leveller.levellertest.LevellerActivity.bigRadius;
import static leveller.levellertest.LevellerActivity.cx;
import static leveller.levellertest.LevellerActivity.cy;
import static leveller.levellertest.LevellerActivity.midRadius;
import static leveller.levellertest.LevellerActivity.smallRadius;
import static leveller.levellertest.LevellerActivity.xOrient;
import static leveller.levellertest.LevellerActivity.yOrient;


public class Ball extends View
{
    private static final int DEFAULT_CIRCLE_COLOR = Color.BLACK;

    private int circleColor = DEFAULT_CIRCLE_COLOR;
    private Paint paint;
    private float x, y, r;
    protected int ballScore;

    public Ball(Context context)
    {
        super(context);
        init(context, null);
    }

    public Ball(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        paint = new Paint();
        paint.setAntiAlias(true);
        x = cx;
        y = cy;
        r = bigRadius/(float) (8*(Math.sqrt(2)));
        ballScore = 0;
    }

    public void setCircleColor(int circleColor)
    {
        this.circleColor = circleColor;
        invalidate();
    }

    public int getCircleColor()
    {
        return circleColor;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, r, paint);

    }


    protected void update(){
        // New center point for the ball
        x = cx + (4*(yOrient/90 * bigRadius));
        y = cy - (4*(xOrient/90 * bigRadius));

        // if the ball is out of bounds, put it on the closest edge.
        if((float) (Math.sqrt(Math.pow(x - cx, 2) + Math.pow(y - cy, 2))) > bigRadius) {
            float tempX = x;
            x = (float) (cx + ((bigRadius * (x - cx))/
                    Math.sqrt(Math.pow(x - cx, 2) + Math.pow(y - cy, 2))));

            y = (float) (cy + ((bigRadius * (y - cy))/
                    Math.sqrt(Math.pow(tempX - cx, 2) + Math.pow(y - cy, 2))));
        }

        updateScore();
        invalidate();
    }


    private void updateScore(){
        double distance = Math.sqrt(Math.pow(x - cx, 2) + Math.pow(y - cy, 2));
        if (distance > smallRadius && distance <=midRadius)
            ballScore += 1;
        else if (distance > midRadius)
            ballScore += 2;
    }
}
