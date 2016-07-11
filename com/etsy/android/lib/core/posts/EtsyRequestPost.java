package com.etsy.android.lib.core.posts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyRequestQueue;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.requests.EtsyRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Deprecated
public abstract class EtsyRequestPost<Result extends BaseModel> implements PersistentRequest<EtsyRequest<Result>, EtsyResult<Result>> {
    private static final String TAG;
    private static final long serialVersionUID = -4739648392270657437L;
    @JsonIgnore
    protected boolean mIsAdded;
    private PostInfo mPostInfo;
    private EtsyRequest<Result> mRequest;

    public abstract int getVersionCode();

    static {
        TAG = EtsyDebug.m1891a(EtsyRequestPost.class);
    }

    public EtsyRequestPost(EtsyRequest<Result> etsyRequest) {
        this.mRequest = etsyRequest;
    }

    public EtsyRequestPost(EtsyRequest<Result> etsyRequest, PostInfo postInfo) {
        this.mRequest = etsyRequest;
        this.mPostInfo = postInfo;
    }

    public EtsyRequest<Result> getRequest() {
        return this.mRequest;
    }

    public boolean isPersisted() {
        return this.mIsAdded;
    }

    public void setPersisted(boolean z) {
        this.mIsAdded = z;
    }

    public PostInfo getPostInfo() {
        return this.mPostInfo;
    }

    public void onAdded(Context context) {
    }

    public final void onPersisted(Context context) {
        this.mIsAdded = true;
        onAdded(context);
    }

    public void onSuccess(Context context, EtsyResult<Result> etsyResult) {
    }

    public boolean onError(Context context, EtsyResult<Result> etsyResult) {
        if (getRequest() != null) {
            EtsyLogger.m1966a().m1996b("etsy-post-manager", "Dropping Post Request, not retrying. URL: " + getRequest().getUrl());
        }
        return false;
    }

    public boolean isValidRequest() {
        return true;
    }

    public void onUpgraded(int i, int i2) {
    }

    public EtsyResult<Result> onPersistentRun(@NonNull EtsyRequestQueue etsyRequestQueue) {
        return (EtsyResult) etsyRequestQueue.m1703b(EtsyJobBuilder.m1307a(getRequest()).m1324a());
    }

    public boolean onPersistentResult(@NonNull Context context, @Nullable EtsyResult<Result> etsyResult) {
        if (etsyResult == null || !etsyResult.m1049a()) {
            EtsyDebug.m1912c(TAG, "runPost error");
            if (onError(context, etsyResult)) {
                return true;
            }
            if (getRequest() == null) {
                return false;
            }
            EtsyLogger.m1966a().m1996b("etsy-post-manager", "Dropping Post Request, not retrying. URL: " + getRequest().getUrl());
            return false;
        }
        EtsyDebug.m1912c(TAG, "runPost success");
        onSuccess(context, etsyResult);
        return false;
    }
}
