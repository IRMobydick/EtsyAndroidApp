package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.eg;
import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.no;

@jw
/* renamed from: com.google.android.gms.ads.internal.overlay.k */
public class C1108k extends C1107h {
    @Nullable
    public zzi m6071a(Context context, no noVar, int i, boolean z, ei eiVar, eg egVar) {
        if (!m6069a(context)) {
            return null;
        }
        return new zzc(context, z, m6070a(noVar), new C1110t(context, noVar.m7265o(), noVar.m7272v(), eiVar, egVar));
    }
}
