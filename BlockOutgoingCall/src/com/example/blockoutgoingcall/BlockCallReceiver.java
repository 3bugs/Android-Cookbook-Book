package com.example.blockoutgoingcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BlockCallReceiver extends BroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        String outgoingNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        
        if (outgoingNumber.equals("12345")) {
            setResultData(null);
            showToast("This call is not allowed!");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
