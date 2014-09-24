package com.example.sendsmsgetfeedback;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    final String SENT = "message_sent";
    final String RECEIVED = "message_received";

    PendingIntent sentIntent, receivedIntent;
    BroadcastReceiver sentReceiver, receivedReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sentIntent = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        receivedIntent = PendingIntent.getBroadcast(this, 0, new Intent(
                RECEIVED), 0);

        final EditText etPhoneNumber = (EditText) findViewById(R.id.phone_number);
        final EditText etMessage = (EditText) findViewById(R.id.message);

        Button btnSendSms = (Button) findViewById(R.id.send_sms_button);
        btnSendSms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String phoneNumber = etPhoneNumber.getText().toString();
                String message = etMessage.getText().toString();

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phoneNumber, null, message, sentIntent, receivedIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    
        sentReceiver = new BroadcastReceiver() {
    
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                case Activity.RESULT_OK:
                    showToast("Message sent");
                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    showToast("Message not sent: Generic failure");
                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                    showToast("Message not sent: No service");
                    break;
                case SmsManager.RESULT_ERROR_NULL_PDU:
                    showToast("Message not sent: Null PDU");
                    break;
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                    showToast("Message not sent: Radio off");
                    break;
                }
            }
        };
        
        receivedReceiver = new BroadcastReceiver() {
    
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                case Activity.RESULT_OK:
                    showToast("Message received");
                    break;
                case Activity.RESULT_CANCELED:
                    showToast("Message not received");
                    break;
                }
            }
        };
        
        registerReceiver(sentReceiver, new IntentFilter(SENT));
        registerReceiver(receivedReceiver, new IntentFilter(RECEIVED));
    }

    @Override
    protected void onPause() {
        super.onPause();
    
        unregisterReceiver(sentReceiver);
        unregisterReceiver(receivedReceiver);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
