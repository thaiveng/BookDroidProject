package com.example.bookdroidproject.adapter;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bookdroidproject.R;

public class Edit_Profile_Activity extends AppCompatActivity {



    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile_);


        bottomNavigationView = findViewById(R.id.bottom_navigationView);


        initToolBar();


        setUpNavigationView();


        // call this method to make action when click on each icon of bottom navigation
        setActionBottomNavigationView();
    }



    // method to set up tool bar
    private void initToolBar(){

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

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
