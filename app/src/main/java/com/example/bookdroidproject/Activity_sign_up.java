package com.example.bookdroidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class Activity_sign_up extends AppCompatActivity {

    EditText editTextName,editTextEmail,editTextPhone,editTextPwd;
    Button btnSignup;

    private String url = "http://171.23.23.216:8000/api/user/signup/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editTextName = findViewById(R.id.edit_text_user);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPhone = findViewById(R.id.edit_text_phone);
        editTextPwd = findViewById(R.id.edit_text_pwd);

        btnSignup = findViewById(R.id.btn_signup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String pwd = editTextPwd.getText().toString();

                HashMap data = new HashMap();
                data.put("name", name);
                data.put("email", email);
                data.put("phone_number", phone);
                data.put("password", pwd);

                postData(url, data);
            }
        });
    }
    private void postData(String url, HashMap data) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("save", response.toString());
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
