package com.leyifu.weitoutiao;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by hahaha on 2017/12/6 0006.
 */

public class InitApp extends MultiDexApplication {

    public static Context getContext;

    @Override
    public void onCreate() {
        super.onCreate();

        getContext = getApplicationContext();
    }

}
