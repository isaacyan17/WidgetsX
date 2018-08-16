package com.jinq.widgetsx.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;

import com.jinq.widgetsx.R;
import com.jinq.widgetsx.base.BaseFragment;
import com.jinq.widgetsx.base.BasePresenter;
import com.jinq.widgetsx.ui.activity.wifitransfer.TransferActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment{
    @BindView(R.id.serverCl)
    ConstraintLayout mServerCl;

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

    @OnClick({R.id.serverCl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.serverCl:
                startActivity(new Intent(getContext(), TransferActivity.class));
                break;

        }
    }
}
