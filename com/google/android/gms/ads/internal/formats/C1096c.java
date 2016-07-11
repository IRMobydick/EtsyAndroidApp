package com.google.android.gms.ads.internal.formats;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.bu;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.no;
import com.google.android.gms.internal.zzge;
import com.google.android.gms.internal.zzgf;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

@jw
/* renamed from: com.google.android.gms.ads.internal.formats.c */
public class C1096c extends C1095f {
    private zzge f4506a;
    private zzgf f4507b;
    private final zzq f4508c;
    private d f4509d;
    private boolean f4510e;
    private Object f4511f;

    private C1096c(Context context, zzq com_google_android_gms_ads_internal_zzq, bu buVar) {
        super(context, com_google_android_gms_ads_internal_zzq, null, buVar, null, null, null, null);
        this.f4510e = false;
        this.f4511f = new Object();
        this.f4508c = com_google_android_gms_ads_internal_zzq;
    }

    public C1096c(Context context, zzq com_google_android_gms_ads_internal_zzq, bu buVar, zzge com_google_android_gms_internal_zzge) {
        this(context, com_google_android_gms_ads_internal_zzq, buVar);
        this.f4506a = com_google_android_gms_internal_zzge;
    }

    public C1096c(Context context, zzq com_google_android_gms_ads_internal_zzq, bu buVar, zzgf com_google_android_gms_internal_zzgf) {
        this(context, com_google_android_gms_ads_internal_zzq, buVar);
        this.f4507b = com_google_android_gms_internal_zzgf;
    }

    public C1094b m5997a(OnClickListener onClickListener) {
        return null;
    }

    public void m5998a() {
        zzaa.zzdc("recordImpression must be called on the main UI thread.");
        synchronized (this.f4511f) {
            m5990a(true);
            if (this.f4509d != null) {
                this.f4509d.a();
                this.f4508c.recordImpression();
            } else {
                try {
                    if (this.f4506a != null && !this.f4506a.getOverrideImpressionRecording()) {
                        this.f4506a.recordImpression();
                        this.f4508c.recordImpression();
                    } else if (!(this.f4507b == null || this.f4507b.getOverrideImpressionRecording())) {
                        this.f4507b.recordImpression();
                        this.f4508c.recordImpression();
                    }
                } catch (Throwable e) {
                    C1129c.m6193d("Failed to call recordImpression", e);
                }
            }
        }
    }

    public void m5999a(View view) {
        synchronized (this.f4511f) {
            this.f4510e = true;
            try {
                if (this.f4506a != null) {
                    this.f4506a.zzl(zze.zzD(view));
                } else if (this.f4507b != null) {
                    this.f4507b.zzl(zze.zzD(view));
                }
            } catch (Throwable e) {
                C1129c.m6193d("Failed to call prepareAd", e);
            }
            this.f4510e = false;
        }
    }

    public void m6000a(View view, Map<String, WeakReference<View>> map, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        zzaa.zzdc("performClick must be called on the main UI thread.");
        synchronized (this.f4511f) {
            if (this.f4509d != null) {
                this.f4509d.a(view, map, jSONObject, jSONObject2, jSONObject3);
                this.f4508c.onAdClicked();
            } else {
                try {
                    if (!(this.f4506a == null || this.f4506a.getOverrideClickHandling())) {
                        this.f4506a.zzk(zze.zzD(view));
                        this.f4508c.onAdClicked();
                    }
                    if (!(this.f4507b == null || this.f4507b.getOverrideClickHandling())) {
                        this.f4507b.zzk(zze.zzD(view));
                        this.f4508c.onAdClicked();
                    }
                } catch (Throwable e) {
                    C1129c.m6193d("Failed to call performClick", e);
                }
            }
        }
    }

    public void m6001a(d dVar) {
        synchronized (this.f4511f) {
            this.f4509d = dVar;
        }
    }

    public boolean m6002b() {
        boolean z;
        synchronized (this.f4511f) {
            z = this.f4510e;
        }
        return z;
    }

    public d m6003c() {
        d dVar;
        synchronized (this.f4511f) {
            dVar = this.f4509d;
        }
        return dVar;
    }

    public no m6004d() {
        return null;
    }
}
