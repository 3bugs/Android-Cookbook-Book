package com.example.receivesms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] msgs = new SmsMessage[pdus.length];
            String str = "";

            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                
                if (i == 0) {
                    str = "SMS from " + msgs[i].getOriginatingAddress();
                    str += "\n--------------------\n";
                }
                str += msgs[i].getMessageBody();
            }
            
            Toast.makeText(context, str, Toast.LENGTH_LONG).show();
        }
    }

}
