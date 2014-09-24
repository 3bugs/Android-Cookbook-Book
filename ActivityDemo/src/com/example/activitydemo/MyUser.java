package com.example.activitydemo;

import java.io.Serializable;

public class MyUser implements Serializable {
    private String m_name;
    private int m_age;
    
    public void setName(String name) {
        m_name = name;
    }
    
    public String getName() {
        return m_name;
    }
    
    public void setAge(int age) {
        m_age = age;
    }
    
    public int getAge() {
        return m_age;
    }
}
