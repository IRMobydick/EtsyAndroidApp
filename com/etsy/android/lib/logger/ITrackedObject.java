package com.etsy.android.lib.logger;

import android.support.annotation.Nullable;
import java.util.HashMap;

/* renamed from: com.etsy.android.lib.logger.r */
public interface ITrackedObject {
    @Nullable
    HashMap<AnalyticsLogAttribute, Object> getTrackingParameters();
}
