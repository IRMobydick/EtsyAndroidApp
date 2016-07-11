package com.etsy.android.lib.messaging;

import android.content.Intent;
import android.net.Uri;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.messaging.d */
public class EtsyRouteHelper {
    public static Intent m2279a(EtsyAction etsyAction, Intent intent) {
        Intent intent2 = new Intent();
        intent2.setAction(etsyAction.getAction());
        if (intent.getExtras() != null && intent.getExtras().getString(etsyAction.getName()) != null) {
            intent2.putExtra(etsyAction.getName(), intent.getExtras().getString(etsyAction.getName()));
        } else if (intent.getBundleExtra(etsyAction.getName()) != null) {
            intent2.putExtra(etsyAction.getName(), intent.getBundleExtra(etsyAction.getName()));
        }
        return intent2;
    }

    public static Uri m2281a(EtsyEntity etsyEntity, String str) {
        return EtsyRoute.m2264a(etsyEntity, str, EtsyAction.VIEW);
    }

    public static EtsyRoute m2283a(EtsyRoute etsyRoute, EtsyEntity etsyEntity, String str) {
        EtsyRoute etsyRoute2 = new EtsyRoute(etsyEntity, str, EtsyAction.VIEW);
        if (etsyRoute == null) {
            return etsyRoute2;
        }
        for (EtsyRoute etsyRoute3 = etsyRoute; etsyRoute3 != null; etsyRoute3 = etsyRoute3.m2277e()) {
            if (etsyRoute3.m2277e() == null) {
                etsyRoute3.m2273a(etsyRoute2);
                return etsyRoute;
            }
        }
        return etsyRoute;
    }

    public static Uri m2280a() {
        return EtsyRoute.m2264a(EtsyEntity.HOME, StringUtils.EMPTY, null);
    }

    public static Uri m2282a(String str, String str2) {
        return EtsyRouteHelper.m2283a(EtsyRouteHelper.m2283a(null, EtsyEntity.RECEIPT, str), EtsyEntity.TRACK_ORDER, str2).m2271a();
    }
}
