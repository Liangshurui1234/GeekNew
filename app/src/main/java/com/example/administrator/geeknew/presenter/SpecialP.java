package com.example.administrator.geeknew.presenter;

import com.example.administrator.geeknew.api.CallBack;
import com.example.administrator.geeknew.base.Basepresenter;
import com.example.administrator.geeknew.bean.Specialbean;
import com.example.administrator.geeknew.model.SpecialpM;
import com.example.administrator.geeknew.view.SpecialV;

public class SpecialP extends Basepresenter<SpecialV> {
    private SpecialpM specialpM;
    @Override
    protected void initModel() {
    specialpM=new SpecialpM();
        mModels.add(specialpM);
    }
    public void getData(){
        specialpM.getData(new CallBack<Specialbean>(){

            @Override
            public void onSusser(Specialbean bean) {
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
