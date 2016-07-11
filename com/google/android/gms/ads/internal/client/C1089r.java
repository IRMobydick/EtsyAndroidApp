package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.internal.reward.client.C1126f;
import com.google.android.gms.ads.internal.util.client.C1128a;
import com.google.android.gms.internal.fc;
import com.google.android.gms.internal.io;
import com.google.android.gms.internal.jb;
import com.google.android.gms.internal.jw;

@jw
/* renamed from: com.google.android.gms.ads.internal.client.r */
public class C1089r {
    private static final Object f4467a;
    private static C1089r f4468b;
    private final C1128a f4469c;
    private final C1088p f4470d;

    static {
        f4467a = new Object();
        C1089r.m5952a(new C1089r());
    }

    protected C1089r() {
        this.f4469c = new C1128a();
        this.f4470d = new C1088p(new C1084l(), new C1083k(), new C1082i(), new fc(), new C1126f(), new jb(), new io());
    }

    public static C1128a m5951a() {
        return C1089r.m5954c().f4469c;
    }

    protected static void m5952a(C1089r c1089r) {
        synchronized (f4467a) {
            f4468b = c1089r;
        }
    }

    public static C1088p m5953b() {
        return C1089r.m5954c().f4470d;
    }

    private static C1089r m5954c() {
        C1089r c1089r;
        synchronized (f4467a) {
            c1089r = f4468b;
        }
        return c1089r;
    }
}
