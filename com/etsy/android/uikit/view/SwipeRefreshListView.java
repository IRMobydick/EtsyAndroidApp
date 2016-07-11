package com.etsy.android.uikit.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.listwrapper.IEndlessListView;
import com.etsy.android.uikit.listwrapper.ListViewEndlessWrapper;

@Deprecated
public class SwipeRefreshListView extends SwipeRefreshObeyRequestDisallowInterceptTouchEventLayout implements IEndlessListView {
    private ListView mList;
    private ListViewEndlessWrapper mListViewEndlessWrapper;

    public SwipeRefreshListView(Context context) {
        super(context);
        init();
    }

    public SwipeRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.mList = new ListView(getContext());
        this.mList.setId(16908298);
        addView(this.mList, -1, -1);
        this.mListViewEndlessWrapper = new ListViewEndlessWrapper(getListView(), R.endless_footer, R.endless_error, R.btn_retry_endless);
    }

    public ListView getListView() {
        return this.mList;
    }

    public void addFooterView(View view) {
        getListView().addFooterView(view);
    }

    public int getCount() {
        return getListView().getCount();
    }

    public void setDivider(Drawable drawable) {
        getListView().setDivider(drawable);
    }

    public ListAdapter getAdapter() {
        return getListView().getAdapter();
    }

    public void setOffset(int i) {
        this.mListViewEndlessWrapper.setOffset(i);
    }

    public void startEndless() {
        this.mListViewEndlessWrapper.startEndless();
    }

    public void stopEndless() {
        this.mListViewEndlessWrapper.stopEndless();
    }

    public boolean isEndlessLoading() {
        return this.mListViewEndlessWrapper.isEndlessLoading();
    }

    public void showEndlessError() {
        this.mListViewEndlessWrapper.showEndlessError();
    }

    public void removeEndlessError() {
        this.mListViewEndlessWrapper.removeEndlessError();
    }

    public void setLoadMoreListener(IEndlessListView iEndlessListView) {
        this.mListViewEndlessWrapper.setLoadMoreListener(iEndlessListView);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mListViewEndlessWrapper.setOnScrollListener(onScrollListener);
    }
}
