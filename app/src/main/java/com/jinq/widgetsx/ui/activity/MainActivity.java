package com.jinq.widgetsx.ui.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.jinq.widgetsx.R;
import com.jinq.widgetsx.base.BaseActivity;
import com.jinq.widgetsx.base.BasePresenter;
import com.jinq.widgetsx.ui.fragment.HomeFragment;
import com.jinq.widgetsx.ui.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    @BindView(R.id.mContainer)
    FrameLayout mContainer;
    @BindView(R.id.bottomBar)
    BottomNavigationBar mBottomBar;

    private final Fragment[] TAB_FRAGMENT = new Fragment[]{
            new HomeFragment(),new MineFragment(),new MineFragment()};

    private Fragment mCurrentFragment = new Fragment();


    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        setStatusBarColorTransparent(true);
        ButterKnife.bind(this);

        //bottom
        mBottomBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        mBottomBar
                .addItem(new BottomNavigationItem(R.drawable.tab_mall_pre, getString(R.string.home))
                        .setActiveColor(getResources().getColor(R.color.baseTheme))
                        .setInactiveIcon(getResources().getDrawable(R.drawable.tab_mall)))
                .addItem(new BottomNavigationItem(R.drawable.tab_mall_pre, getString(R.string.circle))
                        .setActiveColor(getResources().getColor(R.color.baseTheme))
                        .setInactiveIcon(getResources().getDrawable(R.drawable.tab_mall)))
                .addItem(new BottomNavigationItem(R.drawable.tab_mall_pre, getString(R.string.mine))
                        .setActiveColor(getResources().getColor(R.color.baseTheme))
                        .setInactiveIcon(getResources().getDrawable(R.drawable.tab_mall)))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomBar.setTabSelectedListener(this);
    }

    @Override
    protected void loadData() {
        showFragment(TAB_FRAGMENT[0]);
    }

    @Override
    protected void dismissData() {

    }

    private void showFragment(Fragment fragment) {
        if (mCurrentFragment != fragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(mCurrentFragment);
            mCurrentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.mContainer, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }
        }
    }

    @Override
    public void onTabSelected(int position) {
        showFragment(TAB_FRAGMENT[position]);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
