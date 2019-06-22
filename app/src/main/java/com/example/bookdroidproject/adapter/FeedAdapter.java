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
import com.example.bookdroidproject.model.FeedModel;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewholder> {


    private List<FeedModel> feedList;
    private Context context;


    public FeedAdapter(List<FeedModel> feedList, Context context) {
        this.feedList = feedList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View myview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_item_list,viewGroup,false);

        MyViewholder viewholder = new MyViewholder(myview);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder myViewholder, int i) {
        myViewholder.tvTitle.setText(feedList.get(i).getTitle());
        myViewholder.tvDescription.setText(feedList.get(i).getDescription());
        myViewholder.img_feed.setImageResource(feedList.get(i).getImgFeed());
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }


    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        ImageView img_feed;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);


            tvTitle = itemView.findViewById(R.id.title_feed);
            tvDescription = itemView.findViewById(R.id.description_feed);
            img_feed = itemView.findViewById(R.id.image_feed);
        }
    }
}
