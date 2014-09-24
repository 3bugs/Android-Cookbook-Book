package com.example.soundplaywithcontroller;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.MediaController;

public class MainActivity extends Activity implements MediaController.MediaPlayerControl {

    private MediaPlayer mPlayer;
    private MediaController mController;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mController = new MediaController(this);
        mController.setMediaPlayer(this);
        mController.setAnchorView(findViewById(R.id.main_layout));
        mController.setEnabled(true);

        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.sound);
        mPlayer.setOnPreparedListener(new OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mHandler.post(new Runnable() {
                    public void run() {
                        mController.show(10000);
                        mPlayer.start();
                    }
                });
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mController.show(10000);
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mController.hide();
        mPlayer.stop();
        mPlayer.release();
        mPlayer = null;
    }

    public void start() {
        mPlayer.start();
    }

    public void pause() {
        mPlayer.pause();
    }

    public int getDuration() {
        return mPlayer.getDuration();
    }

    public int getCurrentPosition() {
        return mPlayer.getCurrentPosition();
    }

    public void seekTo(int i) {
        mPlayer.seekTo(i);
    }

    public boolean isPlaying() {
        return mPlayer.isPlaying();
    }

    public int getBufferPercentage() {
        return 0;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }
}
