package com.leyifu.weitoutiao.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.leyifu.weitoutiao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NewsFragment extends Fragment {

    @BindView(R.id.tab_news)
    TabLayout tabNews;
    @BindView(R.id.iv_news_add)
    ImageView ivNewsAdd;
    @BindView(R.id.news_view_pager)
    ViewPager viewPagerNews;
    Unbinder unbinder;
    private View view;
    private static NewsFragment mInstance;

    @SuppressLint("ValidFragment")
    private NewsFragment() {
    }

    public static NewsFragment getInstance() {

        if (mInstance == null) {
            synchronized (NewsFragment.class) {
                mInstance = new NewsFragment();
            }
        }

        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.iv_news_add)
    public void onViewClicked() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mInstance != null) {

            mInstance = null;
        }
    }
}
