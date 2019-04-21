package com.example.administrator.geeknew.fragment;


import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.base.BaseFragment;
import com.example.administrator.geeknew.presenter.GankP;
import com.example.administrator.geeknew.view.GankV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class GankFragment extends BaseFragment<GankV,GankP>
    implements GankV{
    @Override
    protected GankP initPresenter() {
        return new GankP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }
}
