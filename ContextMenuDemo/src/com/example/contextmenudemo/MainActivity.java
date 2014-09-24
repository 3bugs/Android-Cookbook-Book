package com.example.contextmenudemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageView image = (ImageView) findViewById(R.id.image);
        Button button = (Button) findViewById(R.id.button);
        
        registerForContextMenu(image);
        registerForContextMenu(button);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        
        MenuInflater inflater = getMenuInflater();
        switch (v.getId()) {
        case R.id.image:
            inflater.inflate(R.menu.context_menu_image, menu);
            break;
        case R.id.button:
            inflater.inflate(R.menu.context_menu_button, menu);
            break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        TextView tv = (TextView) findViewById(R.id.text);
    
        switch (item.getItemId()) {
        case R.id.action_image_item1:
            tv.setText("You selected Item1 on Image");
            return true;
        case R.id.action_image_item2:
            tv.setText("You selected Item2 on Image");
            return true;
            
        case R.id.action_button_item1:
            tv.setText("You selected Item1 on Button");
            return true;
        case R.id.action_button_item2:
            tv.setText("You selected Item2 on Button");
            return true;
        case R.id.action_button_item3:
            tv.setText("You selected Item3 on Button");
            return true;
            
        default:
            return super.onContextItemSelected(item);
        }
    }
    
}
