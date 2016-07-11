package com.etsy.android.lib.core.http.request;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.BaseModel;

@Deprecated
public class EtsyApiLoader<D extends BaseModel> extends Loader<EtsyResult<D>> {
    private EtsyResult<D> mCachedData;
    private BaseHttpRequestJob mJob;
    private final EtsyApiRequestJob mJobBuilder;
    private boolean mRequestStarted;

    /* renamed from: com.etsy.android.lib.core.http.request.EtsyApiLoader.1 */
    class C04491 extends BaseHttpRequestJob<EtsyResult<D>> {
        final /* synthetic */ EtsyApiLoader f1556a;

        C04491(EtsyApiLoader etsyApiLoader) {
            this.f1556a = etsyApiLoader;
        }

        public void m1379a(@NonNull EtsyResult<D> etsyResult) {
            this.f1556a.dispatchComplete(etsyResult);
        }
    }

    public EtsyApiLoader(@NonNull Context context, @NonNull EtsyApiV3RequestJob<D> etsyApiV3RequestJob) {
        super(context);
        this.mJobBuilder = etsyApiV3RequestJob;
        initResultHandler();
    }

    public EtsyApiLoader(Context context, EtsyApiV2RequestJob<D> etsyApiV2RequestJob) {
        super(context);
        this.mJobBuilder = etsyApiV2RequestJob;
        initResultHandler();
    }

    private void initResultHandler() {
        this.mJobBuilder.m1425b(new C04491(this));
    }

    protected void onStartLoading() {
        if (this.mCachedData == null) {
            onForceLoad();
        } else {
            super.deliverResult(this.mCachedData);
        }
    }

    protected void onReset() {
        super.onReset();
        if (this.mJob != null) {
            this.mJob.cancel();
        }
        this.mCachedData = null;
    }

    protected void onForceLoad() {
        if (this.mJobBuilder != null) {
            cancelLoad();
            this.mJob = this.mJobBuilder.m1426c();
            aj.m1101a().m1123i().m1695a(this.mJob);
        }
    }

    public void deliverResult(EtsyResult<D> etsyResult) {
        this.mCachedData = etsyResult;
        if (isStarted()) {
            super.deliverResult(etsyResult);
        }
    }

    protected boolean onCancelLoad() {
        if (this.mJob == null) {
            return false;
        }
        this.mJob.cancel();
        return true;
    }

    protected void dispatchComplete(EtsyResult<D> etsyResult) {
        if (this.mJob != null) {
            commitContentChanged();
            this.mJob = null;
            if (!isAbandoned()) {
                deliverResult((EtsyResult) etsyResult);
            }
        }
    }
}
