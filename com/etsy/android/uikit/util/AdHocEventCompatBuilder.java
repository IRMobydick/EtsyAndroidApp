package com.etsy.android.uikit.util;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewParent;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.logger.ITrackingView;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.TrackedEtsyId;
import com.etsy.android.lib.models.datatypes.TrackedObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.etsy.android.uikit.util.a */
public class AdHocEventCompatBuilder {
    private static final String f4142a;
    @Nullable
    private AnalyticsTracker f4143b;
    @NonNull
    private String f4144c;
    private boolean f4145d;
    @Nullable
    private String f4146e;
    @Nullable
    private String f4147f;
    private long f4148g;
    @Nullable
    private HashMap<String, Object> f4149h;
    private HashMap<AnalyticsLogAttribute, Object> f4150i;

    static {
        f4142a = EtsyDebug.m1891a(AdHocEventCompatBuilder.class);
    }

    public AdHocEventCompatBuilder(@NonNull String str) {
        this.f4145d = false;
        this.f4148g = 0;
        this.f4144c = str;
    }

    @CheckResult
    public AdHocEventCompatBuilder m5513a(@Nullable AnalyticsTracker analyticsTracker) {
        this.f4143b = analyticsTracker;
        return this;
    }

    @CheckResult
    public AdHocEventCompatBuilder m5510a(@Nullable View view) {
        if (view != null) {
            this.f4143b = AdHocEventCompatBuilder.m5506b(view);
        } else {
            this.f4143b = null;
        }
        return this;
    }

    @CheckResult
    public AdHocEventCompatBuilder m5515a(@Nullable String str) {
        this.f4146e = str;
        return this;
    }

    @CheckResult
    public AdHocEventCompatBuilder m5518b(@Nullable String str) {
        this.f4147f = str;
        return this;
    }

    @CheckResult
    public AdHocEventCompatBuilder m5509a(long j) {
        this.f4148g = j;
        return this;
    }

    public AdHocEventCompatBuilder m5516a(@Nullable HashMap<String, Object> hashMap) {
        this.f4149h = hashMap;
        return this;
    }

    public AdHocEventCompatBuilder m5514a(@Nullable ITrackedObject iTrackedObject) {
        m5507b(iTrackedObject);
        return this;
    }

    public AdHocEventCompatBuilder m5511a(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable EtsyId etsyId) {
        m5507b(new TrackedEtsyId(analyticsLogAttribute, etsyId));
        return this;
    }

    public AdHocEventCompatBuilder m5512a(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable Object obj) {
        m5507b(new TrackedObject(analyticsLogAttribute, obj));
        return this;
    }

    private void m5507b(@Nullable ITrackedObject iTrackedObject) {
        if (iTrackedObject != null) {
            if (this.f4150i == null) {
                this.f4150i = new HashMap();
            }
            Map trackingParameters = iTrackedObject.getTrackingParameters();
            if (trackingParameters != null) {
                this.f4150i.putAll(trackingParameters);
            }
        }
    }

    public void m5517a() {
        EtsyLogger.m1966a().m1989a(this.f4144c, this.f4146e, this.f4147f, this.f4145d, this.f4148g, this.f4149h);
        if (EtsyConfig.m837a().m869d() != null && EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bz)) {
            if (this.f4143b == null) {
                this.f4143b = EtsyApplication.get().getAnalyticsTracker();
            }
            this.f4143b.m1845a(this.f4144c, this.f4150i);
        }
    }

    @Nullable
    public static ad m5506b(@NonNull View view) {
        EtsyDebug.m1912c(f4142a, "findViewTracker start");
        Context context = view.getContext();
        HashMap hashMap = new HashMap();
        if (context instanceof FragmentActivity) {
            AdHocEventCompatBuilder.m5505a(((FragmentActivity) context).getSupportFragmentManager(), hashMap);
            for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
                if (parent instanceof View) {
                    Fragment fragment = (Fragment) hashMap.get(parent);
                    if (fragment != null && (fragment instanceof ITrackingView)) {
                        return ((ITrackingView) fragment).getAnalyticsContext();
                    }
                }
            }
        }
        if (context instanceof ITrackingView) {
            return ((ITrackingView) view.getContext()).getAnalyticsContext();
        }
        return null;
    }

    public static AnalyticsTracker m5508c(@Nullable View view) {
        AnalyticsTracker analyticsTracker = null;
        if (view != null) {
            analyticsTracker = AdHocEventCompatBuilder.m5506b(view);
        }
        return analyticsTracker != null ? analyticsTracker : EtsyApplication.get().getAnalyticsTracker();
    }

    private static void m5505a(@NonNull FragmentManager fragmentManager, @NonNull HashMap<View, Fragment> hashMap) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null) {
                    if (fragment.getView() != null) {
                        hashMap.put(fragment.getView(), fragment);
                    }
                    FragmentManager childFragmentManager = fragment.getChildFragmentManager();
                    if (!(childFragmentManager == null || childFragmentManager.getFragments() == null)) {
                        AdHocEventCompatBuilder.m5505a(childFragmentManager, (HashMap) hashMap);
                    }
                }
            }
        }
    }
}
