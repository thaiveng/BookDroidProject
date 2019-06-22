package com.example.bookdroidproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookdroidproject.R;
import com.example.bookdroidproject.model.User_Following_model;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserFollowingAdapter extends BaseAdapter {

   private Context context;
   private List<User_Following_model> user_followings;


    public UserFollowingAdapter(Context context, List<User_Following_model> user_followings) {
        this.context = context;
        this.user_followings = user_followings;
    }



    @Override
    public int getCount() {
        return user_followings.size();
    }

    @Override
    public Object getItem(int position) {
        return user_followings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context, R.layout.listview_following,null);


        CircleImageView imageView = view.findViewById(R.id.img_user_following);
        TextView textView = view.findViewById(R.id.tv_username_following);



        User_Following_model obj = user_followings.get(position);

        imageView.setImageResource(obj.getImgID());
        textView.setText(obj.getUsername());

        return view;
    }
}
