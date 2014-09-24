package com.example.notificationdemo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

        Button btnNotifyUser = (Button) findViewById(R.id.notify_user_button);
        btnNotifyUser.setOnClickListener(new View.OnClickListener() {
        
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 0,
                        intent, 0);
        
                Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_dialog_email);
                
                mNotifyBuilder = new NotificationCompat.Builder(MainActivity.this)
                        .setTicker("New mail!")
                        .setContentTitle("New mail!")
                        .setContentText("You've received mail from someone ;-)")
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setLargeIcon(largeIcon)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true);
        
                Notification notification = mNotifyBuilder.build();

                mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotifyManager.notify(NOTIFY_ID, notification);
            }
        });

        Button btnUpdateNotification = (Button) findViewById(R.id.update_notification_button);
        btnUpdateNotification.setOnClickListener(new View.OnClickListener() {
    
            @Override
            public void onClick(View v) {
                mNotifyBuilder.setContentText("This text is updated.");
                Notification notification = mNotifyBuilder.build();
                
                mNotifyManager.notify(NOTIFY_ID, notification);
            }
        });

        Button btnCancelNotification = (Button) findViewById(R.id.cancel_notification_button);
        btnCancelNotification.setOnClickListener(new View.OnClickListener() {
    
            @Override
            public void onClick(View v) {
                mNotifyManager.cancel(NOTIFY_ID);
            }
        });
    }
}
