package com.example.administrator.geeknew.ZhihuDaily_Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.adapter.Hot_Adapter;
import com.example.administrator.geeknew.base.BaseFragment;
import com.example.administrator.geeknew.bean.Hotbean;
import com.example.administrator.geeknew.presenter.Hotp;
import com.example.administrator.geeknew.view.HotV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment<HotV, Hotp> implements HotV {

    
    @BindView(R.id.lv)
    RecyclerView lv;
    private ArrayList<Hotbean.RecentBean>list;
    private Hot_Adapter hot_adapter;

    public HotFragment() {
        // Required empty public constructor
    }

    @Override
    protected Hotp initPresenter() {
        return new Hotp();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }




    @Override
    protected void initView() {
        list=new ArrayList<>();
        hot_adapter = new Hot_Adapter(list, getActivity());
        lv.setAdapter(hot_adapter);
        lv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void setData(Hotbean bean) {
    list.addAll(bean.getRecent());
    hot_adapter.setList(list);
    hot_adapter.notifyDataSetChanged();
    }
}
