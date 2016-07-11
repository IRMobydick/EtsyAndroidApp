package com.etsy.android.lib.util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.EtsyApiV3RequestJob;
import com.etsy.android.lib.core.http.url.p009a.EtsyV3Urls;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.models.AppBuild;
import java.util.List;

/* renamed from: com.etsy.android.lib.util.k */
public class CheckAlphaUpdateUtil {
    private final FragmentActivity f2059a;
    private AppBuild f2060b;

    /* renamed from: com.etsy.android.lib.util.k.1 */
    class CheckAlphaUpdateUtil extends EtsyApiV3RequestJob<AppBuild> {
        final /* synthetic */ CheckAlphaUpdateUtil f2050a;
        final /* synthetic */ CheckAlphaUpdateUtil f2051b;

        CheckAlphaUpdateUtil(CheckAlphaUpdateUtil checkAlphaUpdateUtil, CheckAlphaUpdateUtil checkAlphaUpdateUtil2) {
            this.f2051b = checkAlphaUpdateUtil;
            this.f2050a = checkAlphaUpdateUtil2;
        }

        public void m3396a(@NonNull List<AppBuild> list, int i, @NonNull EtsyV3Result<AppBuild> etsyV3Result) {
            for (AppBuild appBuild : list) {
                if (appBuild.isMasterVariant()) {
                    this.f2051b.f2060b = appBuild;
                    if (this.f2050a != null) {
                        this.f2050a.m3417a(appBuild);
                    }
                }
            }
        }

        public void m3394a(int i, @Nullable String str, @NonNull EtsyV3Result<AppBuild> etsyV3Result) {
        }
    }

    /* renamed from: com.etsy.android.lib.util.k.2 */
    class CheckAlphaUpdateUtil extends CheckAlphaUpdateUtil {
        final /* synthetic */ CheckAlphaUpdateUtil f2057a;
        final /* synthetic */ CheckAlphaUpdateUtil f2058b;

        CheckAlphaUpdateUtil(CheckAlphaUpdateUtil checkAlphaUpdateUtil, Activity activity, String str, String str2, CheckAlphaUpdateUtil checkAlphaUpdateUtil2) {
            this.f2058b = checkAlphaUpdateUtil;
            this.f2057a = checkAlphaUpdateUtil2;
            super(activity, str, str2);
        }

        protected void m3411a(Boolean bool) {
            super.m3408a(bool);
            if (this.f2057a != null) {
                this.f2057a.m3418a(true);
            }
        }
    }

    public CheckAlphaUpdateUtil(FragmentActivity fragmentActivity) {
        this.f2059a = fragmentActivity;
    }

    public void m3414a(CheckAlphaUpdateUtil checkAlphaUpdateUtil) {
        EtsyApiV3RequestJob a = EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) new EtsyApiV3Request(AppBuild.class, EtsyApplication.get().isSOE() ? EtsyV3Urls.m1502b() : EtsyV3Urls.m1501a()).m1393d());
        a.m1422a(new CheckAlphaUpdateUtil(this, checkAlphaUpdateUtil), this.f2059a);
        aj.m1101a().m1123i().m1696a(this.f2059a, a.m1426c());
    }

    public boolean m3416a() {
        return (this.f2060b == null || TextUtils.isEmpty(this.f2060b.getS3Url())) ? false : true;
    }

    public void m3415a(CheckAlphaUpdateUtil checkAlphaUpdateUtil) {
        if (m3416a()) {
            aj.m1101a().m1123i().m1697a(this.f2059a, new CheckAlphaUpdateUtil(this, this.f2059a, this.f2060b.getS3Url(), this.f2060b.getFilename(), checkAlphaUpdateUtil));
        }
    }
}
