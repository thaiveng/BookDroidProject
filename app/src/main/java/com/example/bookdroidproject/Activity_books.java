package com.example.bookdroidproject;

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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.bookdroidproject.adapter.BooksAdapter;
import com.example.bookdroidproject.adapter.PageAdapter;
import com.example.bookdroidproject.model.BooksModel;
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

    SearchView searchView;


    private BooksModel booksModel;
    private List<BooksModel> list;
    private RecyclerView recyclerView;
    private BooksAdapter booksAdapter;


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

        recyclerView = findViewById(R.id.recycler_home);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        list = new ArrayList<>();

        booksModel = new BooksModel("title1",R.drawable.b1);
        booksModel = new BooksModel("title1",R.drawable.b1);
        booksModel = new BooksModel("title1",R.drawable.b1);
        booksModel = new BooksModel("title1",R.drawable.b1);
        booksModel = new BooksModel("title1",R.drawable.b1);
        booksModel = new BooksModel("title1",R.drawable.b1);

        list.add(booksModel);


        booksAdapter = new BooksAdapter(this,list);

        recyclerView.setAdapter(booksAdapter);




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

    private void setActionBottomNavigationView(){

         BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.books:
                        Toast.makeText(getApplicationContext(),"You are selected book icon",Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.feeds:
                        Toast.makeText(getApplicationContext(),"You are selected feed icon",Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.posts:
                        Toast.makeText(getApplicationContext(),"You are selected posts icon",Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.notification:
                        Toast.makeText(getApplicationContext(),"You are selected notification icon",Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.store:
                        Toast.makeText(getApplicationContext(),"You are selected store icon",Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;

            }
        };
    }



}
