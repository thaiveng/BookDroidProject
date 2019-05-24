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
import com.example.bookdroidproject.model.All_book_model;

import java.util.List;

public class AllBooksAdapter extends RecyclerView.Adapter<AllBooksAdapter.MyViewholder> {

    private View.OnClickListener mOnClickListener;
    private List<All_book_model> allBookModelList;
    private Context context;
    private RecyclerView recyclerViewBook;

    public AllBooksAdapter(List<All_book_model> allBookModelList, Context context) {
        this.allBookModelList = allBookModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View myview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_all_books,viewGroup,false);
        MyViewholder viewholder = new MyViewholder(myview);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder myViewholder, int i) {
        myViewholder.txtTitle.setText(allBookModelList.get(i).getTitle());
        myViewholder.txtCatType.setText(allBookModelList.get(i).getCateType());
        myViewholder.txtAuthor.setText(allBookModelList.get(i).getAuthor());
        myViewholder.txtPubDate.setText(allBookModelList.get(i).getPubDate());
        myViewholder.img_book.setImageResource(allBookModelList.get(i).getImg_book());
    }

    @Override
    public int getItemCount() {
        return allBookModelList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView txtTitle,txtCatType,txtAuthor,txtPubDate;
        ImageView img_book;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtCatType = itemView.findViewById(R.id.txtCatType);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtPubDate = itemView.findViewById(R.id.txtPubDate);
            img_book = itemView.findViewById(R.id.img_book);
        }
    }
}
