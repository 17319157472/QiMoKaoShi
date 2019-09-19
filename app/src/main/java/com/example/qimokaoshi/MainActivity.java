package com.example.qimokaoshi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.qimokaoshi.adapter.PagerAdapter_view;
import com.example.qimokaoshi.bean.TitleBean;
import com.example.qimokaoshi.fragment.ItemFragment;
import com.example.qimokaoshi.fragment.WebFragment;
import com.example.qimokaoshi.persetrn.ImpPersentn;
import com.example.qimokaoshi.view.MianView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MianView {

    private TabLayout mTab;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
//        https://www.wanandroid.com/project/tree/json
//        https://www.wanandroid.com/project/list/1/json?cid=294

    }

    private void initData() {
        new ImpPersentn(this).getData();
    }

    private void initView() {
        //找控件
        mTab = (TabLayout) findViewById(R.id.tab);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
    }

    @Override
    public void onSuess(TitleBean titleBean) {
        //网络请求成功
        List<TitleBean.DataBean> data = titleBean.getData();
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (TitleBean.DataBean dataBean :data) {
            titles.add(dataBean.getName());
            fragments.add(new ItemFragment(dataBean.getCourseId()));
        }
        fragments.add(new WebFragment());
        titles.add("web");
        //创建适配器
        PagerAdapter_view pagerAdapter_view = new PagerAdapter_view(getSupportFragmentManager(), titles, fragments);
        mViewPager.setAdapter(pagerAdapter_view);//绑定适配器
        mTab.setupWithViewPager(mViewPager);//关联
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();//网络失败
    }
}
