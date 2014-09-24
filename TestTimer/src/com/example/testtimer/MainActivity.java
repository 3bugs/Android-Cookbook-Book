package com.example.testtimer;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    Timer timer;
    Button btn;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.number_image);
        btn = (Button) findViewById(R.id.start_animation_button);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn.setVisibility(View.INVISIBLE);
                img.setVisibility(View.VISIBLE);

                timer = new Timer();
                timer.schedule(new MyTask(), 0, 1000);
            }
        });
    }

    class MyTask extends TimerTask {
        int currentNumber = 0;
        int[] numbers = { R.drawable.number_0, R.drawable.number_1,
                          R.drawable.number_2, R.drawable.number_3, 
                          R.drawable.number_4, R.drawable.number_5, 
                          R.drawable.number_6, R.drawable.number_7,
                          R.drawable.number_8, R.drawable.number_9 };

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    img.setImageResource(numbers[currentNumber++]);
                    if (currentNumber >= 10) {
                        timer.cancel();

                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("คู่มือเขียนแอพ Android")
                                .setMessage("ลองนำไปดัดแปลงดูนะครับ ^_^")
                                .setPositiveButton("Start Again",
                                        new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                    int which) {
                                                img.setVisibility(View.INVISIBLE);
                                                img.setImageBitmap(null);
                                                btn.setVisibility(View.VISIBLE);
                                            }
                                        }).setNegativeButton("Close", null)
                                .show();
                    }
                }
            });
        }
    }
}
