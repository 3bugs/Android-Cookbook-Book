package com.example.gridviewdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {

    private int[] imagesId = { R.drawable.photo01, R.drawable.photo02,
            R.drawable.photo03, R.drawable.photo04, R.drawable.photo05,
            R.drawable.photo06, R.drawable.photo07, R.drawable.photo08,
            R.drawable.photo09, R.drawable.photo10, R.drawable.photo11,
            R.drawable.photo12 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        GridView grid = (GridView) findViewById(R.id.grid_of_images);
        ImageAdapter adapter = new ImageAdapter(this, imagesId);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String msg = "You have selected image at position "; 
                msg += String.valueOf(position);
                
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
