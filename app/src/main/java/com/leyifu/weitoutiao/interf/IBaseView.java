package com.leyifu.weitoutiao.interf;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by hahaha on 2017/12/22 0022.
 */

public interface IBaseView<T> {


    /**
     * 显示加载动画
     */
    void onShowLoading();

    /**
     * 隐藏动画
     */
    void onHideLoading();

    /**
     * 网络错误
     */
    void onShowNetError();

    /**
     * 设置presenter
     */
    void setPresenter(T presenter);

    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();
}
