package com.example.parsexml_pull;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MyParserEngine {

    public static class Person {
        public String name;
        public int age;
        public ArrayList<Person> children;
    }

    public static Person parsePersonElement(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        Person father = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            String tagName = parser.getName();

            if (tagName.equals("person")) {
                father = new Person();
                father.children = new ArrayList<Person>();
            } else if (tagName.equals("name")) {
                father.name = readContent(parser);
            } else if (tagName.equals("age")) {
                father.age = Integer.valueOf(readContent(parser));
            } else if (tagName.equals("child")) {
                Person child = parseChildElement(parser);
                father.children.add(child);
            }
        }

        return father;
    }

    private static Person parseChildElement(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        Person child = new Person();

        while (parser.next() != XmlPullParser.END_TAG) {
            String tagName = parser.getName();

            if (tagName.equals("name")) {
                child.name = readContent(parser);
            } else if (tagName.equals("age")) {
                child.age = Integer.valueOf(readContent(parser));
            }
        }

        return child;
    }

    private static String readContent(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.next();
        }
        return result;
    }
}
