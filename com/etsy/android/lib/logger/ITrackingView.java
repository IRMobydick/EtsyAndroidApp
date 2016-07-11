package com.etsy.android.lib.logger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* renamed from: com.etsy.android.lib.logger.s */
public interface ITrackingView {
    ad getAnalyticsContext();

    @Nullable
    Context getAndroidContext();

    @NonNull
    String getDefaultName();

    @NonNull
    String getTrackingName();

    @Nullable
    ITrackingView getTrackingParent();
}
