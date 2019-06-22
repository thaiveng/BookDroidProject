package com.example.bookdroidproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.bookdroidproject.adapter.AllUserFeedAdapter;
import com.example.bookdroidproject.fragment.Feed_fragment;
import com.example.bookdroidproject.model.FeedUserModel;

import java.util.ArrayList;
import java.util.List;

public class All_User_Feed_activity extends AppCompatActivity {


    RecyclerView recyclerViewAllUserFeed;
    AllUserFeedAdapter allUserFeedAdapter;
    List<FeedUserModel> listAllUserFeed;


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_feed);


        initToolBar();

        listAllUserFeed = new ArrayList<FeedUserModel>();


        recyclerViewAllUserFeed = findViewById(R.id.recyler_all_user_feed);


        recyclerViewAllUserFeed.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));


        FeedUserModel feedUserModel1 = new FeedUserModel("Uorn Tay","1000 followers","100 recommendatin",R.drawable.user_profile);
        FeedUserModel feedUserModel2 = new FeedUserModel("Uorn Ty","2000 followers","200 recommendatin",R.drawable.user_profile);
        FeedUserModel feedUserModel3 = new FeedUserModel("Porn Dara","3000 followers","300 recommendatin",R.drawable.user_profile);
        FeedUserModel feedUserModel4 = new FeedUserModel("Aun Chounry","4000 followers","400 recommendatin",R.drawable.user_profile);
        FeedUserModel feedUserModel5 = new FeedUserModel("Thol Thaiveng","5000 followers","500 recommendatin",R.drawable.user_profile);

        listAllUserFeed.add(feedUserModel1);
        listAllUserFeed.add(feedUserModel2);
        listAllUserFeed.add(feedUserModel3);
        listAllUserFeed.add(feedUserModel4);
        listAllUserFeed.add(feedUserModel5);


        allUserFeedAdapter = new AllUserFeedAdapter(listAllUserFeed,getApplicationContext());


        recyclerViewAllUserFeed.setAdapter(allUserFeedAdapter);

    }


    private void initToolBar(){
        toolbar = findViewById(R.id.toolBar_all_user_feed);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All User Feed");
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here


                Intent intent = new Intent(getApplicationContext(), Activity_books.class);
                intent.putExtra("INDEX_ITEM_BOTTOM_NAVIGATION_VIEW",1);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
