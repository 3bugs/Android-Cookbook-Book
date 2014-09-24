package com.example.autoanswercall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;

public class AutoAnswerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            String incomingNumber = intent
                    .getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if (incomingNumber.equals("7890")) {
                Intent i = new Intent(Intent.ACTION_MEDIA_BUTTON);
                i.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(
                        KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
                context.sendOrderedBroadcast(i, null);
            }
        }
    }
}
