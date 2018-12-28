package com.yjp.dbtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NetworkActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        textView = findViewById(R.id.tv_check);
        Button button = findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
                if(status == NetworkStatus.TYPE_MOBILE){
                    textView.setText("모바일로 연결됨.");
                }else if (status == NetworkStatus.TYPE_WIFI){
                    textView.setText("무선랜으로 연결됨.");
                }else {
                    textView.setText("연결 안됨.");
                }
            }
        });
    }
}
