package com.example.fragmentdemo;

import java.util.Locale;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PlanetDetailsFragment extends Fragment {

    private static final String ARG_PLANET_TITLE = "planet_title";
    private String mPlanetTitle;
    
    public static PlanetDetailsFragment newInstance(String planetTitle) {
        PlanetDetailsFragment fragment = new PlanetDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PLANET_TITLE, planetTitle);
        fragment.setArguments(args);
        
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPlanetTitle = getArguments().getString(ARG_PLANET_TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_planet_details, container, false);
        ImageView planetImage = (ImageView) layout.findViewById(R.id.planet_image);

        int imageId = getResources().getIdentifier(mPlanetTitle.toLowerCase(Locale.getDefault()),
                "drawable", getActivity().getPackageName());
        planetImage.setImageResource(imageId);
        
        return layout;
    }
    
}
