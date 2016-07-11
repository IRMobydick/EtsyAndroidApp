package com.etsy.android.lib.logger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLog.LogType;
import java.util.Map;

/* renamed from: com.etsy.android.lib.logger.a */
public class AdHocAnalyticsLog extends AnalyticsLog {
    public AdHocAnalyticsLog(@NonNull String str, @Nullable Map<AnalyticsLogAttribute, Object> map, @NonNull AnalyticsTracker analyticsTracker) {
        super(LogType.AD_HOC, str, false, map);
        m1824a(analyticsTracker);
    }

    protected void m1824a(@NonNull AnalyticsTracker analyticsTracker) {
        m1788b(analyticsTracker);
    }
}
