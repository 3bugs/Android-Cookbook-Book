package com.example.fragmentdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

public class PlanetDetailsActivity extends Activity {

    protected static final String PLANET_TITLE = "planet_title";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_details);
        
        Intent intent = getIntent();
        String planetTitle = intent.getStringExtra(PLANET_TITLE);
        setTitle(planetTitle);
        
        if (savedInstanceState == null) {
            PlanetDetailsFragment fragment = PlanetDetailsFragment.newInstance(planetTitle);
            
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.details_fragment_container, fragment);
            transaction.commit();
        }
    }
}
