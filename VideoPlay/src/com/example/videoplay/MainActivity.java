package com.example.videoplay;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        VideoView video = (VideoView) findViewById(R.id.video);
        video.setKeepScreenOn(true);
        video.setVideoURI(Uri.parse("http://www.promlert.com/temp/test.3gp"));

        MediaController controller = new MediaController(this);
        video.setMediaController(controller); 

        video.start();
    }
}
