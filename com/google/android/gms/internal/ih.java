package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.Map;

@jw
public class ih {
    private final no f5120a;
    private final boolean f5121b;
    private final String f5122c;

    public ih(no noVar, Map<String, String> map) {
        this.f5120a = noVar;
        this.f5122c = (String) map.get("forceOrientation");
        if (map.containsKey("allowOrientationChange")) {
            this.f5121b = Boolean.parseBoolean((String) map.get("allowOrientationChange"));
        } else {
            this.f5121b = true;
        }
    }

    public void m6778a() {
        if (this.f5120a == null) {
            C1129c.m6192d("AdWebView is null");
            return;
        }
        int b = "portrait".equalsIgnoreCase(this.f5122c) ? C1101o.m6043g().m7160b() : "landscape".equalsIgnoreCase(this.f5122c) ? C1101o.m6043g().m7146a() : this.f5121b ? -1 : C1101o.m6043g().m7164c();
        this.f5120a.m7248b(b);
    }
}
