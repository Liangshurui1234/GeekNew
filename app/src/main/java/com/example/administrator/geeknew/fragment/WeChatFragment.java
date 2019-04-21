package com.example.administrator.geeknew.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.adapter.WeChat_Adapter;
import com.example.administrator.geeknew.base.BaseFragment;
import com.example.administrator.geeknew.bean.WeChatbean;
import com.example.administrator.geeknew.presenter.WeChatP;
import com.example.administrator.geeknew.view.WeChatV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xts
 * Created by asus on 2019/4/3.
 */

public class WeChatFragment extends BaseFragment<WeChatV, WeChatP>
        implements WeChatV {

    @BindView(R.id.lv)
    RecyclerView lv;
    private ArrayList<WeChatbean.NewslistBean> list;
    private WeChat_Adapter weChat_adapter;

    @Override
    protected WeChatP initPresenter() {
        return new WeChatP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void initView() {
        list=new ArrayList<>();
        weChat_adapter = new WeChat_Adapter(list, getActivity());
        lv.setAdapter(weChat_adapter);
        lv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void setData(WeChatbean weChatbean) {
        list.addAll(weChatbean.getNewslist());
        weChat_adapter.setList(list);
        weChat_adapter.notifyDataSetChanged();
    }


}
