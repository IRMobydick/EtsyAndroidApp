package com.google.android.gms.internal;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.C1068i;
import com.google.ads.mediation.C1069j;
import com.google.ads.mediation.C1070k;
import com.google.ads.mediation.C1071l;
import com.google.ads.mediation.C1073n;
import com.google.ads.mediation.MediationServerParameters;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.util.client.C1128a;
import com.google.android.gms.ads.internal.util.client.C1129c;

@jw
public final class ic<NETWORK_EXTRAS extends C1073n, SERVER_PARAMETERS extends MediationServerParameters> implements C1069j, C1071l {
    private final zzgc f5087a;

    public ic(zzgc com_google_android_gms_internal_zzgc) {
        this.f5087a = com_google_android_gms_internal_zzgc;
    }

    public void m6740a(C1068i<?, ?> c1068i, ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        C1129c.m6185a(new StringBuilder(String.valueOf(valueOf).length() + 47).append("Adapter called onFailedToReceiveAd with error. ").append(valueOf).toString());
        if (C1089r.m5951a().m6180b()) {
            try {
                this.f5087a.onAdFailedToLoad(id.m6742a(errorCode));
                return;
            } catch (Throwable e) {
                C1129c.m6193d("Could not call onAdFailedToLoad.", e);
                return;
            }
        }
        C1129c.m6192d("onFailedToReceiveAd must be called on the main UI thread.");
        C1128a.f4666a.post(new 2(this, errorCode));
    }

    public void m6741a(C1070k<?, ?> c1070k, ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        C1129c.m6185a(new StringBuilder(String.valueOf(valueOf).length() + 47).append("Adapter called onFailedToReceiveAd with error ").append(valueOf).append(".").toString());
        if (C1089r.m5951a().m6180b()) {
            try {
                this.f5087a.onAdFailedToLoad(id.m6742a(errorCode));
                return;
            } catch (Throwable e) {
                C1129c.m6193d("Could not call onAdFailedToLoad.", e);
                return;
            }
        }
        C1129c.m6192d("onFailedToReceiveAd must be called on the main UI thread.");
        C1128a.f4666a.post(new 1(this, errorCode));
    }
}
