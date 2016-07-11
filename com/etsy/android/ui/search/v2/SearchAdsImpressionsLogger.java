package com.etsy.android.ui.search.v2;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.requests.SearchAdsLogRequest;
import com.etsy.android.ui.cardview.CardViewFactoryRecyclerViewAdapter;
import java.util.HashSet;

public class SearchAdsImpressionsLogger extends OnScrollListener {
    private static final String SAVE_DISPLAYED_ADS = "displayed_ads";
    private final String TAG;
    private HashSet<String> mDisplayedAds;

    public SearchAdsImpressionsLogger() {
        this.TAG = EtsyDebug.m1891a(SearchAdsImpressionsLogger.class);
        this.mDisplayedAds = new HashSet();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.mDisplayedAds != null) {
            bundle.putSerializable(SAVE_DISPLAYED_ADS, this.mDisplayedAds);
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null && bundle.containsKey(SAVE_DISPLAYED_ADS)) {
            this.mDisplayedAds = (HashSet) bundle.getSerializable(SAVE_DISPLAYED_ADS);
        }
    }

    public void reset() {
        this.mDisplayedAds.clear();
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        int findFirstCompletelyVisibleItemPosition = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
        int findLastCompletelyVisibleItemPosition = gridLayoutManager.findLastCompletelyVisibleItemPosition();
        CardViewFactoryRecyclerViewAdapter cardViewFactoryRecyclerViewAdapter = (CardViewFactoryRecyclerViewAdapter) recyclerView.getAdapter();
        if (cardViewFactoryRecyclerViewAdapter == null) {
            EtsyDebug.m1919e(this.TAG, "******************************************************************");
            EtsyDebug.m1919e(this.TAG, "WARNING: No Impressions being logged, SearchAdsImpressionLogger only works with CardViewFactoryRecyclerViewAdapter at the moment");
            EtsyDebug.m1919e(this.TAG, "******************************************************************");
        } else if (findFirstCompletelyVisibleItemPosition != -1 && findLastCompletelyVisibleItemPosition != -1) {
            for (int i3 = findFirstCompletelyVisibleItemPosition; i3 <= findLastCompletelyVisibleItemPosition; i3++) {
                if (cardViewFactoryRecyclerViewAdapter.getItem(i3) instanceof ListingCard) {
                    ListingCard listingCard = (ListingCard) cardViewFactoryRecyclerViewAdapter.getItem(i3);
                    String etsyId = listingCard.getListingId().toString();
                    if (listingCard.isAd() && !this.mDisplayedAds.contains(etsyId)) {
                        this.mDisplayedAds.add(etsyId);
                        aj.m1101a().m1124j().m1663a(SearchAdsLogRequest.logSearchAdsImpression(listingCard));
                    }
                }
            }
        }
    }
}
