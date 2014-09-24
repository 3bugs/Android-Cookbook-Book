package com.example.geocoderdemo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tvAddress = (TextView) findViewById(R.id.address);
        
        Button btnGetAddress = (Button) findViewById(R.id.get_address_button);
        btnGetAddress.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                GetAddressTask task = new GetAddressTask(MainActivity.this);
                task.execute(13.85187, 100.56752);
            }
        });
    }

    private class GetAddressTask extends AsyncTask<Double, Void, String> {
        Context mContext;

        public GetAddressTask(Context context) {
            super();
            mContext = context;
        }

        @Override
        protected String doInBackground(Double... params) {
            double latitude = params[0];
            double longitude = params[1];

            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
            List<Address> addressList = null;
            
            try {
                addressList = geocoder.getFromLocation(latitude, longitude, 10);
            } catch (IOException e) {
                e.printStackTrace();
                return "Error: IO Exception trying to get address";
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return "Error: Invalid latitude/longitude";
            }
            
            if (addressList != null && addressList.size() > 0) {
                String addressText = "";
                
                for (Address address: addressList) {
                    addressText += String.format(
                            "%s | %s | %s",
                            // street address
                            address.getMaxAddressLineIndex() > 0 ? address
                                    .getAddressLine(0) : "",
                            // city
                            address.getLocality(),
                            // country
                            address.getCountryName());
                    addressText += "\n\n";
                }
                return addressText;
            } else {
                return "No address found";
            }
        }
        
        @Override
        protected void onPostExecute(String address) {
            // Display the results of the lookup.
            tvAddress.setText(address);
        }
        
    }
}
