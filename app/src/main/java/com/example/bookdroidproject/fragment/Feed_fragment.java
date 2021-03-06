package com.example.bookdroidproject.fragment;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.bookdroidproject.R;
import com.example.bookdroidproject.adapter.FeedAdapter;
import com.example.bookdroidproject.model.FeedModel;

import java.util.ArrayList;
import java.util.List;

public class Feed_fragment extends Fragment {

    ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.feeds_fragment,container,false);

        return v;
    }

}
