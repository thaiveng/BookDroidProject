package com.example.bookdroidproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.bookdroidproject.adapter.UserFollowingAdapter;
import com.example.bookdroidproject.fragment.UserProfileFragment;
import com.example.bookdroidproject.model.User_Following_model;

import java.util.ArrayList;
import java.util.List;

public class UserFollowingActivity extends AppCompatActivity {


    private ListView listView;
    private Toolbar toolbar;


    private UserFollowingAdapter userFollowingAdapter;
    private List<User_Following_model> listUserFollowing;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_following);


        toolbar = findViewById(R.id.toolBar_User_Following);
        listView = findViewById(R.id.list_view_ScreenUserFollowing);

        listUserFollowing = new ArrayList<User_Following_model>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tay");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        User_Following_model user_following_model1 = new User_Following_model(R.drawable.user_profile,"Tay");
        User_Following_model user_following_model2 = new User_Following_model(R.drawable.user_profile,"Rattanak");
        User_Following_model user_following_model3 = new User_Following_model(R.drawable.user_profile,"Chounry");
        User_Following_model user_following_model4 = new User_Following_model(R.drawable.user_profile,"Dara");
        User_Following_model user_following_model5 = new User_Following_model(R.drawable.user_profile,"Thaiveng");


        listUserFollowing.add(user_following_model1);
        listUserFollowing.add(user_following_model2);
        listUserFollowing.add(user_following_model3);
        listUserFollowing.add(user_following_model4);
        listUserFollowing.add(user_following_model5);


        userFollowingAdapter = new UserFollowingAdapter(getApplicationContext(),listUserFollowing);


        listView.setAdapter(userFollowingAdapter);


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(getApplicationContext(), Activity_books.class);
                intent.putExtra("INDEX_ITEM_BOTTOM_NAVIGATION_VIEW",4);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
