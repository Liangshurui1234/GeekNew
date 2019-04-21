package com.example.administrator.geeknew.Activity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.adapter.RlvShowAdapter;
import com.example.administrator.geeknew.api.SimpleItemTouchCallBack;
import com.example.administrator.geeknew.base.BaseActivity;
import com.example.administrator.geeknew.base.Constants;
import com.example.administrator.geeknew.bean.GoldTitleBean;
import com.example.administrator.geeknew.presenter.EmptyP;
import com.example.administrator.geeknew.view.EmptyV;

import java.util.ArrayList;

import butterknife.BindView;

public class ShowActivity  extends BaseActivity<EmptyP,EmptyV> implements EmptyV{


    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private ArrayList<GoldTitleBean> mTitles;


    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getActivityId() {
        return  R.layout.activity_show;
    }
    @Override
    protected void initView() {
        mToolBar.setTitle(R.string.special_show);
        mToolBar.setNavigationIcon(R.mipmap.ic_close);
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAct();
            }
        });

        mTitles = (ArrayList<GoldTitleBean>) getIntent().getSerializableExtra(Constants.DATA);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        RlvShowAdapter adapter = new RlvShowAdapter(mTitles);
        mRlv.setAdapter(adapter);
        mRlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        //拖拽移动和左滑删除
        SimpleItemTouchCallBack simpleItemTouchCallBack = new SimpleItemTouchCallBack(adapter);
        //true左滑删除条目false不删除
        simpleItemTouchCallBack.setSwipeEnable(false);
        ItemTouchHelper helper = new ItemTouchHelper(simpleItemTouchCallBack);
        helper.attachToRecyclerView(mRlv);
    }



    private void finishAct() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA,mTitles);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAct();
    }
}
