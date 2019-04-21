package com.example.administrator.geeknew.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseActivity<P extends Basepresenter,V extends BaseView> extends AppCompatActivity {
    protected P p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityId());
        ButterKnife.bind(this);
        p=initPresenter();
        p.bind((V)this);
        initView();
        initData();
        initListener();
    }

    protected void initListener() {
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected abstract P initPresenter();

    protected abstract int getActivityId();
}
