package com.google.android.gms.ads.internal.purchase;

import android.content.Intent;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.internal.jw;

@jw
/* renamed from: com.google.android.gms.ads.internal.purchase.e */
public class C1115e {
    private final String f4587a;

    public C1115e(String str) {
        this.f4587a = str;
    }

    public String m6104a() {
        return C1101o.m6041e().m7130d();
    }

    public boolean m6105a(String str, int i, Intent intent) {
        if (str == null || intent == null) {
            return false;
        }
        String b = C1101o.m6051o().m6101b(intent);
        String c = C1101o.m6051o().m6103c(intent);
        if (b == null || c == null) {
            return false;
        }
        if (!str.equals(C1101o.m6051o().m6098a(b))) {
            C1129c.m6192d("Developer payload not match.");
            return false;
        } else if (this.f4587a == null || C1116f.m6107a(this.f4587a, b, c)) {
            return true;
        } else {
            C1129c.m6192d("Fail to verify signature.");
            return false;
        }
    }
}
