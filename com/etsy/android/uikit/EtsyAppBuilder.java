package com.etsy.android.uikit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.XmlRes;
import android.webkit.CookieSyncManager;
import com.adjust.sdk.AdjustConfig;
import com.etsy.android.iconsy.Iconsy;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyBuild;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKey;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.al;
import com.etsy.android.lib.core.an;
import com.etsy.android.lib.core.img.ImageDownloader;
import com.etsy.android.lib.eventhorizon.EventHorizon;
import com.etsy.android.lib.eventhorizon.EventHorizonService;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyAdjust;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.EtsyLoggerDatabaseHelper;
import com.etsy.android.lib.logger.LogSessionManager;
import com.etsy.android.lib.toolbar.AdminToolbar;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.lib.util.CrashUtil.CrashProvider;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.ab;
import com.etsy.android.lib.util.bg;
import com.etsy.android.lib.util.fonts.FontMaps;
import com.etsy.android.uikit.util.GraphikUtil;
import com.pinterest.pinit.PinItButton;
import java.util.ArrayList;
import java.util.List;
import uk.co.chrisjenx.calligraphy.b;
import uk.co.chrisjenx.calligraphy.c;

/* renamed from: com.etsy.android.uikit.c */
public class EtsyAppBuilder {
    private String f3950a;
    private String f3951b;
    private EtsyAppBuilder f3952c;
    private boolean f3953d;
    private String f3954e;
    private int f3955f;
    private String f3956g;
    private Context f3957h;
    private an f3958i;
    private EtsyBuild f3959j;
    private boolean f3960k;
    private boolean f3961l;
    private String f3962m;
    private boolean f3963n;
    @XmlRes
    private int f3964o;
    @XmlRes
    private int f3965p;
    private String f3966q;
    private EtsyConfigKey f3967r;
    private String f3968s;
    private String f3969t;
    private String f3970u;
    private String f3971v;
    private String f3972w;
    private List<al> f3973x;

    public static EtsyAppBuilder m5329a(Context context) {
        return new EtsyAppBuilder(context);
    }

    private EtsyAppBuilder(Context context) {
        this.f3966q = null;
        this.f3967r = null;
        this.f3970u = null;
        this.f3971v = null;
        this.f3972w = null;
        this.f3973x = new ArrayList();
        this.f3957h = context;
    }

    public EtsyAppBuilder m5336a(String str) {
        this.f3954e = str;
        return this;
    }

    public EtsyAppBuilder m5330a(int i) {
        this.f3955f = i;
        return this;
    }

    public EtsyAppBuilder m5341b(String str) {
        this.f3956g = str;
        return this;
    }

    public EtsyAppBuilder m5338a(boolean z) {
        this.f3953d = z;
        return this;
    }

    public EtsyAppBuilder m5331a(EtsyBuild etsyBuild) {
        this.f3959j = etsyBuild;
        return this;
    }

    public EtsyAppBuilder m5337a(String str, String str2) {
        this.f3950a = str;
        this.f3951b = str2;
        return this;
    }

    public EtsyAppBuilder m5343b(boolean z) {
        this.f3960k = z;
        return this;
    }

    public EtsyAppBuilder m5346c(boolean z) {
        this.f3961l = z;
        return this;
    }

    public EtsyAppBuilder m5335a(EtsyAppBuilder etsyAppBuilder) {
        this.f3952c = etsyAppBuilder;
        return this;
    }

    public EtsyAppBuilder m5334a(an anVar) {
        this.f3958i = anVar;
        return this;
    }

    public EtsyAppBuilder m5333a(al alVar) {
        this.f3973x.add(alVar);
        return this;
    }

    public EtsyAppBuilder m5340b(@XmlRes int i) {
        this.f3964o = i;
        return this;
    }

    public EtsyAppBuilder m5344c(@XmlRes int i) {
        this.f3965p = i;
        return this;
    }

    public EtsyAppBuilder m5345c(String str) {
        this.f3962m = str;
        return this;
    }

    public EtsyAppBuilder m5332a(EtsyConfigKey etsyConfigKey, String str) {
        this.f3967r = etsyConfigKey;
        this.f3966q = str;
        return this;
    }

    public EtsyAppBuilder m5342b(String str, String str2) {
        this.f3969t = str;
        this.f3968s = str2;
        return this;
    }

    public void m5339a() {
        boolean z = true;
        if (this.f3956g != null) {
            EtsyDebug.m1895a(this.f3956g);
        }
        EtsyDebug.m1901a(this.f3953d);
        InstallInfo.m920a(this.f3957h, this.f3954e, this.f3955f, this.f3959j, this.f3953d);
        ab.m3175a(this.f3957h);
        EtsyLogger.m1969a(this.f3957h, LogSessionManager.m2054a(this.f3957h), EtsyLoggerDatabaseHelper.getInstance(this.f3957h), this.f3959j, this.f3964o);
        AnalyticsTracker.m1837a(this.f3965p);
        if (this.f3950a == null || this.f3951b == null) {
            throw new IllegalStateException("EtsyAppBuilder must have setProdApiInfo() set");
        }
        aj.m1102a(this.f3957h, this.f3958i, this.f3963n);
        EtsyConfig.m838a(this.f3957h, this.f3950a, this.f3951b);
        CrashUtil.m3037a().m3042a(CrashProvider.CRITTERCISM, this.f3967r, this.f3966q);
        CrashUtil.m3037a().m3041a(this.f3957h);
        CookieSyncManager.createInstance(this.f3957h);
        NetworkUtils.m3109a(this.f3957h);
        for (al a : this.f3973x) {
            aj.m1101a().m1112a(a);
        }
        AdminToolbar.m2987a(this.f3957h, this.f3960k);
        EventHorizon.m1751a(this.f3957h, this.f3961l);
        if (this.f3961l && EventHorizon.m1753a()) {
            this.f3957h.startService(new Intent(this.f3957h, EventHorizonService.class));
        }
        if (this.f3952c != null) {
            int p = SharedPreferencesUtility.m3158p(this.f3957h);
            boolean onAppUpgrade = this.f3952c.onAppUpgrade(p);
            aj.m1101a().m1117c(p == 0);
            z = onAppUpgrade;
        }
        if (z) {
            aj.m1101a().m1110a(aj.m1101a().m1118d());
        }
        SharedPreferencesUtility.m3119a(this.f3957h, InstallInfo.m919a().m936m());
        CurrencyUtil.m3070a(this.f3957h, EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.ad), EtsyApplication.SOE_NAME.equals(this.f3954e) ? EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.ag) : EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.af));
        EtsyMoney.setConditionalCurrencyCodesHidden(EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.ae));
        Iconsy.m771a(this.f3957h, FontMaps.FONT_MAPS);
        EtsyConfig.m837a().m870d(this.f3957h);
        ImageDownloader.m1609a().m1616a(this.f3957h);
        bg.m3328a(this.f3953d);
        aj.m1101a().m1113b();
        if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.cg)) {
            b.a(new c().a(this.f3957h.getString(R.typeface_normal)).a());
            GraphikUtil.m5545a();
        }
        if (this.f3962m != null) {
            PinItButton.setPartnerId(this.f3962m);
            PinItButton.setDebugMode(this.f3953d);
        }
        if (this.f3969t != null && this.f3968s != null) {
            EtsyAdjust.m1882a(new AdjustConfig(this.f3957h, this.f3969t, this.f3968s));
        }
    }
}
