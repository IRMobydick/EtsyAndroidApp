package com.etsy.android.lib.core;

import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequestBatch;

@Deprecated
/* renamed from: com.etsy.android.lib.core.r */
public abstract class EtsyRequestBatchJob extends EtsyRequestJob<EmptyResult> {
    protected abstract EtsyRequestBatch a_();

    protected EtsyRequestBatchJob() {
    }

    protected EtsyRequest<EmptyResult> m721a() {
        EtsyRequest a_ = a_();
        if (!a_.isCommitted()) {
            a_.commitBatch();
        }
        return a_;
    }
}
