package com.etsy.android.ui.nav;

import java.util.HashMap;

final class EtsyEventTracker$15 extends HashMap<String, Object> {
    final /* synthetic */ String val$collectionKey;
    final /* synthetic */ String val$collectionName;
    final /* synthetic */ String val$collectionPrivacy;

    EtsyEventTracker$15(String str, String str2, String str3) {
        this.val$collectionKey = str;
        this.val$collectionName = str2;
        this.val$collectionPrivacy = str3;
        put("collection_key", this.val$collectionKey);
        put("collection_name", this.val$collectionName);
        put("privacy_level", this.val$collectionPrivacy);
    }
}
