package com.example.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final TextView text = (TextView) findViewById(R.id.text);
        
        ObjectAnimator animResizeText = ObjectAnimator.ofFloat(text, "textSize", 12f, 50f);
        animResizeText.setDuration(500);
        animResizeText.setStartDelay(1000);
        animResizeText.setRepeatCount(10);
        animResizeText.setInterpolator(new DecelerateInterpolator());
        animResizeText.setRepeatMode(ObjectAnimator.REVERSE);
        //animResizeText.start();
        
        ObjectAnimator animFadeOut = ObjectAnimator.ofFloat(text, "alpha", 1f, 0f);
        animFadeOut.setDuration(300);
        animFadeOut.setRepeatCount(0);
        animFadeOut.addListener(new Animator.AnimatorListener() {
            
            @Override
            public void onAnimationStart(Animator animation) {
            }
            
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
            
            @Override
            public void onAnimationEnd(Animator animation) {
                text.setText("สวัสดี");
            }
            
            @Override
            public void onAnimationCancel(Animator arg0) {
            }
        });
        
        ObjectAnimator animFadeIn = ObjectAnimator.ofFloat(text, "alpha", 0f, 1f);
        animFadeIn.setDuration(300);
        animFadeIn.setRepeatCount(0);
        
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animResizeText).before(animFadeOut);
        animSet.play(animFadeIn).after(animFadeOut);
        animSet.start();
    }

}
