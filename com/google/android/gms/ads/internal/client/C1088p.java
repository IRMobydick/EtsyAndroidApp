package com.google.android.gms.ads.internal.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.client.p.1;
import com.google.android.gms.ads.internal.client.p.2;
import com.google.android.gms.ads.internal.client.p.3;
import com.google.android.gms.ads.internal.client.p.4;
import com.google.android.gms.ads.internal.client.p.5;
import com.google.android.gms.ads.internal.client.p.6;
import com.google.android.gms.ads.internal.client.p.7;
import com.google.android.gms.ads.internal.client.zzx.zza;
import com.google.android.gms.ads.internal.reward.client.C1126f;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.internal.fc;
import com.google.android.gms.internal.io;
import com.google.android.gms.internal.jb;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzdl;
import com.google.android.gms.internal.zzga;
import com.google.android.gms.internal.zzgz;
import com.google.android.gms.internal.zzhi;

@jw
/* renamed from: com.google.android.gms.ads.internal.client.p */
public class C1088p {
    private zzx f4458a;
    private final Object f4459b;
    private final C1084l f4460c;
    private final C1083k f4461d;
    private final C1082i f4462e;
    private final fc f4463f;
    private final C1126f f4464g;
    private final jb f4465h;
    private final io f4466i;

    public C1088p(C1084l c1084l, C1083k c1083k, C1082i c1082i, fc fcVar, C1126f c1126f, jb jbVar, io ioVar) {
        this.f4459b = new Object();
        this.f4460c = c1084l;
        this.f4461d = c1083k;
        this.f4462e = c1082i;
        this.f4463f = fcVar;
        this.f4464g = c1126f;
        this.f4465h = jbVar;
        this.f4466i = ioVar;
    }

    @Nullable
    private static zzx m5932a() {
        try {
            Object newInstance = C1088p.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (newInstance instanceof IBinder) {
                return zza.asInterface((IBinder) newInstance);
            }
            C1129c.m6192d("ClientApi class is not an instance of IBinder");
            return null;
        } catch (Throwable e) {
            C1129c.m6193d("Failed to instantiate ClientApi class.", e);
            return null;
        }
    }

    private <T> T m5934a(Context context, boolean z, q<T> qVar) {
        if (!(z || C1089r.m5951a().m6181b(context))) {
            C1129c.m6185a("Google Play Services is not available");
            z = true;
        }
        T c;
        if (z) {
            c = qVar.c();
            return c == null ? qVar.b() : c;
        } else {
            c = qVar.b();
            return c == null ? qVar.c() : c;
        }
    }

    private void m5935a(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("action", "no_ads_fallback");
        bundle.putString("flow", str);
        C1089r.m5951a().m6172a(context, null, "gmob-apps", bundle, true);
    }

    private static boolean m5937a(Activity activity, String str) {
        Intent intent = activity.getIntent();
        if (intent.hasExtra(str)) {
            return intent.getBooleanExtra(str, false);
        }
        C1129c.m6188b("useClientJar flag not found in activity intent extras.");
        return false;
    }

    @Nullable
    private zzx m5939b() {
        zzx com_google_android_gms_ads_internal_client_zzx;
        synchronized (this.f4459b) {
            if (this.f4458a == null) {
                this.f4458a = C1088p.m5932a();
            }
            com_google_android_gms_ads_internal_client_zzx = this.f4458a;
        }
        return com_google_android_gms_ads_internal_client_zzx;
    }

    public zzs m5944a(Context context, String str, zzga com_google_android_gms_internal_zzga) {
        return (zzs) m5934a(context, false, new 4(this, context, str, com_google_android_gms_internal_zzga));
    }

    public zzu m5945a(Context context, AdSizeParcel adSizeParcel, String str) {
        return (zzu) m5934a(context, false, new 2(this, context, adSizeParcel, str));
    }

    public zzu m5946a(Context context, AdSizeParcel adSizeParcel, String str, zzga com_google_android_gms_internal_zzga) {
        return (zzu) m5934a(context, false, new 1(this, context, adSizeParcel, str, com_google_android_gms_internal_zzga));
    }

    public zzdl m5947a(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        return (zzdl) m5934a(context, false, new 5(this, frameLayout, frameLayout2, context));
    }

    @Nullable
    public zzhi m5948a(Activity activity) {
        return (zzhi) m5934a((Context) activity, C1088p.m5937a(activity, "com.google.android.gms.ads.internal.purchase.useClientJar"), new 6(this, activity));
    }

    public zzu m5949b(Context context, AdSizeParcel adSizeParcel, String str, zzga com_google_android_gms_internal_zzga) {
        return (zzu) m5934a(context, false, new 3(this, context, adSizeParcel, str, com_google_android_gms_internal_zzga));
    }

    @Nullable
    public zzgz m5950b(Activity activity) {
        return (zzgz) m5934a((Context) activity, C1088p.m5937a(activity, "com.google.android.gms.ads.internal.overlay.useClientJar"), new 7(this, activity));
    }
}
