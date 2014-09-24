package com.example.viewanimationxml;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView) findViewById(R.id.text);
        text.setVisibility(View.INVISIBLE);
        
        final Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.my_anim);
        anim.setAnimationListener(new AnimationListener() {
        
            @Override
            public void onAnimationStart(Animation animation) {
                text.setVisibility(View.VISIBLE);
            }
        
            @Override
            public void onAnimationEnd(Animation animation) {
                text.setVisibility(View.INVISIBLE);
            }
        
            @Override
            public void onAnimationRepeat(Animation animation) {
                return;
            }
        });
        
        Button btnAnimate = (Button) findViewById(R.id.animate_button);
        btnAnimate.setOnClickListener(new View.OnClickListener() {
        
            @Override
            public void onClick(View v) {
                text.startAnimation(anim);
            }
        });
    }

}