package com.example.bookdroidproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookdroidproject.adapter.PageAdapter;
import com.example.bookdroidproject.fragment.Feed_fragment;
import com.example.bookdroidproject.fragment.Notification_fragment;
import com.example.bookdroidproject.fragment.Post_fragment;
import com.example.bookdroidproject.fragment.Store_fragement;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

public class Activity_books extends AppCompatActivity {


    private List<String> lastSearches;
    private MaterialSearchBar searchBar;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private ActionBarDrawerToggle toggle;
    private PageAdapter adapter;

    SearchView searchView;

    RecyclerView r1,r2;
    TextView tvRecommend,tvTrending;
    RelativeLayout rel1,rel2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);


        // call this method to find id of each element
        initView();

        initToolBar();

        // call this method to make action when click on each tab
        setUpViewPager();

        // call this method to make action when click on each icon of bottom navigation
        setActionBottomNavigationView();

        setUpNavigationView();

    }


    // method to set up tool bar
    private void initToolBar(){

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

    }



    // method to find each element id
    private void initView(){


        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigationView);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    private void setUpNavigationView(){

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_main);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.item_profile:
                        Toast.makeText(getApplicationContext(),"You are selected profile",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.item_about_us:
                        Toast.makeText(getApplicationContext(),"You are selected about us",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.item_term_of_use:
                        Toast.makeText(getApplicationContext(),"You are selected term of use",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.item_signout:
                        Toast.makeText(getApplicationContext(),"You are selected signout",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;
                }

                return false;
            }
        });
    }

    // method to setup viewpager when click on each tab
    private void setUpViewPager(){
        final ViewPager touchView = findViewById(R.id.view_pager);
        touchView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        adapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // set action to bottom navigation view

    // set action to bottom navigation view

    private void setActionBottomNavigationView(){

        bottomNavigationView = findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.books:
                        Toast.makeText(getApplicationContext(),"You are selected book icon",Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.feeds:
                        loadFragment(new Feed_fragment());
                        r1 = findViewById(R.id.recycler_home_reco);
                        r2 = findViewById(R.id.recycler_home_tre);

                        tvRecommend = findViewById(R.id.tv_recommend);
                        tvTrending = findViewById(R.id.tv_trending);

                        rel1 = findViewById(R.id.all_books_click);
                        rel2 = findViewById(R.id.all_books_click_trending);

                        r1.setVisibility(View.GONE);
                        r2.setVisibility(View.GONE);

                        tvTrending.setVisibility(View.GONE);
                        tvRecommend.setVisibility(View.GONE);

                        rel1.setVisibility(View.GONE);
                        rel2.setVisibility(View.GONE);
                        return true;


                    case R.id.posts:
                        loadFragment(new Post_fragment());
                        r1 = findViewById(R.id.recycler_home_reco);
                        r2 = findViewById(R.id.recycler_home_tre);

                        tvRecommend = findViewById(R.id.tv_recommend);
                        tvTrending = findViewById(R.id.tv_trending);

                        rel1 = findViewById(R.id.all_books_click);
                        rel2 = findViewById(R.id.all_books_click_trending);

                        r1.setVisibility(View.GONE);
                        r2.setVisibility(View.GONE);

                        tvTrending.setVisibility(View.GONE);
                        tvRecommend.setVisibility(View.GONE);

                        rel1.setVisibility(View.GONE);
                        rel2.setVisibility(View.GONE);

                        return true;


                    case R.id.notification:
                        loadFragment(new Notification_fragment());
                        r1 = findViewById(R.id.recycler_home_reco);
                        r2 = findViewById(R.id.recycler_home_tre);

                        tvRecommend = findViewById(R.id.tv_recommend);
                        tvTrending = findViewById(R.id.tv_trending);

                        rel1 = findViewById(R.id.all_books_click);
                        rel2 = findViewById(R.id.all_books_click_trending);

                        r1.setVisibility(View.GONE);
                        r2.setVisibility(View.GONE);

                        tvTrending.setVisibility(View.GONE);
                        tvRecommend.setVisibility(View.GONE);

                        rel1.setVisibility(View.GONE);
                        rel2.setVisibility(View.GONE);
                        return true;


                    case R.id.store:
                       loadFragment(new Store_fragement());
                        r1 = findViewById(R.id.recycler_home_reco);
                        r2 = findViewById(R.id.recycler_home_tre);

                        tvRecommend = findViewById(R.id.tv_recommend);
                        tvTrending = findViewById(R.id.tv_trending);

                        rel1 = findViewById(R.id.all_books_click);
                        rel2 = findViewById(R.id.all_books_click_trending);

                        r1.setVisibility(View.GONE);
                        r2.setVisibility(View.GONE);

                        tvTrending.setVisibility(View.GONE);
                        tvRecommend.setVisibility(View.GONE);

                        rel1.setVisibility(View.GONE);
                        rel2.setVisibility(View.GONE);
                        return true;
                }
                return false;
            }
        });

    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tf = fm.beginTransaction();
        tf.replace(R.id.container_home,fragment);
        tf.commit();
    }

}
