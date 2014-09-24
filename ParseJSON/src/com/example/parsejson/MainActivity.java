package com.example.parsejson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String JSON_STRING =
            "{\"person\":{\"name\":\"พร้อมเลิศ\",\"age\":30,\"children\":["
            + "{\"name\":\"ต้นยอด\",\"age\":3},"
            + "{\"name\":\"หน่ำน้ำ\",\"age\":5},"
            + "{\"name\":\"นุกเน็ต\",\"age\":8}"
            + "]}}";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textview = (TextView) findViewById(R.id.text);
        String msg = "";
        
        try {
            JSONObject json = new JSONObject(JSON_STRING);
            JSONObject person = json.getJSONObject("person");
            
            String personName = person.getString("name");
            int personAge = person.getInt("age");
            msg = String.format("%s อายุ %d ปี\n", personName, personAge);
            
            JSONArray children = person.getJSONArray("children");
            int childrenCount = children.length();
            msg += String.format("%s มีบุตร %d คน ได้แก่\n\n", personName, childrenCount);

            for (int i = 0; i < childrenCount; i++) {
                JSONObject child = children.getJSONObject(i);
                String childName = child.getString("name");
                int childAge = child.getInt("age");
                
                msg += String.format("%s อายุ %d ขวบ\n", childName, childAge);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        textview.setText(msg);
    }
}
