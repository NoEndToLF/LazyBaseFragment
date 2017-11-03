package com.android.lazybasefragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private List<Fragment> list_fragments;
    private List<String> list_titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        list_titles=new ArrayList<>();
        for (int i=0;i<8;i++){
            list_titles.add("测试 "+(i+1));
        }
        list_fragments=new ArrayList<>();
        for (String title:list_titles){
            tablayout.addTab(tablayout.newTab().setText(title));
            TestFragment fragment=new TestFragment();
            Bundle bundle=new Bundle();
            bundle.putString("title",title);
            fragment.setArguments(bundle);
            list_fragments.add(fragment);
        }
        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewpager);
    }

    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list_titles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragments.get(position);
        }

        @Override
        public int getCount() {
            return list_fragments.size();
        }
    }
}
