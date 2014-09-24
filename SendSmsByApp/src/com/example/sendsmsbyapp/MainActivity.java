package com.example.sendsmsbyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button = (Button) findViewById(R.id.run_app_button);
        button.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                intent.putExtra("address", "5556");
                intent.putExtra("sms_body", "Hello!\nHow are you?");
                intent.setType("vnd.android-dir/mms-sms");
                startActivity(intent);
            }
        });
    }

}
