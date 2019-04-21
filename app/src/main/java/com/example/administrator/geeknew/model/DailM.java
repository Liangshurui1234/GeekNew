package com.example.administrator.geeknew.model;

import com.example.administrator.geeknew.api.CallBack;
import com.example.administrator.geeknew.api.MyServer;
import com.example.administrator.geeknew.base.BaseModel;
import com.example.administrator.geeknew.bean.Bannerbean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailM extends BaseModel {
    public void getData(final CallBack<Bannerbean> callBack) {
        Retrofit build = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MyServer.Url)
                .build();
        MyServer myServer = build.create(MyServer.class);
        Observable<Bannerbean> data = myServer.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bannerbean>() {
                    @Override
                    public void accept(Bannerbean bannerbean) throws Exception {
                        callBack.onSusser(bannerbean);
                    }
                });
    }
}
