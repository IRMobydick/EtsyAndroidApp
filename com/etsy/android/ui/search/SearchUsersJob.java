package com.etsy.android.ui.search;

import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.UsersRequest;
import com.etsy.android.ui.util.EtsyCardUtil;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.etsy.android.ui.search.c */
public class SearchUsersJob extends EtsyRequestJob<User> {
    private final String f3214a;
    private final int f3215c;
    private final int f3216d;
    private final boolean f3217e;

    public SearchUsersJob(String str, int i, int i2, boolean z) {
        this.f3214a = str;
        this.f3215c = i;
        this.f3216d = i2;
        this.f3217e = z;
    }

    protected EtsyRequest<User> m4726a() {
        EtsyRequest findAllUsers = UsersRequest.findAllUsers();
        Map hashMap = new HashMap();
        if (this.f3214a != null) {
            hashMap.put("keywords", this.f3214a);
        }
        hashMap.put("limit", String.valueOf(this.f3215c));
        hashMap.put("offset", String.valueOf(this.f3216d));
        if (!this.f3217e) {
            hashMap.put("includes", EtsyCardUtil.m5114d(6));
            hashMap.put("fields", "user_id,login_name,follower_count");
        }
        findAllUsers.addParams(hashMap);
        return findAllUsers;
    }
}
