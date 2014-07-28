package com.example.backstackdemo;

import java.util.Random;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CountingFragment extends Fragment {
    private static final String ARG_NUM = "num";
    private static final String ARG_BACKGROUND_COLOR = "background_color";

    private int mNum;
    private int mBackgroundColor;

    public static CountingFragment newInstance(int num) {
        CountingFragment fragment = new CountingFragment();

        Bundle args = new Bundle();
        // เก็บหมายเลข (ค่า mCount ที่ส่งมาจาก MainActivity) เป็นอาร์กิวเมนต์ของแฟรกเมนต์
        args.putInt(ARG_NUM, num);

        // สุ่มค่าสีที่จะกำหนดเป็นพื้นหลังของแฟรกเมนต์
        Random rnd = new Random();
        int randomColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256),
                rnd.nextInt(256));
        // เก็บค่าสีเป็นอาร์กิวเมนต์ของแฟรกเมนต์ 
        args.putInt(ARG_BACKGROUND_COLOR, randomColor);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // อ่านหมายเลขและค่าสีจากอาร์กิวเมนต์ มากำหนดให้ตัวแปร (ฟีลด์) ในคลาส
        mNum = getArguments().getInt(ARG_NUM);
        mBackgroundColor = getArguments().getInt(ARG_BACKGROUND_COLOR);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_counting, container,
                false);
        layout.setBackgroundColor(mBackgroundColor);
        TextView tv = (TextView) layout.findViewById(R.id.text);
        tv.setText("Fragment #" + mNum);

        return layout;
    }
}
