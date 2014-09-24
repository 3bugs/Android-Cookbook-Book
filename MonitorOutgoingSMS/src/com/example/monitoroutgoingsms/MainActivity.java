package com.example.monitoroutgoingsms;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {

    MyContentObserver observer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        observer = new MyContentObserver(new Handler(), this);
        Uri uri = Uri.parse("content://sms");
        getContentResolver().registerContentObserver(uri, true, observer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(observer);
    }
    
}
