package com.example.bookdroidproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.bookdroidproject.adapter.BooksAdapter;
import com.example.bookdroidproject.adapter.PageAdapter;
import com.example.bookdroidproject.fragment.Feed_fragment;
import com.example.bookdroidproject.fragment.Notification_fragment;
import com.example.bookdroidproject.fragment.Post_fragment;
import com.example.bookdroidproject.fragment.UserProfileFragment;
import com.example.bookdroidproject.model.Booksmodel;
import com.mancj.materialsearchbar.MaterialSearchBar;
import java.util.ArrayList;
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


    Context context;

    RecyclerView recyclerViewHome,recyclerViewHometrend;
    BooksAdapter adapterBooks;
    List<Booksmodel> booksmodelList,booksmodelList1;
    LinearLayout btnAll,btnAllTrend;


    SessionManager sessionManager;
    LinearLayout linearLayout_containter_home;
    LinearLayout linearLayout_container_borrow_buy_book;
    LinearLayout linearLayout_container_following_follower;


    // declare variable to show or hide view
    LinearLayout linearLayout_container_book_home;
    ViewPager viewPager_eacbtab;
    LinearLayout linearLayout_recommend;
    LinearLayout linearLayout_trending;
    RecyclerView recyclerView_reco;
    RecyclerView recyclerView_tre;
    LinearLayout tab_btnBorrow_btnBuy;


    // declare button to find all borrow book and all buy book
    Button btnBorrow;
    Button btnBuy;



    int index_bottom_navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);


        linearLayout_container_following_follower = findViewById(R.id.linear_container_following_follower);

        linearLayout_containter_home = findViewById(R.id.container_home);
        viewPager_eacbtab = findViewById(R.id.view_pager);
        tab_btnBorrow_btnBuy = findViewById(R.id.tab_layout_secondary);


        btnBorrow = findViewById(R.id.btn_borrow);
        btnBuy = findViewById(R.id.btn_buy);

        // call method to check if log in

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();



        // call this method to find id of each element
        initView();



        // method to create toolbar
        initToolBar();



        // call this method to make action when click on each tab
        setUpViewPager();



        //this method to show navigation view and action when click on each item
        setUpNavigationView();


        Intent intent = getIntent();

        index_bottom_navigation = intent.getIntExtra("INDEX_ITEM_BOTTOM_NAVIGATION_VIEW",0);


        selectBottomNavigationOption(index_bottom_navigation);
        Log.e("Index BTN ",""+index_bottom_navigation);


        // this method to show bottom navigation view and action when click on each item
        setActionBottomNavigationView();

    }






    //------------------------ method to set up tool bar -----------------------//

    private void initToolBar(){

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

    }

    //************************************************************************//





    //-------------------- method to find each element id ---------------------//

    private void initView(){

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_main);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigationView);
        toolbar = findViewById(R.id.toolBar);

    }

    //************************************************************************//



    //---------------------------------------- method to set up navigation view ------------------------------------//

    private void setUpNavigationView(){


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.item_store:
                        Toast.makeText(getApplicationContext(),"You are selected store",Toast.LENGTH_SHORT).show();
                        tab_btnBorrow_btnBuy.setVisibility(View.GONE);
                        viewPager_eacbtab.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.item_about_us:

                        Intent intent_about_us = new Intent(Activity_books.this,Activity_About_Us.class);
                        startActivity(intent_about_us);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.item_term_of_use:
                        Intent intent_term_of_use = new Intent(Activity_books.this,Activity_Term_Of_Use.class);
                        startActivity(intent_term_of_use);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.item_signout:
                        Intent intent_signout = new Intent(Activity_books.this,MainActivity.class);
                        startActivity(intent_signout);
                        drawerLayout.closeDrawers();
                        sessionManager.logout();
                        return true;

                }

                return false;
            }
        });
    }

    //---------------------------------------end of function setUpNavigationView---------------------------------------//





    //----------------------------------- method to show data in viewpager when click on each tab ----------------------------//

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

        viewPager.setOffscreenPageLimit(0);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int index = tab.getPosition();

                switch (index){

                    case 0:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        tab_btnBorrow_btnBuy.setVisibility(View.VISIBLE);
                        break;

                    case 1:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        tab_btnBorrow_btnBuy.setVisibility(View.VISIBLE);
                        break;


                    case 2:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        tab_btnBorrow_btnBuy.setVisibility(View.VISIBLE);
                        break;



                    case 3:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        tab_btnBorrow_btnBuy.setVisibility(View.VISIBLE);
                        break;



                    case 4:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        tab_btnBorrow_btnBuy.setVisibility(View.VISIBLE);
                        break;



                    case 5:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        tab_btnBorrow_btnBuy.setVisibility(View.VISIBLE);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    //-------------------------------------- end of function setUpViewPager ---------------------------------------------//


    //--------------- initial option menu in tool bar -------------------//

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_tool_bar,menu);
        return true;
    }

    //*******************************************************************//





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




    //----------------------------------- method to set action to bottom navigation view  --------------------------------------//

    private void setActionBottomNavigationView(){

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.item_all_book:

                        viewPager.setCurrentItem(0);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        linearLayout_containter_home.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.VISIBLE);
                        initToolBar();

                        return true;


                    case R.id.item_feed_book:

                        loadFragment(new Feed_fragment());
