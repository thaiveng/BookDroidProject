package com.example.bookdroidproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookdroidproject.R;
import com.example.bookdroidproject.model.FeedModel;

import java.util.List;

public class FeedAdapter extends BaseAdapter {
    private Context context;
    private List<FeedModel> feedModels;
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public FeedAdapter(Context context, List<FeedModel> feedModels) {
        this.context = context;
        this.feedModels = feedModels;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.feed_item_list,null);

        ImageView imageView = view.findViewById(R.id.image_feed);
        TextView title = view.findViewById(R.id.title_feed);
        TextView description = view.findViewById(R.id.description_feed);
        imageView.setImageResource(feedModels.get(position).getImg());
        title.setText(feedModels.get(position).getTitle());
        description.setText(feedModels.get(position).getDescription());
        return view;
    }
}
