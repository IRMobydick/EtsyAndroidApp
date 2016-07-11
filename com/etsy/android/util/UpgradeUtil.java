package com.etsy.android.util;

import android.content.Context;
import android.text.TextUtils;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.GuestRequest;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.etsy.android.util.posts.MergeGuestsPost;
import java.util.Arrays;
import java.util.HashSet;

/* renamed from: com.etsy.android.util.g */
public class UpgradeUtil {
    protected static final HashSet<String> f4307a;

    static {
        f4307a = new HashSet(Arrays.asList(new String[]{"9774d56d682e549c"}));
    }

    public static void m5725a(Context context) {
        if (!f4307a.contains(InstallInfo.m919a().m927d())) {
            EtsyRequest mergeFromGuest = GuestRequest.mergeFromGuest(InstallInfo.m919a().m927d());
            mergeFromGuest.setRetryCount(5);
            mergeFromGuest.setRetryBackOffMultiplier(1.5f);
            aj.m1101a().m1124j().m1663a(new MergeGuestsPost(mergeFromGuest));
            new AdHocEventCompatBuilder("merged_guest_cart_to_uuid").m5515a("app").m5517a();
        }
    }

    public static void m5726b(Context context) {
        if (TextUtils.equals(InstallInfo.m919a().m925b(), InstallInfo.m919a().m927d())) {
            InstallInfo.m919a().m924a(context);
        }
    }
}
