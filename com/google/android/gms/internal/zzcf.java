package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

@TargetApi(14)
@jw
public class zzcf implements ActivityLifecycleCallbacks {
    private Activity mActivity;
    private Context mContext;
    private final Object zzpp;

    public zzcf(Application application, Context context) {
        this.zzpp = new Object();
        application.registerActivityLifecycleCallbacks(this);
        if (context instanceof Activity) {
            setActivity((Activity) context);
        }
        this.mContext = context;
    }

    private void setActivity(Activity activity) {
        synchronized (this.zzpp) {
            if (!activity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                this.mActivity = activity;
            }
        }
    }

    @Nullable
    public Activity getActivity() {
        return this.mActivity;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        synchronized (this.zzpp) {
            if (this.mActivity == null) {
                return;
            }
            if (this.mActivity.equals(activity)) {
                this.mActivity = null;
            }
        }
    }

    public void onActivityPaused(Activity activity) {
        setActivity(activity);
    }

    public void onActivityResumed(Activity activity) {
        setActivity(activity);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        setActivity(activity);
    }

    public void onActivityStopped(Activity activity) {
    }
}
