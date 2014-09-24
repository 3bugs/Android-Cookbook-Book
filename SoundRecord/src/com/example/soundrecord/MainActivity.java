package com.example.soundrecord;

import java.io.File;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button mStartButton, mStopButton;
    private MediaRecorder mRecorder;
    private File mOutputFile;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mStartButton = (Button) findViewById(R.id.start_button);
        mStartButton.setOnClickListener(startListener);
        mStopButton = (Button) findViewById(R.id.stop_button);
        mStopButton.setOnClickListener(stopListener);
        
        mRecorder = new MediaRecorder();
        mOutputFile = new File(Environment.getExternalStorageDirectory(), "sound.3gp");
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
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            mRecorder.setOutputFile(mOutputFile.getAbsolutePath());
            
            try {
                mRecorder.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            mRecorder.start();
            
            mStartButton.setEnabled(false);
            mStopButton.setEnabled(true);
        }
    };

    private View.OnClickListener stopListener = new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            mRecorder.stop();
            
            mStartButton.setEnabled(true);
            mStopButton.setEnabled(false);
        }
    };
    
}
