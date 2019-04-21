package com.example.administrator.geeknew.fragment;


import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.base.BaseFragment;
import com.example.administrator.geeknew.presenter.EmptyP;
import com.example.administrator.geeknew.view.EmptyV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class CollectFragment extends BaseFragment<EmptyV,EmptyP>
    implements EmptyV{
    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }
}
