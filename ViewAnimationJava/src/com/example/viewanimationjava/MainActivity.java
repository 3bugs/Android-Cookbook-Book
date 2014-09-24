package com.example.viewanimationjava;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {

    ImageView imgCoin;
    ScaleAnimation shrink, grow;
    boolean isHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgCoin = (ImageView) findViewById(R.id.image);
        imgCoin.setImageResource(R.drawable.coin_head);
        isHead = true;

        shrink = new ScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        shrink.setDuration(150);
        shrink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isHead) {
                    isHead = false;
                    imgCoin.setImageResource(R.drawable.coin_tail);
                } else {
                    isHead = true;
                    imgCoin.setImageResource(R.drawable.coin_head);
                }
                imgCoin.startAnimation(grow);
            }
        });

        grow = new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        grow.setDuration(150);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            imgCoin.startAnimation(shrink);
            return true;
        }
        return super.onTouchEvent(event);
    }
}
