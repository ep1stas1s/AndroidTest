package com.yjp.collectiontest.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.yjp.collectiontest.R;

public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView ivImg, ivShare;
    public CheckBox cbLike;
    public TextView tvLikeCount, tvUserName, tvPostText;
    private PostAdapter mAdapter;

    public PostViewHolder(View itemView, PostAdapter postAdapter) {
        super(itemView);
        mAdapter = postAdapter;
        ivImg = itemView.findViewById(R.id.iv_img);
        cbLike = itemView.findViewById(R.id.cb_like);
        ivShare = itemView.findViewById(R.id.iv_share);
        tvLikeCount = itemView.findViewById(R.id.tv_likeCount);
        tvUserName = itemView.findViewById(R.id.tv_userName);
        tvPostText = itemView.findViewById(R.id.tv_postText);

        cbLike.setOnClickListener(this);
        ivShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int position = getAdapterPosition();
        switch (v.getId()){
            case R.id.cb_like:
                mAdapter.onLikeClicked(position);
                break;
            case R.id.iv_share:

                break;
        }
    }
}
