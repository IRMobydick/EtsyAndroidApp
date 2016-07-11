package com.google.android.gms.internal;

import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.concurrent.Callable;

@jw
public class mt {
    public static <T> T m7191a(Callable<T> callable) {
        ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        T call;
        try {
            StrictMode.setThreadPolicy(new Builder(threadPolicy).permitDiskReads().permitDiskWrites().build());
            call = callable.call();
            return call;
        } catch (Throwable th) {
            call = th;
            C1129c.m6189b("Unexpected exception.", call);
            C1101o.m6044h().m7021a((Throwable) call, true);
            return null;
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }
}