//                        linearLayout_container_book_home.setVisibility(View.GONE);
                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        tab_btnBorrow_btnBuy.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
                        viewPager_eacbtab.setVisibility(View.GONE);
                        initToolBar();



                        return true;


                    case R.id.item_post:
                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        tabLayout.setVisibility(View.GONE);
                        viewPager_eacbtab.setVisibility(View.GONE);
                        loadFragment(new Post_fragment());


                        getSupportActionBar().setTitle("Post Book");
                        ActionBar actionbar = getSupportActionBar();
                        actionbar.setDisplayHomeAsUpEnabled(false);
                        actionbar.setHomeAsUpIndicator(null);




                        return true;


                    case R.id.item_notification:
                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        tabLayout.setVisibility(View.GONE);
                        viewPager_eacbtab.setVisibility(View.GONE);
                        tab_btnBorrow_btnBuy.setVisibility(View.GONE);
                        loadFragment(new Notification_fragment());
                        getSupportActionBar().setTitle("Notification");
                        ActionBar actionbar1 = getSupportActionBar();
                        actionbar1.setDisplayHomeAsUpEnabled(false);
                        actionbar1.setHomeAsUpIndicator(null);



                        return true;


                    case R.id.item_profile:

                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        tabLayout.setVisibility(View.GONE);

                        getSupportActionBar().setTitle("Profile");
                        ActionBar actionbar3 = getSupportActionBar();
                        actionbar3.setDisplayHomeAsUpEnabled(false);
                        actionbar3.setHomeAsUpIndicator(null);


                        loadFragment(new UserProfileFragment());

                        return true;
                }
                return false;
            }
        });

    }

    //----------------------------------- end of function setUpBottomNavigationView ---------------------------------------------//



    // method to set up fragment when click item on bottom navigation //

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tf = fm.beginTransaction();
        tf.replace(R.id.container_home,fragment);
        tf.commit();
    }
    //**************************************************************//




    // method to set up fragment when click item book on bottom navigation //


    //**************************************************************//




    public void selectBottomNavigationOption(int index) {
        switch (index) {
            case 0:
                index = R.id.item_all_book;
                break;
            case 1:
                index = R.id.item_feed_book;
                loadFragment(new Feed_fragment());
                linearLayout_containter_home.setVisibility(View.VISIBLE);

                tab_btnBorrow_btnBuy.setVisibility(View.GONE);
                tabLayout.setVisibility(View.GONE);
                viewPager_eacbtab.setVisibility(View.GONE);

                finish();
                break;
            case 2:
                index = R.id.item_post;
                break;
            case 3:
                index = R.id.item_notification;
                break;

            case 4:
                index = R.id.item_profile;
                linearLayout_containter_home.setVisibility(View.VISIBLE);

                tab_btnBorrow_btnBuy.setVisibility(View.GONE);
                tabLayout.setVisibility(View.GONE);
                viewPager_eacbtab.setVisibility(View.GONE);
                loadFragment(new UserProfileFragment());
                finish();
                break;
        }
        bottomNavigationView.setSelectedItemId(index);
    }

}