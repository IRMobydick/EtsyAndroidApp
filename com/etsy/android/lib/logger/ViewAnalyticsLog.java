package com.etsy.android.lib.logger;

import android.support.annotation.NonNull;
import com.etsy.android.lib.logger.AnalyticsLog.LogType;

/* renamed from: com.etsy.android.lib.logger.z */
public class ViewAnalyticsLog extends AnalyticsLog {
    public ViewAnalyticsLog(@NonNull ITrackingView iTrackingView, boolean z) {
        this(iTrackingView, z, false);
    }

    public ViewAnalyticsLog(@NonNull ITrackingView iTrackingView, boolean z, boolean z2) {
        super(LogType.VIEW, ViewAnalyticsLog.m2095a(iTrackingView.getAnalyticsContext().m1846b(), z, z2), ViewAnalyticsLog.m2096a(iTrackingView.getAnalyticsContext()), iTrackingView.getAnalyticsContext().m1865g());
        m1784a(AnalyticsLogAttribute.IS_SURFACED, Boolean.valueOf(z));
        m1784a(AnalyticsLogAttribute.IS_RESUME, Boolean.valueOf(z2));
        m1784a(AnalyticsLogAttribute.PAGE_GUID, iTrackingView.getAnalyticsContext().m1843a());
        if (ViewAnalyticsLog.m2096a(iTrackingView.getAnalyticsContext())) {
            m1790d();
        }
        if (iTrackingView.getAndroidContext() != null) {
            m1783a(iTrackingView.getAndroidContext());
        }
        if (iTrackingView.getAnalyticsContext().m1863e() != null) {
            m1784a(AnalyticsLogAttribute.PARENT_PAGE_GUID, iTrackingView.getAnalyticsContext().m1863e());
        }
    }

    static boolean m2096a(@NonNull ad adVar) {
        return true;
    }

    @NonNull
    static String m2095a(@NonNull String str, boolean z, boolean z2) {
        if (z || z2) {
            return str;
        }
        return str + "_init";
    }
}
