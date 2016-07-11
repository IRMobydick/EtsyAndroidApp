package com.etsy.android.ui.search;

import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.ShopsRequest;
import com.etsy.android.ui.util.EtsyCardUtil;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.etsy.android.ui.search.a */
public class SearchShopsJob extends EtsyRequestJob<Shop> {
    private final String f3208a;
    private final int f3209c;
    private final int f3210d;
    private final boolean f3211e;

    public SearchShopsJob(String str, int i, int i2, boolean z) {
        this.f3208a = str;
        this.f3209c = i;
        this.f3210d = i2;
        this.f3211e = z;
    }

    protected EtsyRequest<Shop> m4725a() {
        EtsyRequest findAllShops = ShopsRequest.findAllShops();
        Map hashMap = new HashMap();
        if (this.f3208a != null) {
            hashMap.put(ResponseConstants.SHOP_NAME, this.f3208a);
        }
        hashMap.put("limit", String.valueOf(this.f3209c));
        hashMap.put("offset", String.valueOf(this.f3210d));
        if (!this.f3211e) {
            hashMap.put("includes", "User(user_id)/Profile(image_url_75x75,city)/Country(name)," + EtsyCardUtil.m5113c(6) + "," + EtsyCardUtil.m5110b(6));
            hashMap.put("fields", "shop_id,shop_name,total_rating_count,average_rating,icon_url_fullxfull");
        }
        findAllShops.addParams(hashMap);
        return findAllShops;
    }
}
