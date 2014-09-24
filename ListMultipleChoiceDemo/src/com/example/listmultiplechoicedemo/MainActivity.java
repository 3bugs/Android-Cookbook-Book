package com.example.listmultiplechoicedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
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
    
        ListView list = getListView();
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked, countries);
        setListAdapter(adapter);
        
        /*
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ListView list = getListView();
                String msg = "Selected items:";
    
                for (int i = 0; i < list.getCount(); i++) {
                    if (list.isItemChecked(i)) {
                        msg += "\n" + list.getItemAtPosition(i);
                    }
                }
    
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
        */
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String msg;
        CheckedTextView check = (CheckedTextView) v;
    
        if (check.isChecked()) {
            msg = "You have selected " + check.getText();
        }
        else {
            msg = "You have deselected " + check.getText();
        }
        msg += " at position " + String.valueOf(position);
        
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    
}
