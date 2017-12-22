package com.leyifu.weitoutiao.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.leyifu.weitoutiao.R;
import com.leyifu.weitoutiao.RxBus;
import com.leyifu.weitoutiao.adapter.BaseAdapter;
import com.leyifu.weitoutiao.bean.NewsChannelBean;
import com.leyifu.weitoutiao.db.dao.NewsChannelDao;
import com.leyifu.weitoutiao.fragment.newsframgent.ArticleFragment;
import com.leyifu.weitoutiao.fragment.newsframgent.EssayOrQuestionFragment;
import com.leyifu.weitoutiao.fragment.newsframgent.JokeContentFragment;
import com.leyifu.weitoutiao.util.SettingUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import constant.Constant;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class NewsFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    @BindView(R.id.tab_news)
    TabLayout tabNews;
    @BindView(R.id.iv_news_add)
    ImageView ivNewsAdd;
    @BindView(R.id.news_view_pager)
    ViewPager viewPagerNews;
    Unbinder unbinder;
    @BindView(R.id.ll_news_tab)
    LinearLayout llNewsTab;
    private View view;
    private static NewsFragment mInstance;

    NewsChannelDao dao = new NewsChannelDao();
    Map<String, Fragment> map = new HashMap<>();

    private List<Fragment> fragments;
    private List<String> titles;
    private Observable<Boolean> observable;

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

        llNewsTab.setBackgroundColor(SettingUtil.getInstance().getThemeColor());

        initData();


        return view;
    }

    private void initData() {
        initTabs();

        final BaseAdapter adapter = new BaseAdapter(getChildFragmentManager(), titles, fragments);
        observable = RxBus.getInstance().register(TAG);
        observable.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean isRefresh) throws Exception {
                if (isRefresh) {
                    initTabs();
                    adapter.recreateItem(titles, fragments);
                }
            }
        });

        viewPagerNews.setAdapter(adapter);
        viewPagerNews.setOffscreenPageLimit(15);
        tabNews.setupWithViewPager(viewPagerNews);
        Log.e(TAG, "onCreateView: " + titles + fragments + " &fragments=" + fragments.size());
    }

    private void initTabs() {

        List<NewsChannelBean> channelList = dao.query(1);

        fragments = new ArrayList<>();
        titles = new ArrayList<>();

        if (channelList.size() == 0) {
            dao.addInitData();
            channelList = dao.query(Constant.NEWS_CHANNEL_ENABLE);
        }

        for (NewsChannelBean bean : channelList) {

            Fragment fragment = null;

            String channelId = bean.getChannelId();

            switch (channelId) {
                case "essay_joke":
                    if (map.containsKey(channelId)) {
                        fragments.add(map.get(channelId));
                    } else {
                        fragment = JokeContentFragment.getInstance();
                        fragments.add(fragment);
                    }
                    break;
                case "question_and_answer":
                    if (map.containsKey(channelId)) {
                        fragments.add(map.get(channelId));
                    } else {
                        fragment = EssayOrQuestionFragment.getInstance();
                        fragments.add(fragment);
                    }
                    break;

                default:
                    if (map.containsKey(channelId)) {
                        fragments.add(map.get(channelId));
                    } else {
                        fragment = ArticleFragment.getInstance(channelId);
                        fragments.add(fragment);
                    }
                    break;
            }

            titles.add(bean.getChannelName());

            if (fragment != null) {

                map.put(channelId, fragment);
            }

        }

    }


    @OnClick(R.id.iv_news_add)
    public void onViewClicked() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        RxBus.getInstance().unRegister(TAG, observable);

        if (mInstance != null) {

            mInstance = null;
        }
    }
}
