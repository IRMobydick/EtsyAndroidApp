package com.etsy.android.ui.shop;

import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.ShopsRequest;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.etsy.android.ui.shop.d */
class FeedbackFragment extends EtsyRequestJob<Shop> {
    final /* synthetic */ FeedbackFragment f3431a;

    private FeedbackFragment(FeedbackFragment feedbackFragment) {
        this.f3431a = feedbackFragment;
    }

    protected EtsyRequest<Shop> m4982a() {
        if (!this.f3431a.mShopIdentifier.hasId()) {
            return null;
        }
        EtsyRequest<Shop> shop = ShopsRequest.getShop(this.f3431a.mShopIdentifier);
        Map hashMap = new HashMap();
        hashMap.put("fields", "shop_id,shop_name,title,total_rating_count,average_rating,icon_url_fullxfull");
        hashMap.put("includes", "User(user_id,login_name)/Profile(image_url_75x75)");
        shop.addParams(hashMap);
        return shop;
    }

    protected void m4983a(EtsyResult<Shop> etsyResult) {
        if (etsyResult != null && etsyResult.m1049a() && etsyResult.m1058i()) {
            this.f3431a.mShop = (Shop) etsyResult.m1056g().get(0);
            if (this.f3431a.mShop != null) {
                this.f3431a.mShopIdentifier = this.f3431a.mShop.getShopId();
                this.f3431a.setShopInfo(this.f3431a.mShop);
            }
        }
    }
}
