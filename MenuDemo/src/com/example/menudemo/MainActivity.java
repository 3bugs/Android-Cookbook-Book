package com.example.menudemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView tv = (TextView) findViewById(R.id.text);
        
        switch (item.getItemId()) {
        case R.id.action_add:
            tv.setText("You selected Add");
            return true;
        case R.id.action_edit:
            tv.setText("You selected Edit");
            return true;
        case R.id.action_save:
            tv.setText("You selected Save");
            return true;
        case R.id.action_close:
            tv.setText("You selected Close");
            return true;
        case R.id.action_share:
            tv.setText("You selected Share");
            return true;
        case R.id.action_search:
            tv.setText("You selected Search");
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

}
