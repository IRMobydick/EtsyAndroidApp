package com.etsy.android.ui.favorites;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.requests.apiv3.ShopCardRequest;
import com.etsy.android.lib.util.ac;
import com.etsy.android.ui.EtsyLoadingListFragment;
import com.etsy.android.ui.adapters.CardListAdapter;
import java.util.Collection;
import java.util.List;

public class MyFavoriteLocalShopsFragment extends EtsyLoadingListFragment {
    private CardListAdapter mAdapter;
    private int mMaxItemCount;
    private int mOffset;

    /* renamed from: com.etsy.android.ui.favorites.MyFavoriteLocalShopsFragment.1 */
    class C06851 implements EtsyJobResponse<ShopCard> {
        final /* synthetic */ boolean f2949a;
        final /* synthetic */ MyFavoriteLocalShopsFragment f2950b;

        C06851(MyFavoriteLocalShopsFragment myFavoriteLocalShopsFragment, boolean z) {
            this.f2950b = myFavoriteLocalShopsFragment;
            this.f2949a = z;
        }

        public void m4227a(List<ShopCard> list, int i, EtsyResult<ShopCard> etsyResult) {
            if (this.f2949a) {
                this.f2950b.mAdapter.clear();
            }
            this.f2950b.stopPullToRefresh();
            this.f2950b.mMaxItemCount = i;
            MyFavoriteLocalShopsFragment.access$212(this.f2950b, 24);
            this.f2950b.mAdapter.addAll((Collection) list);
            this.f2950b.handleViews();
        }
    }

    /* renamed from: com.etsy.android.ui.favorites.MyFavoriteLocalShopsFragment.2 */
    class C06862 implements EtsyJobResponse {
        final /* synthetic */ boolean f2951a;
        final /* synthetic */ MyFavoriteLocalShopsFragment f2952b;

        C06862(MyFavoriteLocalShopsFragment myFavoriteLocalShopsFragment, boolean z) {
            this.f2952b = myFavoriteLocalShopsFragment;
            this.f2951a = z;
        }

        public void m4228a(EtsyResult etsyResult) {
            if (this.f2951a) {
                this.f2952b.mAdapter.clear();
            }
            this.f2952b.stopPullToRefresh();
            if (ac.m3186a(this.f2952b.mAdapter) == 0) {
                this.f2952b.showEmptyView();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.favorites.MyFavoriteLocalShopsFragment.3 */
    class C06873 implements EtsyJobResponse {
        final /* synthetic */ MyFavoriteLocalShopsFragment f2953a;

        C06873(MyFavoriteLocalShopsFragment myFavoriteLocalShopsFragment) {
            this.f2953a = myFavoriteLocalShopsFragment;
        }

        public void m4229a(int i, String str, EtsyResult etsyResult) {
            this.f2953a.handleError();
        }
    }

    public MyFavoriteLocalShopsFragment() {
        this.mOffset = 0;
    }

    static /* synthetic */ int access$212(MyFavoriteLocalShopsFragment myFavoriteLocalShopsFragment, int i) {
        int i2 = myFavoriteLocalShopsFragment.mOffset + i;
        myFavoriteLocalShopsFragment.mOffset = i2;
        return i2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        setPullToRefreshEnabled(true);
        if (!aj.m1101a().m1118d()) {
            EtsyDebug.m1894a(new IllegalStateException("Can't access my favorite local shops when not signed in"));
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setEmptyText((int) R.empty_favorites);
        if (this.mAdapter == null) {
            this.mAdapter = new CardListAdapter(this.mActivity, 2130903283, getImageBatch(), getAnalyticsContext());
            showLoadingView();
            onLoadMoreItems();
        } else if (ac.m3186a(this.mAdapter) == 0) {
            showEmptyView();
        } else if (this.mMaxItemCount > ac.m3186a(this.mAdapter)) {
            startEndless();
            showListView();
        } else {
            showListView();
        }
        this.mListView.setDivider(null);
        this.mListView.setPadding(0, 0, getResources().getDimensionPixelOffset(2131361960), 0);
        this.mAdapter.notifyDataSetChanged();
        setListAdapter(this.mAdapter);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mAdapter.notifyDataSetChanged();
    }

    public void onLoadMoreItems() {
        loadFavoriteLocalEventShops(false);
    }

    protected void onPullToRefresh() {
        stopEndless();
        this.mOffset = 0;
        loadFavoriteLocalEventShops(true);
    }

    private void loadFavoriteLocalEventShops(boolean z) {
        EtsyJobBuilder a = EtsyJobBuilder.m1307a(ShopCardRequest.getFavoriteShopsSellingNearby());
        a.m1325b(24);
        a.m1316a(this.mOffset);
        a.m1321a(new C06851(this, z));
        a.m1319a(new C06862(this, z));
        a.m1320a(new C06873(this));
        getRequestQueue().m1697a((Object) this, a.m1324a());
    }

    private void handleViews() {
        if (ac.m3186a(this.mAdapter) >= this.mMaxItemCount) {
            showListView();
            stopEndless();
            return;
        }
        showListView();
        startEndless();
    }

    protected void onRetryClicked() {
        showLoadingView();
        onLoadMoreItems();
    }

    private void handleError() {
        stopPullToRefresh();
        if (ac.m3186a(this.mAdapter) > 0) {
            showEndlessError();
        } else {
            showErrorView();
        }
    }

    @NonNull
    public String getTrackingName() {
        return "your_favorite_shops_local";
    }
}
