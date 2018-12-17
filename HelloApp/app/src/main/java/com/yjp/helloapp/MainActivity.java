package com.yjp.helloapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // R = resource

        Button button = (Button) findViewById(R.id.btn_call);
        button.setOnClickListener(this);        // button이 눌렸을 때
    }

    @Override
    public void onClick(View v) {
        // 간단안 팝업 메세지 띄우기
        /*Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show();*/

        // Intent 활용 (통화 띄우기)
        /*Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        startActivity(intent);*/

        // Intent 활용 (Activity 간 이동)
        /*Intent intent = new Intent(MainActivity.this, CallActivity.class);
        startActivity(intent);*/

        Intent intent = new Intent(MainActivity.this, CallActivity.class);
        intent.putExtra("intent-message", "game joa joa");
        startActivity(intent);
    }
}
