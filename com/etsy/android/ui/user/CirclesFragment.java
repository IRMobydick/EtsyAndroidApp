package com.etsy.android.ui.user;

import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.requests.CirclesRequest;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.ac;
import com.etsy.android.ui.util.EtsyCardUtil;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.user.a */
class CirclesFragment extends EtsyRequestJob<User> {
    final /* synthetic */ CirclesFragment f3522a;
    private boolean f3523c;

    public CirclesFragment(CirclesFragment circlesFragment, boolean z) {
        this.f3522a = circlesFragment;
        this.f3523c = z;
    }

    protected EtsyRequest<User> m5020a() {
        EtsyRequest<User> membersOfMyCircle;
        if (this.f3522a.mIsTypeFollowing && this.f3522a.isYou()) {
            membersOfMyCircle = CirclesRequest.getMembersOfMyCircle();
        } else if (this.f3522a.mIsTypeFollowing) {
            membersOfMyCircle = CirclesRequest.getMembersOfUsersCircle(this.f3522a.mUserId);
        } else if (this.f3522a.isYou()) {
            membersOfMyCircle = CirclesRequest.getCirclesContainingMe();
        } else {
            membersOfMyCircle = CirclesRequest.getCirclesContainingUser(this.f3522a.mUserId);
        }
        Map hashMap = new HashMap();
        hashMap.put("fields", "user_id,login_name,follower_count");
        hashMap.put("includes", EtsyCardUtil.m5114d(4));
        hashMap.put("limit", "30");
        hashMap.put("offset", this.f3522a.mOffset + StringUtils.EMPTY);
        membersOfMyCircle.addParams(hashMap);
        return membersOfMyCircle;
    }

    protected void m5021a(EtsyResult<User> etsyResult) {
        if (this.f3523c) {
            this.f3522a.mAdapter.clear();
        }
        this.f3522a.stopPullToRefresh();
        access$312(this.f3522a, ac.m3185a(etsyResult, this.f3522a.mAdapter, this.f3522a));
    }
}
