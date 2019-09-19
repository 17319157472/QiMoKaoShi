package com.example.qimokaoshi;

import com.example.qimokaoshi.bean.ListBean;
import com.example.qimokaoshi.bean.TitleBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainServer {
    String TITLE="https://www.wanandroid.com/project/";
    @GET("tree/json")
    Observable<TitleBean> getTitle();


    String ITEM="https://www.wanandroid.com/project/list/1/";
    @GET("json?")
    Observable<ListBean> getList(@Query("cid") int cid);
}
