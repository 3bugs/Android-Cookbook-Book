package com.example.popupmenudemo;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        Button btnPost = (Button) findViewById(R.id.post_button);
        btnPost.setOnClickListener(new View.OnClickListener() {
    
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, v);
                
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.popup_menu, popup.getMenu());
    
                popup.setOnMenuItemClickListener(new MyPopupHandler());
    
                popup.show();
            }
        });
    }

    private class MyPopupHandler implements PopupMenu.OnMenuItemClickListener {
    
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            TextView tv = (TextView) findViewById(R.id.text);
            
            switch (item.getItemId()) {
            case R.id.action_post_status:
                tv.setText("You choose to post status.");
                return true;
            case R.id.action_post_picture:
                tv.setText("You choose to post picture.");
                return true;
            default:
                return false;
            }
        }
    }

}
