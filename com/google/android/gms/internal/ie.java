package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsIntent.Builder;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.mediation.a;
import com.google.android.gms.ads.mediation.f;
import com.google.android.gms.ads.mediation.g;

@jw
public class ie implements f {
    private Activity f5088a;
    private em f5089b;
    private g f5090c;
    private Uri f5091d;

    public static boolean m6747a(Context context) {
        return em.m6484a(context);
    }

    public void m6750a() {
        C1129c.m6185a("Destroying AdMobCustomTabsAdapter adapter.");
        try {
            this.f5089b.m6486a(this.f5088a);
        } catch (Throwable e) {
            C1129c.m6189b("Exception while unbinding from CustomTabsService.", e);
        }
    }

    public void m6751a(Context context, g gVar, Bundle bundle, a aVar, Bundle bundle2) {
        this.f5090c = gVar;
        if (this.f5090c == null) {
            C1129c.m6192d("Listener not set for mediation. Returning.");
        } else if (!(context instanceof Activity)) {
            C1129c.m6192d("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.f5090c.a(this, 0);
        } else if (m6747a(context)) {
            Object string = bundle.getString("tab_url");
            if (TextUtils.isEmpty(string)) {
                C1129c.m6192d("The tab_url retrieved from mediation metadata is empty. Bailing out.");
                this.f5090c.a(this, 0);
                return;
            }
            this.f5088a = (Activity) context;
            this.f5091d = Uri.parse(string);
            this.f5089b = new em();
            this.f5089b.m6487a(new 1(this));
            this.f5089b.m6488b(this.f5088a);
            this.f5090c.a(this);
        } else {
            C1129c.m6192d("Default browser does not support custom tabs. Bailing out.");
            this.f5090c.a(this, 0);
        }
    }

    public void m6752b() {
        C1129c.m6185a("Pausing AdMobCustomTabsAdapter adapter.");
    }

    public void m6753c() {
        C1129c.m6185a("Resuming AdMobCustomTabsAdapter adapter.");
    }

    public void m6754e() {
        CustomTabsIntent build = new Builder(this.f5089b.m6485a()).build();
        build.intent.setData(this.f5091d);
        lt.f5423a.post(new 3(this, new AdOverlayInfoParcel(new AdLauncherIntentInfoParcel(build.intent), null, new 2(this), null, new VersionInfoParcel(0, 0, false))));
        C1101o.m6044h().m7025b(false);
    }
}
