package com.etsy.android.lib.core;

import com.etsy.android.lib.core.posts.EtsyPostManager;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.uikit.receiver.DataStateReceiver;

/* compiled from: Session */
class ak extends DataStateReceiver {
    private static final String f1444a;

    private ak() {
    }

    static {
        f1444a = EtsyDebug.m1891a(ak.class);
    }

    public void m1133a() {
        EtsyDebug.m1906b(f1444a, "Run Post Manager - Network Connected");
        EtsyPostManager j = aj.m1101a().m1124j();
        if (j != null) {
            j.m1661a();
        }
    }

    public void m1134b() {
    }
}
