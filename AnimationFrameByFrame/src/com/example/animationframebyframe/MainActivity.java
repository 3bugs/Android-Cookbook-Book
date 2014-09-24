package com.example.animationframebyframe;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageView image = (ImageView) findViewById(R.id.image);
        image.setBackgroundResource(R.drawable.rotating_android);
        
        AnimationDrawable anim = (AnimationDrawable) image.getBackground();
        anim.start();
    }

}
