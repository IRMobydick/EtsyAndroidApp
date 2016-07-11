package com.etsy.android.uikit.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.logger.ViewClickAnalyticsLog.ViewAction;
import com.etsy.android.lib.models.datatypes.EtsyId;

public abstract class TrackingOnCheckedChangeListener extends TrackingViewListener implements OnCheckedChangeListener {
    private static final String TAG;

    public abstract void onViewCheckedChanged(CompoundButton compoundButton, boolean z);

    static {
        TAG = EtsyDebug.m1891a(TrackingOnCheckedChangeListener.class);
    }

    public TrackingOnCheckedChangeListener(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable Object obj) {
        super(analyticsLogAttribute, obj);
    }

    public TrackingOnCheckedChangeListener(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable EtsyId etsyId) {
        super(analyticsLogAttribute, etsyId);
    }

    public TrackingOnCheckedChangeListener(ITrackedObject... iTrackedObjectArr) {
        super(iTrackedObjectArr);
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        trackAction(compoundButton, z ? ViewAction.checked : ViewAction.unchecked);
        onViewCheckedChanged(compoundButton, z);
    }
}
