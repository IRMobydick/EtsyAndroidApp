package com.etsy.android.lib.core.http.p008a;

import android.app.Activity;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.request.BaseHttpRequestJob;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.uikit.BaseActivity;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* renamed from: com.etsy.android.lib.core.http.a.a */
public class ActivityResultExecutor extends UIResultExecutor {
    private static final String f1530a;
    private final Reference<Activity> f1531b;

    static {
        f1530a = EtsyDebug.m1891a(ActivityResultExecutor.class);
    }

    public ActivityResultExecutor(@NonNull Activity activity) {
        this.f1531b = new WeakReference(activity);
    }

    public <RequestResult extends HttpResult> void m1333a(BaseHttpRequestJob<RequestResult> baseHttpRequestJob, RequestResult requestResult) {
        Activity activity = (Activity) this.f1531b.get();
        boolean z = false;
        if (activity instanceof BaseActivity) {
            z = ((BaseActivity) activity).isDestroyedCompat();
        } else if (activity != null && VERSION.SDK_INT >= 17) {
            z = activity.isDestroyed();
        }
        if (activity == null || activity.isFinishing() || r1) {
            EtsyDebug.m1912c(f1530a, "Activity is no longer active - dropping network response");
        } else {
            super.m1332a(baseHttpRequestJob, requestResult);
        }
    }
}
