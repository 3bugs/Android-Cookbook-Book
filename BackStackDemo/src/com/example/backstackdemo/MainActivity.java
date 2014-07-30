package com.example.backstackdemo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    private static final int MENU_ADD_FRAGMENT = 1;
    /*
     * หมายเลขแฟรกเมนต์ที่เรากำหนดขึ้นเอง
     * ซึ่งเป็นเลขจำนวนเต็มที่เพิ่มขึ้นเรื่อยๆ ในแต่ละครั้งที่อินสแทนซ์ของ
     * CountingFragment ถูกสร้างขึ้น
     */
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // สร้างคำสั่ง Add Fragment ในเมนู
        MenuItem item = menu.add(1, MENU_ADD_FRAGMENT, 0, "Add Fragment");
        // แสดงคำสั่งนี้บน action bar ถ้ามีที่ว่างพอ
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        // เมื่อเลือกคำสั่ง Add Fragment จากเมนู
        case MENU_ADD_FRAGMENT:
            addFragmentToStack();
            return true;
        default:
            return true;
        }
    }

    void addFragmentToStack() {
        mCount++;
        // สร้างอินสแทนซ์ของ CountingFragment โดยส่ง mCount ไปเป็นพารามิเตอร์
        Fragment fragment = CountingFragment.newInstance(mCount);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        // เพิ่มแฟรกเมนต์ที่เพิ่งสร้างลงใน FrameLayout
        ft.replace(R.id.fragment_container, fragment);
        // กำหนดแอนิเมชั่นในการแสดงแฟรกเมนต์ใหม่
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        /*
         * บันทึกการดำเนินการเกี่ยวกับแฟรกเมนต์ลงใน back stack
         * เพื่อให้สามารถใช้ปุ่ม Back ย้อนกลับไปยังแฟรกเมนต์ก่อนหน้าได้
         */
        ft.addToBackStack(null);
        ft.commit();
    }
}
