package com.etsy.android.lib.logger;

import java.util.HashMap;

class EtsyLogger$1 extends HashMap<String, Object> {
    final /* synthetic */ EtsyLogger this$0;
    final /* synthetic */ String val$eventType;
    final /* synthetic */ Object val$eventValue;

    EtsyLogger$1(EtsyLogger etsyLogger, String str, Object obj) {
        this.this$0 = etsyLogger;
        this.val$eventType = str;
        this.val$eventValue = obj;
        put(this.val$eventType, this.val$eventValue);
    }
}
