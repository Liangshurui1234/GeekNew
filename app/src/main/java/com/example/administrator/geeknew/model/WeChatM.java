package com.example.administrator.geeknew.model;

import com.example.administrator.geeknew.api.CallBack;
import com.example.administrator.geeknew.api.MyServer;
import com.example.administrator.geeknew.base.BaseModel;
import com.example.administrator.geeknew.bean.WeChatbean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeChatM extends BaseModel {
    public void getData(final CallBack<WeChatbean> callBack) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(MyServer.Wech)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyServer myServer = build.create(MyServer.class);
        Observable<WeChatbean> wech = myServer.getWech();

        wech.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeChatbean>() {
                    @Override
                    public void accept(WeChatbean weChatbean) throws Exception {
                        callBack.onSusser(weChatbean);
                    }
                });
    }
}
