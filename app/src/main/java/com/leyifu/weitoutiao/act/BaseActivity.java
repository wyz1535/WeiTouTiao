package com.leyifu.weitoutiao.act;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.afollestad.materialdialogs.color.CircleView;
import com.leyifu.weitoutiao.R;
import com.leyifu.weitoutiao.util.ActivityCollections;
import com.leyifu.weitoutiao.util.SettingUtil;
import com.leyifu.weitoutiao.weiget.Constant;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrInterface;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public class BaseActivity extends RxAppCompatActivity {

    private static final String TAG = "BaseActivity";
    private SlidrInterface slidrInterface;
    private int customIconValue = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCollections.addActivity(this);

        customIconValue = SettingUtil.getInstance().getCustomIconValue();

//        initSlidable();
    }

    /**
     * 初始化滑动返回
     */
    private void initSlidable() {

        int slidable = SettingUtil.getInstance().getSlidable();

        if (slidable != Constant.SLIDABLE_DISABLE) {

            SlidrConfig config = new SlidrConfig.Builder()
                    .edge(slidable == Constant.SLIDABLE_EDGE)
                    .build();

            slidrInterface = Slidr.attach(this, config);

        }
    }

    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {

        toolbar.setTitle(title);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e(TAG, "BaseActivity: " + getClass().getSimpleName());

        int color = SettingUtil.getInstance().getThemeColor();

        int drawable = Constant.ICONS_DRAWABLES[SettingUtil.getInstance().getCustomIconValue()];

        if (getSupportActionBar() != null) {

            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {

            getWindow().setStatusBarColor(CircleView.shiftColorDown(color));

            ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(
                    getString(R.string.app_name),
                    BitmapFactory.decodeResource(getResources(), drawable),
                    color);

            setTaskDescription(taskDescription);

            if (SettingUtil.getInstance().getNavBarColor()) {

                getWindow().setNavigationBarColor(CircleView.shiftColorDown(color));

            } else {

                getWindow().setNavigationBarColor(Color.BLACK);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        // Fragment 逐个出栈
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {

            super.onBackPressed();
        } else {

            getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    protected void onStop() {

        if (customIconValue != SettingUtil.getInstance().getCustomIconValue()) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    String act = ".SplashActivity_";

                    for (String s : Constant.ICONS_TYPE) {

                        getPackageManager().setComponentEnabledSetting(
                                new ComponentName(BaseActivity.this, getPackageName() + act + s),
                                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
                                PackageManager.DONT_KILL_APP
                        );
                    }

                    act += Constant.ICONS_TYPE[SettingUtil.getInstance().getCustomIconValue()];

                    getPackageManager().setComponentEnabledSetting(
                            new ComponentName(BaseActivity.this, getPackageName() + act),
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP
                    );

                }
            }).start();
        }

        super.onStop();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityCollections.removeActivity(this);
    }
}
