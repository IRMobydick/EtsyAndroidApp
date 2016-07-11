package com.crittercism.app;

import crittercism.android.ae;
import crittercism.android.dy;
import crittercism.android.dz;
import crittercism.android.ef;
import crittercism.android.ew;
import java.util.HashMap;
import java.util.Map;

public class CritterUserDataRequest {
    private final CritterCallback f1054a;
    private ae f1055b;
    private Map f1056c;
    private ef f1057d;

    /* renamed from: com.crittercism.app.CritterUserDataRequest.1 */
    final class C04351 implements Runnable {
        final /* synthetic */ CritterUserDataRequest f1053a;

        C04351(CritterUserDataRequest critterUserDataRequest) {
            this.f1053a = critterUserDataRequest;
        }

        public final void run() {
            this.f1053a.f1057d.run();
            this.f1053a.f1056c = this.f1053a.f1057d.a;
            this.f1053a.f1054a.onCritterDataReceived(new CritterUserData(this.f1053a.f1056c, this.f1053a.f1055b.f.b()));
        }
    }

    public CritterUserDataRequest(CritterCallback critterCallback) {
        this.f1054a = critterCallback;
        this.f1055b = ae.A();
        this.f1056c = new HashMap();
        this.f1057d = new ef(this.f1055b);
    }

    public CritterUserDataRequest requestRateMyAppInfo() {
        this.f1057d.e();
        return this;
    }

    public CritterUserDataRequest requestDidCrashOnLastLoad() {
        this.f1057d.c();
        return this;
    }

    public CritterUserDataRequest requestUserUUID() {
        this.f1057d.d();
        return this;
    }

    public CritterUserDataRequest requestOptOutStatus() {
        this.f1057d.b();
        return this;
    }

    public synchronized void makeRequest() {
        dz dzVar = this.f1055b.q;
        if (dzVar == null) {
            dy.a("Must initialize Crittercism before calling " + getClass().getName() + ".makeRequest()", new IllegalStateException());
        } else {
            Runnable c04351 = new C04351(this);
            if (!dzVar.a(c04351)) {
                new ew(c04351).start();
            }
        }
    }
}
