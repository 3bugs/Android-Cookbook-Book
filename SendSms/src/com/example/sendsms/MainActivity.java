package com.example.sendsms;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final EditText etPhoneNumber = (EditText) findViewById(R.id.phone_number);
        final EditText etMessage = (EditText) findViewById(R.id.message);
        
        Button btnSendSms = (Button) findViewById(R.id.send_sms_button);
        btnSendSms.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                String phoneNumber = etPhoneNumber.getText().toString();
                String message = etMessage.getText().toString();
                
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phoneNumber, null, message, null, null);
            }
        });
    }

}
