package com.example.canvasdrawanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

public class MyView extends View {

    private static final int RADIUS = 40;
    private static final int DISTANCE_X = 5;
    private static final int DISTANCE_Y = 5;

    private Paint mPaint;
    private float mX, mY;
    private int mDirectionX = 1;
    private int mDirectionY = 1;

    Handler mHandler = new Handler();

    Runnable mTick = new Runnable() {
        public void run() {
            mX += (mDirectionX * DISTANCE_X);
            if (mX - RADIUS <= 0) {
                mDirectionX = 1;
            } else if (mX + RADIUS >= MyView.this.getWidth()) {
                mDirectionX = -1;
            }

            mY += (mDirectionY * DISTANCE_Y);
            if (mY - RADIUS <= 0) {
                mDirectionY = 1;
            } else if (mY + RADIUS >= MyView.this.getHeight()) {
                mDirectionY = -1;
            }

            invalidate();
            mHandler.postDelayed(this, 40);
        }
    };

    void startAnimation() {
        mHandler.removeCallbacks(mTick);
        mHandler.post(mTick);
    }

    void stopAnimation() {
        mHandler.removeCallbacks(mTick);
    }

    public MyView(Context context) {
        super(context);

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        startAnimation();
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(mX, mY, RADIUS, mPaint);
    }

}
