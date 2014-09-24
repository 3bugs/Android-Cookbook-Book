package com.example.parsexml_sax;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {
    
    public class Person {
        public String name;
        public int age;
        public ArrayList<Person> children;
    }
    
    private StringBuffer buf;
    private Person father;
    private Person child;
    private boolean inPerson = false;
    private boolean inChild = false;
    
    public Person getParsedPerson() {
        return father;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        if (localName.equals("person")) {
            father = new Person();
            father.children = new ArrayList<Person>();
            inPerson = true;
        } else if (localName.equals("child")) {
            child = new Person();
            inChild = true;
        } else if (localName.equals("name") || localName.equals("age")) {
            buf = new StringBuffer();
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (localName.equals("person")) {
            inPerson = false;
        } else if (localName.equals("child")) {
            father.children.add(child);
            inChild = false;
        } else if (localName.equals("name") && (inPerson == true) && (inChild == false)) {
            father.name = buf.toString();
        } else if (localName.equals("age") && (inPerson == true) && (inChild == false)) {
            father.age = Integer.valueOf(buf.toString());
        } else if (localName.equals("name") && (inPerson == true) && (inChild == true)) {
            child.name = buf.toString();
        } else if (localName.equals("age") && (inPerson == true) && (inChild == true)) {
            child.age = Integer.valueOf(buf.toString());
        }
        
        buf = null;
    }
    
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (buf != null) {
            for (int i = start; i < start + length; i++) {
                buf.append(ch[i]);
            }
        }
    }
}
