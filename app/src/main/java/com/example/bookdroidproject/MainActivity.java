package com.example.bookdroidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookdroidproject.SQLlite.DBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView txtRegister,txtEmail,txtPassword;
    private ProgressBar loading;
    private Button btnLogin;

    DBHelper dbHelper;

    SessionManager sessionManager;
    private String urllogin = "http://192.168.100.187:8000/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);
        Log.e("Sesson ", sessionManager.getUserDetail().toString());

//        if (sessionManager.isLoggin()){
//            Intent intent = new Intent(MainActivity.this, Activity_books.class);
//            startActivity(intent);
//        }
        loading = findViewById(R.id.loading);
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
                String email = txtEmail.getText().toString().trim();
                String pwd = txtPassword.getText().toString().trim();

                if (!email.isEmpty() || !pwd.isEmpty()) {

                    HashMap data = new HashMap();
                    data.put("email", email);
                    data.put("password", pwd);

                    postData(urllogin, data);
                } else {
                    txtEmail.setError("Please insert email");
                    txtPassword.setError("Please insert password");
                }

            }
        });

    }

    private void postData(String urllogin, HashMap data) {
        loading.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.GONE);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, urllogin, new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String res = null;
                try {
                    res = response.getString("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("login",response.toString());
                String log = "login";
                if (res.equals(log)){
                    Log.e("save", response.toString());
                    Intent intent = new Intent(MainActivity.this, Activity_books.class);

                    try {
                        sessionManager.createSession(response.getString("name"),response.getString("email"),response.getString("id"));
                        dbHelper = new DBHelper(getApplicationContext());
                        dbHelper.change(response.getString("id"));
                        Log.e("Data ",response.getString("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    startActivity(intent);
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.toString());
                Toast.makeText(MainActivity.this,"Fail",Toast.LENGTH_SHORT).show();
                btnLogin.setVisibility(View.VISIBLE);
            }
        });

        requestQueue.add(request);
    }
}
