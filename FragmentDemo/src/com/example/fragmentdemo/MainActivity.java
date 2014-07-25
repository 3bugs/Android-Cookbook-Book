package com.example.fragmentdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.example.fragmentdemo.PlanetListFragment.OnPlanetListSelectedListener;

public class MainActivity extends Activity implements
        OnPlanetListSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onPlanetListSelected(int position) {
        String planetTitle = PlanetListFragment.mPlanetTitles[position];

        if (findViewById(R.id.details_fragment_container) == null) {
            Intent intent = new Intent(this, PlanetDetailsActivity.class);
            intent.putExtra(PlanetDetailsActivity.PLANET_TITLE, planetTitle);
            startActivity(intent);
        } else {
            PlanetDetailsFragment fragment = PlanetDetailsFragment
                    .newInstance(planetTitle);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.details_fragment_container, fragment);
            transaction.commit();
        }
    }
}
