package com.example.mysqldemo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    EditText etPersonName;
    Button btnAddPerson;
    ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPersonName = (EditText) findViewById(R.id.person_name);
        btnAddPerson = (Button) findViewById(R.id.add_button);
        btnAddPerson.setOnClickListener(this);
        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        
        getAllData();
    }

    @Override
    public void onClick(View v) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String strDate = dateFormat.format(new Date());

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("pName", etPersonName.getText().toString()));
        params.add(new BasicNameValuePair("pDate", strDate));
        
        String url = "http://www.promlert.com/temp/insert.php";
        InsertDeleteTask task = new InsertDeleteTask(this, url, params);
        task.execute();
        
        getAllData();
        etPersonName.setText(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        String rowId = ((TextView) v.findViewById(R.id.item_id)).getText().toString();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("_id", rowId));
        
        String url = "http://www.promlert.com/temp/delete.php";
        InsertDeleteTask task = new InsertDeleteTask(this, url, params);
        task.execute();

        getAllData();
    }
    
    private void getAllData() {
        String url = "http://www.promlert.com/temp/select_all.php";
        SelectTask task = new SelectTask(this, url);
        task.execute();
    }

}