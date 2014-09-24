package com.example.imagesgallery;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;

public class MainActivity extends Activity {

    private int[] imagesId = { R.drawable.photo01, R.drawable.photo02,
            R.drawable.photo03, R.drawable.photo04, R.drawable.photo05,
            R.drawable.photo06, R.drawable.photo07, R.drawable.photo08,
            R.drawable.photo09, R.drawable.photo10, R.drawable.photo11,
            R.drawable.photo12 };

    private ImageView imgOldSelected;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout gallery = (LinearLayout) findViewById(R.id.images_gallery);
        final ImageView imgFullSize = (ImageView) findViewById(R.id.full_size_image);
        
        for (int id : imagesId) {
            ImageView image = new ImageView(this);
            image.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
            image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            image.setPadding(8, 8, 8, 8);
            image.setImageResource(id);
            image.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    ImageView imgSelected = (ImageView) v;
                    Bitmap bitmap = ((BitmapDrawable) imgSelected.getDrawable()).getBitmap();
                    imgFullSize.setImageBitmap(bitmap);
                    
                    if (imgOldSelected != null) {
                        imgOldSelected.setBackgroundColor(Color.TRANSPARENT);
                    }
                    imgSelected.setBackgroundColor(Color.YELLOW);
                    imgOldSelected = imgSelected;
                }
            });
            
            gallery.addView(image);
        }
    }
    
}
