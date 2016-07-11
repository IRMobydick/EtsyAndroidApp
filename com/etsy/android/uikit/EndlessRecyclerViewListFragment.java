package com.etsy.android.uikit;

import android.os.Bundle;
import com.etsy.android.uikit.adapter.EndlessRecyclerViewAdapter;
import java.util.List;

public abstract class EndlessRecyclerViewListFragment<T> extends BaseRecyclerViewListFragment<T> implements EndlessRecyclerViewAdapter, IEtsyEndlessListFragment {
    private static final String KEY_CONTENT_EXHAUSTED = "key_content_exhausted";
    private static final String KEY_OFFSET = "key_offset";
    private boolean mIsContentExhausted;
    private int mOffset;

    /* renamed from: com.etsy.android.uikit.EndlessRecyclerViewListFragment.1 */
    class C09191 implements Runnable {
        final /* synthetic */ EndlessRecyclerViewListFragment f3895a;

        C09191(EndlessRecyclerViewListFragment endlessRecyclerViewListFragment) {
            this.f3895a = endlessRecyclerViewListFragment;
        }

        public void run() {
            this.f3895a.mAdapter.addFooter(EndlessRecyclerViewAdapter.VIEW_TYPE_ENDLESS_LOADING);
        }
    }

    /* renamed from: com.etsy.android.uikit.EndlessRecyclerViewListFragment.2 */
    class C09202 implements Runnable {
        final /* synthetic */ EndlessRecyclerViewListFragment f3896a;

        C09202(EndlessRecyclerViewListFragment endlessRecyclerViewListFragment) {
            this.f3896a = endlessRecyclerViewListFragment;
        }

        public void run() {
            this.f3896a.mAdapter.removeFooter(EndlessRecyclerViewAdapter.VIEW_TYPE_ENDLESS_LOADING);
        }
    }

    /* renamed from: com.etsy.android.uikit.EndlessRecyclerViewListFragment.3 */
    class C09213 implements Runnable {
        final /* synthetic */ EndlessRecyclerViewListFragment f3897a;

        C09213(EndlessRecyclerViewListFragment endlessRecyclerViewListFragment) {
            this.f3897a = endlessRecyclerViewListFragment;
        }

        public void run() {
            this.f3897a.mAdapter.addFooter(EndlessRecyclerViewAdapter.VIEW_TYPE_ENDLESS_ERROR);
        }
    }

    /* renamed from: com.etsy.android.uikit.EndlessRecyclerViewListFragment.4 */
    class C09224 implements Runnable {
        final /* synthetic */ EndlessRecyclerViewListFragment f3898a;

        C09224(EndlessRecyclerViewListFragment endlessRecyclerViewListFragment) {
            this.f3898a = endlessRecyclerViewListFragment;
        }

        public void run() {
            this.f3898a.mAdapter.removeFooter(EndlessRecyclerViewAdapter.VIEW_TYPE_ENDLESS_ERROR);
        }
    }

    protected abstract void onLoadContent();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mIsContentExhausted = bundle.getBoolean(KEY_CONTENT_EXHAUSTED, false);
            this.mOffset = bundle.getInt(KEY_OFFSET, 0);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(KEY_CONTENT_EXHAUSTED, this.mIsContentExhausted);
        bundle.putInt(KEY_OFFSET, this.mOffset);
    }

    public boolean canLoadContent() {
        return !this.mIsContentExhausted && super.canLoadContent();
    }

    protected void setContentExhausted(boolean z) {
        this.mIsContentExhausted = z;
    }

    protected void onLoadSuccess(List<T> list, int i) {
        if (this.mAdapter.getDataItemCount() > 0 && !isRefreshing()) {
            stopEndless();
            removeEndlessError();
        }
        this.mOffset += list.size();
        super.onLoadSuccess(list, i);
        if (this.mOffset >= i) {
            this.mIsContentExhausted = true;
        }
    }

    protected void onLoadFailure() {
        if (this.mAdapter.getDataItemCount() <= 0) {
            super.onLoadFailure();
        } else if (isRefreshing()) {
            super.onLoadFailure();
        } else {
            setLoading(false);
            showEndlessError();
        }
    }

    protected void onPreLoadContent() {
        if (this.mAdapter.getDataItemCount() <= 0 || isRefreshing()) {
            super.onPreLoadContent();
        } else {
            startEndless();
        }
    }

    public void onScrolledToLoadTrigger() {
        loadContent();
    }

    public int getLoadTriggerPosition() {
        return 12;
    }

    public void startEndless() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.post(new C09191(this));
        }
    }

    public void stopEndless() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.post(new C09202(this));
        }
    }

    public void showEndlessError() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.post(new C09213(this));
        }
    }

    public void removeEndlessError() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.post(new C09224(this));
        }
    }

    public int getApiOffset() {
        return this.mOffset;
    }

    public void setApiOffset(int i) {
        this.mOffset = i;
    }

    protected void resetAndLoadContent() {
        setApiOffset(0);
        setLoading(false);
        this.mIsContentExhausted = false;
        loadContent();
    }

    public void onRefresh() {
        if (!isRefreshing()) {
            this.mOffset = 0;
            this.mIsContentExhausted = false;
            super.onRefresh();
        }
    }
}
