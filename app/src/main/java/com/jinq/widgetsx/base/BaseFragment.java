package com.jinq.widgetsx.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private BasePresenter presenter = null;

    protected Activity activity;
    Unbinder unbinder;
    /** 日志输出标志 **/
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 获得全局的，防止使用getActivity()为空
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(activity).inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view, savedInstanceState);
        loadData();
        presenter = bindPresenter();

        setListener();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        dismissData();
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
            System.gc();
        }
    }

    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 该抽象方法就是 初始化view
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 执行数据的加载
     */
    protected abstract void loadData();

    protected abstract void dismissData();

    public void setListener( ){

    }

    /**
     * 绑定presenter，主要用于销毁工作
     *
     * @return
     */
    protected abstract BasePresenter bindPresenter();

}
