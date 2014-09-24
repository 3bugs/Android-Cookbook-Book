package com.example.activitydemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 回枇 Display Second Activity
        Button btnDisplaySecondActivity = (Button) findViewById(R.id.display_second_activity_button);
        btnDisplaySecondActivity.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
        
        // 回枇 Send Data to Second Activity
        Button btnSendData = (Button) findViewById(R.id.send_data_button);
        btnSendData.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);

                i.putExtra("user1", "Nooknet");
                i.putExtra("age1", 8);
                i.putExtra("user2", "Numnahm");
                i.putExtra("age2", 5);

                Bundle bundle = new Bundle();
                bundle.putString("user3", "Tonyod");
                bundle.putInt("age3", 3);
                bundle.putString("user4", "Yui");
                bundle.putInt("age4", 41);
                i.putExtras(bundle);
                
                startActivity(i);
            }
        });

        // 回枇 Send Object to Second Activity
        Button btnSendObject = (Button) findViewById(R.id.send_object_button);
        btnSendObject.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                MyUser user = new MyUser();
                user.setName("Nooknet");
                user.setAge(8);

                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("someuser", user);
                startActivity(i);
            }
        });
        
        // 回枇 Send Multiple Objects (Using ArrayList)
        Button btnSendMultipleObjects = (Button) findViewById(R.id.send_multiple_objects_button);
        btnSendMultipleObjects.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ArrayList<MyUser> list = new ArrayList<MyUser>();
                MyUser user;
                
                user = new MyUser();
                user.setName("Nooknet");
                user.setAge(8);
                list.add(user);

                user = new MyUser();
                user.setName("Numnahm");
                user.setAge(5);
                list.add(user);
                
                user = new MyUser();
                user.setName("Tonyod");
                user.setAge(3);
                list.add(user);

                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("allusers", list);
                startActivity(i);
            }
        });        
    }
}
