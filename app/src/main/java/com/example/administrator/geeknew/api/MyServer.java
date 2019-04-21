package com.example.administrator.geeknew.api;

import com.example.administrator.geeknew.bean.Bannerbean;
import com.example.administrator.geeknew.bean.Bodybean;
import com.example.administrator.geeknew.bean.Hotbean;
import com.example.administrator.geeknew.bean.Specialbean;
import com.example.administrator.geeknew.bean.Specialbean2;
import com.example.administrator.geeknew.bean.WeChatbean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyServer {
    String Url="http://news-at.zhihu.com/api/4/";
    @GET("news/latest")
    Observable<Bannerbean>getData();
    @GET("sections")
    Observable<Specialbean>getSpeci();
    @GET("section/{id}")
    Observable<Specialbean2>getSpeci2(@Path("id")String id);
    @GET("news/hot")
    Observable<Hotbean>gethot();
    @GET("news/{id}")
    Observable<Bodybean>getboy(@Path("id")String id);


    String Wech="http://api.tianapi.com/wxnew/";
    @GET("?key=52b7ec3471ac3bec6846577e79f20e4c&num=20&page=1")
    Observable<WeChatbean>getWech();
}
