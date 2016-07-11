package com.etsy.android.lib.logger;

import java.util.HashMap;

final class EventTracker$14 extends HashMap<String, Object> {
    final /* synthetic */ int val$errorCode;

    EventTracker$14(int i) {
        this.val$errorCode = i;
        put("google_error_code", Integer.valueOf(this.val$errorCode));
    }
}
