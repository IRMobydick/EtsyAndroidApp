package com.etsy.android.lib.core.http.url.p009a;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.datatypes.EtsyId;

/* renamed from: com.etsy.android.lib.core.http.url.a.c */
public final class EtsyV2Urls {
    public static String m1497a() {
        return "/users/__SELF__";
    }

    public static String m1498a(@NonNull EtsyId etsyId) {
        return "/users/" + etsyId.getId();
    }
}
