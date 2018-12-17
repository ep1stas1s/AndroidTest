package com.yjp.fragmenttest;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements OnColorButtonListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fragment load
        // 처음 fragment 설정
        YellowFragment yellowFragment = new YellowFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_right, yellowFragment).commit();
    }


    // Color
    // 0: red, 1: yellow, 2: blue
    @Override
    public void onColorClick(int color) {
        Fragment fragment = null;

        switch (color){
            case 0:
                fragment = new RedFragment();
                break;
            case 1:
                fragment = new YellowFragment();
                break;
            case 2:
                fragment = new BlueFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_right, fragment).commit();
    }

}
