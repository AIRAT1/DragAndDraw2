package de.android.draganddraw;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoxDrawingView extends View{
    private static final String TAG = BoxDrawingView.class.getSimpleName();

    private Box currentBox;
    private List<Box> boxes = new ArrayList<>();
    private Paint boxPaint, backgroundPaint;

    // for code
    public BoxDrawingView(Context context) {
        this(context, null);
    }

    // for XML
    public BoxDrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        boxPaint = new Paint();
        boxPaint.setColor(0x22ff0000);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(0xfff8efe0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(backgroundPaint);

        for (Box box : boxes) {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);
            canvas.drawRect(left, top, right, bottom, boxPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());
        String action = "";

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                currentBox = new Box(current);
                boxes.add(currentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                if (currentBox != null) {
                    currentBox.setCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                currentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANCEL";
                currentBox = null;
                break;
            default:
                super.onTouchEvent(event);
        }
        Log.i(TAG, action + " at x = " + current.x + ", y = " + current.y);
        return true;
    }
}
