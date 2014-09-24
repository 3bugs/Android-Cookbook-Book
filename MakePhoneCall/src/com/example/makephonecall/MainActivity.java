package com.example.makephonecall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMakeCall = (Button) findViewById(R.id.make_call_button);
        btnMakeCall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TelephonyManager telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                
                if (telephony.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE) {
                    String msg = "Sorry, this device can't make phone calls!";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
            
                TextView txtPhoneNumber = (TextView) findViewById(R.id.phone_number);
                String uri = "tel:" + txtPhoneNumber.getText();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
                startActivity(intent);
            }
        });
    }
}
