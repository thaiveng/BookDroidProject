package com.example.bookdroidproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bookdroidproject.fragment.Mindset_category_fragment;
import com.example.bookdroidproject.fragment.Novel_category_fragment;
import com.example.bookdroidproject.fragment.Mathematic_category_fragment;
import com.example.bookdroidproject.fragment.Progeamming_category_fragment;
import com.example.bookdroidproject.fragment.Sport_category_fragment;
import com.example.bookdroidproject.fragment.Tourist_category_fragment;

public class PageAdapter extends FragmentPagerAdapter {

    int numTab;
    public PageAdapter(FragmentManager fm, int numTab) {
        super(fm);
        this.numTab = numTab;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new Novel_category_fragment();
            case 1:
                return new Sport_category_fragment();
            case 2:
                return new Progeamming_category_fragment();
            case 3:
                return new Mindset_category_fragment();
            case 4:
                return new Tourist_category_fragment();
            case 5:
                return new Mathematic_category_fragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numTab;
    }
}
