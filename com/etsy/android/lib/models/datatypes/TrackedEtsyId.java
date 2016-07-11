package com.etsy.android.lib.models.datatypes;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.ITrackedObject;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

public class TrackedEtsyId implements ITrackedObject {
    private AnalyticsLogAttribute mAttribute;
    private String mId;

    public TrackedEtsyId(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable EtsyId etsyId) {
        this.mAttribute = analyticsLogAttribute;
        this.mId = etsyId == null ? StringUtils.EMPTY : etsyId.getId();
    }

    public TrackedEtsyId(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable String str) {
        this.mAttribute = analyticsLogAttribute;
        this.mId = str;
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(this.mAttribute, this.mId);
        return hashMap;
    }
}
