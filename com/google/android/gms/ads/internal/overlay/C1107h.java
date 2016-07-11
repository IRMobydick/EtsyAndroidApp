package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.eg;
import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.no;

@jw
/* renamed from: com.google.android.gms.ads.internal.overlay.h */
public abstract class C1107h {
    @Nullable
    public abstract zzi m6068a(Context context, no noVar, int i, boolean z, ei eiVar, eg egVar);

    protected boolean m6069a(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return zzs.zzva() && (applicationInfo == null || applicationInfo.targetSdkVersion >= 11);
    }

    protected boolean m6070a(no noVar) {
        return noVar.m7261k().zzvt;
    }
}
