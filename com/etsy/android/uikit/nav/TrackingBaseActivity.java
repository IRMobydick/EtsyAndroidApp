package com.etsy.android.uikit.nav;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import com.etsy.android.lib.config.TrackingEtsyConfigMap;
import com.etsy.android.lib.logger.ITrackingView;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.util.GraphikUtil;

public abstract class TrackingBaseActivity extends BaseActivity implements ITrackingView {
    private ad mAnalyticsTracker;
    @Nullable
    private String mNameFromIntent;

    @StyleRes
    protected abstract int getGraphikTheme();

    public void onCreate(Bundle bundle) {
        if (GraphikUtil.m5548b()) {
            setTheme(getGraphikTheme());
        }
        super.onCreate(bundle);
        this.mNameFromIntent = getIntent().getStringExtra("TRACKING_NAME");
        this.mAnalyticsTracker = ad.m1848a((ITrackingView) this, true, getIntent().getExtras());
        if (shouldAutoTrack()) {
            this.mAnalyticsTracker.m1856a((ITrackingView) this);
        }
    }

    protected void onResume() {
        super.onResume();
        if (shouldAutoTrack() && this.mAnalyticsTracker != null) {
            this.mAnalyticsTracker.m1859b(this);
        }
    }

    protected void onPause() {
        super.onPause();
        if (shouldAutoTrack() && this.mAnalyticsTracker != null) {
            this.mAnalyticsTracker.m1868j();
        }
    }

    protected void onStop() {
        super.onStop();
        if (shouldAutoTrack() && this.mAnalyticsTracker != null) {
            this.mAnalyticsTracker.m1869k();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (shouldAutoTrack() && this.mAnalyticsTracker != null) {
            this.mAnalyticsTracker.m1870l();
        }
    }

    @Nullable
    public ITrackingView getTrackingParent() {
        return null;
    }

    @NonNull
    public String getTrackingName() {
        if (this.mNameFromIntent != null) {
            return this.mNameFromIntent;
        }
        return getDefaultName();
    }

    @NonNull
    public final String getDefaultName() {
        return getClass().getSimpleName();
    }

    @NonNull
    public Context getAndroidContext() {
        return this;
    }

    public TrackingEtsyConfigMap getConfigMap() {
        return this.mAnalyticsTracker.m1864f();
    }

    public ad getAnalyticsContext() {
        return this.mAnalyticsTracker;
    }

    public boolean shouldAutoTrack() {
        return true;
    }
}
