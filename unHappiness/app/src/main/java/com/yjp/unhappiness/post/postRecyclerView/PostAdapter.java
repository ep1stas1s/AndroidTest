package com.yjp.unhappiness.post.postRecyclerView;

import android.widget.Toast;

import com.yjp.unhappiness.post.model.Post;

public class PostAdapter {

    public void onTearClicked(int position) {
        Post item = post.get(position);
        Toast.makeText(mContext, position + " : " + item.getPostText(), Toast.LENGTH_SHORT).show();
    }
}
