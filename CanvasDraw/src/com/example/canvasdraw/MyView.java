package com.example.canvasdraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

    private static final int RADIUS = 90;

    private Paint mPaint;
    private Bitmap mImage;
    private float mX, mY;

    public MyView(Context context) {
        super(context);

        mPaint = new Paint();
        mImage = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

        setOnTouchListener(new View.OnTouchListener() {
            
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mX = event.getX();
                mY = event.getY();
                invalidate();
                return true;
            }
        });
    }

    @Override
    public void onDraw(Canvas canvas) {
        mPaint.setColor(Color.argb(200, 50, 0, 255));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(mX, mY, RADIUS, mPaint);
        
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(40);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("Android", mX, mY - 10, mPaint);
        
        canvas.drawBitmap(mImage, mX - (mImage.getWidth() / 2), mY, mPaint);
    }

}
