package com.example.bookdroidproject;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.asksira.bsimagepicker.BSImagePicker;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class Activity_Posts extends AppCompatActivity implements BSImagePicker.OnSingleImageSelectedListener,
        BSImagePicker.OnMultiImageSelectedListener, BSImagePicker.ImageLoaderDelegate, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private ImageView imageView;
    private Button btnSelect;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private ActionBarDrawerToggle toggle;

    private LinearLayout linearLayout;


    private Spinner spinner;

    private String[] cate = {"Mathematic","Mindset","Novel","Programming","Sport"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        imageView = findViewById(R.id.img_post);
        linearLayout = findViewById(R.id.sub_linear1_in_ScreenPosts);


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BSImagePicker pickerDialog = new BSImagePicker.Builder("com.asksira.imagepickersheetdemo.fileprovider")
                        .build();
                pickerDialog.show(getSupportFragmentManager(), "picker");
            }
        });



        bottomNavigationView = findViewById(R.id.bottom_navigationView);


        initToolBar();


        setUpNavigationView();


        // call this method to make action when click on each icon of bottom navigation
        setActionBottomNavigationView();



        spinner = findViewById(R.id.spinner_category);


        spinner.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,cate);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // settting arrray adapter in spinner
        spinner.setAdapter(adapter);

    }

    @Override
    public void onSingleImageSelected(Uri uri, String tag) {
        Glide.with(Activity_Posts.this).load(uri).into(imageView);
    }

    @Override
    public void onMultiImageSelected(List<Uri> uriList, String tag) {

    }

    @Override
    public void loadImage(File imageFile, ImageView ivImage) {
        Glide.with(Activity_Posts.this).load(imageFile).into(ivImage);
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



    // method when click each category item in spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getApplicationContext(),"Category " + cate[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"Category " + cate[position] , Toast.LENGTH_LONG).show();
    }



    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_sell:
                if (checked)
                    Toast.makeText(getApplicationContext(),"Click sell ",Toast.LENGTH_SHORT).show();
                    break;
            case R.id.radio_borrow:
                if (checked)
                    Toast.makeText(getApplicationContext(),"Click borrow",Toast.LENGTH_SHORT).show();
                    break;
        }
    }
}

