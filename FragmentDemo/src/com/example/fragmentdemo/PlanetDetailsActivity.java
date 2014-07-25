package com.example.fragmentdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

public class PlanetDetailsActivity extends Activity {

    protected static final String PLANET_TITLE = "planet_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_details);

        Intent intent = getIntent();
        String planetTitle = intent.getStringExtra(PLANET_TITLE);
        setTitle(planetTitle);

        /*
         * ถ้าหากแอคทิวิตีถูกสร้างขึ้นครั้งแรก
         * (ไม่ได้ถูกสร้างหลังจากเพิ่งถูกทำลาย
         * เนื่องจากมีการเปลี่ยนคอนฟิกของเครื่อง เช่น ผู้ใช้หมุนจอ เป็นต้น)
         * เราจะสร้างอินสแทนซ์ ของ PlanetDetailsFragment แล้วเพิ่มลงใน layout
         * 
         * ส่วนกรณีที่แอคทิวิตีถูกทำลายแล้วสร้างขึ้นใหม่นั้น
         * แอนดรอยด์จะสร้างอินสแทนซ์ของ PlanetDetailsFragment ให้ใหม่
         * แล้วเพิ่มลงใน layout ให้อัตโนมัติ เราจึงไม่ต้อง ทำอะไร
         */
        if (savedInstanceState == null) {
            PlanetDetailsFragment fragment = PlanetDetailsFragment
                    .newInstance(planetTitle);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.details_fragment_container, fragment);
            transaction.commit();
        }
    }
}
