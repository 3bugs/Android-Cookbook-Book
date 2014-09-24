package com.example.resizeimage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MainActivity extends Activity {

    ImageView sourceImage;
    ImageView resizedImage;
    private File sdCardRoot;
    private File file;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sourceImage = (ImageView) findViewById(R.id.source_image);
        sdCardRoot = Environment.getExternalStorageDirectory();
        file = new File(sdCardRoot, "family.jpg");
        
        Bitmap sourceBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        sourceImage.setImageBitmap(sourceBitmap);
        
        resizedImage = (ImageView) findViewById(R.id.resized_image);
        Button button = (Button) findViewById(R.id.resize_button);
        button.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Bitmap resizedBitmap = decodeAndResize(file);
                resizedImage.setImageBitmap(resizedBitmap);
            
                try {
                    File outFile = new File(sdCardRoot, "family_resized.jpg");
                    FileOutputStream fos = new FileOutputStream(outFile);
                    resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.close();
                    
                    String msg = "File saved to SD card.";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private Bitmap decodeAndResize(File file) {
        Bitmap bitmap = null;
        try {
            /*
            BitmapFactory.Options option = new BitmapFactory.Options();
            option.inJustDecodeBounds = true;
    
            FileInputStream fis = new FileInputStream(file);
            BitmapFactory.decodeStream(fis, null, option);
            fis.close();
            int scale = 1;
            if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
                scale = (int)Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE / 
                   (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
            }
            */
    
            BitmapFactory.Options option = new BitmapFactory.Options();
            option.inSampleSize = 2;
            FileInputStream fis = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(fis, null, option);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
