package com.google.ads.conversiontracking;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.google.ads.conversiontracking.g.d;

/* renamed from: com.google.ads.conversiontracking.a */
public class C1058a extends C1057c {
    private final Context f4347a;
    private final String f4348b;
    private final String f4349c;
    private final d f4350d;
    private final String f4351e;
    private final String f4352f;
    private final boolean f4353g;

    public C1058a(Context context, String str, String str2, String str3, String str4, boolean z) {
        d dVar;
        this.f4347a = context;
        this.f4348b = str;
        this.f4349c = str2;
        this.f4351e = str3;
        this.f4352f = str4;
        this.f4353g = z;
        if (this instanceof b) {
            dVar = d.b;
        } else {
            dVar = d.c;
        }
        this.f4350d = dVar;
    }

    public void m5805a() {
        boolean z = true;
        w c = new w().a(this.f4348b).a(this.f4350d).b(this.f4349c).c(this.f4351e);
        if (this.f4352f != null) {
            c.d(this.f4352f);
        }
        if (this.f4350d == d.c) {
            o a = o.a(this.f4347a);
            a.a(this.f4348b);
            c.a(a.b(this.f4348b));
        }
        if (g.a(this.f4347a, c, this.f4353g)) {
            try {
                if (this.f4350d == d.c) {
                    c.a(g.a(this.f4347a, this.f4348b));
                } else {
                    z = false;
                }
                m5802a(this.f4347a, c, true, this.f4353g, z);
            } catch (Throwable e) {
                Log.e("GoogleConversionReporter", "Error sending ping", e);
            }
        }
    }

    public static boolean m5804a(Context context, Uri uri) {
        boolean z = false;
        if (uri == null) {
            Log.e("GoogleConversionReporter", "Failed to register referrer from a null click url");
        } else {
            String valueOf = String.valueOf(uri);
            Log.i("GoogleConversionReporter", new StringBuilder(String.valueOf(valueOf).length() + 13).append("Registering: ").append(valueOf).toString());
            v a = g.a(uri);
            if (a == null) {
                valueOf = String.valueOf(uri);
                Log.w("GoogleConversionReporter", new StringBuilder(String.valueOf(valueOf).length() + 31).append("Failed to parse referrer from: ").append(valueOf).toString());
            } else {
                z = g.a(context, a);
                if (z) {
                    valueOf = String.valueOf(uri);
                    Log.i("GoogleConversionReporter", new StringBuilder(String.valueOf(valueOf).length() + 25).append("Successfully registered: ").append(valueOf).toString());
                } else {
                    valueOf = String.valueOf(uri);
                    Log.w("GoogleConversionReporter", new StringBuilder(String.valueOf(valueOf).length() + 20).append("Failed to register: ").append(valueOf).toString());
                }
            }
        }
        return z;
    }

    public static void m5803a(Context context, String str, String str2, String str3, String str4, boolean z) {
        new C1058a(context, str, str2, str3, str4, z).m5805a();
    }
}
