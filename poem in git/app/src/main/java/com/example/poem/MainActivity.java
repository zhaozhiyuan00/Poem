package com.example.poem;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<String> title;
    private ArrayList<Fragment> list;
    private clarify f2;
    private everyday f1;
    private my f3;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private myadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);
        title = new ArrayList<>();
        title.add("每日一句");
        title.add("分类查找");
        title.add("我的主页");
        f2 = new clarify();
        f1 = new everyday();
        f3 = new my();
        list = new ArrayList<>();
        list.add(f1);
        list.add(f2);
        list.add(f3);
        fm = getSupportFragmentManager();
        adapter = new myadapter(fm);
        ft = fm.beginTransaction();
        ft.commit();
        viewPager.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText(title.get(0)),true);
        tabLayout.addTab(tabLayout.newTab().setText(title.get(1)),false);
        tabLayout.addTab(tabLayout.newTab().setText(title.get(2)),false);
        tabLayout.setupWithViewPager(viewPager);
    }

    class myadapter extends FragmentPagerAdapter {
        public myadapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        public CharSequence getPageTitle(int x)
        {
            return title.get(x);
        }
    }
}

