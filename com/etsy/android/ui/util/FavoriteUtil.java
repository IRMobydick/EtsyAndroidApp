package com.etsy.android.ui.util;

import android.os.AsyncTask;
import com.etsy.android.contentproviders.EtsyDatabaseUtil;
import com.etsy.android.contentproviders.EtsyProvider;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.ap;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.TreasuriesRequest;

/* renamed from: com.etsy.android.ui.util.w */
class FavoriteUtil extends FavoriteUtil {
    final /* synthetic */ FavoriteUtil f3763a;
    private String f3764f;

    /* renamed from: com.etsy.android.ui.util.w.1 */
    class FavoriteUtil extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ boolean f3760a;
        final /* synthetic */ boolean f3761b;
        final /* synthetic */ FavoriteUtil f3762c;

        FavoriteUtil(FavoriteUtil favoriteUtil, boolean z, boolean z2) {
            this.f3762c = favoriteUtil;
            this.f3760a = z;
            this.f3761b = z2;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m5192a((Void[]) objArr);
        }

        protected Void m5192a(Void... voidArr) {
            EtsyDatabaseUtil.m741a(this.f3762c.f3763a.f3732b, this.f3762c.f3764f, this.f3760a);
            if (this.f3761b) {
                this.f3762c.f3763a.f3732b.getContentResolver().notifyChange(EtsyProvider.f1095a, null);
                this.f3762c.f3763a.f3732b.getContentResolver().notifyChange(EtsyProvider.f1094a, null);
            }
            return null;
        }
    }

    public FavoriteUtil(FavoriteUtil favoriteUtil, FavoriteUtil favoriteUtil2, boolean z, boolean z2, String str) {
        this.f3763a = favoriteUtil;
        super(favoriteUtil, favoriteUtil2, z, z2);
        this.f3764f = str;
    }

    protected void b_() {
        boolean z;
        if (this.d) {
            z = false;
        } else {
            z = true;
        }
        m5194a(z, false);
    }

    protected EtsyRequest m5195a() {
        if (this.d) {
            EtsyRequest deleteUserFavoriteTreasury = TreasuriesRequest.deleteUserFavoriteTreasury(this.f3764f);
            EventTracker.m2034b(this.f3764f, this.f3763a.f3736f);
            return deleteUserFavoriteTreasury;
        }
        deleteUserFavoriteTreasury = TreasuriesRequest.createUserFavoriteTreasury(this.f3764f);
        EventTracker.m2022a(this.f3764f, this.f3763a.f3736f);
        return deleteUserFavoriteTreasury;
    }

    protected void m5196a(EtsyResult etsyResult) {
        if (etsyResult.m1049a()) {
            this.f3763a.f3732b.getContentResolver().notifyChange(EtsyProvider.f1095a, null);
            this.f3763a.f3732b.getContentResolver().notifyChange(EtsyProvider.f1094a, null);
        } else {
            m5194a(this.d, true);
        }
        super.m5181a(etsyResult);
    }

    private void m5194a(boolean z, boolean z2) {
        ap.m1142a(new FavoriteUtil(this, z, z2), new Void[0]);
    }
}
ra(EtsyAction.STATE_FAVORITE, z);
        LocalBroadcastManager.getInstance(this.f3752a.f3732b).sendBroadcast(intent);
    }

    protected EtsyRequest<? extends BaseModel> m5185a() {
        if (this.d) {
            EtsyRequest deleteUserFavoriteListings = FavoriteListingsRequest.deleteUserFavoriteListings("__SELF__", this.f3753f);
            EventTracker.m2038c(this.f3753f, this.f3752a.f3736f);
            return deleteUserFavoriteListings;
        }
        EtsyRequest<? extends BaseModel> favoriteListings = SocialShareRequest.favoriteListings(this.f3753f);
        EventTracker.m2031b(this.f3753f, this.f3752a.f3736f);
        return favoriteListings;
    }

    protected void m5186a(EtsyResult etsyResult) {
        boolean z = true;
        if (etsyResult.m1049a()) {
            if (this.f3754g != null) {
                ListingLike listingLike = this.f3754g;
                if (this.d) {
                    z = false;
                }
                listingLike.setIsFavorite(z);
            }
            this.f3752a.f3732b.getContentResolver().notifyChange(EtsyProvider.f1095a, null);
            this.f3752a.f3732b.getContentResolver().notifyChange(EtsyProvider.f1094a, null);
            this.f3752a.f3732b.getContentResolver().notifyChange(EtsyProvider.f1097a, null);
        } else {
            if (this.f3754g != null) {
                this.f3754g.setIsFavorite(this.d);
            }
            m5184a(this.d, true);
        }
        super.m5181a(etsyResult);
    }

    private void m5184a(boolean z, boolean z2) {
        ap.m1142a(new FavoriteUtil(this, z, z2), new Void[0]);
    }
}
