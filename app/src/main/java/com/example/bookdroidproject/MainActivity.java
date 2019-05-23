package com.example.bookdroidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtRegister,txtEmail,txtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = findViewById(R.id.edit_text_email_login);
        txtPassword = findViewById(R.id.edit_text_pwd);
        txtRegister = findViewById(R.id.txt_register);
        btnLogin = findViewById(R.id.btn_login);


        Intent intent = new Intent(MainActivity.this, Activity_books.class);
        startActivity(intent);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Activity_sign_up.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String pwd = txtPassword.getText().toString();
                Intent intent = new Intent(MainActivity.this, Activity_books.class);
                startActivity(intent);
            }
        });
    }
}
