package com.example.bookdroidproject;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bookdroidproject.adapter.PageAdapter;

public class Activity_books extends AppCompatActivity {

    private DrawerLayout drawerLayoutMain;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private ViewPager pager;
    private PagerAdapter adapter;
    private TabLayout tabLayout;
    private TabItem tabNovel,tabProgramming,tabSport,tabMathematic,tabTourist,tabMindset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        pager = findViewById(R.id.view_pager_home);
        tabNovel = findViewById(R.id.tab_novel);
        tabMathematic = findViewById(R.id.tab_Mathematics);
        tabMindset = findViewById(R.id.tab_mindset);
        tabSport = findViewById(R.id.tab_sport);
        tabTourist = findViewById(R.id.tab_tourist);
        tabProgramming = findViewById(R.id.tab_programming);
        tabLayout = findViewById(R.id.tab_layout);
        drawerLayoutMain = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.nav_main);

        toggle = new ActionBarDrawerToggle(this,drawerLayoutMain,R.string.opened_menu,R.string.closed_menu);
        drawerLayoutMain.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        pager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
