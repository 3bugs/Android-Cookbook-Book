package com.example.parsexml_pull;

import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parsexml_pull.MyParserEngine.Person;

public class MainActivity extends Activity {

    private static final String XML_STRING = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" 
            + "<person><name>พร้อมเลิศ</name><age>38</age>"
            + "<child><name>ต้นยอด</name><age>3</age></child>"
            + "<child><name>หน่ำน้ำ</name><age>5</age></child>"
            + "<child><name>นุกเน็ต</name><age>8</age></child>" + "</person>";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textview = (TextView) findViewById(R.id.text);
        Person person = null;
        String msg = "";
        
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(XML_STRING));
            person = MyParserEngine.parsePersonElement(parser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (person != null) {
            msg = String.format("%s อายุ %d ปี\n", person.name, person.age);
            int childrenCount = person.children.size();
            msg += String.format("%s มีบุตร %d คน ได้แก่\n\n", person.name,
                    childrenCount);
        
            for (int i = 0; i < childrenCount; i++) {
                Person child = person.children.get(i);
                msg += String.format("%s อายุ %d ขวบ\n", child.name, child.age);
            }
        
            textview.setText(msg);
        }
    }
}
