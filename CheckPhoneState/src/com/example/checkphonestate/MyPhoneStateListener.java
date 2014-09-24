package com.example.checkphonestate;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyPhoneStateListener extends PhoneStateListener {
    Context context;

    public MyPhoneStateListener(Context context) {
        this.context = context;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        
        switch (state) {
        case TelephonyManager.CALL_STATE_IDLE:
            showToast("Idle");
            break;
        case TelephonyManager.CALL_STATE_RINGING:
            showToast("Ringing from " + incomingNumber);
            break;
        case TelephonyManager.CALL_STATE_OFFHOOK:
            showToast("Offhook");
            break;
        }
    }
    
    private void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
