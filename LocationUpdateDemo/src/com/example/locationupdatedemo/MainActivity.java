package com.example.locationupdatedemo;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity implements
        GooglePlayServicesClient.ConnectionCallbacks, 
        GooglePlayServicesClient.OnConnectionFailedListener,
        LocationListener {

    private LocationClient mLocationClient;
    private GoogleMap map;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        map = mapFragment.getMap();

        mLocationClient = new LocationClient(this, this, this);
        
        final LocationRequest request = LocationRequest.create();
        request.setInterval(5000);
        request.setFastestInterval(1000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        final Button btnStartUpdate = (Button) findViewById(R.id.start_update_button);
        final Button btnStopUpdate = (Button) findViewById(R.id.stop_update_button);

        btnStartUpdate.setEnabled(true);
        btnStopUpdate.setEnabled(false);

        btnStartUpdate.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if (mLocationClient.isConnected()) {
                    mLocationClient.requestLocationUpdates(request, MainActivity.this);
                    btnStartUpdate.setEnabled(false);
                    btnStopUpdate.setEnabled(true);
                } else {
                    String msg = "Can't connect to location services, try again later.";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                }
            }
        });

        btnStopUpdate.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if (mLocationClient.isConnected()) {
                    mLocationClient.removeLocationUpdates(MainActivity.this);
                    btnStartUpdate.setEnabled(true);
                    btnStopUpdate.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLocationClient.connect();
    }
    
    @Override
    protected void onStop() {
        mLocationClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle data) {
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnected() {
        Toast.makeText(this, "Disconnected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        map.clear();

        double lat = location.getLatitude();
        double lng = location.getLongitude();
        
        LatLng currentLatLng = new LatLng(lat, lng);
        map.animateCamera(CameraUpdateFactory.newLatLng(currentLatLng));
        map.addMarker(new MarkerOptions()
                .position(currentLatLng)
        );
    }

}
