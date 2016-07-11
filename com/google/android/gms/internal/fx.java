package com.google.android.gms.internal;

import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.Map;

@jw
public class fx implements fk {
    private final fy f4939a;

    public fx(fy fyVar) {
        this.f4939a = fyVar;
    }

    public static void m6550a(no noVar, fy fyVar) {
        noVar.m7262l().zza("/reward", new fx(fyVar));
    }

    private void m6551a(Map<String, String> map) {
        RewardItemParcel rewardItemParcel;
        try {
            int parseInt = Integer.parseInt((String) map.get(ResponseConstants.AMOUNT));
            String str = (String) map.get(FindsModule.FIELD_TYPE);
            if (!TextUtils.isEmpty(str)) {
                rewardItemParcel = new RewardItemParcel(str, parseInt);
                this.f4939a.zzb(rewardItemParcel);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Unable to parse reward amount.", e);
        }
        rewardItemParcel = null;
        this.f4939a.zzb(rewardItemParcel);
    }

    private void m6552b(Map<String, String> map) {
        this.f4939a.zzbP();
    }

    public void m6553a(no noVar, Map<String, String> map) {
        String str = (String) map.get("action");
        if ("grant".equals(str)) {
            m6551a(map);
        } else if ("video_start".equals(str)) {
            m6552b(map);
        }
    }
}
