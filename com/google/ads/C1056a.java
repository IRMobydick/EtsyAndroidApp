package com.google.ads;

import com.appboy.AppboyImageUtils;
import com.foresee.mobileReplay.recorder.ScreenRecorder;
import com.google.android.gms.ads.f;

@Deprecated
/* renamed from: com.google.ads.a */
public final class C1056a {
    public static final C1056a f4339a;
    public static final C1056a f4340b;
    public static final C1056a f4341c;
    public static final C1056a f4342d;
    public static final C1056a f4343e;
    public static final C1056a f4344f;
    private final f f4345g;

    static {
        f4339a = new C1056a(-1, -2, "mb");
        f4340b = new C1056a(320, 50, "mb");
        f4341c = new C1056a(300, ScreenRecorder.DEFAULT_CAPTURE_FREQ, "as");
        f4342d = new C1056a(468, 60, "as");
        f4343e = new C1056a(728, 90, "as");
        f4344f = new C1056a(AppboyImageUtils.BASELINE_SCREEN_DPI, 600, "as");
    }

    private C1056a(int i, int i2, String str) {
        this(new f(i, i2));
    }

    public C1056a(f fVar) {
        this.f4345g = fVar;
    }

    public int m5800a() {
        return this.f4345g.b();
    }

    public int m5801b() {
        return this.f4345g.a();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C1056a)) {
            return false;
        }
        return this.f4345g.equals(((C1056a) obj).f4345g);
    }

    public int hashCode() {
        return this.f4345g.hashCode();
    }

    public String toString() {
        return this.f4345g.toString();
    }
}
