package com.example.soundplay;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements MediaPlayer.OnCompletionListener {

    private MediaPlayer mPlayer;
    Button button;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button = (Button) findViewById(R.id.play_stop_button);
        button.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if (mPlayer == null) {
                    mPlayer = MediaPlayer.create(MainActivity.this, R.raw.sound);
                    mPlayer.setOnCompletionListener(MainActivity.this);
                    mPlayer.start();
                    button.setText("Stop");
                } else {
                    mPlayer.stop();
                    mPlayer.release();
                    mPlayer = null;
                    button.setText("Play");
                }
            }
        });
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mPlayer.release();
        mPlayer = null;
        button.setText("Play");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }    
}
