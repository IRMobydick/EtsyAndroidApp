package com.etsy.android.lib.logger;

import java.util.HashMap;

class LogTracker$1 extends HashMap<String, Object> {
    final /* synthetic */ LogTracker this$0;
    final /* synthetic */ String val$convoId;

    LogTracker$1(LogTracker logTracker, String str) {
        this.this$0 = logTracker;
        this.val$convoId = str;
        put("convo_id", this.val$convoId);
    }
}
