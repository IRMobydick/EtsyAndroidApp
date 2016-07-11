package com.google.android.gms.internal;

import com.appboy.Constants;
import com.google.android.gms.ads.internal.C1078b;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.gcm.Task;
import java.util.Map;

@jw
public class fu implements fk {
    static final Map<String, Integer> f4933a;
    private final C1078b f4934b;
    private final ig f4935c;

    static {
        f4933a = zzf.zza("resize", Integer.valueOf(1), "playVideo", Integer.valueOf(2), "storePicture", Integer.valueOf(3), "createCalendarEvent", Integer.valueOf(4), "setOrientationProperties", Integer.valueOf(5), "closeResizedAd", Integer.valueOf(6));
    }

    public fu(C1078b c1078b, ig igVar) {
        this.f4934b = c1078b;
        this.f4935c = igVar;
    }

    public void m6544a(no noVar, Map<String, String> map) {
        int intValue = ((Integer) f4933a.get((String) map.get(Constants.APPBOY_PUSH_CONTENT_KEY))).intValue();
        if (intValue == 5 || this.f4934b == null || this.f4934b.m5856b()) {
            switch (intValue) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    this.f4935c.m6771a((Map) map);
                    return;
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    new ii(noVar, map).m6782a();
                    return;
                case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    new C1159if(noVar, map).m6765a();
                    return;
                case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                    new ih(noVar, map).m6778a();
                    return;
                case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                    this.f4935c.m6772a(true);
                    return;
                default:
                    C1129c.m6190c("Unknown MRAID command called.");
                    return;
            }
        }
        this.f4934b.m5855a(null);
    }
}
