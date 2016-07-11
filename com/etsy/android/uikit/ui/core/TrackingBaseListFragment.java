package com.etsy.android.uikit.ui.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.config.TrackingEtsyConfigMap;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.ITrackingView;
import com.etsy.android.lib.logger.ad;

@Deprecated
public abstract class TrackingBaseListFragment extends BaseListFragment implements ITrackingView {
    private static final String TAG;
    @NonNull
    private TrackingFragmentDelegate mTrackingDelegate;

    static {
        TAG = EtsyDebug.m1891a(TrackingBaseListFragment.class);
    }

    public TrackingBaseListFragment() {
        this.mTrackingDelegate = new TrackingFragmentDelegate(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mTrackingDelegate.m5430a(bundle);
    }

    public void onResume() {
        super.onResume();
        this.mTrackingDelegate.m5428a();
    }

    public void onPause() {
        super.onPause();
        this.mTrackingDelegate.m5433b();
    }

    public void onStop() {
        super.onStop();
        this.mTrackingDelegate.m5435c();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mTrackingDelegate.m5436d();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mTrackingDelegate.m5429a(activity);
    }

    public void onDetach() {
        super.onDetach();
        this.mTrackingDelegate.m5437e();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mTrackingDelegate.m5434b(bundle);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.mTrackingDelegate.m5432a(z);
    }

    public TrackingEtsyConfigMap getConfigMap() {
        return this.mTrackingDelegate.m5438f();
    }

    @NonNull
    public String getTrackingName() {
        return this.mTrackingDelegate.getTrackingName();
    }

    @NonNull
    public final String getDefaultName() {
        return this.mTrackingDelegate.getDefaultName();
    }

    @Nullable
    public ITrackingView getTrackingParent() {
        return this.mTrackingDelegate.getTrackingParent();
    }

    public ad getAnalyticsContext() {
        return this.mTrackingDelegate.getAnalyticsContext();
    }

    @Nullable
    public Context getAndroidContext() {
        return this.mTrackingDelegate.getAndroidContext();
    }

    public boolean shouldAutoTrack() {
        return this.mTrackingDelegate.m5439g();
    }
}
