package com.yjp.collectiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yjp.collectiontest.model.PostItem;
import com.yjp.collectiontest.recyclerView.PostAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<PostItem> listItem = new ArrayList<>();

        RecyclerView rvList = findViewById(R.id.rv_list);

        // 입력
        for(int i = 0; i < 30; i++) {
            PostItem item = new PostItem("wldbs2580", "dahyun", "Wow!!", 100, true);
            listItem.add(i, item);
        }

        PostAdapter adapter = new PostAdapter(this, listItem);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(adapter);

        // 출력
        /*for(PostItem item : listItem){
            View v = View.inflate(this, R.layout.post_item, null);
            TextView tvUserName = v.findViewById(R.id.tv_UserName);
            TextView tvPostText = v.findViewById(R.id.tv_PostText);

            tvUserName.setText(item.getUserName());
            tvPostText.setText(item.getPostText());

            llScrollParent.addView(v);
        }*/

    }
}
