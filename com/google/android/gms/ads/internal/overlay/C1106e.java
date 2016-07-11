package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.jw;

@jw
/* renamed from: com.google.android.gms.ads.internal.overlay.e */
public class C1106e {
    public void m6066a(Context context, AdOverlayInfoParcel adOverlayInfoParcel) {
        m6067a(context, adOverlayInfoParcel, true);
    }

    public void m6067a(Context context, AdOverlayInfoParcel adOverlayInfoParcel, boolean z) {
        if (adOverlayInfoParcel.zzHL == 4 && adOverlayInfoParcel.zzHE == null) {
            if (adOverlayInfoParcel.zzHD != null) {
                adOverlayInfoParcel.zzHD.onAdClicked();
            }
            C1101o.m6038b().m6064a(context, adOverlayInfoParcel.zzHC, adOverlayInfoParcel.zzHK);
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", adOverlayInfoParcel.zzsx.zzRE);
        intent.putExtra("shouldCallOnOverlayOpened", z);
        AdOverlayInfoParcel.zza(intent, adOverlayInfoParcel);
        if (!zzs.isAtLeastL()) {
            intent.addFlags(AccessibilityNodeInfoCompat.ACTION_COLLAPSE);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        C1101o.m6041e().m7101a(context, intent);
    }
}
