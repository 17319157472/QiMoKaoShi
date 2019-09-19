package com.example.qimokaoshi.calllback;

import com.example.qimokaoshi.bean.TitleBean;

public interface CallBack {
    void onSuess(TitleBean titleBean);
    void onFail(String s);
}
