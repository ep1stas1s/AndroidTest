package com.yjp.unhappiness.post.postRecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.yjp.unhappiness.R;

public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView ivUserImage, ivPostImage;
    CheckBox cbTear;
    TextView tvTearCount, tvUserId, tvPostContent;
    PostAdapter postAdapter;


    public PostViewHolder(View itemView, PostAdapter postAdapter) {
        super(itemView);
        this.postAdapter = postAdapter;
        ivUserImage = itemView.findViewById(R.id.iv_userImage);
        ivPostImage = itemView.findViewById(R.id.iv_postImage);
        cbTear = itemView.findViewById(R.id.cb_tear);
        tvTearCount = itemView.findViewById(R.id.tv_tearCount);
        tvUserId = itemView.findViewById(R.id.tv_userId);
        tvPostContent = itemView.findViewById(R.id.tv_postContent);

        cbTear.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        switch (v.getId()){
            case R.id.cb_tear:
                postAdapter.onTearClicked(position);
                break;
        }
    }
}
