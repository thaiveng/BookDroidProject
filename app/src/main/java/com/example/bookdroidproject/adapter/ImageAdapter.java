package com.example.bookdroidproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.example.bookdroidproject.R;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private Integer[] imgID;

    // Constructor
    public ImageAdapter(Context context, Integer[] imgID) {
        this.context = context;
        this.imgID = imgID;
    }

    public int getCount() {
        return imgID.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context,R.layout.grid_view,null);

        ImageView imageView = view.findViewById(R.id.imageView);

        imageView.setImageResource(imgID[position]);

        return view;
    }
}