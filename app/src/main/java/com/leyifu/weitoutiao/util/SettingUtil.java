package com.leyifu.weitoutiao.util;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

import com.leyifu.weitoutiao.InitApp;
import com.leyifu.weitoutiao.R;
import constant.Constant;

/**
 * Created by hahaha on 2017/12/6 0006.
 */

public class SettingUtil {

    private static SettingUtil instance;
    private final SharedPreferences setting;

    private SettingUtil() {

        setting = PreferenceManager.getDefaultSharedPreferences(InitApp.getContext);
    }

    public static SettingUtil getInstance() {

        if (instance == null) {

            instance = new SettingUtil();

        }

        return instance;

    }

    /**
     * @return 获取滑动返回值
     */
    public int getSlidable() {

        String slidable = setting.getString("slidable", "1");

        return Integer.parseInt(slidable);
    }


    /**
     * @return  获取主题颜色
     */
    public int getThemeColor() {

        int defaultColor = InitApp.getContext.getResources().getColor(R.color.colorAccent);

        int color = setting.getInt("color", defaultColor);

        if (color != 0 && (Color.alpha(color) != 255)) {

            return defaultColor;
        }

        return color;
    }

    public void setThemeColor(int color) {
        setting.edit().putInt("color",color).apply();
    }

    /**
     * @return 获取图标值
     */
    public int getCustomIconValue(){

        String customIcon = setting.getString("custom_icon", "0");

        return Integer.parseInt(customIcon);
    }

    /**
     * @return  是否改变底部导航栏颜色
     */
    public boolean getNavBarColor() {

        return setting.getBoolean("nav_bar", false);

    }

    public void setFirstToApp(boolean flag) {
        setting.edit().putBoolean(Constant.ISFIRSTTOAPP, flag).apply();
    }

    public boolean getIsFirstToApp() {
        return setting.getBoolean(Constant.ISFIRSTTOAPP, true);
    }


}
