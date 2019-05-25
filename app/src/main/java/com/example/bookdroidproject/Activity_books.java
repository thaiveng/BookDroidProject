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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookdroidproject.adapter.BooksAdapter;
import com.example.bookdroidproject.adapter.PageAdapter;
import com.example.bookdroidproject.fragment.BookFragment;
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



    RecyclerView recyclerViewHome,recyclerViewHometrend;
    BooksAdapter adapterBooks;
    List<Booksmodel> booksmodelList,booksmodelList1;
    LinearLayout btnAll,btnAllTrend;


    LinearLayout linearLayout_containter_home;



    // declare variable to show or hide view
    LinearLayout linearLayout_container_book_home;
    ViewPager viewPager_eacbtab;
    LinearLayout linearLayout_recommend;
    LinearLayout linearLayout_trending;
    RecyclerView recyclerView_reco;
    RecyclerView recyclerView_tre;
    TabLayout tab_btnBorrow_btnBuy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);


        linearLayout_containter_home = findViewById(R.id.container_home);

        linearLayout_container_book_home = findViewById(R.id.container_book_fragment);
        linearLayout_recommend = findViewById(R.id.linear_recommend);
        linearLayout_trending = findViewById(R.id.linear_trending);
        viewPager_eacbtab = findViewById(R.id.view_pager);
        recyclerView_reco = findViewById(R.id.recycler_home_reco);
        recyclerView_tre = findViewById(R.id.recycler_home_tre);
        tab_btnBorrow_btnBuy = findViewById(R.id.tab_layout_secondary);


        // call this method to find id of each element
        initView();

        initToolBar();

        // call this method to make action when click on each tab
        setUpViewPager();


        setUpNavigationView();



        booksmodelList = new ArrayList<Booksmodel>();
        booksmodelList1 = new ArrayList<Booksmodel>();

        btnAll = findViewById(R.id.all_books_click);
        btnAllTrend = findViewById(R.id.all_books_click_trending);
        recyclerViewHome = findViewById(R.id.recycler_home_reco);
        recyclerViewHometrend = findViewById(R.id.recycler_home_tre);

        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

        for(int i=0;i<10;i++)
        {
            Booksmodel model = new Booksmodel();
            model.setImg_book(R.drawable.book);
            model.setTitle("Book "+i);
            booksmodelList.add(model);

        }

        adapterBooks = new BooksAdapter(booksmodelList,getApplicationContext());
        recyclerViewHome.setAdapter(adapterBooks);


        recyclerViewHometrend.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

        for(int i=0;i<10;i++)
        {
            Booksmodel model1 = new Booksmodel();
            model1.setImg_book(R.drawable.book);
            model1.setTitle("Book "+i+i);
            booksmodelList1.add(model1);
        }

        adapterBooks = new BooksAdapter(booksmodelList1,getApplicationContext());
        recyclerViewHometrend.setAdapter(adapterBooks);

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
                        Toast.makeText(getApplicationContext(),"You are selected profile item",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.item_about_us:
                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        linearLayout_recommend.setVisibility(View.GONE);
                        linearLayout_trending.setVisibility(View.GONE);
                        recyclerView_reco.setVisibility(View.GONE);
                        recyclerView_tre.setVisibility(View.GONE);
                        tab_btnBorrow_btnBuy.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"You are selected about us",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.item_term_of_use:

                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        linearLayout_recommend.setVisibility(View.GONE);
                        linearLayout_trending.setVisibility(View.GONE);
                        recyclerView_reco.setVisibility(View.GONE);
                        recyclerView_tre.setVisibility(View.GONE);
                        tab_btnBorrow_btnBuy.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"You are selected term of use",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.item_signout:
                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        linearLayout_recommend.setVisibility(View.GONE);
                        linearLayout_trending.setVisibility(View.GONE);
                        recyclerView_reco.setVisibility(View.GONE);
                        recyclerView_tre.setVisibility(View.GONE);
                        tab_btnBorrow_btnBuy.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"You are selected signout",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;


                    case R.id.item_all_book:

                        linearLayout_container_book_home.setVisibility(View.VISIBLE);
                        linearLayout_recommend.setVisibility(View.GONE);
                        linearLayout_trending.setVisibility(View.GONE);
                        recyclerView_reco.setVisibility(View.GONE);
                        recyclerView_tre.setVisibility(View.GONE);
                        loadFragmentBook(new BookFragment());
                        drawerLayout.closeDrawers();
                        return true;


                    case R.id.item_feed_book:
                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        linearLayout_recommend.setVisibility(View.GONE);
                        linearLayout_trending.setVisibility(View.GONE);
                        recyclerView_reco.setVisibility(View.GONE);
                        recyclerView_tre.setVisibility(View.GONE);
                        tab_btnBorrow_btnBuy.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"You are selected feed item",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;


                    case R.id.item_post:
                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        linearLayout_recommend.setVisibility(View.GONE);
                        linearLayout_trending.setVisibility(View.GONE);
                        recyclerView_reco.setVisibility(View.GONE);
                        recyclerView_tre.setVisibility(View.GONE);
                        tab_btnBorrow_btnBuy.setVisibility(View.GONE);
                       loadFragment(new Post_fragment());
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.item_notification:
                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        linearLayout_recommend.setVisibility(View.GONE);
                        linearLayout_trending.setVisibility(View.GONE);
                        recyclerView_reco.setVisibility(View.GONE);
                        recyclerView_tre.setVisibility(View.GONE);
                        tab_btnBorrow_btnBuy.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"You are selected notification item",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.item_store:
                        linearLayout_containter_home.setVisibility(View.VISIBLE);
                        linearLayout_recommend.setVisibility(View.GONE);
                        linearLayout_trending.setVisibility(View.GONE);
                        recyclerView_reco.setVisibility(View.GONE);
                        recyclerView_tre.setVisibility(View.GONE);
                        tab_btnBorrow_btnBuy.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"You are selected store item",Toast.LENGTH_SHORT).show();
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
                        linearLayout_recommend.setVisibility(View.GONE);
                        linearLayout_trending.setVisibility(View.GONE);
                        recyclerView_reco.setVisibility(View.GONE);
                        recyclerView_tre.setVisibility(View.GONE);

                        break;

                    case 1:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        linearLayout_recommend.setVisibility(View.GONE);
                        linearLayout_trending.setVisibility(View.GONE);
                        recyclerView_reco.setVisibility(View.GONE);
                        recyclerView_tre.setVisibility(View.GONE);
                        break;


                    case 2:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        linearLayout_recommend.setVisibility(View.GONE);
                        linearLayout_trending.setVisibility(View.GONE);
                        recyclerView_reco.setVisibility(View.GONE);
                        recyclerView_tre.setVisibility(View.GONE);
                        break;



                    case 3:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        linearLayout_recommend.setVisibility(View.GONE);
                        linearLayout_trending.setVisibility(View.GONE);
                        recyclerView_reco.setVisibility(View.GONE);
                        recyclerView_tre.setVisibility(View.GONE);
                        break;



                    case 4:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        linearLayout_recommend.setVisibility(View.GONE);
                        linearLayout_trending.setVisibility(View.GONE);
                        recyclerView_reco.setVisibility(View.GONE);
                        recyclerView_tre.setVisibility(View.GONE);
                        break;



                    case 5:
                        viewPager.setCurrentItem(index);
                        viewPager_eacbtab.setVisibility(View.VISIBLE);
                        linearLayout_recommend.setVisibility(View.GONE);
                        linearLayout_trending.setVisibility(View.GONE);
                        recyclerView_reco.setVisibility(View.GONE);
                        recyclerView_tre.setVisibility(View.GONE);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_tool_bar,menu);
        return true;
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




    private void loadFragment(Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tf = fm.beginTransaction();
        tf.replace(R.id.container_home,fragment);
        tf.commit();
    }


    private void loadFragmentBook(Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tf = fm.beginTransaction();
        tf.replace(R.id.container_home,fragment);
        tf.commit();

    }

}
