package com.example.bookdroidproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookdroidproject.Activity_books;
import com.example.bookdroidproject.R;
import com.example.bookdroidproject.model.Booksmodel;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewholder> {

    private View.OnClickListener mOnClickListener;
    private List<Booksmodel> booklist;
    private Context context;
    private RecyclerView recyclerViewBook;

    public BooksAdapter(List<Booksmodel> booklist, Context context) {
        this.booklist = booklist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View myview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recycler_books,viewGroup,false);
//        mOnClickListener = new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                int itemPos = recyclerViewBook.getChildLayoutPosition(v);
//                Booksmodel book = booklist.get(itemPos);
//                Intent intent = new Intent(context, Activity_books.class);
//                intent.putExtra("title",book.getTitle());
//                intent.putExtra("img_book",book.getImg_book());
//                context.startActivity(intent);
//            }
//        };
//        myview.setOnClickListener(mOnClickListener);
//        return new MyViewholder(myview);
        MyViewholder viewholder = new MyViewholder(myview);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder myViewholder, int i) {
        myViewholder.txt_title.setText(booklist.get(i).getTitle());
        myViewholder.img_book.setImageResource(booklist.get(i).getImg_book());
    }

    @Override
    public int getItemCount() {
        return booklist.size();
    }


    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView txt_title;
        ImageView img_book;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title);
            img_book = itemView.findViewById(R.id.img_book);
        }
    }
}
