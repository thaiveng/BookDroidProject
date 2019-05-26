package com.example.bookdroidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView txtRegister,txtEmail,txtPassword;
    Button btnLogin;


    private String url = "http://192.168.43.132:8000/api/user/getall";
    private String urllogin = "http://192.168.43.132:8000/api/user/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = findViewById(R.id.edit_text_email_login);
        txtPassword = findViewById(R.id.edit_text_pwd);
        txtRegister = findViewById(R.id.txt_register);
        btnLogin = findViewById(R.id.btn_login);



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

                HashMap data = new HashMap();
                data.put("email", email);
                data.put("password", pwd);

                postData(urllogin, data);

            }
        });

    }
//    private void logIn(String urllogin, HashMap data) {
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urllogin, new JSONObject(data), new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                if (response.toString().equals(log)){
//                    Intent intent = new Intent(MainActivity.this, Activity_books.class);
//                    startActivity(intent);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("error", error.toString());
//            }
//        });
//
//
//        requestQueue.add(request);
//    }
    private void postData(String url, HashMap data) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String res = null;
                try {
                    res = response.getString("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String log = "logedin";
                if (res.equals(log)){
                    Log.e("save", response.toString());
                    Intent intent = new Intent(MainActivity.this, Activity_books.class);
                    startActivity(intent);
                    txtEmail.setText("");
                    txtPassword.setText("");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.toString());
            }
        });

        requestQueue.add(request);
    }
}
