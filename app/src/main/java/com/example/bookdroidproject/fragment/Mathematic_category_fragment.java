package com.example.bookdroidproject.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookdroidproject.R;
import com.example.bookdroidproject.adapter.BooksAdapter;
import com.example.bookdroidproject.model.Booksmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mathematic_category_fragment extends Fragment {
    RecyclerView recyclerViewHome,recyclerViewHometrend;
    BooksAdapter adapterBooks;
    List<Booksmodel> booksmodelList,booksmodelList1;
    public String url ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mathematics_category_fragment,container,false);

        booksmodelList = new ArrayList<Booksmodel>();
        booksmodelList1 = new ArrayList<Booksmodel>();

        recyclerViewHome = view.findViewById(R.id.recycler_home_reco);
        recyclerViewHometrend = view.findViewById(R.id.recycler_home_tre);
        postData();


        return view;
    }

    private void postData() {
        url = getResources().getString(R.string.url)+"/api/book/all";

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("cat_id",6);

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONArray objArray = new JSONArray(response);

                        for (int i=0; i<objArray.length(); i++){

                            JSONObject jsonObject = objArray.getJSONObject(i);

                            recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

                            Booksmodel model1 = new Booksmodel();
                            String img_url = getResources().getString(R.string.url)+"/storage/images/" +jsonObject.getString("book_image");
                            model1.setTitle_book(jsonObject.getString("book_title"));
                            model1.setImg_book(img_url);
                            booksmodelList.add(model1);
                            adapterBooks = new BooksAdapter(booksmodelList,getActivity());
                            recyclerViewHome.setAdapter(adapterBooks);

                            //trending page
                            recyclerViewHometrend.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

                            Booksmodel model = new Booksmodel();
                            String img_url1 = getResources().getString(R.string.url)+"/storage/images/" +jsonObject.getString("book_image");
                            model.setTitle_book(jsonObject.getString("book_title"));
                            model.setImg_book(img_url1);
                            booksmodelList1.add(model);

                        }

                        adapterBooks = new BooksAdapter(booksmodelList,getActivity());
                        recyclerViewHome.setAdapter(adapterBooks);

                        adapterBooks = new BooksAdapter(booksmodelList1,getActivity());
                        recyclerViewHometrend.setAdapter(adapterBooks);

                    }catch (Exception e){
                        Log.e("HOUSE LIST JSON ",e.toString());
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return super.parseNetworkResponse(response);
                }
            };

            requestQueue.add(stringRequest);
        }

        catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
