package com.example.qimokaoshi.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qimokaoshi.MainServer;
import com.example.qimokaoshi.R;
import com.example.qimokaoshi.adapter.RecyAdapter;
import com.example.qimokaoshi.bean.ListBean;
import com.example.qimokaoshi.bean.TitleBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("ValidFragment")
public class ItemFragment extends Fragment {
    private int id;
    private View view;
    private RecyclerView mRecyView;
    private RecyAdapter recyAdapter;

    public ItemFragment(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.item_fragment, null);
        initView(inflate);
        initData();
        return inflate;
    }

    private static final String TAG = "ItemFragment";
    private void initData() {
        //获取网络数据
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MainServer.ITEM)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MainServer mainServer = retrofit.create(MainServer.class);
        Observable<ListBean> observable = mainServer.getList(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean listBean) {
                        //网络解析数据
                        List<ListBean.DataBean.DatasBean> datas = listBean.getData().getDatas();
                        recyAdapter.getData(datas);//给数据
                    }

                    @Override
                    public void onError(Throwable e) {
                        //网络请求失败
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(View inflate) {
        //找空件
        mRecyView = (RecyclerView) inflate.findViewById(R.id.recy_view);
        mRecyView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyAdapter = new RecyAdapter(getActivity());
        mRecyView.setAdapter(recyAdapter);
        //点击事件
        recyAdapter.setOnClickListener(new RecyAdapter.OnClickListener() {
            @Override
            public void click(int i) {
                //获取数据
                ListBean.DataBean.DatasBean datasBean = recyAdapter.datas.get(i);
                String link = datasBean.getLink();//获取字符串
                //跳转
//                Intent intent = new Intent(getActivity(), WebActivity.class);
//                startActivity(intent);
                EventBus.getDefault().postSticky(link);//传值
            }
        });
    }
}
