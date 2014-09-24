package com.example.cameraoverlay;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.BitmapFactory.Options;
import android.hardware.Camera;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

public class MySurfaceView extends SurfaceView implements
        SurfaceHolder.Callback {

    private static Context mContext;
    private static Camera mCamera;
    private Paint mPaint;
    private Bitmap mImage;
    private int mCenterX, mCenterY;
    private RectF mOvalBound;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressWarnings("deprecation")
    public MySurfaceView(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
        mContext = context;
    
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        
        mPaint = new Paint();
        mImage = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    }
    
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera = Camera.open();
            setWillNotDraw(false);
            mCamera.setPreviewDisplay(holder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {

        Camera.Parameters params = mCamera.getParameters();
        List<Camera.Size> sizes = params.getSupportedPreviewSizes();
        Camera.Size selected = sizes.get(0);
        params.setPreviewSize(selected.width, selected.height);
        mCamera.setParameters(params);

        setCameraDisplayOrientation();
        mCamera.startPreview();
    }
    
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCamera.stopPreview();
        mCamera.release();
    }

    public void setCameraDisplayOrientation() {
        android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(0, info);
    
        int rotation = ((WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getRotation();
    
        int degrees = 0;
        switch (rotation) {
        case Surface.ROTATION_0:
            degrees = 0;
            break;
        case Surface.ROTATION_90:
            degrees = 90;
            break;
        case Surface.ROTATION_180:
            degrees = 180;
            break;
        case Surface.ROTATION_270:
            degrees = 270;
            break;
        }
    
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;
        } else {
            result = (info.orientation - degrees + 360) % 360;
        }
        mCamera.setDisplayOrientation(result);
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        
        mCenterX = getWidth() / 2;
        mCenterY = getHeight() / 2;
        mOvalBound = new RectF(mCenterX, mCenterY - 10, mCenterX + 45, mCenterY + 50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.argb(200, 255, 255, 0));
        canvas.drawCircle(mCenterX, mCenterY, 65, mPaint);
        
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(mCenterX - 15, mCenterY - 25, 12, mPaint);
    
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(mCenterX + 35, mCenterY - 32, 12, mPaint);
    
        
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.argb(128, 255, 0, 0));
        canvas.drawOval(mOvalBound, mPaint);
    
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(40);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("WoW! Android", mCenterX, getHeight() - 30, mPaint);
        
        canvas.drawBitmap(mImage, 50, (getHeight() - mImage.getHeight()) / 2, null);
    }
    
    private static class TakePictureCallback implements Camera.PictureCallback {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            
            try {
                File imageFile = new File(Environment.getExternalStorageDirectory(), 
                        "picture.jpg");
                FileOutputStream fos = new FileOutputStream(imageFile); 
                fos.write(data);
                fos.flush();
                fos.close();

                /*
                File imageFile = new File(Environment.getExternalStorageDirectory(), 
                        "picture.jpg");
                FileOutputStream fos = new FileOutputStream(imageFile);
                
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length).copy(Bitmap.Config.ARGB_8888, true);
                Canvas canvas = new Canvas(bitmap);
                
                Paint paint = new Paint();
                paint.setFlags(Paint.ANTI_ALIAS_FLAG);
                
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.argb(200, 255, 255, 0));
                int pictureWidth = bitmap.getWidth();
                int pictureHeight = bitmap.getHeight();
                canvas.drawCircle(pictureWidth / 2, pictureHeight / 2, pictureHeight / 4, paint);
                
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
                */
                
                Toast.makeText(mContext, "Picture saved.", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            camera.startPreview();
        }
    }
    
    public static void takePicture() {
        Camera.Parameters params = mCamera.getParameters();
        List<Camera.Size> sizes = params.getSupportedPictureSizes();
        Camera.Size selected = sizes.get(0);
        params.setPictureSize(selected.width, selected.height);
        mCamera.setParameters(params);

        TakePictureCallback callback = new TakePictureCallback();
        mCamera.takePicture(null, null, null, callback);
    }
    
}