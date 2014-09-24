package com.example.customspinnerdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CountryAdapter extends ArrayAdapter<Country> {

    private Context context;
    private int itemLayoutId;
    private Country[] countries = null;

    public CountryAdapter(Context context, int itemLayoutId, Country[] countries) {
        super(context, itemLayoutId, countries);
        
        this.context = context;
        this.itemLayoutId = itemLayoutId;
        this.countries = countries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View item = inflater.inflate(itemLayoutId, parent, false);
        
        ImageView imgFlag = (ImageView) item.findViewById(R.id.flag_image);
        TextView txtCountryName = (TextView) item.findViewById(R.id.country_name);
        
        Country country = countries[position];
        imgFlag.setImageResource(country.getFlagImageId());
        txtCountryName.setText(country.getCountryName());
        
        return item;
    }
    
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

}
