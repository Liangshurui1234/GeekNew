package com.example.administrator.geeknew.ZhihuDaily_Fragment;


import android.support.v4.app.Fragment;

import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.base.BaseFragment;
import com.example.administrator.geeknew.presenter.ThemeP;
import com.example.administrator.geeknew.view.ThemeV;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThemeFragment extends BaseFragment <ThemeV,ThemeP>implements ThemeV{


    @Override
    protected ThemeP initPresenter() {
        return new ThemeP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_theme;
    }
}
