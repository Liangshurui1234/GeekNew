package com.example.administrator.geeknew.ZhihuDaily_Fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.adapter.Zhihuribao_Adapter;
import com.example.administrator.geeknew.base.BaseFragment;
import com.example.administrator.geeknew.bean.Bannerbean;
import com.example.administrator.geeknew.presenter.DailyP;
import com.example.administrator.geeknew.view.DailyV;
import java.util.ArrayList;
import butterknife.BindView;
/**
 * A simple {@link Fragment} subclass.
 */
public class DailyFragment extends BaseFragment<DailyV, DailyP>
        implements DailyV {

    @BindView(R.id.lv)
    RecyclerView lv;
    private ArrayList<Bannerbean.StoriesBean> list;
    private ArrayList<Bannerbean.TopStoriesBean> beans;
    private Zhihuribao_Adapter zhihuribao_adapter;
    private String date;

    @Override
    protected DailyP initPresenter() {
        return new DailyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void initView() {
        initArgument();
        list = new ArrayList<>();
        beans = new ArrayList<>();
        zhihuribao_adapter = new Zhihuribao_Adapter(beans, list, getActivity());
        lv.setAdapter(zhihuribao_adapter);
        lv.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initArgument() {
//        Bundle arguments = getArguments();
//        date = arguments.getString("date");
//        Log.e("TAG",date);

    }

    @Override
    public void setData(Bannerbean bean) {
        list.addAll(bean.getStories());
        beans.addAll(bean.getTop_stories());
        zhihuribao_adapter.setList(beans);
        zhihuribao_adapter.setDataBeans(list);
        zhihuribao_adapter.notifyDataSetChanged();

    }



}
