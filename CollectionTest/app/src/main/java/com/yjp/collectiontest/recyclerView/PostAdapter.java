package com.yjp.collectiontest.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yjp.collectiontest.R;
import com.yjp.collectiontest.model.PostItem;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private Context mContext;
    private ArrayList<PostItem> postItems;

    public PostAdapter(Context context, List<PostItem> listItem){
        mContext = context;
        postItems = (ArrayList<PostItem>) listItem;
    }

    @Override
    public PostViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {

        View baseView = View.inflate(mContext, R.layout.post_item, null);
        PostViewHolder postViewHolder = new PostViewHolder(baseView, this);

        return postViewHolder;
    }

    @Override
    public void onBindViewHolder( PostViewHolder holder, int position) {
        PostItem item = postItems.get(position);

        holder.tvUserName.setText(item.getUserName());
        holder.tvPostText.setText(item.getPostText());
        holder.tvLikeCount.setText(String.valueOf(item.getPostLikeCount()));
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    public void onLikeClicked(int position){
        PostItem item = postItems.get(position);
        Toast.makeText(mContext, position + " : " + item.getPostText(), Toast.LENGTH_SHORT).show();
    }
}
