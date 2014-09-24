package com.example.customlistviewdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ListActivity;

public class MainActivity extends ListActivity {

    static final String[] countries = new String[] { "Brunei", "Cambodia",
        "Indonesia", "Laos", "Malaysia", "Myanmar (Burma)", "Philippines",
        "Singapore", "Thailand", "Vietnam" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
    
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.item, R.id.country_name, countries);
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
