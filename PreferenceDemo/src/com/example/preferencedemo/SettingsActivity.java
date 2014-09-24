package com.example.preferencedemo;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settings);
        addPreferencesFromResource(R.xml.settings);
        
        SharedPreferences settings =
                PreferenceManager.getDefaultSharedPreferences(this);
        settings.registerOnSharedPreferenceChangeListener(this);
        
        displayCurrentName(settings);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("namePref")) {
            displayCurrentName(sharedPreferences);
            Toast.makeText(this, "Name changed", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void displayCurrentName(SharedPreferences sharedPreferences) {
        Preference namePref = findPreference("namePref");
        String msg = "Current name: " + sharedPreferences.getString("namePref", ""); 
        namePref.setSummary(msg);
    }
}
