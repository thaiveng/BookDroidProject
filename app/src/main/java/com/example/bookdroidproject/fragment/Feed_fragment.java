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
        View v = inflater.inflate(R.layout.activity__feeds,container,false);


        List<FeedModel> model_list = new ArrayList<>();
        listView = v.findViewById(R.id.feed_list_view);


        FeedModel model = new FeedModel("Title1","​Description1" ,R.drawable.book);//វិទ្យុ​អា​ស៊ី​សេរី
        model_list.add(model);
        model = new FeedModel("Title2","​Description2" ,R.drawable.book);
        model_list.add(model);

        model = new FeedModel("Title3","​Description3" ,R.drawable.book);//វិទ្យុ​សំលេង​អាមេរិិក​
        model_list.add(model);
        model = new FeedModel("Title4","​Description4" ,R.drawable.book);//វិទ្យុ​អេ​ប៊ី​ស៊ី​កម្ពុុជា​
        model_list.add(model);
        model = new FeedModel("Title5","​Description5" ,R.drawable.book);
        model_list.add(model);


        FeedAdapter feedAdapter = new FeedAdapter(getContext(),model_list);

        listView.setAdapter(feedAdapter);

        return v;
    }

}
