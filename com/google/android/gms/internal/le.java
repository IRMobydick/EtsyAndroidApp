package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.SystemClock;

@jw
final class le {
    private long f5372a;
    private long f5373b;

    public le() {
        this.f5372a = -1;
        this.f5373b = -1;
    }

    public long m6999a() {
        return this.f5373b;
    }

    public void m7000b() {
        this.f5373b = SystemClock.elapsedRealtime();
    }

    public void m7001c() {
        this.f5372a = SystemClock.elapsedRealtime();
    }

    public Bundle m7002d() {
        Bundle bundle = new Bundle();
        bundle.putLong("topen", this.f5372a);
        bundle.putLong("tclose", this.f5373b);
        return bundle;
    }
}
