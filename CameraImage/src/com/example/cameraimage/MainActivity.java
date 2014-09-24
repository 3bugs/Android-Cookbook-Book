package com.example.cameraimage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private static final int TAKE_PICTURE = 100;
    private static final int TAKE_PICTURE_SAVE = 101;
    private File imageFile;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnCapture = (Button) findViewById(R.id.capture_button);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_PICTURE);
            }
        });

        Button btnCaptureSave = (Button) findViewById(R.id.capture_and_save_button);
        btnCaptureSave.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageFile = new File(Environment.getExternalStorageDirectory(), "my_image.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                startActivityForResult(intent, TAKE_PICTURE_SAVE);
            }
        });
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView image = (ImageView) findViewById(R.id.image);
    
        if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK) {
            Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(capturedImage);
        } else if (requestCode == TAKE_PICTURE_SAVE && resultCode == RESULT_OK) {
            try {
                FileInputStream fis = new FileInputStream(imageFile);
                //Bitmap fullSizeImage = BitmapFactory.decodeStream(fis);
                
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap fullSizeImage = BitmapFactory.decodeStream(fis, null, options);
                
                image.setImageBitmap(fullSizeImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } 
    }

}
