package com.example.notificationprogressdemo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    static final int NOTIFY_ID = 0;
    NotificationCompat.Builder mNotifyBuilder;
    NotificationManager mNotifyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnNotifyUser = (Button) findViewById(R.id.notify_user_progress_button);
        btnNotifyUser.setOnClickListener(new View.OnClickListener() {
        
            @Override
            public void onClick(View v) {
                mNotifyBuilder = new NotificationCompat.Builder(
                        MainActivity.this).setContentTitle("File Download")
                        .setContentText("Download in progress")
                        .setSmallIcon(android.R.drawable.ic_popup_sync);
        
                mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        
                Thread thread = new Thread(new Runnable() {
                    
                    @Override
                    public void run() {
                        for (int i = 1; i <= 20; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
        
                            mNotifyBuilder.setProgress(20, i, false);
                            Notification notification = mNotifyBuilder.build();
                            mNotifyManager.notify(NOTIFY_ID, notification);
                        }
        
                        mNotifyBuilder.setContentText("Download complete")
                                .setProgress(0, 0, false);
                        Notification notification = mNotifyBuilder.build();
                        mNotifyManager.notify(NOTIFY_ID, notification);
                    }
                });
                
                thread.start();
            }
        });
    }

}
