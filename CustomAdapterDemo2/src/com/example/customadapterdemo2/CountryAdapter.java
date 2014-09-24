package com.example.customadapterdemo2;

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
    private Country countries[] = null;

    public CountryAdapter(Context context, int itemLayoutId, Country[] countries) {
        super(context, itemLayoutId, countries);
        
        this.itemLayoutId = itemLayoutId;
        this.context = context;
        this.countries = countries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View item = inflater.inflate(itemLayoutId, parent, false);
        
        ImageView imgFlag = (ImageView) item.findViewById(R.id.flag_image);
        TextView txtCountryName = (TextView) item.findViewById(R.id.country_name);
        TextView txtCountryOfficialName = (TextView) item.findViewById(R.id.country_official_name);
        TextView txtCapitalName = (TextView) item.findViewById(R.id.capital_name);
        
        Country country = countries[position];
        imgFlag.setImageResource(country.getFlagImageId());
        txtCountryName.setText(country.getCountryName());
        txtCountryOfficialName.setText(country.getCountryOfficialName());
        txtCapitalName.setText(country.getCapitalName());
        
        return item;

        /* *******************************************************************************
         * Re-use item's layout
         * *******************************************************************************
        View item = convertView;
        CountryHolder holder = null;

        if (item == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            item = inflater.inflate(itemLayoutId, parent, false);

            holder = new CountryHolder();
            holder.imgFlag = (ImageView) item.findViewById(R.id.flag_image);
            holder.txtCountryName = (TextView) item.findViewById(R.id.country_name);
            holder.txtCountryOfficialName = (TextView) item.findViewById(R.id.country_official_name);
            holder.txtCapitalName = (TextView) item.findViewById(R.id.capital_name);

            item.setTag(holder);
        } else {
            holder = (CountryHolder) item.getTag();
        }

        Country country = countries[position];
        holder.imgFlag.setImageResource(country.getFlagImageId());
        holder.txtCountryName.setText(country.getCountryName());
        holder.txtCountryOfficialName.setText(country.getCountryOfficialName());
        holder.txtCapitalName.setText(country.getCapitalName());

        return item;
        ******************************************************************************** */
    }

    /*
    static class CountryHolder {
        ImageView imgFlag;
        TextView txtCountryName;
        TextView txtCountryOfficialName;
        TextView txtCapitalName;
    }
    */
}
