package com.example.checkphonestatebackground;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    
    Context context;
    
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        
        if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            showToast("Idle");
        } else if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            showToast("Ringing from " + incomingNumber);
        } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            showToast("Offhook");
        }
    }
    
    private void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
