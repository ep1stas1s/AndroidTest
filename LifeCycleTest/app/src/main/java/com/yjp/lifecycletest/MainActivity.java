package com.yjp.lifecycletest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate() 호출됨.", Toast.LENGTH_SHORT).show();

        Button button = findViewById(R.id.button);

        // click action
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // editText 의 String 값을 Service(MyService) 로 전달
        editText = findViewById(R.id.editText);
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("command", "show");
                intent.putExtra("name", name);
                startService(intent);
            }
        });

        // Service 에게서 받은 Intent data
        Intent passedIntent = getIntent();
        processCommand(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);

        super.onNewIntent(intent);
    }

    private void processCommand(Intent intent) {
        if(intent != null){
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            Toast.makeText(this, "서비스로부터 전달받은 데이터 : " + command + ", " + name, Toast.LENGTH_SHORT).show();
        }
    }

    /*
    * 처음 실행 시
    * onCreate() -> onStart() -> onResume()
    *
    * 홈 버튼 클릭 시 (주로 onPause()에 일시정지 시 method 를 추가)
    * onPause() -> onStop()
    *
    * 재실행 시 (주로 onResume()에 불러왔을 때 method 를 추가)
    * onStart() -> onResume()
    *
    * 종료 시
    * onPause() -> onStop() -> onDestroy()
    * */

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "onStart() 호출됨.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(this, "onStop() 호출됨.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "onDestroy() 호출됨.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this, "onPause() 호출됨.", Toast.LENGTH_SHORT).show();

        // SharedPreferences save & commit
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", "Twice");
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(this, "onResume()호출됨.", Toast.LENGTH_SHORT).show();

        // SharedPreferences load
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if(pref != null){
            String name = pref.getString("name", "");
            Toast.makeText(this, "복구된 이름 : " + name, Toast.LENGTH_SHORT).show();
        }
    }
}
