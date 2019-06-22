package com.example.bookdroidproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.asksira.bsimagepicker.BSImagePicker;
import com.bumptech.glide.Glide;
import com.example.bookdroidproject.Activity_books;
import com.example.bookdroidproject.MainActivity;
import com.example.bookdroidproject.R;
import com.example.bookdroidproject.SQLlite.DBHelper;
import com.example.bookdroidproject.SessionManager;


import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Post_fragment extends Fragment implements BSImagePicker.OnSingleImageSelectedListener,
        BSImagePicker.OnMultiImageSelectedListener, BSImagePicker.ImageLoaderDelegate{

    EditText edtTitleBook;
    EditText edtAuthorBook;
    EditText edtDescriptionBook;
    Button btnPostBook;


    RadioButton postTypeButton;
    RadioGroup radioGroup;
    ImageView imageView;


    LinearLayout linearLayout;
    Spinner spinner ;


    Bitmap bitmap = null;

    public String val_titleBook;
    public String val_authorBook;
    public String val_descriptionBook;

    int value_book_type;
    int value_cate_id;
    Uri value_img_file;

    // array value category book
    String[] cate_book = {"Novel","Sport","Programming","Mindset","Tourist","Mathematic"};


    String url = "http://192.168.100.187:8000/api/book/post";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.posts_fragment, container, false);

        edtTitleBook = view.findViewById(R.id.edt_title_post_book);
        edtAuthorBook = view.findViewById(R.id.edt_author_post_book);
        edtDescriptionBook = view.findViewById(R.id.edt_des_post_book);
        btnPostBook = view.findViewById(R.id.btn_post_book);


        radioGroup = view.findViewById(R.id.radioGroup);

        imageView = view.findViewById(R.id.img_post_book);

        linearLayout = view.findViewById(R.id.linear_choose_img_book_post);



        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BSImagePicker pickerDialog = new BSImagePicker.Builder("com.asksira.imagepickersheetdemo.fileprovider")
                        .build();
                pickerDialog.show(getChildFragmentManager(), "picker");

            }


        });




        spinner = view.findViewById(R.id.spinner_category);
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,cate_book);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (cate_book[position].equals("Novel")){

                    value_cate_id = 1;
                }

                if (cate_book[position].equals("Sport")){

                    value_cate_id = 2;
                }


                if (cate_book[position].equals("Programming")){

                    value_cate_id = 3;
                }


                if (cate_book[position].equals("Mindset")){

                    value_cate_id = 4;
                }


                if (cate_book[position].equals("Tourist")){

                    value_cate_id = 5;
                }

                if (cate_book[position].equals("Mathematic")){

                    value_cate_id = 6;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.radio_sell:
                        value_book_type = 1;
                        break;
                    case R.id.radio_borrow:
                        value_book_type = 2;
                        break;
                }
            }
        });



        btnPostBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val_titleBook = edtTitleBook.getText().toString();
                val_authorBook = edtAuthorBook.getText().toString();
                val_descriptionBook = edtDescriptionBook.getText().toString();


                if (val_titleBook.isEmpty()){

                    edtTitleBook.setError("Enter the title book!");
                }

                if (val_authorBook.isEmpty()){

                    edtAuthorBook.setError("Enter the author book!");
                }

                if (val_descriptionBook.isEmpty()){

                    edtDescriptionBook.setError("Enter the description book!");
                }

                uploadMultipart(value_img_file);
//                Log.e("user_id::",helper.getUser());

            }
        });


        return view;
    }



    private void postData(String url, HashMap data) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("save", response.toString());

                String res = null;

                try {
                    res = response.getString("status");
//                    Log.e("Status :: ",""+res);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (res.equals("successful")){
                    Log.e("save", response.toString());
                    Toast.makeText(getActivity(),"You posted successfully!",Toast.LENGTH_SHORT).show();
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

    @Override
    public void onSingleImageSelected(Uri uri, String tag) {
        value_img_file = uri;
        Glide.with(getActivity()).load(uri).into(imageView);
    }


    @Override
    public void loadImage(File imageFile, ImageView ivImage) {
        Glide.with(getActivity()).load(imageFile).into(ivImage);
    }

    @Override
    public void onMultiImageSelected(List<Uri> uriList, String tag) {

    }



    public void uploadMultipart(Uri fileURI) {
        try {
            String uploadId = UUID.randomUUID().toString();
            DBHelper helper = new DBHelper(getActivity());
            String user_id = helper.getUser();
            //Creating a multi part request
            MultipartUploadRequest mUploadRequest = new MultipartUploadRequest(getActivity(), uploadId, url)
                    .addParameter("book_title", val_titleBook) //Adding text parameter to the request
                    .addParameter("author",val_authorBook ) //Adding text parameter to the request
                    .addParameter("description", val_descriptionBook) //Adding text parameter to the request
                    .addParameter("cat_id", value_cate_id+"") //Adding text parameter to the request
                    .addParameter("user_id", user_id) //Adding text parameter to the request
                    .addParameter("book_type_id", value_book_type+"") //Adding text parameter to the request
//                  .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2); // try request at least 2 time before give up

            String path = fileURI.getPath();
            mUploadRequest.addFileToUpload(path, "imgs");

            mUploadRequest.startUpload();

            Toast.makeText(getActivity(),"Upload successful", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(),Activity_books.class);
            startActivity(intent);
            getActivity().finish();
        } catch (Exception exc) {
            Log.e("Mutipart Error ", exc.toString());
            Toast.makeText(getActivity(),"Multipart Error" + exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}