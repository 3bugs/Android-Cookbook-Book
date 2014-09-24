package com.example.mapdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends Activity implements InfoWindowAdapter {

    private static final LatLng CHIANGMAI = new LatLng(18.701224, 98.789770);
    private static final LatLng PHUKET = new LatLng(7.966598, 98.359929);
    
    private static final LatLng CHONBURI = new LatLng(13.36114, 100.98467);
    private static final LatLng BANGSAEN_BEACH = new LatLng(13.29466, 100.90582);
    private static final LatLng KHAO_KHEOW_OPEN_ZOO = new LatLng(13.21498, 101.05598);
    private static final LatLng SIRACHA_TIGER_ZOO = new LatLng(13.14873, 101.01256);
    
    private static final LatLng KASETSART = new LatLng(13.85187, 100.56752);
    
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        map = mapFragment.getMap();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.chiangmai_menu:
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(CHIANGMAI, 15));
            map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
            return true;
        case R.id.phuket_menu:
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(PHUKET)
                    .zoom(15)
                    .bearing(270)
                    .tilt(30)
                    .build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            return true;
        case R.id.chonburi_menu:
            CameraPosition cameraChonburi = new CameraPosition.Builder()
                    .target(CHONBURI)
                    .zoom(10)
                    .bearing(0)
                    .tilt(0)
                    .build();
            map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraChonburi));
            addMarker();
            return true;
        case R.id.kasetsart_menu:
            CameraPosition cameraKasetsart = new CameraPosition.Builder()
                    .target(KASETSART)
                    .zoom(16)
                    .bearing(0)
                    .tilt(0)
                    .build();
            map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraKasetsart));
            drawOnMap();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void addMarker() {
        map.addMarker(new MarkerOptions()
                .position(CHONBURI)
                .title(getString(R.string.chonburi_title))
                .snippet(getString(R.string.chonburi_snippet))
        );
        
        map.addMarker(new MarkerOptions()
                .position(BANGSAEN_BEACH)
                .title(getString(R.string.bangsaen_title))
                .snippet(getString(R.string.bangsaen_snippet))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        );
        
        map.addMarker(new MarkerOptions()
                .position(KHAO_KHEOW_OPEN_ZOO)
                .title(getString(R.string.kk_zoo_title))
                .snippet(getString(R.string.kk_zoo_snippet))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.kk_zoo_logo))
        );
        
        map.addMarker(new MarkerOptions()
                .position(SIRACHA_TIGER_ZOO)
                .title(getString(R.string.tg_zoo_title))
                .snippet(getString(R.string.tg_zoo_snippet))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        );
        
        map.setInfoWindowAdapter(this);
    }

    @Override
    public View getInfoContents(Marker marker) {
        if (marker.getTitle().equals(getString(R.string.tg_zoo_title))) {
            LayoutInflater inflater = getLayoutInflater();
            View infoWindow = inflater.inflate(R.layout.info_window, null);
            
            ImageView icon = (ImageView) infoWindow.findViewById(R.id.icon);
            icon.setImageResource(R.drawable.tg_zoo_logo);
            
            TextView tv = (TextView) infoWindow.findViewById(R.id.title);
            tv.setText(marker.getTitle());
    
            tv = (TextView) infoWindow.findViewById(R.id.snippet);
            tv.setText(marker.getSnippet());
    
            return infoWindow;
        } else {
            return null;
        }
    }
    
    @Override
    public View getInfoWindow(Marker marker) {
        if (marker.getTitle().equals(getString(R.string.kk_zoo_title))) {
            LayoutInflater inflater = getLayoutInflater();
            View infoWindow = inflater.inflate(R.layout.info_window, null);
            
            LinearLayout layout = (LinearLayout) infoWindow.findViewById(R.id.layout);
            layout.setBackgroundResource(R.drawable.myshape);
            
            ImageView icon = (ImageView) infoWindow.findViewById(R.id.icon);
            icon.setImageResource(R.drawable.kk_zoo_logo);
            
            TextView tv = (TextView) infoWindow.findViewById(R.id.title);
            tv.setText(marker.getTitle());
    
            tv = (TextView) infoWindow.findViewById(R.id.snippet);
            tv.setText(marker.getSnippet());
    
            return infoWindow;
        } else {
            return null;
        }
    }
    
    private void drawOnMap() {
        PolylineOptions line = new PolylineOptions()
            .add(
                new LatLng(13.85243, 100.56449), 
                new LatLng(13.85197, 100.56539),
                new LatLng(13.85212, 100.56583),
                new LatLng(13.85278, 100.56621),
                new LatLng(13.85290, 100.56653),
                new LatLng(13.85287, 100.56803),
                new LatLng(13.85254, 100.56885),
                new LatLng(13.85254, 100.56906),
                new LatLng(13.85299, 100.56909),
                new LatLng(13.85303, 100.56980))
            .width(3)
            .color(Color.RED);
        map.addPolyline(line);
        
        map.addMarker(new MarkerOptions()
                .position(new LatLng(13.85243, 100.56449))
                .title(getString(R.string.line_begin))
        );
        map.addMarker(new MarkerOptions()
                .position(new LatLng(13.85303, 100.56980))
                .title(getString(R.string.line_end))
        );
        
        PolygonOptions area = new PolygonOptions()
            .add(
                new LatLng(13.85275, 100.56654), 
                new LatLng(13.85054, 100.56651), 
                new LatLng(13.85050, 100.56943), 
                new LatLng(13.85245, 100.56950), 
                new LatLng(13.85245, 100.56883), 
                new LatLng(13.85279, 100.56799))
            .strokeWidth(3)
            .strokeColor(Color.BLUE)
            .fillColor(Color.argb(64, 0, 0, 255));
        map.addPolygon(area);
    }
}
