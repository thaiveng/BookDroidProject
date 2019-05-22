package com.example.bookdroidproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookdroidproject.R;
import com.example.bookdroidproject.model.BooksModel;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder> {

    private Context context;
    private List<BooksModel> booksModels;

    public BooksAdapter(Context context, List<BooksModel> booksModels) {
        this.context = context;
        this.booksModels = booksModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View myview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_book,viewGroup,false);


        return new MyViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.textView.setText(booksModels.get(i).getTitle_book());
        myViewHolder.imageView.setImageResource(booksModels.get(i).getImgID());


    }

    @Override
    public int getItemCount() {
        return booksModels.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_title_book);
            imageView = itemView.findViewById(R.id.img_book_recyclerView);
        }
    }
}
