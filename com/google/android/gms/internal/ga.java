package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.Map;

@jw
class ga implements fk {
    ga() {
    }

    private int m6556a(Map<String, String> map) {
        int parseInt = Integer.parseInt((String) map.get("playbackState"));
        return (parseInt < 0 || 3 < parseInt) ? 0 : parseInt;
    }

    public void m6557a(no noVar, Map<String, String> map) {
        Throwable e;
        if (((Boolean) dz.aE.m6433c()).booleanValue()) {
            zzlf com_google_android_gms_internal_zzlf;
            zzlf z = noVar.m7276z();
            if (z == null) {
                try {
                    z = new zzlf(noVar, Float.parseFloat((String) map.get("duration")));
                    noVar.m7241a(z);
                    com_google_android_gms_internal_zzlf = z;
                } catch (NullPointerException e2) {
                    e = e2;
                    C1129c.m6189b("Unable to parse videoMeta message.", e);
                    C1101o.m6044h().m7021a(e, true);
                    return;
                } catch (NumberFormatException e3) {
                    e = e3;
                    C1129c.m6189b("Unable to parse videoMeta message.", e);
                    C1101o.m6044h().m7021a(e, true);
                    return;
                }
            }
            com_google_android_gms_internal_zzlf = z;
            com_google_android_gms_internal_zzlf.zza(Float.parseFloat((String) map.get("currentTime")), m6556a(map), "1".equals(map.get("muted")));
        }
    }
}
