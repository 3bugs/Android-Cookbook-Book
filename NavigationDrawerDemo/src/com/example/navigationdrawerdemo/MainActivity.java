package com.example.navigationdrawerdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    // ชื่อดาวเคราะห์ทั้งหมดในระบบสุริยะ
    protected static final String[] mPlanetTitles = new String[] { "Mercury",
            "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune" };
    // อ้างอิงไปยัง root view ของ layout
    private DrawerLayout mDrawerLayout;
    // อ้างอิงไปยัง ListView ที่เป็นแถบ drawer 
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // สร้างและกำหนด adapter ให้กับ ListView (แถบ drawer) เพื่อแสดงรายชื่อดาวเคราะห์ในลิสต์
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, mPlanetTitles));
        // กำหนด click listener ให้กับ ListView
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // ระบุการทำงานเมื่อไอเท็ม (ชื่อดาวเคราะห์) ในลิสต์ถูกคลิกเลือก
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // โหลดแฟรกเมนต์ที่แสดงภาพดาวเคราะห์
                String planetTitle = mPlanetTitles[position];
                showPlanetDetails(planetTitle);
                // ไฮไลท์ไอเท็มที่ถูกเลือกในแถบ drawer
                mDrawerList.setItemChecked(position, true);
                // แสดงชื่อดาวเคราะห์บน title bar
                setTitle(mPlanetTitles[position]);
                // ปิด drawer
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });
    }
    
    // สร้างอินสแทนซ์ของ PlanetDetailsFragment แล้วเพิ่มลงใน layout
    void showPlanetDetails(String planetTitle) {
        PlanetDetailsFragment fragment = PlanetDetailsFragment
                .newInstance(planetTitle);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}
