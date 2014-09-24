package com.example.preferencedemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        final SharedPreferences settings =
                PreferenceManager.getDefaultSharedPreferences(this);
        
        Button btnDisplayValues = (Button) findViewById(R.id.display_values_button);
        btnDisplayValues.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                String name = settings.getString("namePref", "");
                boolean showTips = settings.getBoolean("tipsPref", false);
                String favoriteColor = settings.getString("colorPref", ""); 
                boolean useGPS = settings.getBoolean("gpsPref", false);
                boolean useNetwork = settings.getBoolean("networkPref", false);
                
                TextView text = (TextView) findViewById(R.id.text);
                
                String msg = "Name: %s\nShow Tips: %b\nFavorite Color: %s\nUse GPS Location: %b\nUse Network Location: %b\n";
                text.setText(String.format(msg, name, showTips, favoriteColor, useGPS, useNetwork));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.settings_menu:
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

}
