package com.example.getphoneinfo;

import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextView txtPhoneNumber = (TextView) findViewById(R.id.phone_number);
        TextView txtSimId = (TextView) findViewById(R.id.sim_id);
        TextView txtImeiNumber = (TextView) findViewById(R.id.imei_number);
        TextView txtOtherInfo = (TextView) findViewById(R.id.other_info);
        
        TelephonyManager telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        
        String phoneNumber = telephony.getLine1Number();
        if (phoneNumber != null) {
            txtPhoneNumber.setText(phoneNumber);
        }
        
        String simId = telephony.getSimSerialNumber();
        if (simId != null) {
            txtSimId.setText(simId);
        }
        
        String imeiNumber = telephony.getDeviceId();
        if (imeiNumber != null) {
            txtImeiNumber.setText(imeiNumber);
        }
        
        String text = "Operator name: " + telephony.getNetworkOperatorName() + "\n";
        text += "SIM operator name: " + telephony.getSimOperatorName();
        txtOtherInfo.setText(text);
    }

}
