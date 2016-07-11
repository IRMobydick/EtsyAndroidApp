package com.etsy.android.uikit.util;

import com.etsy.android.lib.core.posts.EtsyRequestPost;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.requests.SupportFeedbackRequest;

public class SupportFeedbackPost extends EtsyRequestPost<EmptyResult> {
    public SupportFeedbackPost(SupportFeedbackRequest<EmptyResult> supportFeedbackRequest) {
        super(supportFeedbackRequest);
    }

    public int getVersionCode() {
        return 1;
    }
}
