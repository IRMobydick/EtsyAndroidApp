package com.etsy.android.lib.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKey;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.http.body.FormBody;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.EtsyApiV3RequestJob;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.apiv3.DismissibleNotice;
import java.util.List;

/* compiled from: PromoUtil */
public class ay {
    private static final String f2010a;

    /* renamed from: com.etsy.android.lib.util.ay.1 */
    final class PromoUtil extends EtsyApiV3RequestJob<DismissibleNotice> {
        final /* synthetic */ String f2009a;

        PromoUtil(String str) {
            this.f2009a = str;
        }

        public void m3281a(@NonNull List<DismissibleNotice> list, int i, @NonNull EtsyV3Result<DismissibleNotice> etsyV3Result) {
            EtsyDebug.m1914c(ay.f2010a, "dismissed notice (%s)", this.f2009a);
        }

        public void m3279a(int i, @Nullable String str, @NonNull EtsyV3Result<DismissibleNotice> etsyV3Result) {
            EtsyDebug.m1920e(ay.f2010a, "error dismissing notice (%s) - %s", this.f2009a, str);
        }
    }

    static {
        f2010a = EtsyDebug.m1891a(ay.class);
    }

    public static boolean m3288a(Context context, @NonNull EtsyConfigKey etsyConfigKey) {
        int a = m3283a(etsyConfigKey);
        return InstallInfo.m919a().m936m() < a && SharedPreferencesUtility.m3127a(context, m3285a(a));
    }

    public static int m3283a(@NonNull EtsyConfigKey etsyConfigKey) {
        return EtsyConfig.m837a().m869d().m886d(etsyConfigKey);
    }

    public static String m3285a(int i) {
        return "update_promo" + i;
    }

    public static boolean m3289a(Context context, AppBuild appBuild) {
        return appBuild.isValidUpdatedBuild(context) && SharedPreferencesUtility.m3127a(context, m3286a(appBuild.getVersion()));
    }

    public static String m3286a(String str) {
        return "update_beta" + str;
    }

    public static void m3290b(String str) {
        m3287a(str, 0);
    }

    public static void m3287a(String str, long j) {
        aj.m1101a().m1123i().m1695a(((EtsyApiV3RequestJob) EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) ((EtsyApiV3Request) ((EtsyApiV3Request) EtsyApiV3Request.m1454a(DismissibleNotice.class, String.format("/member/notices/%s", new Object[]{str})).m1382a(1)).m1383a(((FormBody) new FormBody().m1341b("reinstate_date", Long.toString(j))).m1345f())).m1393d()).m1421a(new PromoUtil(str))).m1426c());
    }
}
