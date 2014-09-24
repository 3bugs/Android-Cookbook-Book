package com.example.canvasdrawwithotherviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

    private static final int CLEAR_SCREEN = 0;
    private static final int DRAW_CIRCLE = 1;
    private static final int DRAW_RECTANGLE = 2;

    private int mDrawAction = CLEAR_SCREEN;
    private Paint mPaint;

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context) {
        this(context, null, 0);
    }

    public void drawCircle() {
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        
        mDrawAction = DRAW_CIRCLE;
        invalidate();
    }

    public void drawRectangle() {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);

        mDrawAction = DRAW_RECTANGLE;
        invalidate();
    }
    
    public void clearScreen() {
        mDrawAction = CLEAR_SCREEN;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = (this.getWidth() / 2);
        float centerY = (this.getHeight() / 2);

        switch (mDrawAction) {
        case CLEAR_SCREEN:
            canvas.drawColor(Color.TRANSPARENT);
            break;
        case DRAW_CIRCLE:
            canvas.drawCircle(centerX, centerY, 100, mPaint);
            break;
        case DRAW_RECTANGLE:
            RectF rect = new RectF(centerX - 100, centerY - 100, centerX + 100, centerY + 100);
            canvas.drawRect(rect, mPaint);
            break;
        }
    }
}
