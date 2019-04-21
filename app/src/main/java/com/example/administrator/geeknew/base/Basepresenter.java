package com.example.administrator.geeknew.base;

import java.util.ArrayList;

public abstract class Basepresenter<V extends BaseView> {
    protected V mMvpView;
    protected ArrayList<BaseModel> mModels = new ArrayList<>();

    public Basepresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void bind(V view) {
        this.mMvpView = view;
    }

    public void onDestory() {
        //打断P层和V层的联系,
        mMvpView = null;
        //掐断网络请求
        if (mModels.size()>0){
            for (BaseModel model :mModels) {
                model.onDestory();
            }
            mModels.clear();
        }
    }
}
