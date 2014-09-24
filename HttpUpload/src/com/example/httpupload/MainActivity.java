package com.example.httpupload;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.upload_button);
        button.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                try {
                    Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
                    
                    File imageFile = new File(getCacheDir(), "android.png");
                    FileOutputStream fos = new FileOutputStream(imageFile);
                    image.compress(CompressFormat.PNG, 0, fos);
                    fos.flush();
                    fos.close();
                    
                    UploadTask task = new UploadTask(MainActivity.this, imageFile);
                    //task.execute("http://httpbin.org/post");
                    task.execute("http://www.promlert.com/temp/get_file2.php");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
