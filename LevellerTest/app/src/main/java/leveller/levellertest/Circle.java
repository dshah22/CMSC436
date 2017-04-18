package leveller.levellertest;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import static leveller.levellertest.LevellerActivity.cx;
import static leveller.levellertest.LevellerActivity.cy;
import static leveller.levellertest.LevellerActivity.bigRadius;
import static leveller.levellertest.LevellerActivity.midRadius;
import static leveller.levellertest.LevellerActivity.smallRadius;


public class Circle extends View
{
    private static final int DEFAULT_CIRCLE_COLOR = Color.BLACK;

    private int circleColor = DEFAULT_CIRCLE_COLOR;
    private Paint paint;


    public Circle(Context context)
    {
        super(context);
        init(context, null);
    }

    public Circle(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        paint = new Paint();
        paint.setAntiAlias(true);

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

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        /*
         * Pick colors to be mindful of color blindness.
         */
        paint.setColor(Color.BLUE);
        canvas.drawCircle(cx, cy, bigRadius, paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawCircle(cx, cy, midRadius, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(cx, cy, smallRadius, paint);
    }


}

