package com.example.bookdroidproject;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Activity_feeds extends AppCompatActivity {

    private DrawerLayout drawerLayoutMain;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);
        init();

        toggle = new ActionBarDrawerToggle(this,drawerLayoutMain,R.string.opened_menu,R.string.closed_menu);
        drawerLayoutMain.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    private void init(){

        drawerLayoutMain = findViewById(R.id.drawerlayout);

        bottomNavigationView = findViewById(R.id.botttom_navigation);
        navigationView = findViewById(R.id.nav_main);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch (menuItem.getItemId()){

                    case R.id.nav_book:
                        Toast.makeText(Activity_feeds.this,"You are selected book icon!",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_feed:
                        Toast.makeText(Activity_feeds.this,"You are selected feed icon!",Toast.LENGTH_SHORT).show();
                        break;


                    case R.id.nav_add:
                        Toast.makeText(Activity_feeds.this,"You are selected add icon!",Toast.LENGTH_SHORT).show();
                        break;


                    case R.id.nav_notification:
                        Toast.makeText(Activity_feeds.this,"You are selected notifcation icon!",Toast.LENGTH_SHORT).show();
                        break;


                    case R.id.nav_store:
                        Toast.makeText(Activity_feeds.this,"You are selected store icon!",Toast.LENGTH_SHORT).show();
                        break;
                }


                return true;
            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
