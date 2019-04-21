package com.example.administrator.geeknew.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.geeknew.Activity.CalendarActivity;
import com.example.administrator.geeknew.Activity.CircularAnimUtil;
import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.ZhihuDaily_Fragment.DailyFragment;
import com.example.administrator.geeknew.ZhihuDaily_Fragment.HotFragment;
import com.example.administrator.geeknew.ZhihuDaily_Fragment.SpecialFragment;
import com.example.administrator.geeknew.ZhihuDaily_Fragment.ThemeFragment;
import com.example.administrator.geeknew.adapter.FragmentAdapter;
import com.example.administrator.geeknew.base.BaseFragment;
import com.example.administrator.geeknew.presenter.ZhihuDailyNewsP;
import com.example.administrator.geeknew.view.ZhihuDailyNewsV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xts
 * Created by asus on 2019/4/3.
 */

public class ZhihuDailyNewsFragment extends BaseFragment<ZhihuDailyNewsV, ZhihuDailyNewsP>
        implements ZhihuDailyNewsV {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.view)
    ViewPager view;
    @BindView(R.id.floatButton)
    FloatingActionButton floatButton;


    private ArrayList<Fragment> list;
    private DailyFragment dailyFragment;
    private Bundle bundle;

    @Override
    protected ZhihuDailyNewsP initPresenter() {
        return new ZhihuDailyNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_daily_news;
    }

    //梁书瑞
    @Override
    protected void initView() {
        dailyFragment = new DailyFragment();
        bundle = new Bundle();
        bundle.putString("date","");
        tab.addTab(tab.newTab().setText("日报"));
        tab.addTab(tab.newTab().setText("主题"));
        tab.addTab(tab.newTab().setText("专栏"));
        tab.addTab(tab.newTab().setText("热门"));
        list = new ArrayList<>();
        list.add(new DailyFragment());
        list.add(new ThemeFragment());
        list.add(new SpecialFragment());
        list.add(new HotFragment());
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        view.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), list);
        view.setAdapter(fragmentAdapter);
    }

    @Override
    protected void initData() {
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(getContext(), CalendarActivity.class);
                startActivityForResult(it,10);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10&&resultCode==20) {
            String date = data.getStringExtra("date");
            bundle.putString("date",date);
            Log.e("tag",date);
            dailyFragment.setArguments(bundle);
        }
    }
}
