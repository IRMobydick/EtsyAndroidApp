package com.etsy.android.uikit.ui.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import com.etsy.android.lib.config.TrackingEtsyConfigMap;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.ITrackingView;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.logger.p010a.EtsyGraphite;

public class TrackingFragmentDelegate implements ITrackingView {
    private static final String f4059a;
    @NonNull
    private final Fragment f4060b;
    @NonNull
    private final ITrackingView f4061c;
    private ad f4062d;
    @Nullable
    private String f4063e;
    private boolean f4064f;
    private boolean f4065g;

    static {
        f4059a = EtsyDebug.m1891a(TrackingFragmentDelegate.class);
    }

    public TrackingFragmentDelegate(@NonNull Fragment fragment) {
        this.f4064f = true;
        this.f4065g = false;
        this.f4060b = fragment;
        this.f4061c = (ITrackingView) fragment;
    }

    public void m5430a(Bundle bundle) {
        EtsyDebug.m1912c(f4059a, "onCreate: isVisible (" + this.f4060b.isVisible() + ") " + this.f4060b.getClass().getSimpleName());
        if (bundle != null) {
            this.f4064f = bundle.getBoolean("Tracking.IsVisibleHint", this.f4064f);
        }
        Bundle arguments = this.f4060b.getArguments();
        if (arguments != null) {
            this.f4063e = arguments.getString("TRACKING_NAME");
        }
        this.f4062d = ad.m1848a(this.f4061c, this.f4064f, arguments);
        if (m5439g() && this.f4062d != null) {
            this.f4062d.m1856a(this.f4061c);
        }
    }

    public void m5428a() {
        EtsyDebug.m1912c(f4059a, "onResume: isVisible (" + this.f4060b.isVisible() + ") " + this.f4060b.getClass().getSimpleName());
        if (m5439g() && this.f4062d != null) {
            this.f4062d.m1859b(this.f4061c);
        }
        EtsyGraphite.m1806a(this.f4060b.getArguments(), "performance.fragment_launch." + this.f4060b.getClass().getSimpleName());
    }

    public void m5433b() {
        EtsyDebug.m1912c(f4059a, "onPause: isVisible (" + this.f4060b.isVisible() + ") " + this.f4060b.getClass().getSimpleName());
        if (m5439g() && this.f4062d != null) {
            this.f4062d.m1868j();
        }
    }

    public void m5435c() {
        EtsyDebug.m1912c(f4059a, "onStop: isVisible (" + this.f4060b.isVisible() + ") " + this.f4060b.getClass().getSimpleName());
        if (m5439g() && this.f4062d != null) {
            this.f4062d.m1869k();
        }
    }

    public void m5436d() {
        if (m5439g() && this.f4062d != null) {
            this.f4062d.m1870l();
        }
    }

    public void m5429a(Activity activity) {
        if (m5439g() && this.f4062d != null) {
            this.f4062d.m1867i();
        }
    }

    public void m5437e() {
        if (m5439g() && this.f4062d != null) {
            this.f4062d.m1866h();
        }
    }

    public void m5434b(Bundle bundle) {
        if (this.f4065g) {
            this.f4064f = false;
        }
        bundle.putBoolean("Tracking.IsVisibleHint", this.f4064f);
    }

    public void m5432a(boolean z) {
        this.f4065g = true;
        EtsyDebug.m1912c(f4059a, "setUserVisibleHint (" + z + ") " + this.f4060b.getClass().getSimpleName());
        this.f4064f = z;
        if (m5439g() && this.f4062d != null) {
            this.f4062d.m1860b(this.f4061c, z);
        }
    }

    public TrackingEtsyConfigMap m5438f() {
        return this.f4062d.m1864f();
    }

    @NonNull
    public String getTrackingName() {
        if (this.f4063e != null) {
            return this.f4063e;
        }
        return getDefaultName();
    }

    @NonNull
    public final String getDefaultName() {
        return this.f4060b.getClass().getSimpleName();
    }

    @Nullable
    public ITrackingView getTrackingParent() {
        if (this.f4060b.getParentFragment() != null && (this.f4060b.getParentFragment() instanceof ITrackingView)) {
            return (ITrackingView) this.f4060b.getParentFragment();
        }
        if (this.f4060b.getActivity() == null || !(this.f4060b.getActivity() instanceof ITrackingView)) {
            return null;
        }
        return (ITrackingView) this.f4060b.getActivity();
    }

    public ad getAnalyticsContext() {
        return this.f4062d;
    }

    @Nullable
    public Context getAndroidContext() {
        return this.f4060b.getActivity();
    }

    public boolean m5439g() {
        return true;
    }

    @VisibleForTesting
    public void m5431a(ad adVar) {
        this.f4062d = adVar;
    }
}
