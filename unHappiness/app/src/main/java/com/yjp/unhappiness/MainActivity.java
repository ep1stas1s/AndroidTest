package com.yjp.unhappiness;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private  Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create TabLayout
        TabLayout tabLayout = findViewById(R.id.tl_bottom);

        // Create Fragment Array
        fragments = new Fragment[4];
        fragments[0] = new PostFragment();
        fragments[1] = new HomeFragment();
        fragments[2] = new ChatFragment();
        fragments[3] = new LogFragment();

        // Create Adapter
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        // Create ViewPager & Setting
        ViewPager viewPager = findViewById(R.id.vp_main);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

        // TabLayout Setting
        tabLayout.setupWithViewPager(viewPager);

    }

    private class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments[i];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        // TODO Input Drawable Vector
        @Override
        public CharSequence getPageTitle(int position) {
            CharSequence charSequence = null;
            switch (position){
                case 0:
                    charSequence = "ADD";
                    break;
                case 1:
                    charSequence = "HOME";
                    break;
                case 2:
                    charSequence = "CHAT";
                    break;
                case 3:
                    charSequence = "LOG";
                    break;
//                case 4: charSequence = "SEARCH"; break;
            }

            return charSequence;
        }
    }
}
