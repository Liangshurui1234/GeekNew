package com.example.administrator.geeknew.model;

import com.example.administrator.geeknew.api.CallBack;
import com.example.administrator.geeknew.api.MyServer;
import com.example.administrator.geeknew.base.BaseModel;
import com.example.administrator.geeknew.bean.Specialbean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpecialpM extends BaseModel {
    public void getData(final CallBack<Specialbean> callBack) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(MyServer.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = build.create(MyServer.class);
        Observable<Specialbean> speci = myServer.getSpeci();
        speci.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Specialbean>() {
                    @Override
                    public void accept(Specialbean specialbean) throws Exception {
                        callBack.onSusser(specialbean);
                    }
                });
    }
}
