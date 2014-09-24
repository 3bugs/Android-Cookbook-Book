package com.example.activityreturndemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        
        Button btnOK = (Button) findViewById(R.id.ok_button);
        btnOK.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                EditText etName = (EditText) findViewById(R.id.name);
                String strName = etName.getText().toString();
                
                Intent i = new Intent();
                i.putExtra("username", strName);
                
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

}
