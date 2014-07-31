package com.example.swipeviewsdemo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
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
                setTitle(mAdapter.getPageTitle(position));
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
        // แสดงชื่อดาวเคราะห์แรกบน title bar ในตอนเริ่มต้น
        pageChangeListener.onPageSelected(0);
    }

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
}
