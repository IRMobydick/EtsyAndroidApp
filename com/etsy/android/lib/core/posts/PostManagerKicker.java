package com.etsy.android.lib.core.posts;

import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.util.NetworkUtils;

/* renamed from: com.etsy.android.lib.core.posts.g */
public class PostManagerKicker implements Runnable {
    public void run() {
        if (NetworkUtils.m3107a().m3114b() && aj.m1101a().m1118d()) {
            EtsyDebug.m1906b(PostManagerKicker.f1652a, "Run Post Manager - PostManagerKicker");
            EtsyPostManager j = aj.m1101a().m1124j();
            if (j != null) {
                j.m1661a();
            }
        }
    }
}
