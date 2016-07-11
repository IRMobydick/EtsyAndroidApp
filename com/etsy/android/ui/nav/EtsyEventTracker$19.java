package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

final class EtsyEventTracker$19 extends HashMap<String, Object> {
    final /* synthetic */ EtsyId val$findsPageId;
    final /* synthetic */ EtsyId val$findsPagePublishedId;
    final /* synthetic */ String val$slug;

    EtsyEventTracker$19(EtsyId etsyId, EtsyId etsyId2, String str) {
        this.val$findsPageId = etsyId;
        this.val$findsPagePublishedId = etsyId2;
        this.val$slug = str;
        put(ResponseConstants.FINDS_PAGE_ID, this.val$findsPageId.getId());
        put(ResponseConstants.FINDS_PAGE_PUBLISHED_ID, this.val$findsPagePublishedId.getId());
        put(ResponseConstants.SLUG, this.val$slug);
    }
}
