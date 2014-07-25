package com.example.fragmentdemo;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlanetListFragment extends ListFragment {

    // ชื่อดาวเคราะห์ทั้งหมดในระบบสุริยะ
    protected static final String[] mPlanetTitles = new String[] { "Mercury",
            "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune" };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // สร้าง adapter และกำหนด adapter ให้กับ ListView ของแฟรกเมนต์
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, mPlanetTitles);
        setListAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ให้แสดงสถานะการเลือกใน ListView กรณีเป็น layout สำหรับจอใหญ่
        if (getActivity().findViewById(R.id.details_fragment_container) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    private OnPlanetListSelectedListener mCallback;

    public interface OnPlanetListSelectedListener {
        public void onPlanetListSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnPlanetListSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnPlanetListSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallback.onPlanetListSelected(position);
        getListView().setItemChecked(position, true);
    }
}
