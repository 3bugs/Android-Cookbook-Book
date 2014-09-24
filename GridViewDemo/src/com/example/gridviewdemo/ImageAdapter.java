package com.example.gridviewdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private int[] imagesId; 

    public ImageAdapter(Context context, int[] imagesId) {
        this.context = context;
        this.imagesId = imagesId;
    }

    @Override
    public int getCount() {
        return imagesId.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView image;

        if (convertView == null) {
            image = new ImageView(context);

            image.setLayoutParams(new GridView.LayoutParams(150, 150));
            image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            image.setPadding(8, 8, 8, 8);
        } else {
            image = (ImageView) convertView;
        }

        image.setImageResource(imagesId[position]);
        return image;
    }

}