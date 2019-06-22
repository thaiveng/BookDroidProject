package com.example.bookdroidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class Activity_Term_Of_Use extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__term__of__use);


        toolbar = findViewById(R.id.toolbar_term_of_use);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Term Of Use");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
