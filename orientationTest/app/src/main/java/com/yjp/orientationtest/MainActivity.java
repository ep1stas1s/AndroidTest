package com.yjp.orientationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate() 호출됨", Toast.LENGTH_SHORT).show();

        editText =  findViewById(R.id.editText);

        Button button = findViewById(R.id.button);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = editText.getText().toString();
                    // MainActivity.this = getApplicationContext()
                    Toast.makeText(getApplicationContext(), "입력한 값을 name 변수에 할당함 (" + name + ")", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if(savedInstanceState != null){
            String name = savedInstanceState.getString("name");
            if(editText != null){
                editText.setText("복원된 이름 : " + name);
                Toast.makeText(getApplicationContext(), "이름이 복원됨 : " + name, Toast.LENGTH_SHORT).show();
            }
        }

    }

    // 상태 저장을 위한 메소드
    // Bundle outState 에 저장할 수 있음
    // 근데 id 를 동일하게 작성하면 저장되는듯??
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name", name);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "onStart() 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(this, "onStop() 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "onDestroy() 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this, "onPause() 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(this, "onResume() 호출됨", Toast.LENGTH_SHORT).show();
    }
}
