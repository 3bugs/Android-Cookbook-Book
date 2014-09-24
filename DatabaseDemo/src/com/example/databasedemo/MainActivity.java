package com.example.databasedemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    EditText etPersonName;
    Button btnAddPerson;
    ListView list;

    MyDbHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    SimpleCursorAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPersonName = (EditText) findViewById(R.id.person_name);
        btnAddPerson = (Button) findViewById(R.id.add_button);
        btnAddPerson.setOnClickListener(this);
        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(this);

        dbHelper = new MyDbHelper(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        db = dbHelper.getWritableDatabase();
        String[] queryColumns = new String[] { "_id", MyDbHelper.COL_NAME,
                MyDbHelper.COL_DATE };
        cursor = db.query(MyDbHelper.TABLE_NAME, queryColumns, null, null,
                null, null, null);
        
        String[] showColumns = new String[] { MyDbHelper.COL_NAME,
                MyDbHelper.COL_DATE };
        int[] views = new int[] { android.R.id.text1, android.R.id.text2 };
        
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item, cursor, showColumns, views);
        list.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        cursor.close();
        db.close();
    }

    @Override
    public void onClick(View v) {
        ContentValues cv = new ContentValues();
        cv.put(MyDbHelper.COL_NAME, etPersonName.getText().toString());

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        cv.put(MyDbHelper.COL_DATE, dateFormat.format(new Date()));

        db.insert(MyDbHelper.TABLE_NAME, null, cv);
        cursor.requery();
        adapter.notifyDataSetChanged();
        etPersonName.setText(null);
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        cursor.moveToPosition(position);
        String rowId = cursor.getString(0);
        db.delete(MyDbHelper.TABLE_NAME, "_id = ?", new String[] { rowId });
        cursor.requery();
        adapter.notifyDataSetChanged();
    }

}