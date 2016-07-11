package com.etsy.android.lib.ui.nav;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.ITrackingView;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;

/* renamed from: com.etsy.android.lib.ui.nav.a */
public abstract class TrackingNavigator<NavigatorClass extends TrackingNavigator<NavigatorClass>> {
    private AnalyticsTracker f1949a;

    protected abstract Context m3011a();

    @NonNull
    protected abstract NavigatorClass m3015c();

    @NonNull
    public final NavigatorClass m3012a(@Nullable View view) {
        if (view != null) {
            this.f1949a = AdHocEventCompatBuilder.m5506b(view);
        }
        return m3015c();
    }

    @NonNull
    public final NavigatorClass m3013a(@Nullable ITrackingView iTrackingView) {
        if (iTrackingView != null) {
            this.f1949a = iTrackingView.getAnalyticsContext();
        } else {
            this.f1949a = null;
        }
        return m3015c();
    }

    @NonNull
    protected final AnalyticsTracker m3014b() {
        if (this.f1949a != null) {
            return this.f1949a;
        }
        if (m3011a() instanceof ITrackingView) {
            return ((ITrackingView) m3011a()).getAnalyticsContext();
        }
        return EtsyApplication.get().getAnalyticsTracker();
    }
}
