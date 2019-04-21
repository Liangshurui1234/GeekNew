package com.example.administrator.geeknew.presenter;


import com.example.administrator.geeknew.api.CallBack;
import com.example.administrator.geeknew.base.Basepresenter;
import com.example.administrator.geeknew.bean.WeChatbean;
import com.example.administrator.geeknew.model.WeChatM;
import com.example.administrator.geeknew.view.WeChatV;

import java.security.BasicPermission;

/**
 * @author xts
 * Created by asus on 2019/4/3.
 */
//梁书瑞

public class WeChatP extends Basepresenter<WeChatV> {
    private WeChatM weChatM;

    @Override
    protected void initModel() {
        weChatM = new WeChatM();
        mModels.add(weChatM);
    }
    public void getData(){
        weChatM.getData(new CallBack<WeChatbean>(){
            @Override
            public void onSusser(WeChatbean bean) {
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
