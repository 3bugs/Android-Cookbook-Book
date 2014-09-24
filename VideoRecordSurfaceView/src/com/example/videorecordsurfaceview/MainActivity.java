package com.example.videorecordsurfaceview;

import java.io.File;

import android.app.Activity;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button mButton;
    private MediaRecorder mRecorder;
    private File mOutputFile;
    private boolean mIsRecording = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mButton = (Button) findViewById(R.id.start_stop_button);
        mButton.setOnClickListener(startListener);
        
        mRecorder = new MediaRecorder();
        SurfaceView preview = (SurfaceView) findViewById(R.id.camera_preview);
        mRecorder.setPreviewDisplay(preview.getHolder().getSurface());
        
        mOutputFile = new File(Environment.getExternalStorageDirectory(), "video.mp4");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
    }
    
    private View.OnClickListener startListener = new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            if (mIsRecording == true) {
                mRecorder.stop();
                mButton.setText("Start");
                mIsRecording = false;
            } else {
                setupRecorder();
                
                try {
                    mRecorder.prepare();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                mRecorder.start();
                mButton.setText("Stop");
                mIsRecording = true;
            }
        }
    };
    
    private void setupRecorder() {
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        
        CamcorderProfile profile = null;
        
        if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_1080P))
            profile = CamcorderProfile.get(CamcorderProfile.QUALITY_1080P);
        else if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_720P))
            profile = CamcorderProfile.get(CamcorderProfile.QUALITY_720P);
        else if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_480P))
            profile = CamcorderProfile.get(CamcorderProfile.QUALITY_480P);
        else if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_HIGH))
            profile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
        
        if (profile != null) 
            mRecorder.setProfile(profile);
        
        mRecorder.setOutputFile(mOutputFile.getAbsolutePath());
    }

}
