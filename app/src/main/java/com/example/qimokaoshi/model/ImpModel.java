package com.example.qimokaoshi.model;

import com.example.qimokaoshi.MainServer;
import com.example.qimokaoshi.bean.TitleBean;

import com.example.qimokaoshi.calllback.CallBack;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImpModel implements Model{
    @Override
    public void getData(final CallBack callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainServer.TITLE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MainServer mainServer = retrofit.create(MainServer.class);
        Observable<TitleBean> observable = mainServer.getTitle();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TitleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TitleBean titleBean) {
                        callback.onSuess(titleBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                    callback.onFail("网络请求失败"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
