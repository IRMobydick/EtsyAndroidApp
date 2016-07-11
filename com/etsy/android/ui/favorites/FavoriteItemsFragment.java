package com.etsy.android.ui.favorites;

import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.FavoriteTreasury;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.TreasuriesRequest;
import com.etsy.android.ui.util.EtsyCardUtil;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.favorites.c */
class FavoriteItemsFragment extends EtsyRequestJob<FavoriteTreasury> {
    boolean f2962a;
    final /* synthetic */ FavoriteItemsFragment f2963c;

    public FavoriteItemsFragment(FavoriteItemsFragment favoriteItemsFragment, boolean z) {
        this.f2963c = favoriteItemsFragment;
        this.f2962a = z;
    }

    protected EtsyRequest<FavoriteTreasury> m4239a() {
        EtsyRequest<FavoriteTreasury> findMyFavoriteTreasuries;
        if (this.f2963c.isYou()) {
            findMyFavoriteTreasuries = TreasuriesRequest.findMyFavoriteTreasuries();
        } else {
            findMyFavoriteTreasuries = TreasuriesRequest.findAllUserFavoriteTreasuries(this.f2963c.mUserId);
        }
        Map hashMap = new HashMap();
        hashMap.put("includes", "Treasury(id,title,description)/" + EtsyCardUtil.m5113c(4));
        hashMap.put("limit", "24");
        hashMap.put("offset", this.f2963c.mOffset + StringUtils.EMPTY);
        findMyFavoriteTreasuries.addParams(hashMap);
        return findMyFavoriteTreasuries;
    }

    protected void m4240a(EtsyResult<FavoriteTreasury> etsyResult) {
        if (etsyResult == null || !etsyResult.m1049a() || etsyResult.m1056g() == null) {
            this.f2963c.handleError(etsyResult);
            return;
        }
        if (this.f2962a) {
            this.f2963c.mAdapter.clear();
        }
        this.f2963c.mAdapter.addAll(etsyResult.m1056g());
        this.f2963c.mAdapter.notifyDataSetChanged();
        this.f2963c.showViews(etsyResult);
    }
}
