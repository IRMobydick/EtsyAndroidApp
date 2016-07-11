package com.etsy.android.uikit.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnLongClickListener;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.logger.ViewClickAnalyticsLog.ViewAction;
import com.etsy.android.lib.models.datatypes.EtsyId;

public abstract class TrackingOnLongClickListener extends TrackingViewListener implements OnLongClickListener {
    private static final String TAG;

    public abstract boolean onViewLongClick(View view);

    static {
        TAG = EtsyDebug.m1891a(TrackingOnClickListener.class);
    }

    public TrackingOnLongClickListener(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable Object obj) {
        super(analyticsLogAttribute, obj);
    }

    public TrackingOnLongClickListener(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable EtsyId etsyId) {
        super(analyticsLogAttribute, etsyId);
    }

    public TrackingOnLongClickListener(ITrackedObject... iTrackedObjectArr) {
        super(iTrackedObjectArr);
    }

    public final boolean onLongClick(View view) {
        trackAction(view, ViewAction.long_clicked);
        return onViewLongClick(view);
    }
}
