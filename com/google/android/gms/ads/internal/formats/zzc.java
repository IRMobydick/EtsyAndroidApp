package com.google.android.gms.ads.internal.formats;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzdj.zza;

@jw
public class zzc extends zza {
    private final Uri mUri;
    private final Drawable zzAA;
    private final double zzAB;

    public zzc(Drawable drawable, Uri uri, double d) {
        this.zzAA = drawable;
        this.mUri = uri;
        this.zzAB = d;
    }

    public double getScale() {
        return this.zzAB;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public zzd zzeM() {
        return zze.zzD(this.zzAA);
    }
}
