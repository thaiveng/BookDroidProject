package com.example.bookdroidproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bookdroidproject.All_User_Feed_activity;
import com.example.bookdroidproject.Post_Feed_Activity;
import com.example.bookdroidproject.R;
import com.example.bookdroidproject.adapter.BooksAdapter;
import com.example.bookdroidproject.adapter.FeedAdapter;
import com.example.bookdroidproject.adapter.FeedUserAdpater;
import com.example.bookdroidproject.model.Booksmodel;
import com.example.bookdroidproject.model.FeedModel;
import com.example.bookdroidproject.model.FeedUserModel;

import java.util.ArrayList;
import java.util.List;

public class Feed_fragment extends Fragment {
    RecyclerView recyclerViewBookFeed;
    RecyclerView recyclerViewUserFeed;
    FeedAdapter feedAdapter;
    FeedUserAdpater feedUserAdpater;
    List<FeedModel> feedBookList;
    List<FeedUserModel> feedUserList;
    FloatingActionButton floatingActionButton;


    RelativeLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feeds_fragment,container,false);


        feedBookList = new ArrayList<FeedModel>();
        feedUserList = new ArrayList<FeedUserModel>();


       recyclerViewBookFeed = view.findViewById(R.id.recycler_feed);


        recyclerViewBookFeed.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));


        FeedModel feedModel1 = new FeedModel("Title1","This is a title1",R.drawable.sport_book);
        FeedModel feedModel2 = new FeedModel("Title2","This is a title2",R.drawable.sport_book);
        FeedModel feedModel3 = new FeedModel("Title3","This is a title3",R.drawable.sport_book);
        FeedModel feedModel4 = new FeedModel("Title4","This is a title4",R.drawable.sport_book);
        FeedModel feedModel5 = new FeedModel("Title5","This is a title5",R.drawable.sport_book);


        feedBookList.add(feedModel1);
        feedBookList.add(feedModel2);
        feedBookList.add(feedModel3);
        feedBookList.add(feedModel4);
        feedBookList.add(feedModel5);

        feedAdapter = new FeedAdapter(feedBookList,getActivity());

        recyclerViewBookFeed.setAdapter(feedAdapter);




        recyclerViewUserFeed = view.findViewById(R.id.recycler_user_feed);

        recyclerViewUserFeed.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));


        FeedUserModel feedUserModel1 = new FeedUserModel("Uorn Tay","1000 followers","100 recommendatin",R.drawable.user_profile);
        FeedUserModel feedUserModel2 = new FeedUserModel("Uorn Ty","2000 followers","200 recommendatin",R.drawable.user_profile);
        FeedUserModel feedUserModel3 = new FeedUserModel("Porn Dara","3000 followers","300 recommendatin",R.drawable.user_profile);
        FeedUserModel feedUserModel4 = new FeedUserModel("Aun Chounry","4000 followers","400 recommendatin",R.drawable.user_profile);
        FeedUserModel feedUserModel5 = new FeedUserModel("Thol Thaiveng","5000 followers","500 recommendatin",R.drawable.user_profile);


        feedUserList.add(feedUserModel1);
        feedUserList.add(feedUserModel2);
        feedUserList.add(feedUserModel3);
        feedUserList.add(feedUserModel4);
        feedUserList.add(feedUserModel5);


        feedUserAdpater = new FeedUserAdpater(feedUserList,getActivity());


        recyclerViewUserFeed.setAdapter(feedUserAdpater);


        floatingActionButton = view.findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), Post_Feed_Activity.class);

                startActivity(intent);


            }
        });


        relativeLayout = view.findViewById(R.id.rel_all_btn_feed);


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getActivity(),"You click all feed",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), All_User_Feed_activity.class);

                startActivity(intent);
            }
        });

        return view;
    }
}
