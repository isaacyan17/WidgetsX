package com.jinq.widgetsx.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.jinq.widgetsx.R;
import com.jinq.widgetsx.base.BaseFragment;
import com.jinq.widgetsx.base.BasePresenter;

public class MineFragment extends BaseFragment{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void dismissData() {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}
