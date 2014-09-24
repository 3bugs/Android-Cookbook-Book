package com.example.gridviewdemo2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;


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
                /*
                ImageView imgThumb = (ImageView) v;
                Bitmap bitmap = ((BitmapDrawable) imgThumb.getDrawable()).getBitmap();
                
                ImageView imgNormal = new ImageView(MainActivity.this);
                imgNormal.setPadding(16, 16, 16, 16);
                imgNormal.setImageBitmap(bitmap);
                */
                
                ImageView image = new ImageView(MainActivity.this);
                image.setPadding(16, 16, 16, 16);
                image.setImageResource(imagesId[position]);
                
                new AlertDialog.Builder(MainActivity.this)
                    .setView(image)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    })
                    .show();
            }
        });
    }

}
