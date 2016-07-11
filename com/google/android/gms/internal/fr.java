package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.Map;

@jw
public class fr implements fk {
    private final fs f4931a;

    public fr(fs fsVar) {
        this.f4931a = fsVar;
    }

    public void m6539a(no noVar, Map<String, String> map) {
        float parseFloat;
        boolean equals = "1".equals(map.get("transparentBackground"));
        boolean equals2 = "1".equals(map.get("blur"));
        try {
            if (map.get("blurRadius") != null) {
                parseFloat = Float.parseFloat((String) map.get("blurRadius"));
                this.f4931a.zzf(equals);
                this.f4931a.zza(equals2, parseFloat);
            }
        } catch (Throwable e) {
            C1129c.m6189b("Fail to parse float", e);
        }
        parseFloat = 0.0f;
        this.f4931a.zzf(equals);
        this.f4931a.zza(equals2, parseFloat);
    }
}
