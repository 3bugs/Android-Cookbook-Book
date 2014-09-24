package com.example.activityreturndemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

    //static final int REQUEST_CODE = 123;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btn = (Button) findViewById(R.id.display_second_activity_button);
        btn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(i, 123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                String strName = data.getStringExtra("username");
                Toast.makeText(this, strName, Toast.LENGTH_LONG).show();
            }
        }
    }
}
