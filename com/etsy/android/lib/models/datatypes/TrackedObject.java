package com.etsy.android.lib.models.datatypes;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.ITrackedObject;
import java.util.HashMap;

public class TrackedObject implements ITrackedObject {
    private AnalyticsLogAttribute mAttribute;
    private Object mValue;

    public TrackedObject(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable Object obj) {
        this.mAttribute = analyticsLogAttribute;
        this.mValue = obj;
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(this.mAttribute, this.mValue);
        return hashMap;
    }
}
