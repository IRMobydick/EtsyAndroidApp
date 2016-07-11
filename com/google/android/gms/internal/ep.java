package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.ads.formats.b;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;

@jw
public class ep extends b {
    private final zzdj f4887a;
    private final Drawable f4888b;
    private final Uri f4889c;
    private final double f4890d;

    public ep(zzdj com_google_android_gms_internal_zzdj) {
        Drawable drawable;
        double d;
        Uri uri = null;
        this.f4887a = com_google_android_gms_internal_zzdj;
        try {
            zzd zzeM = this.f4887a.zzeM();
            if (zzeM != null) {
                drawable = (Drawable) zze.zzx(zzeM);
                this.f4888b = drawable;
                uri = this.f4887a.getUri();
                this.f4889c = uri;
                d = 1.0d;
                d = this.f4887a.getScale();
                this.f4890d = d;
            }
        } catch (Throwable e) {
            C1129c.m6189b("Failed to get drawable.", e);
        }
        Object obj = uri;
        this.f4888b = drawable;
        try {
            uri = this.f4887a.getUri();
        } catch (Throwable e2) {
            C1129c.m6189b("Failed to get uri.", e2);
        }
        this.f4889c = uri;
        d = 1.0d;
        try {
            d = this.f4887a.getScale();
        } catch (Throwable e3) {
            C1129c.m6189b("Failed to get scale.", e3);
        }
        this.f4890d = d;
    }

    public Drawable m6489a() {
        return this.f4888b;
    }

    public Uri m6490b() {
        return this.f4889c;
    }

    public double m6491c() {
        return this.f4890d;
    }
}
