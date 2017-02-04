package com.dalong.wxtab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dalong.wxtab.adapter.MyFragmentPagerAdapter;
import com.dalong.wxtab.fragment.ContactsFragment;
import com.dalong.wxtab.fragment.FindFragment;
import com.dalong.wxtab.fragment.MeFragment;
import com.dalong.wxtab.fragment.MessageFragment;
import com.dalong.wxtab.tab.TabGroupView;
import com.dalong.wxtab.tab.TabView;

import java.util.ArrayList;
import java.util.List;

import static com.dalong.wxtab.R.id.view_pager;

public class MainActivity extends AppCompatActivity  implements TabGroupView.OnItemClickListener{

    private List<Fragment> mFragments = new ArrayList<>();
    private ViewPager mViewPager;
    private TabGroupView mTabGroupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager)findViewById(view_pager);
        mTabGroupView = (TabGroupView)findViewById(R.id.group_tab_layout);
        mTabGroupView.setOnItemClickListener(this);
        mTabGroupView.setUnreadCount(0,12);
        mTabGroupView.setHasNew(2,true);
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragments));
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTabGroupView.onScrolling(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                mTabGroupView.setCurrentItem(position);
                mTabGroupView.setHasNew(position,false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFragments() {
        mFragments.add(new MessageFragment());
        mFragments.add(new ContactsFragment());
        mFragments.add(new FindFragment());
        mFragments.add(new MeFragment());
    }

    @Override
    public void onClick(TabView tabLayout, int position) {
        mViewPager.setCurrentItem(position, false);
        mTabGroupView.setHasNew(position,false);
    }
}
