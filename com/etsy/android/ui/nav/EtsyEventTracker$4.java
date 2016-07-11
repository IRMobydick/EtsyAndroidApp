package com.etsy.android.ui.nav;

import com.adjust.sdk.Constants;
import com.etsy.android.lib.messaging.EtsyAction;
import java.util.HashMap;

final class EtsyEventTracker$4 extends HashMap<String, Object> {
    final /* synthetic */ EtsyAction val$type;

    EtsyEventTracker$4(EtsyAction etsyAction) {
        this.val$type = etsyAction;
        put(Constants.REFERRER, this.val$type.getName());
    }
}
