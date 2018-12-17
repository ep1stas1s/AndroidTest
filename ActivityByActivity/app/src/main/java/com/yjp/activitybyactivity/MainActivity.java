package com.yjp.activitybyactivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Intent 예제1 (Activity 간 Data 전송)
        Button btn1 = findViewById(R.id.button);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);

                // 위와 같은 code 지만 (package, class를 직접 기입하는 방법)
//                Intent intent = new Intent();
//                ComponentName name = new ComponentName("com.yjp.activitybyactivity", "com.yjp.activitybyactivity.AnotherActivity");
//                intent.setComponent(name);

                // Activity 간 Serializable Data 전송
                ArrayList<String> names = new ArrayList<>();
                names.add("김진수");
                names.add("황수연");
                intent.putExtra("names", names);

                // Activity 간 Parcel Data 전송
                SimpleData data = new SimpleData(100, "hello");
                intent.putExtra("data", data);    // 그냥 객체는 넣을 수 없지만,
                                                        // Parcelable 을 통해 구현한 객체는 넣을 수 있음

                startActivityForResult(intent, 101);


            }
        });

        // Intent 예제1 (
        editText = findViewById(R.id.editText2);
        Button btn2 = findViewById(R.id.button3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String receiver = editText.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + receiver));
                startActivity(intent);
            }
        });

    }


    // requestCode : MainActivity에서 전달한 requestCode (101)이 AnotherActivity를 거쳐서 다시 전달 받음
    // resultCode : AnotherActivity에서 전달한 setResult(Activity.RESULT_OK, intent) 중 Activity.RESULT_OK 값
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101){
            String name = data.getStringExtra("name");
            Toast.makeText(this,  "메뉴 화면으로부터의 응답 : " + name, Toast.LENGTH_SHORT).show();
        }
    }
}
