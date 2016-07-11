package com.etsy.android.lib.core.http.p008a;

import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.request.BaseHttpRequestJob;

/* renamed from: com.etsy.android.lib.core.http.a.d */
public class SynchronousResultExecutor implements BaseHttpRequestJob {
    public <RequestResult extends HttpResult> void m1336a(BaseHttpRequestJob<RequestResult> baseHttpRequestJob, RequestResult requestResult) {
        baseHttpRequestJob.m830a(requestResult);
    }
}
