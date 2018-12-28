package com.yjp.splashtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yjp.splashtest.MainActivity;

public class SplashActivity extends AppCompatActivity {

    // 시간지연을 위한 핸들러
    Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Main Activity 실행
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, 5000);

    }
}
