package com.google.android.gms.ads.internal.formats;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.google.android.gms.internal.jw;
import java.util.List;

@jw
/* renamed from: com.google.android.gms.ads.internal.formats.a */
public class C1093a {
    static final int f4480a;
    static final int f4481b;
    private static final int f4482c;
    private static final int f4483d;
    private final String f4484e;
    private final List<Drawable> f4485f;
    private final int f4486g;
    private final int f4487h;
    private final int f4488i;
    private final int f4489j;

    static {
        f4482c = Color.rgb(12, 174, 206);
        f4483d = Color.rgb(204, 204, 204);
        f4480a = f4483d;
        f4481b = f4482c;
    }

    public C1093a(String str, List<Drawable> list, Integer num, Integer num2, Integer num3, int i) {
        this.f4484e = str;
        this.f4485f = list;
        this.f4486g = num != null ? num.intValue() : f4480a;
        this.f4487h = num2 != null ? num2.intValue() : f4481b;
        this.f4488i = num3 != null ? num3.intValue() : 12;
        this.f4489j = i;
    }

    public String m5974a() {
        return this.f4484e;
    }

    public List<Drawable> m5975b() {
        return this.f4485f;
    }

    public int m5976c() {
        return this.f4486g;
    }

    public int m5977d() {
        return this.f4487h;
    }

    public int m5978e() {
        return this.f4488i;
    }

    public int m5979f() {
        return this.f4489j;
    }
}
