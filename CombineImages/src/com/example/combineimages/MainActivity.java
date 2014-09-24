package com.example.combineimages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        File sdCardRoot = Environment.getExternalStorageDirectory();
        
        File file = new File(sdCardRoot, "main.jpg");
        Bitmap mainBitmap = BitmapFactory.decodeFile(file.getAbsolutePath())
                .copy(Bitmap.Config.ARGB_8888, true);
        
        file = new File(sdCardRoot, "sticker.png");
        Bitmap stickerBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        
        Canvas canvas = new Canvas(mainBitmap);
        canvas.drawBitmap(stickerBitmap, 0f, 0f, null);
        
        ImageView image = (ImageView) findViewById(R.id.result_image);
        image.setImageBitmap(mainBitmap);
        
        // ∫—π∑÷°¿“æº≈≈—æ∏Ï≈ß‰ø≈Ï result.jpg „π√Ÿ∑‰¥‡√§∑Õ√’¢Õß SD Card
        try {
            File outFile = new File(sdCardRoot, "result.jpg");
            FileOutputStream fos = new FileOutputStream(outFile);
            mainBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        
            String msg = "File saved to SD card.";
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
