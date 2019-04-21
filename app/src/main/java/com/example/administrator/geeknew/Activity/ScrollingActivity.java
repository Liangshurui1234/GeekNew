package com.example.administrator.geeknew.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.api.MyServer;
import com.example.administrator.geeknew.bean.Bodybean;

import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScrollingActivity extends AppCompatActivity {

    private ImageView mImageHot;
    /**  */
    private TextView mTvHot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        initView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("name");

        Retrofit build = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(MyServer.Url)
                .build();
        MyServer myServer = build.create(MyServer.class);
        Observable<Bodybean> gethot = myServer.getboy(id);
        gethot.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bodybean>() {
                    @Override
                    public void accept(Bodybean hotbean) throws Exception {
                        String image1 = hotbean.getImage();
                        Glide.with(ScrollingActivity.this).load(image1).into(mImageHot);
                        final String html = hotbean.getBody();//需要将转的数据转为String).getBody();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final Spanned html_str = Html.fromHtml(html, new Html.ImageGetter() {
                                    @Override
                                    public Drawable getDrawable(String source) {
                                        Drawable d = null;
                                        URL url = null;
                                        try {
                                            url = new URL(source);
                                            //获取html中的图片数据
                                            d = Drawable.createFromStream(url.openStream(), "");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                                        return d;
                                    }
                                }, null);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mTvHot.setText(html_str);
                                    }
                                });
                            }
                        }).start();


                    }
                });
    }

    private void initView() {
        mImageHot = (ImageView) findViewById(R.id.image_hot);
        mTvHot = (TextView) findViewById(R.id.tv_hot);
    }
}
