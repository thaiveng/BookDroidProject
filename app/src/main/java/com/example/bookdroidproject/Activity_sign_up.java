package com.example.bookdroidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class Activity_sign_up extends AppCompatActivity {

    EditText editTextName,editTextEmail,editTextPhone,editTextPwd,getEditTextC_Pwd;
    Button btnSignup;

    private String url = getResources().getString(R.string.url)+"/api/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editTextName = findViewById(R.id.edit_text_user);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPhone = findViewById(R.id.edit_text_phone);
        editTextPwd = findViewById(R.id.edit_text_pwd);
        getEditTextC_Pwd = findViewById(R.id.edit_text_con_pwd);

        btnSignup = findViewById(R.id.btn_signup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String pwd = editTextPwd.getText().toString();
                String co_pwd = getEditTextC_Pwd.getText().toString();

                if (!email.isEmpty() || !pwd.isEmpty()) {

                    HashMap data = new HashMap();
                    data.put("name", name);
                    data.put("email", email);
                    data.put("phone_number", phone);
                    data.put("password", pwd);
                    data.put("c_password", co_pwd);

                    postData(url, data);
                } else {
                    editTextEmail.setError("Please insert email");
                    editTextPwd.setError("Please insert password");
                    editTextName.setError("Please insert name");
                    editTextPhone.setError("Please insert phone number");
                    getEditTextC_Pwd.setError("Please insert Confirm password");
                }


            }
        });
    }
    private void postData(String url, HashMap data) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("save", response.toString());
                Toast.makeText(Activity_sign_up.this,"success",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Activity_sign_up.this,MainActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.toString());
                Toast.makeText(Activity_sign_up.this,"Fail",Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);
    }
}
