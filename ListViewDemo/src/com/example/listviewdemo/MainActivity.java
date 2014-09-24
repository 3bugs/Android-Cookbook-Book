package com.example.listviewdemo;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.view.View;

public class MainActivity extends Activity {

    static final String[] countries = new String[] { "Brunei", "Cambodia",
            "Indonesia", "Laos", "Malaysia", "Myanmar (Burma)", "Philippines",
            "Singapore", "Thailand", "Vietnam" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView) findViewById(R.id.text);
        ListView list = (ListView) findViewById(R.id.list_of_countries);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, countries);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position,
                    long id) {
                String msg = "You have selected " + ((TextView) v).getText();
                msg += " at position " + String.valueOf(position);

                text.setText(msg);
            }
        });
    }

}
