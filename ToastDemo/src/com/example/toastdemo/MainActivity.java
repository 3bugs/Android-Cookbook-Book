package com.example.toastdemo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ปุ่ม Show Toast
        Button btnShowToast = (Button) findViewById(R.id.show_toast_button);
        btnShowToast.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        // ปุ่ม Show Toast Longer
        Button btnShowToastLonger = (Button) findViewById(R.id.show_toast_longer_button);
        btnShowToastLonger.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Hello 5-second toast!";
                int duration = Toast.LENGTH_SHORT;

                final Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                new CountDownTimer(5000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        toast.show();
                    }

                    public void onFinish() {
                    }
                }.start();
            }
        });

        // ปุ่ม Show Custom Toast
        Button btnShowCustomToast = (Button) findViewById(R.id.show_custom_toast_button);
        btnShowCustomToast.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_layout,
                        (ViewGroup) findViewById(R.id.toast_layout_root));

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("This is a custom toast");

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });
    }

}
