package com.example.contextmenulistviewdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private String[] contacts = { "Nooknet", "Numnahm", "Tonyod" };
    private String[] actions = { "Call", "Send message", "Edit", "Delete" };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.listitem, contacts);
        list.setAdapter(adapter);

        registerForContextMenu(list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.list) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(contacts[info.position]);

            for (int i = 0; i < actions.length; i++) {
                menu.add(Menu.NONE, i, i, actions[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int actionIndex = item.getItemId();
        String actionName = actions[actionIndex];
    
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String contactName = contacts[info.position];
    
        TextView text = (TextView) findViewById(R.id.text);
        text.setText(String.format("Selected %s for item %s", actionName,
                contactName));
        return true;
    }
}
