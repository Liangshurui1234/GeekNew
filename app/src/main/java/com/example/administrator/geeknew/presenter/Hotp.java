package com.example.administrator.geeknew.presenter;

import com.example.administrator.geeknew.api.CallBack;
import com.example.administrator.geeknew.base.Basepresenter;
import com.example.administrator.geeknew.bean.Hotbean;
import com.example.administrator.geeknew.model.HotM;
import com.example.administrator.geeknew.view.HotV;

public class Hotp extends Basepresenter<HotV> {
    private HotM hotM;
    @Override
    protected void initModel() {
        hotM=new HotM();
        mModels.add(hotM);
    }
    public void getData(){
        hotM.getData(new CallBack<Hotbean>(){
            @Override
            public void onSusser(Hotbean bean) {
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
