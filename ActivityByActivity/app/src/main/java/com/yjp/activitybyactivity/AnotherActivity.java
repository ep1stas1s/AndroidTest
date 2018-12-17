package com.yjp.activitybyactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        Button btn2 = findViewById(R.id.button2);

        // AnotherActivity (this) -> MainActivity 로 다시 결과값 전송
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", "TEST");

                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });

        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    private void processIntent(Intent intent) {
        if(intent != null){
            // 단순히 putExtra 를 통해 받은 data
            ArrayList<String> names = (ArrayList<String>) intent.getSerializableExtra("names");
            if(names != null){
                Toast.makeText(this, "전달 받은 names : " + names.size(), Toast.LENGTH_SHORT).show();
            }

            // (권장) Parcelable 을 통해 받은 data
            SimpleData data = intent.getParcelableExtra("data");
            if(data != null){
                Toast.makeText(this, "전달받은 Data : " + data.message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
