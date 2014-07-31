package com.example.tabsdemo;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {

    // ชื่อดาวเคราะห์ทั้งหมดในระบบสุริยะ
    protected static final String[] mPlanetTitles = new String[] { "Mercury",
            "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // ออบเจ็ค TabListener สำหรับระบุโค้ดการทำงานเมื่อเกิดอีเวนต์ต่างๆเกี่ยวกับแท็บ
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                // โหลดแฟรกเมนต์ที่แสดงภาพดาวเคราะห์
                String planetTitle = mPlanetTitles[tab.getPosition()];
                showPlanetDetails(planetTitle);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab,
                    FragmentTransaction ft) {
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab,
                    FragmentTransaction ft) {
            }
        };

        // สร้างแท็บตามจำนวนข้อมูลในอาร์เรย์ mPlanetTitles
        for (int i = 0; i < mPlanetTitles.length; i++) {
            // สร้างแท็บใหม่ (ออบเจ็ค ActionBar.Tab)
            Tab tab = actionBar.newTab();
            // กำหนดข้อความและ listener ให้กับแท็บ
            tab.setText(mPlanetTitles[i]);
            tab.setTabListener(tabListener);
            
            // เพิ่มแท็บลงใน ActionBar
            actionBar.addTab(tab);
        }
    }

    // สร้างอินสแทนซ์ของ PlanetDetailsFragment แล้วเพิ่มลงใน layout
    void showPlanetDetails(String planetTitle) {
        PlanetDetailsFragment fragment = PlanetDetailsFragment
                .newInstance(planetTitle);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
