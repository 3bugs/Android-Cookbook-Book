package com.example.monitoroutgoingsms;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;

public class MyContentObserver extends ContentObserver {

    private Context context;
    private int smsCount;

    public MyContentObserver(Handler handler, Context context) {
        super(handler);
        this.context = context;
        
        Uri SmsUri = Uri.parse("content://sms/sent");
        Cursor c = context.getContentResolver().query(SmsUri, null, null,
                null, null);
        smsCount = c.getCount();
        c.close();
    }

    @Override
    public void onChange(boolean selfChange) {
        Uri SmsUri = Uri.parse("content://sms/sent");
        String[] columns = { "address", "date", "body", "type" };

        Cursor c = context.getContentResolver().query(SmsUri, columns, null,
                null, null);
        c.moveToNext();

        if (c.getCount() > smsCount) {
            smsCount = c.getCount();
            
            String text = "Recipient: "
                    + c.getString(c.getColumnIndex(columns[0]));
            text += "\nDate: " + c.getString(c.getColumnIndex(columns[1]));
            text += "\nMessage: " + c.getString(c.getColumnIndex(columns[2]));
            text += "\nType: " + c.getString(c.getColumnIndex(columns[3]));
            text += "\n--------------------";
            text += "\nSMS Count: " + String.valueOf(smsCount);

            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        }
        c.close();
    }

    @Override
    public boolean deliverSelfNotifications() {
        return true;
    }
}
