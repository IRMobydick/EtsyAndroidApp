package com.etsy.android.uikit.listwrapper;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.etsy.android.lib.util.AndroidIssuesUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;

@Deprecated
public class ListViewEndlessWrapper implements OnScrollListener, IEndlessListView {
    private View mFooter;
    private View mFooterError;
    private boolean mHasRequestedMore;
    private boolean mIsErrorAdded;
    private boolean mIsFooterAdded;
    protected ListView mListView;
    private IEndlessListView mLoadMoreListener;
    private int mOffset;
    private OnScrollListener mOnScrollListener;

    /* renamed from: com.etsy.android.uikit.listwrapper.ListViewEndlessWrapper.1 */
    class C09311 extends TrackingOnClickListener {
        final /* synthetic */ ListViewEndlessWrapper f3977a;

        C09311(ListViewEndlessWrapper listViewEndlessWrapper) {
            this.f3977a = listViewEndlessWrapper;
        }

        public void onViewClick(View view) {
            if (this.f3977a.mLoadMoreListener != null) {
                this.f3977a.startEndless();
                this.f3977a.requestLoadMore();
            }
        }
    }

    public ListViewEndlessWrapper(ListView listView, int i, int i2, int i3) {
        this.mListView = listView;
        LayoutInflater from = LayoutInflater.from(listView.getContext());
        this.mFooter = from.inflate(i, null);
        this.mFooter.setEnabled(false);
        this.mFooter.setClickable(false);
        this.mFooterError = from.inflate(i2, null);
        this.mFooterError.setEnabled(false);
        this.mFooterError.setClickable(false);
        this.mFooterError.findViewById(i3).setOnClickListener(new C09311(this));
        this.mOffset = 2;
        listView.setOnScrollListener(this);
        if (AndroidIssuesUtil.m3165b()) {
            this.mListView.addFooterView(new View(this.mListView.getContext()));
        }
    }

    public void setLoadMoreListener(IEndlessListView iEndlessListView) {
        this.mLoadMoreListener = iEndlessListView;
    }

    public void setOffset(int i) {
        this.mOffset = i;
    }

    public void startEndless() {
        removeEndlessError();
        if (!this.mIsFooterAdded && this.mListView.getCount() > 0) {
            this.mIsFooterAdded = true;
            this.mListView.addFooterView(this.mFooter);
        }
        this.mHasRequestedMore = false;
    }

    public void stopEndless() {
        if (this.mIsFooterAdded) {
            this.mListView.removeFooterView(this.mFooter);
            this.mIsFooterAdded = false;
        }
        this.mHasRequestedMore = false;
    }

    public void showEndlessError() {
        stopEndless();
        if (!this.mIsErrorAdded && this.mListView.getCount() > 0) {
            this.mIsErrorAdded = true;
            this.mListView.addFooterView(this.mFooterError);
        }
        this.mHasRequestedMore = false;
    }

    public void removeEndlessError() {
        if (this.mIsErrorAdded) {
            this.mListView.removeFooterView(this.mFooterError);
            this.mIsErrorAdded = false;
        }
    }

    public boolean isEndlessLoading() {
        return this.mIsFooterAdded;
    }

    public void setAdapter(ListAdapter listAdapter) {
        this.mListView.setAdapter(listAdapter);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.mIsFooterAdded && this.mLoadMoreListener != null && !this.mHasRequestedMore && (i + i2) + this.mOffset >= i3) {
            requestLoadMore();
        }
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScroll(absListView, i, i2, i3);
        }
    }

    private void requestLoadMore() {
        this.mHasRequestedMore = true;
        this.mLoadMoreListener.onLoadMoreItems();
    }
}
