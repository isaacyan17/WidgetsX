package com.jinq.widgetsx.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreFooterLayout;
import com.jinq.widgetsx.R;

/**
 * Created by Aspsine on 2015/9/2.
 */
public class ClassicLoadMoreFooterView extends SwipeLoadMoreFooterLayout {
    private ProgressBar pbLoadMore;

    public ClassicLoadMoreFooterView(Context context) {
        this(context, null);
    }

    public ClassicLoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClassicLoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        pbLoadMore = (ProgressBar) findViewById(R.id.pbLoadMore);
    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {

    }

}
