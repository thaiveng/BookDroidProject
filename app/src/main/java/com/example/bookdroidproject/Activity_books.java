package com.example.bookdroidproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bookdroidproject.adapter.BooksAdapter;
import com.example.bookdroidproject.adapter.PageAdapter;
import com.example.bookdroidproject.fragment.AboutUsFragment;
import com.example.bookdroidproject.fragment.Feed_fragment;
import com.example.bookdroidproject.fragment.Notification_fragment;
import com.example.bookdroidproject.fragment.Post_fragment;
import com.example.bookdroidproject.fragment.Store_fragement;
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


    LinearLayout linearLayout_containter_home;
    LinearLayout linearLayout_container_borrow_buy_book;

    SessionManager sessionManager;
    // declare variable to show or hide view
    ViewPager viewPager_eacbtab;


    // declare button to find all borrow book and all buy book
    Button btnBorrow;
    Button btnBuy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);


        linearLayout_containter_home = findViewById(R.id.container_home);

        viewPager_eacbtab = findViewById(R.id.view_pager);

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


        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigationView);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    //************************************************************************//



    //---------------------------------------- method to set up navigation view ------------------------------------//

    private void setUpNavigationView(){

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_main);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.item_profile:
                        Toast.makeText(getApplicationContext(),"You are selected profile item",Toast.LENGTH_SHORT).show();
                        viewPager_eacbtab.setVisibility(View.GONE);
                        linearLayout_container_borrow_buy_book.setVisibility(View.GONE);
                        linearLayout_containter_home.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.item_about_us:
                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        linearLayout_container_borrow_buy_book.setVisibility(View.GONE);
                        viewPager_eacbtab.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
                        loadFragment(new AboutUsFragment());
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.item_term_of_use:
                        linearLayout_containter_home.setVisibility(View.GONE);
                        linearLayout_container_borrow_buy_book.setVisibility(View.GONE);
                        viewPager_eacbtab.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"You are selected term of use",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.item_signout:
                        linearLayout_container_borrow_buy_book.setVisibility(View.GONE);
                        linearLayout_containter_home.setVisibility(View.GONE);
                        viewPager_eacbtab.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"You are selected signout",Toast.LENGTH_SHORT).show();
                        sessionManager.logout();
                        drawerLayout.closeDrawers();
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
                        break;

                    case 1:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        break;


                    case 2:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        break;



                    case 3:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
//                        linearLayout_recommend.setVisibility(View.GONE);
//                        linearLayout_trending.setVisibility(View.GONE);
//                        recyclerView_reco.setVisibility(View.GONE);
//                        recyclerView_tre.setVisibility(View.GONE);
//                        linearLayout_container_book_home.setVisibility(View.GONE);
//                        linearLayout_container_borrow_buy_book.setVisibility(View.GONE);
                        break;



                    case 4:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        break;



                    case 5:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
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

        bottomNavigationView = findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.item_all_book:
                        viewPager.setCurrentItem(0);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        linearLayout_containter_home.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.VISIBLE);

                        return true;


                    case R.id.item_feed_book:
                        loadFragment(new Feed_fragment());
//                        linearLayout_container_book_home.setVisibility(View.GONE);
                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        tabLayout.setVisibility(View.GONE);
                        viewPager_eacbtab.setVisibility(View.GONE);
                        return true;


                    case R.id.item_post:
//                        linearLayout_container_book_home.setVisibility(View.GONE);
                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        tabLayout.setVisibility(View.GONE);
                        viewPager_eacbtab.setVisibility(View.GONE);
                        loadFragment(new Post_fragment());

                        return true;


                    case R.id.item_notification:
//                        linearLayout_container_book_home.setVisibility(View.GONE);
                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        tabLayout.setVisibility(View.GONE);
                        viewPager_eacbtab.setVisibility(View.GONE);
                        loadFragment(new Notification_fragment());

                        return true;


                    case R.id.item_store:
//                        linearLayout_container_book_home.setVisibility(View.GONE);
                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        tabLayout.setVisibility(View.GONE);
                        viewPager_eacbtab.setVisibility(View.GONE);
                        loadFragment(new Store_fragement());

                        return true;
                }
                return false;
            }
        });

    }

    //----------------------------------- end of function setUpBottomNavigationView ---------------------------------------------//



    // method to set up fragment when click item on bottom navigation //

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tf = fm.beginTransaction();
        tf.replace(R.id.container_home,fragment);
        tf.commit();
    }
    //**************************************************************//




    // method to set up fragment when click item book on bottom navigation //

    private void loadFragmentBook(Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tf = fm.beginTransaction();
        //tf.replace(R.id.container_book_fragment,fragment);
        tf.commit();

    }

    //**************************************************************//


//    private void loadFragmentBorrowBuyBook(Fragment fragment){
//
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction tf = fm.beginTransaction();
//        tf.replace(R.id.linear_container_book_borrow_fragment,fragment);
//        tf.commit();
//    }

}
