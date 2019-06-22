package com.example.bookdroidproject.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookdroidproject.R;
import com.example.bookdroidproject.adapter.AllBooksAdapter;
import com.example.bookdroidproject.model.All_book_model;

import java.util.ArrayList;
import java.util.List;

public class All_book_fragment extends Fragment {

    RecyclerView recyclerViewHome;
    AllBooksAdapter adapterBooks;
    List<All_book_model> booksmodelList;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_all_book, container, false);



        booksmodelList = new ArrayList<All_book_model>();
        recyclerViewHome = view.findViewById(R.id.recycler_all_book);
        recyclerViewHome.setHasFixedSize(true);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity()));

        for(int i=0;i<10;i++)
        {
            All_book_model model = new All_book_model();
            model.setImg_book(R.drawable.b3);
            model.setAuthor("thaiveng" +i+i);
            model.setCateType("math"+i+i+i);
            model.setPubDate("today"+i);
            model.setTitle("Book "+i);
            booksmodelList.add(model);

        }
        adapterBooks = new AllBooksAdapter(booksmodelList,getActivity());
        recyclerViewHome.setAdapter(adapterBooks);

        return view;
    }
}
