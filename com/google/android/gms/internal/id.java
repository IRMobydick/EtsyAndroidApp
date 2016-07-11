package com.google.android.gms.internal;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;
import com.google.ads.C1056a;
import com.google.ads.mediation.C1066g;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.m;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.Date;
import java.util.HashSet;

@jw
public final class id {
    public static int m6742a(ErrorCode errorCode) {
        switch (1.b[errorCode.ordinal()]) {
            case Task.NETWORK_STATE_ANY /*2*/:
                return 1;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return 2;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return 3;
            default:
                return 0;
        }
    }

    public static Gender m6743a(int i) {
        switch (i) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return Gender.MALE;
            case Task.NETWORK_STATE_ANY /*2*/:
                return Gender.FEMALE;
            default:
                return Gender.UNKNOWN;
        }
    }

    public static C1056a m6744a(AdSizeParcel adSizeParcel) {
        int i = 0;
        C1056a[] c1056aArr = new C1056a[]{C1056a.f4339a, C1056a.f4340b, C1056a.f4341c, C1056a.f4342d, C1056a.f4343e, C1056a.f4344f};
        while (i < 6) {
            if (c1056aArr[i].m5800a() == adSizeParcel.width && c1056aArr[i].m5801b() == adSizeParcel.height) {
                return c1056aArr[i];
            }
            i++;
        }
        return new C1056a(m.a(adSizeParcel.width, adSizeParcel.height, adSizeParcel.zzvs));
    }

    public static C1066g m6745a(AdRequestParcel adRequestParcel) {
        return new C1066g(new Date(adRequestParcel.zzuN), m6743a(adRequestParcel.zzuO), adRequestParcel.zzuP != null ? new HashSet(adRequestParcel.zzuP) : null, adRequestParcel.zzuQ, adRequestParcel.zzuV);
    }
}
