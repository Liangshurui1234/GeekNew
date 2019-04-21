package com.example.administrator.geeknew.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.adapter.Special_Adapter2;
import com.example.administrator.geeknew.api.MyServer;
import com.example.administrator.geeknew.bean.Specialbean2;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpecialActivity extends AppCompatActivity {

    private RecyclerView mLv;
    private ArrayList<Specialbean2.StoriesBean>list;
    private Special_Adapter2 special_adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);
        initView();
        initData();
    }

    private void initData() {
       Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Retrofit build = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MyServer.Url)
                .build();
        MyServer myServer = build.create(MyServer.class);
        Observable<Specialbean2> speci2 = myServer.getSpeci2(id);
        speci2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Specialbean2>() {
                    @Override
                    public void accept(Specialbean2 specialbean2) throws Exception {
                        list.addAll(specialbean2.getStories());
                        special_adapter2.setList(list);
                        special_adapter2.notifyDataSetChanged();
                    }
                });


    }

    private void initView() {
        mLv = (RecyclerView) findViewById(R.id.lv);
        list=new ArrayList<>();
        special_adapter2 = new Special_Adapter2(list, this);
        mLv.setAdapter(special_adapter2);
        mLv.setLayoutManager(new LinearLayoutManager(this));
    }
}
