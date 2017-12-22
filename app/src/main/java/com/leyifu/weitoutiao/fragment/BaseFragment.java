package com.leyifu.weitoutiao.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.leyifu.weitoutiao.act.BaseActivity;
import com.leyifu.weitoutiao.interf.IBasePresenter;
import com.leyifu.weitoutiao.interf.IBaseView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.RxFragment;

/**
 * Created by hahaha on 2017/12/22 0022.
 */

public abstract class BaseFragment<T extends IBasePresenter> extends RxFragment implements IBaseView<T> {

    private T presenter;

    /**
     * 绑定布局文件
     */
    protected abstract void attachLayoutId();


    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化toolbar
     *
     * @param toolbar
     * @param homeAsUpEnable
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnable, String title) {
        ((BaseActivity) getActivity()).initToolBar(toolbar, homeAsUpEnable, title);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setPresenter(presenter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public <T1> LifecycleTransformer<T1> bindToLife() {
        return null;
    }
}
