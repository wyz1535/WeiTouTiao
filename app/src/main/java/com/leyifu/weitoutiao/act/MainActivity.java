package com.leyifu.weitoutiao.act;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.leyifu.weitoutiao.R;
import com.leyifu.weitoutiao.fragment.AccountFragment;
import com.leyifu.weitoutiao.fragment.NewsFragment;
import com.leyifu.weitoutiao.fragment.PictureFragment;
import com.leyifu.weitoutiao.fragment.VideoFragment;
import com.leyifu.weitoutiao.util.SettingUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    //    @BindView(R.id.app_bar_layout)
//    AppBarLayout appBarLayout;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.rb_news)
    RadioButton rbNews;
    @BindView(R.id.rb_picture)
    RadioButton rbPicture;
    @BindView(R.id.rb_video)
    RadioButton rbVideo;
    @BindView(R.id.rb_account)
    RadioButton rbAccount;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.iv_ic_san)
    ImageView ivIcSan;
    @BindView(R.id.tv_tool_bar_title)
    TextView tvToolBarTitle;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.ic_sourch)
    ImageView icSourch;
    private String TAG = getClass().getSimpleName();

    private static final int PREMISSIONCODE = 200;
    private String[] premissionArray = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS};
    private TextView tvNavHead;
    private NewsFragment newsFragment;
    private PictureFragment pictureFragment;
    private VideoFragment videoFragment;
    private AccountFragment accountFragment;
    private Fragment currentFragment;
    private FragmentManager supportFragmentManager;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //申请危险权限
        applyPremossion();

        initDrawable();

        initMenu();

        initFragment();

        Log.e(TAG, "onCreate: " + SettingUtil.getInstance().getIsFirstToApp());
        if (SettingUtil.getInstance().getIsFirstToApp()) {
            showTapTarget();
        }

    }


    private void initFragment() {

        newsFragment = NewsFragment.getInstance();
        pictureFragment = PictureFragment.getInstance();
        videoFragment = VideoFragment.getInstance();
        accountFragment = AccountFragment.getInstance();

        fragments.add(newsFragment);
        fragments.add(pictureFragment);
        fragments.add(videoFragment);
        fragments.add(accountFragment);

        supportFragmentManager = getSupportFragmentManager();

        if (!fragments.get(0).isAdded()) {
            supportFragmentManager.beginTransaction().add(R.id.frame_layout, fragments.get(0)).commit();

            currentFragment = fragments.get(0);
            tvToolBarTitle.setText(getString(R.string.rb_news));
        }

    }

    private void initMenu() {
        navigationView.inflateMenu(R.menu.navigation_menu);

        View headerView = navigationView.getHeaderView(0);

        tvNavHead = ((TextView) headerView.findViewById(R.id.tv_nav_head));

        tvNavHead.setText("168wyz");

    }

    private void showTapTarget() {

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        Rect rect = new Rect(0, displayMetrics.heightPixels, 0, displayMetrics.heightPixels);

        rect.offset(displayMetrics.heightPixels / 8, -56);

        TapTargetSequence tapTargetSequence = new TapTargetSequence(this)
                .targets(TapTarget.forToolbarMenuItem(mainToolbar, R.id.ic_sourch, getString(R.string.click_sourch))
                                .dimColor(R.color.black)
                                .outerCircleColor(R.color.colorAccent)
                                .drawShadow(true)
                                .id(1),
                        TapTarget.forToolbarMenuItem(mainToolbar, R.id.iv_ic_san, getString(R.string.click_navigation))
                                .dimColor(R.color.black)
                                .outerCircleColor(R.color.colorAccent)
                                .drawShadow(true)
                                .id(2),
                        TapTarget.forBounds(rect, "点击这里切换新闻", "双击返回顶部\n再次双击刷新当前页面")
                                .dimColor(R.color.black)
                                .outerCircleColor(R.color.colorPrimary)
                                .targetRadius(60)
                                .transparentTarget(true)
                                .drawShadow(true)
                                .id(3)
                ).listener(new TapTargetSequence.Listener() {
                    @Override
                    public void onSequenceFinish() {
                        SettingUtil.getInstance().setFirstToApp(false);
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        SettingUtil.getInstance().setFirstToApp(false);
                    }
                });

        tapTargetSequence.start();

    }

    @OnClick({R.id.rb_news, R.id.rb_picture, R.id.rb_video, R.id.rb_account, R.id.iv_ic_san, R.id.ic_sourch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_news:
                tvToolBarTitle.setText(getString(R.string.rb_news));
                showOrHideFragment(fragments.get(0));
                break;
            case R.id.rb_picture:
                tvToolBarTitle.setText(getString(R.string.rb_picture));
                showOrHideFragment(fragments.get(1));
                break;
            case R.id.rb_video:
                tvToolBarTitle.setText(getString(R.string.rb_video));
                showOrHideFragment(fragments.get(2));
                break;
            case R.id.rb_account:
                tvToolBarTitle.setText(getString(R.string.rb_account));
                showOrHideFragment(fragments.get(3));
                break;
            case R.id.iv_ic_san:
                drawerLayout.openDrawer(navigationView);
                break;
            case R.id.ic_sourch:
                break;
            default:
                break;
        }
    }

    private void showOrHideFragment(Fragment fragment) {

        if (fragment == currentFragment) {
            return;
        }

        if (!fragment.isAdded()) {
            supportFragmentManager.beginTransaction().add(R.id.frame_layout, fragment).hide(currentFragment).commit();
        } else {
            supportFragmentManager.beginTransaction().hide(currentFragment).show(fragment).commit();
        }

        currentFragment = fragment;
    }

    /**
     * 设置radiobutton图片大小
     */
    private void initDrawable() {
        Drawable drawableHome = getResources().getDrawable(R.drawable.main_home);
        Drawable drawableBelle = getResources().getDrawable(R.drawable.main_belle);
        Drawable drawableVideo = getResources().getDrawable(R.drawable.main_video);
        Drawable drawableAttention = getResources().getDrawable(R.drawable.main_attention);

        drawableHome.setBounds(0, 0, 40, 40);
        drawableBelle.setBounds(0, 0, 40, 40);
        drawableVideo.setBounds(0, 0, 40, 40);
        drawableAttention.setBounds(0, 0, 40, 40);

        rbNews.setCompoundDrawables(null, drawableHome, null, null);
        rbPicture.setCompoundDrawables(null, drawableBelle, null, null);
        rbVideo.setCompoundDrawables(null, drawableVideo, null, null);
        rbAccount.setCompoundDrawables(null, drawableAttention, null, null);
    }

    private void applyPremossion() {

        for (String premission : premissionArray) {
            if (ContextCompat.checkSelfPermission(this, premission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{premission}, PREMISSIONCODE);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PREMISSIONCODE:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "申请成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "申请失败", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                break;
            default:
                break;
        }
    }


}
