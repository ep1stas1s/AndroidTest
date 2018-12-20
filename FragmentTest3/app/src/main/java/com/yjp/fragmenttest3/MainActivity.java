package com.yjp.fragmenttest3;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListFragment fragment1;
    ViewerFragment fragment2;

    // XML 에 fragment가 들어가 있는 경우
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // XML 에 fragment가 들어가 있는 경우
        manager = getSupportFragmentManager();
        fragment1 = (ListFragment) manager.findFragmentById(R.id.listFragment);
        fragment2 = (ViewerFragment) manager.findFragmentById(R.id.ViewerFragment);


//        fragment1 = new ListFragment();
//        fragment2 = new ViewerFragment();
    }

    public void onImageChange(int index){
        fragment2.setImage(index);
    }
}
