package com.example.administrator.geeknew.presenter;

import android.support.v7.view.menu.BaseMenuPresenter;

import com.bumptech.glide.request.ResourceCallback;
import com.example.administrator.geeknew.api.CallBack;
import com.example.administrator.geeknew.base.BaseView;
import com.example.administrator.geeknew.base.Basepresenter;
import com.example.administrator.geeknew.bean.Bannerbean;
import com.example.administrator.geeknew.model.DailM;
import com.example.administrator.geeknew.view.DailyV;

import java.util.ResourceBundle;

public class DailyP extends Basepresenter<DailyV>  {
    private DailM dailM;
    @Override
    protected void initModel() {
        dailM=new DailM();
        mModels.add(dailM);
    }
    public void getData(){
        dailM.getData(new CallBack<Bannerbean>(){
            @Override
            public void onSusser(Bannerbean bean) {
                if(bean!=null){
                    if(mMvpView!=null){
                        mMvpView.setData(bean);
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
