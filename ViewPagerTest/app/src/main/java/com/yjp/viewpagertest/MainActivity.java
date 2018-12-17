package com.yjp.viewpagertest;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TabLayout Create
        TabLayout tabLayout = findViewById(R.id.tl_tabs);

        // Fragment Array
        Fragment[] arrFragments = new Fragment[3];
        arrFragments[0] = new RedFragment();
        arrFragments[1] = new YellowFragment();
        arrFragments[2] = new GreenFragment();

        // Adapter
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), arrFragments);

        // ViewPager setting
        ViewPager viewPager = findViewById(R.id.vp_pager);
        viewPager.setAdapter(adapter);

        // TabLayout setting
        tabLayout.setupWithViewPager(viewPager);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter{

        private Fragment[] arrFragments;

        public MyPagerAdapter(FragmentManager fm, Fragment[] arrFragments) {
            super(fm);
            this.arrFragments = arrFragments;
        }

        // Layout 설정
        @Override
        public Fragment getItem(int position) {
            return arrFragments[position];

        }

        // Layout 개수
        @Override
        public int getCount() {
            return arrFragments.length;
        }

        // TabLayout 의 Title을 설정 해주는 Method
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "RED";
                case 1:
                    return "YELLOW";
                case 2:
                    return "BLUE";
                default:
                    return "";
            }
        }
    }
}
