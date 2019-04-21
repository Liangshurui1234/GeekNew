package com.example.administrator.geeknew.ZhihuDaily_Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.adapter.Special_Adapter;
import com.example.administrator.geeknew.base.BaseFragment;
import com.example.administrator.geeknew.bean.Specialbean;
import com.example.administrator.geeknew.presenter.SpecialP;
import com.example.administrator.geeknew.view.SpecialV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialFragment extends BaseFragment<SpecialV, SpecialP> implements SpecialV {


    @BindView(R.id.lv)
    RecyclerView lv;
    private ArrayList<Specialbean.DataBean> list;
    private Special_Adapter special_adapter;

    public SpecialFragment() {
        // Required empty public constructor
    }

    @Override
    protected SpecialP initPresenter() {
        return new SpecialP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_special;
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        special_adapter = new Special_Adapter(list, getActivity());
        lv.setAdapter(special_adapter);
        lv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    public void setData(Specialbean bean) {
        list.addAll(bean.getData());
        special_adapter.setList(list);
        special_adapter.notifyDataSetChanged();
    }


}
