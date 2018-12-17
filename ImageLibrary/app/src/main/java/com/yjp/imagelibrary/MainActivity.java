package com.yjp.imagelibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView ivDahyun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivDahyun = findViewById(R.id.iv_dahyun);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoadingDahyunImage();
            }
        });
    }

    private void startLoadingDahyunImage() {
        String url = "https://kpopping.com/uploads/documents/Dahyun260.jpeg";
        Glide.with(this).load(R.drawable.dahyun).into(ivDahyun);
    }
}
