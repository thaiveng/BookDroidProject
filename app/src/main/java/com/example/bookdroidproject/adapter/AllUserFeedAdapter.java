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
import com.example.bookdroidproject.model.FeedUserModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class AllUserFeedAdapter extends RecyclerView.Adapter<AllUserFeedAdapter.MyViewholder>{

    private List<FeedUserModel> feedAllUserList;
    private Context context;


    public AllUserFeedAdapter(List<FeedUserModel> feedAllUserList, Context context) {
        this.feedAllUserList = feedAllUserList;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View myview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_user_feed_item,viewGroup,false);

        MyViewholder viewholder = new AllUserFeedAdapter.MyViewholder(myview);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllUserFeedAdapter.MyViewholder myViewholder, int i) {

        myViewholder.tvUsername.setText(feedAllUserList.get(i).getUsername());
        myViewholder.tvFollower.setText(feedAllUserList.get(i).getFollower());
        myViewholder.tvRecommendation.setText(feedAllUserList.get(i).getRecommendation());
        myViewholder.imgUserFeed.setImageResource(feedAllUserList.get(i).getImg_user_feed());
    }

    @Override
    public int getItemCount() {
        return feedAllUserList.size();
    }


    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView tvUsername;
        TextView tvFollower;
        TextView tvRecommendation;
        CircleImageView imgUserFeed;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);


            tvUsername = itemView.findViewById(R.id.tv_username_all_feed);
            tvFollower = itemView.findViewById(R.id.tv_follower_all_feed);
            tvRecommendation = itemView.findViewById(R.id.tv_recommendation_all_feed);
            imgUserFeed = itemView.findViewById(R.id.img_all_user_feed);
        }
    }

}

