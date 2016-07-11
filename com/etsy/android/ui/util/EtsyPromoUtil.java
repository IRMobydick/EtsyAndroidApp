package com.etsy.android.ui.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.ay;
import com.etsy.android.ui.nav.EtsyEventTracker;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.promos.VersionPromo;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.util.EtsyBuildHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.etsy.android.ui.util.m */
public class EtsyPromoUtil extends ay {
    public static final List<VersionPromo> f3720a;

    /* renamed from: com.etsy.android.ui.util.m.1 */
    final class EtsyPromoUtil extends TrackingOnClickListener {
        final /* synthetic */ TrackingBaseActivity f3719a;

        EtsyPromoUtil(TrackingBaseActivity trackingBaseActivity) {
            this.f3719a = trackingBaseActivity;
        }

        public void onViewClick(View view) {
            view.getContext().startActivity(EtsyPromoUtil.m5141a(this.f3719a.getAnalyticsContext()));
        }
    }

    static {
        f3720a = new ArrayList();
    }

    public static void m5145a(@Nullable AnalyticsTracker analyticsTracker, Context context, @NonNull VersionPromo versionPromo) {
        HashMap hashMap = new HashMap();
        hashMap.put("promo_version", versionPromo.getUniqueName());
        new AdHocEventCompatBuilder("update_beta_dismissed").m5515a(NotificationCompatApi21.CATEGORY_PROMO).m5516a(hashMap).m5512a(AnalyticsLogAttribute.PROMO_VERSION, versionPromo.getUniqueName()).m5513a(analyticsTracker).m5517a();
        SharedPreferencesUtility.m3136c(context, versionPromo.getUniqueName());
    }

    public static void m5143a(Context context, int i) {
        for (VersionPromo versionPromo : f3720a) {
            if (i < versionPromo.getMinVersion()) {
                SharedPreferencesUtility.m3139d(context, versionPromo.getUniqueName());
            }
        }
    }

    public static VersionPromo m5142a(Context context) {
        for (VersionPromo versionPromo : f3720a) {
            if (SharedPreferencesUtility.m3132b(context, versionPromo.getUniqueName())) {
                return versionPromo;
            }
        }
        return null;
    }

    public static boolean m5148b(Context context) {
        return ay.m3288a(context, EtsyConfigKeys.cG);
    }

    public static void m5146a(TrackingBaseActivity trackingBaseActivity) {
        EtsyEventTracker.m4595j();
        Nav.m4682a((FragmentActivity) trackingBaseActivity).m4686d().m4404a(2130903454, ay.m3285a(ay.m3283a(EtsyConfigKeys.cG)), new EtsyPromoUtil(trackingBaseActivity));
    }

    public static Intent m5141a(@NonNull ad adVar) {
        return new Intent("android.intent.action.VIEW", Uri.parse(adVar.m1864f().m883b(EtsyConfigKeys.cE)));
    }

    public static boolean m5147b() {
        return EtsyBuildHelper.m5707b();
    }

    public static void m5144a(FragmentActivity fragmentActivity, OnClickListener onClickListener, String str) {
        Nav.m4682a(fragmentActivity).m4686d().m4404a(2130903453, ay.m3286a(str), onClickListener);
    }
}
