package com.example.qimokaoshi.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.qimokaoshi.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment {


    private View view;
    private WebView mWebView;

    public WebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflate = inflater.inflate(R.layout.fragment_web2, container, false);

        initView(inflate);
        EventBus.getDefault().register(this);
        return inflate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView(View inflate) {
        mWebView = (WebView) inflate.findViewById(R.id.web_view);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData(String link){
        mWebView.loadUrl(link);//给数据
        mWebView.setWebViewClient(new WebViewClient());//在页面
    }


}
