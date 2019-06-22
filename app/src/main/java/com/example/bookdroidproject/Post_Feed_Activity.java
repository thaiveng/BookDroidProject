package com.example.bookdroidproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.asksira.bsimagepicker.BSImagePicker;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class Post_Feed_Activity extends AppCompatActivity implements BSImagePicker.OnSingleImageSelectedListener,
        BSImagePicker.OnMultiImageSelectedListener, BSImagePicker.ImageLoaderDelegate{

    private Toolbar toolbar;

    LinearLayout linearLayout;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_feed_);

        initToolBar();

        imageView = findViewById(R.id.img_post_feed);


        linearLayout = findViewById(R.id.linear_choose_img_post_feed);



        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BSImagePicker pickerDialog = new BSImagePicker.Builder("com.asksira.imagepickersheetdemo.fileprovider")
                        .build();
                pickerDialog.show(getSupportFragmentManager(), "picker");
            }
        });

    }



    private void initToolBar(){
        toolbar = findViewById(R.id.toolBar_post_feed);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Post Feed");
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here


                Intent intent = new Intent(getApplicationContext(), Activity_books.class);
                intent.putExtra("INDEX_ITEM_BOTTOM_NAVIGATION_VIEW",1);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void loadImage(File imageFile, ImageView ivImage) {

        Glide.with(getApplicationContext()).load(imageFile).into(ivImage);
    }

    @Override
    public void onMultiImageSelected(List<Uri> uriList, String tag) {

    }

    @Override
    public void onSingleImageSelected(Uri uri, String tag) {

        Glide.with(getApplicationContext()).load(uri).into(imageView);
    }
}
