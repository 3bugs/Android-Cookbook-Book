package com.example.spinnerdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends Activity {

    static final String[] countries = new String[] { "Brunei", "Cambodia",
        "Indonesia", "Laos", "Malaysia", "Myanmar (Burma)", "Philippines",
        "Singapore", "Thailand", "Vietnam" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final TextView text = (TextView) findViewById(R.id.text);
        final Spinner spinner = (Spinner) findViewById(R.id.list_of_countries);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, countries);
        spinner.setAdapter(adapter);
        
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> av, View v, int position,
                    long id) {
                String msg = "You have selected " + ((TextView) v).getText();
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
                msg += (String) spinner.getSelectedItem();
                text.setText(msg);
            }
        });
    }

}
