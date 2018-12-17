package com.yjp.test_4e;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.iv_like).setOnClickListener(this);
        findViewById(R.id.iv_share).setOnClickListener(this);

        findViewById(R.id.iv_photo).setOnClickListener(this);
        findViewById(R.id.iv_photo).setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_like :
                Toast.makeText(this, "I Like It!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_share :
                Toast.makeText(this, "TOGETHER THIS LINK!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_photo :
                Toast.makeText(this, "TOGETHER THIS LINK!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 보통 touchListener는 잘 건들지 않음
        Toast.makeText(this, "IMG!!!!!!!", Toast.LENGTH_SHORT).show();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN :
                Toast.makeText(this, "IMG DOWN!!!!!!!", Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_UP :
                Toast.makeText(this, "IMG UP!!!!!!!", Toast.LENGTH_SHORT).show();
                break;
        }

        // Touch가 상위여서 return false면 onClick까지 실행 됨 -> 무슨말인진 모르겠음
        // true 로 하면 ontouch가 4번이 실행되는 듯

        // false -> onclick까지 실행 되고,
        // true -> onclick 실행 안 됨
        return true;

    }
}
