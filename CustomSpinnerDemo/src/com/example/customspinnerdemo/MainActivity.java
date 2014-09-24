package com.example.customspinnerdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final TextView text = (TextView) findViewById(R.id.text);
        final Spinner spinner = (Spinner) findViewById(R.id.list_of_countries);
        
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
        spinner.setAdapter(adapter);
        
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> av, View v, int position,
                    long id) {
                TextView tv = (TextView) v.findViewById(R.id.country_name);
                
                String msg = "You have selected " + tv.getText();
                msg += " at position " + String.valueOf(position);
                
                text.setText(msg);
            }
        
            @Override
            public void onNothingSelected(AdapterView<?> av) {
                return;
            }
        });
        
        Button button = (Button) findViewById(R.id.show_selected_item_button);
        button.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                String msg = "Selected item: ";
                Country selected = (Country) spinner.getSelectedItem();
                msg += selected.getCountryName();
                text.setText(msg);
            }
        });
    }

}
