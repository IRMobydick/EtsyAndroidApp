package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.doubleclick.a;
import com.google.android.gms.ads.internal.client.zzw.zza;
import com.google.android.gms.internal.jw;

@jw
public final class zzj extends zza {
    private final a zzvy;

    public zzj(a aVar) {
        this.zzvy = aVar;
    }

    public void onAppEvent(String str, String str2) {
        this.zzvy.a(str, str2);
    }
}
