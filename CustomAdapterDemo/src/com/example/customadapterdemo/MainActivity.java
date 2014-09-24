package com.example.customadapterdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ListActivity;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
    
        Country countries[] = new Country[] {
                new Country(R.drawable.brunei, "Brunei"),
                new Country(R.drawable.cambodia, "Cambodia"),
                new Country(R.drawable.indonesia, "Indonesia"),
                new Country(R.drawable.laos, "Laos"),
                new Country(R.drawable.malaysia, "Malaysia"),
                new Country(R.drawable.myanmar, "Myanmar (Burma)"),
                new Country(R.drawable.philippines, "Philippines"),
                new Country(R.drawable.singapore, "Singapore"),
                new Country(R.drawable.thailand, "Thailand"),
                new Country(R.drawable.vietnam, "Vietnam"), };
    
        CountryAdapter adapter = new CountryAdapter(this, R.layout.item,
                countries);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        TextView tv = (TextView) v.findViewById(R.id.country_name);
    
        String msg = "You have selected " + tv.getText();
        msg += " at position " + String.valueOf(position);
    
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
