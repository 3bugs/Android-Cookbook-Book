package com.example.swipewithtabsdemo;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends Activity {

    // ชื่อดาวเคราะห์ทั้งหมดในระบบสุริยะ
    protected static final String[] mPlanetTitles = new String[] { "Mercury",
            "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune" };

    PlanetPagerAdapter mAdapter; // pager adapter
    ViewPager mViewPager;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setupViewPager();
        setupTabs();
    }

    private void setupViewPager() {
        /*
         * สร้างออบเจ็คที่เป็น pager adapter (อินสแทนซ์ของ PlanetPagerAdapter)
         * แล้วนำไปกำหนดให้กับ ViewPager
         */
        mAdapter = new PlanetPagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAdapter);

        // ออบเจ็ค OnPageChangeListener สำหรับระบุโค้ดการทำงานเมื่อมีการเปลี่ยนเพจใน ViewPager
        ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // เมื่อเลื่อนไปยังเพจต่างๆ ให้เลือกแท็บที่สัมพันธ์กัน
                getActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                    int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };

        // กำหนดออบเจ็ค OnPageChangeListener ให้กับ ViewPager
        mViewPager.setOnPageChangeListener(pageChangeListener);
    }

    // pager adapter ซึ่งจะส่งอินสแทนซ์ของ PlanetDetailsFragment ไปให้ ViewPager 
    public class PlanetPagerAdapter extends FragmentStatePagerAdapter {
        public PlanetPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            // สร้างแฟรกเมนต์ที่แสดงภาพดาวเคราะห์ แล้วส่งคืนแฟรกเมนต์นี้กลับออกไป
            String planetTitle = mPlanetTitles[i];
            Fragment fragment = PlanetDetailsFragment.newInstance(planetTitle);
            return fragment;
        }

        @Override
        public int getCount() {
            return mPlanetTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mPlanetTitles[position];
        }
    }

    private void setupTabs() {
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // ออบเจ็ค TabListener สำหรับระบุโค้ดการทำงานเมื่อเกิดอีเวนต์ต่างๆเกี่ยวกับแท็บ
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                // เมื่อเลือกแท็บจะกำหนดให้ ViewPager แสดงเพจที่สัมพันธ์กัน
                mViewPager.setCurrentItem(tab.getPosition());
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
}
