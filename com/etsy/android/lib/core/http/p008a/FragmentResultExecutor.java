package com.etsy.android.lib.core.http.p008a;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.request.BaseHttpRequestJob;
import com.etsy.android.lib.logger.EtsyDebug;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* renamed from: com.etsy.android.lib.core.http.a.c */
public class FragmentResultExecutor extends UIResultExecutor {
    private static final String f1536a;
    private final Reference<Fragment> f1537b;

    static {
        f1536a = EtsyDebug.m1891a(FragmentResultExecutor.class);
    }

    public FragmentResultExecutor(@NonNull Fragment fragment) {
        this.f1537b = new WeakReference(fragment);
    }

    public <RequestResult extends HttpResult> void m1335a(BaseHttpRequestJob<RequestResult> baseHttpRequestJob, RequestResult requestResult) {
        Fragment fragment = (Fragment) this.f1537b.get();
        if (fragment == null || !fragment.isAdded() || fragment.isRemoving() || fragment.isDetached()) {
            EtsyDebug.m1912c(f1536a, "Fragment is no longer active - dropping network response");
        } else {
            super.m1332a(baseHttpRequestJob, requestResult);
        }
    }
}
