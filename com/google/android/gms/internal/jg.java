package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.zzs;

@jw
public class jg {
    public me m6814a(Context context, zza com_google_android_gms_ads_internal_zza, lc lcVar, bu buVar, @Nullable no noVar, zzga com_google_android_gms_internal_zzga, jh jhVar, ei eiVar) {
        me jkVar;
        AdResponseParcel adResponseParcel = lcVar.f5353b;
        if (adResponseParcel.zzLP) {
            jkVar = new jk(context, lcVar, com_google_android_gms_internal_zzga, jhVar, eiVar, noVar);
        } else if (!adResponseParcel.zzvv) {
            jkVar = adResponseParcel.zzLV ? new jf(context, lcVar, noVar, jhVar) : (((Boolean) dz.f4822V.m6433c()).booleanValue() && zzs.zzve() && !zzs.isAtLeastL() && noVar != null && noVar.m7261k().zzvt) ? new jj(context, lcVar, noVar, jhVar) : new ji(context, lcVar, noVar, jhVar);
        } else if (com_google_android_gms_ads_internal_zza instanceof zzq) {
            jkVar = new jl(context, (zzq) com_google_android_gms_ads_internal_zza, lcVar, buVar, jhVar);
        } else {
            String valueOf = String.valueOf(com_google_android_gms_ads_internal_zza != null ? com_google_android_gms_ads_internal_zza.getClass().getName() : "null");
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 65).append("Invalid NativeAdManager type. Found: ").append(valueOf).append("; Required: NativeAdManager.").toString());
        }
        String str = "AdRenderer: ";
        String valueOf2 = String.valueOf(jkVar.getClass().getName());
        C1129c.m6185a(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
        jkVar.zzhs();
        return jkVar;
    }

    public me m6815a(Context context, lc lcVar, zziy com_google_android_gms_internal_zziy) {
        me ksVar = new ks(context, lcVar, com_google_android_gms_internal_zziy);
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(ksVar.getClass().getName());
        C1129c.m6185a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        ksVar.zzhs();
        return ksVar;
    }
}
