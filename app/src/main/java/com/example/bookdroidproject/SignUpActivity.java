package com.example.bookdroidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.rilixtech.Country;
import com.rilixtech.CountryCodePicker;

public class SignUpActivity extends AppCompatActivity {


    CountryCodePicker cpp;

    EditText edtPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        cpp = findViewById(R.id.ccp);

        edtPhoneNumber = findViewById(R.id.edtPhoneNumber_signup);


        Toast.makeText(SignUpActivity.this,"Country Name : " + cpp.getDefaultCountryName()+
                "Country Code : "+ cpp.getDefaultCountryCode(),Toast.LENGTH_LONG);


        cpp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {

                Toast.makeText(SignUpActivity.this,"Country Name : " + cpp.getSelectedCountryName()+
                        "Country Code : "+ cpp.getSelectedCountryCode(),Toast.LENGTH_LONG);

            }
        });

    }
}
