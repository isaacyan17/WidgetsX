package com.jinq.widgetsx.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jinq.widgetsx.R;
import com.jinq.widgetsx.application.App;
import com.jinq.widgetsx.util.LightStatusBarUtil;
import com.jinq.widgetsx.util.RomUtil;


public abstract class BaseActivity extends AppCompatActivity {

    private BasePresenter presenter = null;
    protected Activity self = this;
    /** 是否需要修改状态栏字体颜色 **/
    private boolean mAllowChangeStatusBarTextColor = false;
    /** 状态栏颜色是否透明 **/
    private boolean isStatusBarColorTransparent = false;
    /** 日志输出标志 **/
    protected final String TAG = this.getClass().getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self.setTheme(R.style.BaseAppTheme);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initVariable();
        initView( savedInstanceState );
        loadData();
        presenter = bindPresenter();

        steepStatusBar();
        setListener();
        App.activitys.add(self);
    }

    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        if(mAllowChangeStatusBarTextColor) {
            switch (RomUtil.getLightStatausBarAvailableRomType()) {
                case RomUtil.AvailableRomType.MIUI:
                    LightStatusBarUtil.setLightStatusBar(self, true);
                    break;
                case RomUtil.AvailableRomType.FLYME:
                    LightStatusBarUtil.setLightStatusBar(self, true);
                    break;
                case RomUtil.AvailableRomType.ANDROID_NATIVE:
                    LightStatusBarUtil.setLightStatusBar(self, true);
                    break;
                case RomUtil.AvailableRomType.NA:
                    LightStatusBarUtil.setLightStatusBar(self, false);
                    break;
            }
        }

    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN);
            if (isStatusBarColorTransparent) {
                window.setStatusBarColor(getResources().getColor(R.color.transparent));
            } else {
                window.setStatusBarColor(getResources().getColor(R.color.baseTheme));
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window =getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    public void setListener( ){

    }

    public void setAllowChangeStatusBarTextColor(Boolean isAllow) {
        mAllowChangeStatusBarTextColor = isAllow;
    }

    public void setStatusBarColorTransparent(Boolean isAllow) {
        isStatusBarColorTransparent = isAllow;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissData();
        App.activitys.remove(self);
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
            System.gc();
        }
    }

    /**
     * 绑定presenter，主要用于销毁工作
     *
     * @return
     */
    protected abstract BasePresenter bindPresenter();

    protected abstract  void initVariable();
    protected abstract  void initView( Bundle savedInstanceState);
    protected abstract  void loadData();
    protected abstract void dismissData();

}
