package com.etsy.android.lib.core.posts;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.EtsyRequestQueue;
import java.io.Serializable;

public interface PersistentRequest<Request, Result> extends Serializable {
    @Nullable
    PostInfo getPostInfo();

    @Nullable
    Request getRequest();

    int getVersionCode();

    boolean isPersisted();

    boolean isValidRequest();

    void onPersisted(Context context);

    @CheckResult
    boolean onPersistentResult(@NonNull Context context, @Nullable Result result);

    Result onPersistentRun(@NonNull EtsyRequestQueue etsyRequestQueue);

    void onUpgraded(int i, int i2);

    void setPersisted(boolean z);
}
