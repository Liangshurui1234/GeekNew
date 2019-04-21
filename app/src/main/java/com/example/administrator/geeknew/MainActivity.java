package com.example.administrator.geeknew;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.geeknew.base.BaseActivity;
import com.example.administrator.geeknew.fragment.AboutFragment;
import com.example.administrator.geeknew.fragment.CollectFragment;
import com.example.administrator.geeknew.fragment.GankFragment;
import com.example.administrator.geeknew.fragment.GoldFragment;
import com.example.administrator.geeknew.fragment.SettingFragment;
import com.example.administrator.geeknew.fragment.V2exFragment;
import com.example.administrator.geeknew.fragment.WeChatFragment;
import com.example.administrator.geeknew.fragment.ZhihuDailyNewsFragment;
import com.example.administrator.geeknew.presenter.Mypresenter;
import com.example.administrator.geeknew.utli.ToastUtil;
import com.example.administrator.geeknew.view.Myview;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;


public class MainActivity extends BaseActivity<Mypresenter, Myview> implements Myview {

    @BindView(R.id.toob)
    Toolbar toob;
    @BindView(R.id.lv)
    FrameLayout lv;
    @BindView(R.id.ne)
    NavigationView ne;
    @BindView(R.id.dr)
    DrawerLayout dr;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    private final int TYPE_ZHIHU = 0;
    private final int TYPE_WECHAT = 1;
    private final int TYPE_GANK = 2;
    private final int TYPE_GOLD = 3;
    private final int TYPE_V2EX = 4;
    private final int TYPE_COLLECT = 5;
    private final int TYPE_SETTING = 6;
    private final int TYPE_ABOUT = 7;
    private MenuItem mSearchItem;
    private ArrayList<Fragment> mFragments;
    private ArrayList<Integer> mTitles;
    private FragmentManager mManager;
    private int mLastFragmentPosition = 0;
    //梁书瑞
    @Override
    protected Mypresenter initPresenter() {
        return new Mypresenter();
    }

    @Override
    protected int getActivityId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mManager = getSupportFragmentManager();
        setSupportActionBar(toob);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dr, toob, R.string.app_name, R.string.app_name);

        dr.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        initFragments();
        initTitles();
        addZhihuDailyNewsFragment();
    }
    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(R.id.zhihu);
        mTitles.add(R.id.wechat);
        mTitles.add(R.id.gank);
        mTitles.add(R.id.gold);
        mTitles.add(R.id.v2ex);
        mTitles.add(R.id.collect);
        mTitles.add(R.id.settings);
        mTitles.add(R.id.about);
    }
    private void addZhihuDailyNewsFragment() {
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.lv,mFragments.get(0));
        transaction.commit();

        toob.setTitle(mTitles.get(0));
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new ZhihuDailyNewsFragment());
        mFragments.add(new WeChatFragment());
        mFragments.add(new GankFragment());
        mFragments.add(new GoldFragment());
        mFragments.add(new V2exFragment());
        mFragments.add(new CollectFragment());
        mFragments.add(new SettingFragment());
        mFragments.add(new AboutFragment());
    }
    @Override
    protected void initListener() {
        ne.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId != R.id.info_title && itemId != R.id.option_title) {
                    menuItem.setChecked(true);
                    text.setText(menuItem.getTitle());
                    switch (menuItem.getItemId()) {
                        case R.id.zhihu:
                            switchFragments(TYPE_ZHIHU);
                            break;
                        case R.id.wechat:
                            switchFragments(TYPE_WECHAT);
                            break;
                        case R.id.gank:
                            switchFragments(TYPE_GANK);
                            break;
                        case R.id.gold:
                            switchFragments(TYPE_GOLD);
                            break;
                        case R.id.v2ex:
                            switchFragments(TYPE_V2EX);
                            break;
                        case R.id.collect:
                            switchFragments(TYPE_COLLECT);
                            break;
                        case R.id.settings:
                            switchFragments(TYPE_SETTING);
                            break;
                        case R.id.about:
                            switchFragments(TYPE_ABOUT);
                            break;
                    }
                    dr.closeDrawer(Gravity.LEFT);
                } else {
                    menuItem.setChecked(false);
                }

                return false;
            }
        });
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //提交搜索内容时的监听
                ToastUtil.showShort("提交的内容:"+query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //文本发生改变的监听
                ToastUtil.showShort(newText);
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //搜索框展开
                ToastUtil.showShort("展开");
            }

            @Override
            public void onSearchViewClosed() {
                //搜索框关闭
                ToastUtil.showShort("关闭");
            }
        });
    }
    private void switchFragments(int type) {
        //本质显示一个framgnet,隐藏一个
        //要显示的fragment
        Fragment fragment = mFragments.get(type);
        //要隐藏的碎片
        Fragment hideFragment = mFragments.get(mLastFragmentPosition);
        FragmentTransaction transaction = mManager.beginTransaction();
        if (!fragment.isAdded()){
            transaction.add(R.id.lv,fragment);
        }

        transaction.hide(hideFragment);
        transaction.show(fragment);
        transaction.commit();

        mLastFragmentPosition = type;

        //显示隐藏搜索框
        if (type == TYPE_WECHAT|| type== TYPE_GANK){
            mSearchItem.setVisible(true);
        }else {
            mSearchItem.setVisible(false);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        mSearchItem = menu.findItem(R.id.action_search);
        mSearchItem.setVisible(false);
        searchView.setMenuItem(mSearchItem);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
//    public void getFragment(Fragment fragment) {
//        FragmentManager supportFragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.lv, fragment);
//        fragmentTransaction.commit();
//    }



}
