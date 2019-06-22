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


public class FeedUserAdpater extends RecyclerView.Adapter<FeedUserAdpater.MyViewholder>{

    private List<FeedUserModel> feedUserList;
    private Context context;


    public FeedUserAdpater(List<FeedUserModel> feedUserList, Context context) {
        this.feedUserList = feedUserList;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View myview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_user_item,viewGroup,false);

        MyViewholder viewholder = new FeedUserAdpater.MyViewholder(myview);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedUserAdpater.MyViewholder myViewholder, int i) {

       myViewholder.tvUsername.setText(feedUserList.get(i).getUsername());
       myViewholder.tvFollower.setText(feedUserList.get(i).getFollower());
       myViewholder.tvRecommendation.setText(feedUserList.get(i).getRecommendation());
       myViewholder.imgUserFeed.setImageResource(feedUserList.get(i).getImg_user_feed());
    }

    @Override
    public int getItemCount() {
        return feedUserList.size();
    }


    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView tvUsername;
        TextView tvFollower;
        TextView tvRecommendation;
        CircleImageView imgUserFeed;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);


           tvUsername = itemView.findViewById(R.id.tv_username_feed);
           tvFollower = itemView.findViewById(R.id.tv_follower_feed);
           tvRecommendation = itemView.findViewById(R.id.tv_recommendation_feed);
           imgUserFeed = itemView.findViewById(R.id.img_user_feed);
        }
    }

}
