package com.etsy.android.ui.favorites;

import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.FavoriteUser;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.FavoriteUsersRequest;
import com.etsy.android.ui.nav.EtsyEventTracker;
import com.etsy.android.ui.util.EtsyCardUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.favorites.e */
class MyFavoriteShopsFragment extends EtsyRequestJob<FavoriteUser> {
    boolean f2965a;
    final /* synthetic */ MyFavoriteShopsFragment f2966c;

    public MyFavoriteShopsFragment(MyFavoriteShopsFragment myFavoriteShopsFragment, boolean z) {
        this.f2966c = myFavoriteShopsFragment;
        this.f2965a = z;
    }

    protected EtsyRequest<FavoriteUser> m4245a() {
        EtsyRequest<FavoriteUser> findMyFavoriteUsers = FavoriteUsersRequest.findMyFavoriteUsers();
        Map hashMap = new HashMap();
        hashMap.put("includes", "TargetUser(user_id)/Shops(shop_id,shop_name,total_rating_count,average_rating,icon_url_fullxfull)/User(user_id)/Profile(image_url_75x75,city)/Country(name),TargetUser(user_id)/Shops(shop_id)/" + EtsyCardUtil.m5113c(4) + ",TargetUser(user_id)/Shops(shop_id)/" + EtsyCardUtil.m5110b(4) + ",TargetUser(user_id)/Shops(shop_id)/NearbyLocalMarket(city,local_market_id,happening_status)");
        hashMap.put("fields", "target_user_id");
        hashMap.put("limit", "24");
        hashMap.put("offset", this.f2966c.mOffset + StringUtils.EMPTY);
        findMyFavoriteUsers.addParams(hashMap);
        return findMyFavoriteUsers;
    }

    protected void m4246a(EtsyResult<FavoriteUser> etsyResult) {
        if (etsyResult == null || !etsyResult.m1049a() || etsyResult.m1056g() == null) {
            this.f2966c.handleError();
            return;
        }
        Collection arrayList = new ArrayList(0);
        for (User mainShop : etsyResult.m1056g()) {
            arrayList.add(mainShop.getMainShop());
        }
        if (this.f2965a) {
            this.f2966c.mAdapter.clear();
        }
        this.f2966c.mAdapter.addAll(arrayList);
        this.f2966c.mAdapter.notifyDataSetChanged();
        EtsyEventTracker.m4554a(this.f2966c.getAnalyticsContext(), etsyResult.m1056g(), true);
        this.f2966c.showViews(etsyResult);
    }
}
