package com.example.databasedemo;

import java.util.Date;
import java.text.SimpleDateFormat;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 2;

    public static final String TABLE_NAME = "people";
    public static final String COL_NAME = "pName";
    public static final String COL_DATE = "pDate";

    private static final String STRING_CREATE =
        "CREATE TABLE " + TABLE_NAME 
        + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + COL_NAME + " TEXT, " + COL_DATE + " DATE);";

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create the database table
        db.execSQL(STRING_CREATE);
        
        //You may also load initial values into the database here
        ContentValues cv = new ContentValues();
        
        cv.put(COL_NAME, "Promlert Lovichit");
        //Create a formatter for SQL date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cv.put(COL_DATE, dateFormat.format(new Date())); //Insert 'now' as the date

        db.insert(TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //For now, clear the database and re-create
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}