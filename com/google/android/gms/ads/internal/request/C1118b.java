package com.google.android.gms.ads.internal.request;

import android.content.Context;
import com.google.android.gms.internal.bu;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ln;
import java.util.concurrent.Future;

@jw
/* renamed from: com.google.android.gms.ads.internal.request.b */
public class C1118b {
    public ln m6118a(Context context, C1117a c1117a, bu buVar, c cVar) {
        ln c1124m = c1117a.f4605b.extras.getBundle("sdk_less_server_data") != null ? new C1124m(context, c1117a, cVar) : new zzb(context, c1117a, buVar, cVar);
        Future future = (Future) c1124m.zzhs();
        return c1124m;
    }
}
