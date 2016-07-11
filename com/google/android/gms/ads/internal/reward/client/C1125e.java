package com.google.android.gms.ads.internal.reward.client;

import com.google.android.gms.ads.a.a;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.internal.jw;

@jw
/* renamed from: com.google.android.gms.ads.internal.reward.client.e */
public class C1125e implements a {
    private final zza f4664a;

    public C1125e(zza com_google_android_gms_ads_internal_reward_client_zza) {
        this.f4664a = com_google_android_gms_ads_internal_reward_client_zza;
    }

    public String m6154a() {
        String str = null;
        if (this.f4664a != null) {
            try {
                str = this.f4664a.getType();
            } catch (Throwable e) {
                C1129c.m6193d("Could not forward getType to RewardItem", e);
            }
        }
        return str;
    }

    public int m6155b() {
        int i = 0;
        if (this.f4664a != null) {
            try {
                i = this.f4664a.getAmount();
            } catch (Throwable e) {
                C1129c.m6193d("Could not forward getAmount to RewardItem", e);
            }
        }
        return i;
    }
}
