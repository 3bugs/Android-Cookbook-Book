package com.example.listactivitydemo;

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
                android.R.layout.simple_list_item_1, countries);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String msg = "You have selected " + ((TextView) v).getText();
        msg += " at position " + String.valueOf(position);

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    
}
