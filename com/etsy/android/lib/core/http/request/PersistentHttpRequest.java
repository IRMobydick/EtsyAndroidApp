package com.etsy.android.lib.core.http.request;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.request.BasePersistentHttpRequest.OnPersistentResultHandler;
import com.etsy.android.lib.core.http.url.HttpUrl;

public final class PersistentHttpRequest extends BasePersistentHttpRequest<HttpRequest, HttpResult, HttpUrl> {
    private static final long serialVersionUID = 1309127590684219663L;

    public abstract class HttpPersistentResultHandler extends OnPersistentResultHandler<HttpResult> {
        private static final long serialVersionUID = 7344525823463297769L;

        public abstract boolean onPersistentResult(@NonNull Context context, @Nullable HttpResult httpResult);
    }

    private PersistentHttpRequest(ab abVar) {
        super(abVar);
    }
}
