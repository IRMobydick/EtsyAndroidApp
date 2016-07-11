package com.etsy.android.ui.core;

import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.lib.models.Treasury;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.TreasuriesRequest;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.etsy.android.ui.core.i */
class TreasuryFragment extends EtsyRequestJob<Treasury> {
    final /* synthetic */ TreasuryFragment f2792a;

    private TreasuryFragment(TreasuryFragment treasuryFragment) {
        this.f2792a = treasuryFragment;
    }

    protected void b_() {
        this.f2792a.showLoadingView();
    }

    protected EtsyRequest<Treasury> m4012a() {
        EtsyRequest<Treasury> treasury = TreasuriesRequest.getTreasury(this.f2792a.mTreasuryKey);
        Map hashMap = new HashMap();
        hashMap.put("includes", "Listings(state,listing_id,price,currency_code,converted_price,converted_currency_code,title,is_fund_on_etsy_campaign)/Images(url_170x135,red,green,blue):1,Listings/Shop(shop_id,shop_name)");
        treasury.addParams(hashMap);
        return treasury;
    }

    protected void m4013a(EtsyResult<Treasury> etsyResult) {
        if (etsyResult == null || !etsyResult.m1049a()) {
            this.f2792a.showErrorView();
            return;
        }
        this.f2792a.mTreasury = (Treasury) etsyResult.m1056g().get(0);
        this.f2792a.fillHeader(this.f2792a.mTreasury);
        this.f2792a.fillFooter(this.f2792a.mTreasury);
        this.f2792a.mAdapter.clear();
        this.f2792a.mAdapter.addAll(this.f2792a.mTreasury.getListings());
        EventTracker.m2016a(this.f2792a.getAnalyticsContext(), this.f2792a.mTreasury.getListings(), "view_treasury_list", this.f2792a.mAdapter.getCount());
        if (this.f2792a.mAdapter.getCount() > 0) {
            this.f2792a.showListView();
            this.f2792a.getUser();
        } else {
            this.f2792a.showEmptyView();
        }
        this.f2792a.getActivity().supportInvalidateOptionsMenu();
    }
}
