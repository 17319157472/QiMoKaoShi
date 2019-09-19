package com.example.qimokaoshi.persetrn;

import com.example.qimokaoshi.bean.TitleBean;
import com.example.qimokaoshi.calllback.CallBack;
import com.example.qimokaoshi.model.ImpModel;
import com.example.qimokaoshi.view.MianView;

public class ImpPersentn implements Persentn, CallBack {
    private ImpModel impModel;
    private MianView mianView;

    public ImpPersentn(MianView mianView) {
        impModel=new ImpModel();
        this.mianView = mianView;
    }

    @Override
    public void getData() {
            if (impModel!=null){
                impModel.getData(this);
            }
    }

    @Override
    public void onSuess(TitleBean titleBean) {
        if (mianView!=null){
            mianView.onSuess(titleBean);
        }
    }

    @Override
    public void onFail(String s) {
        if (mianView!=null){
            mianView.onFail(s);
        }
    }
}
