package com.etsy.android.uikit.util;

import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.CirclesRequest;
import com.etsy.android.lib.requests.EtsyRequest;

/* renamed from: com.etsy.android.uikit.util.h */
class FollowUtil extends EtsyRequestJob<User> {
    private EtsyId f4157a;
    private FollowUtil f4158c;

    public FollowUtil(EtsyId etsyId, FollowUtil followUtil) {
        this.f4157a = etsyId;
        this.f4158c = followUtil;
    }

    protected EtsyRequest<User> m5533a() {
        if (aj.m1101a().m1118d()) {
            return CirclesRequest.findUserInCircle(this.f4157a);
        }
        return null;
    }

    protected void m5534a(EtsyResult<User> etsyResult) {
        if (etsyResult != null && etsyResult.m1049a()) {
            if (etsyResult.m1054e() > 0) {
                this.f4158c.m5073a();
            } else {
                this.f4158c.m5074b();
            }
        }
    }
}
