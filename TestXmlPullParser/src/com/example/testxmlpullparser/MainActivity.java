package com.example.testxmlpullparser;

import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        try {
            test();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void test() throws XmlPullParserException, IOException {
        TextView textview = (TextView) findViewById(R.id.text);

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser parser = factory.newPullParser();

        parser.setInput(new StringReader("<foo>Hello World!</foo>"));
        int eventType = parser.getEventType();
        String msg = "";

        while (true) {
            if (eventType == XmlPullParser.START_DOCUMENT) {
                msg += "eventType: START_DOCUMENT\n";
            } else if (eventType == XmlPullParser.END_DOCUMENT) {
                msg += "eventType: END_DOCUMENT\n";
            } else if (eventType == XmlPullParser.START_TAG) {
                msg += "eventType: START_TAG\n";
            } else if (eventType == XmlPullParser.END_TAG) {
                msg += "eventType: END_TAG\n";
            } else if (eventType == XmlPullParser.TEXT) {
                msg += "eventType: TEXT\n";
            }
            msg += "name: " + parser.getName() + "\n";
            msg += "text: " + parser.getText() + "\n\n";
            textview.setText(msg);
            
            if (eventType == XmlPullParser.END_DOCUMENT) {
                break;
            }
            else { 
                eventType = parser.next();
            }
        }
    }

}
