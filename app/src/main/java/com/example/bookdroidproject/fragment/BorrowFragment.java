package com.example.bookdroidproject.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.bookdroidproject.R;
import com.example.bookdroidproject.adapter.BooksAdapter;
import com.example.bookdroidproject.model.Booksmodel;

import java.util.ArrayList;
import java.util.List;

public class BorrowFragment extends Fragment {

    RecyclerView recyclerViewHome,recyclerViewHometrend;
    BooksAdapter adapterBooks;
    List<Booksmodel> booksmodelList,booksmodelList1;
    LinearLayout btnAll,btnAllTrend;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.borrow_book_fragment, container, false);


        booksmodelList = new ArrayList<Booksmodel>();
        booksmodelList1 = new ArrayList<Booksmodel>();

        btnAll = view.findViewById(R.id.all_books_click);
        btnAllTrend = view.findViewById(R.id.all_books_click_trending);
        recyclerViewHome = view.findViewById(R.id.recycler_home_reco);
        recyclerViewHometrend = view.findViewById(R.id.recycler_home_tre);


        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        for(int i=0;i<10;i++)
        {
            Booksmodel model = new Booksmodel();
//            model.setImg_book(R.drawable.novel_book);
            model.setTitle_book("Book "+i);
            booksmodelList.add(model);

        }

        adapterBooks = new BooksAdapter(booksmodelList,getActivity());
        recyclerViewHome.setAdapter(adapterBooks);



        recyclerViewHometrend.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        for(int i=0;i<10;i++)
        {
            Booksmodel model1 = new Booksmodel();
//            model1.setImg_book(R.drawable.b5);
            model1.setTitle_book("Book "+i+i);
            booksmodelList1.add(model1);
        }

        adapterBooks = new BooksAdapter(booksmodelList1,getActivity());
        recyclerViewHometrend.setAdapter(adapterBooks);

        return view;

    }
}
