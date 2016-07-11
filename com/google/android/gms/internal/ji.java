package com.google.android.gms.internal;

import android.content.Context;
import com.adjust.sdk.Constants;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;

@jw
public class ji extends je implements np {
    ji(Context context, lc lcVar, no noVar, jh jhVar) {
        super(context, lcVar, noVar, jhVar);
    }

    protected void m6816b() {
        if (this.e.errorCode == -2) {
            this.c.m7262l().zza((np) this);
            m6817d();
            C1129c.m6185a("Loading HTML in WebView.");
            this.c.loadDataWithBaseURL(C1101o.m6041e().m7095a(this.e.zzHH), this.e.body, "text/html", Constants.ENCODING, null);
        }
    }

    protected void m6817d() {
    }
}
