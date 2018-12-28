package com.yjp.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // (context, 가로 세로 방향 선택, 아이템이 추가되는 방향)
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        // RecyclerView 설정
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);

        // Adaptor -> inflation -> View holder
        adapter = new SingerAdapter(getApplicationContext());
        // add sample data
        adapter.addItem(new SingerItem("소녀시대", "010-1000-1000"));
        adapter.addItem(new SingerItem("콘츄리꼬꼬", "010-2000-2000"));
        adapter.addItem(new SingerItem("빻자친구", "010-3000-3000"));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SingerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SingerAdapter.ViewHolder holder, View view, int position) {
                SingerItem item = adapter.getItem(position);

                Toast.makeText(MainActivity.this, "아이템 선택됨 : " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
