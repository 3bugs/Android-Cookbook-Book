package com.example.httppost;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.post_button);
        button.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ArrayList<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
                
                data.add(new BasicNameValuePair("name", "Promlert"));
                data.add(new BasicNameValuePair("lastname", "Lovichit"));
                data.add(new BasicNameValuePair("age", "38"));
        
                PostTask task = new PostTask(MainActivity.this, data);
                task.execute("http://httpbin.org/post");
            }
        });
    }
}
