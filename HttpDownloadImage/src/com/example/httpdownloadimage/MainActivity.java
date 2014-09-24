package com.example.httpdownloadimage;

import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.download_button);
        button.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                DownloadImageTask task = new DownloadImageTask();
                task.execute("http://promlert.com/wp-content/uploads/2013/04/android_complete.jpg");
            }
        });
    }

    private Bitmap downloadImage(String strUrl) {
        Bitmap bmpResult = null;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bmpResult = BitmapFactory.decodeStream(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmpResult;
    }
    
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    
        @Override
        protected Bitmap doInBackground(String... urls) {
            return downloadImage(urls[0]);
        }
    
        @Override
        protected void onPostExecute(Bitmap result) {
            ImageView imageview = (ImageView) findViewById(R.id.image);
            imageview.setImageBitmap(result);
        }
    }
}
