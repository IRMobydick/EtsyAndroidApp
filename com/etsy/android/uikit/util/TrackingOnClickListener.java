package com.etsy.android.uikit.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.logger.ViewClickAnalyticsLog.ViewAction;
import com.etsy.android.lib.models.datatypes.EtsyId;

public abstract class TrackingOnClickListener extends TrackingViewListener implements OnClickListener {
    private static final String TAG;

    public abstract void onViewClick(View view);

    static {
        TAG = EtsyDebug.m1891a(TrackingOnClickListener.class);
    }

    public TrackingOnClickListener(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable Object obj) {
        super(analyticsLogAttribute, obj);
    }

    public TrackingOnClickListener(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable EtsyId etsyId) {
        super(analyticsLogAttribute, etsyId);
    }

    public TrackingOnClickListener(ITrackedObject... iTrackedObjectArr) {
        super(iTrackedObjectArr);
    }

    public final void onClick(View view) {
        trackAction(view, ViewAction.clicked);
        onViewClick(view);
    }
}
