package com.example.webimageview;

import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;

public class WebImageView extends ImageView {
    
    private Drawable mPlaceholder;
    private Drawable mImage;

    public WebImageView(Context context) {
        this(context, null);
    }

    public WebImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebImageView(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
    }

    public void setPlaceholderImage(Drawable drawable) {
        mPlaceholder = drawable;
        if (mImage == null) {
            setImageDrawable(mPlaceholder);
        }
    }

    public void setPlaceholderImage(int resid) {
        mPlaceholder = getResources().getDrawable(resid);
        if (mImage == null) {
            setImageDrawable(mPlaceholder);
        }
    }

    public void setImageUrl(String url) {
        DownloadTask task = new DownloadTask();
        task.execute(url);
    }

    private class DownloadTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap bmpResult = null;
            String strUrl = urls[0];
            try {
                URL url = new URL(strUrl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                bmpResult = BitmapFactory.decodeStream(con.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bmpResult;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            mImage = new BitmapDrawable(getResources(), result);
            if (mImage != null) {
                setImageDrawable(mImage);
            }
        }
    }
}