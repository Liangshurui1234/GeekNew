package com.example.administrator.geeknew.model;

import com.example.administrator.geeknew.api.CallBack;
import com.example.administrator.geeknew.api.MyServer;
import com.example.administrator.geeknew.base.BaseModel;
import com.example.administrator.geeknew.bean.Hotbean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HotM extends BaseModel {
    public void getData(final CallBack<Hotbean> callBack) {
        Retrofit build = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(MyServer.Url)
                .build();
        MyServer myServer = build.create(MyServer.class);
        Observable<Hotbean> gethot = myServer.gethot();
        gethot.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Hotbean>() {
                    @Override
                    public void accept(Hotbean hotbean) throws Exception {
                            callBack.onSusser(hotbean);
                    }
                });
    }
}
