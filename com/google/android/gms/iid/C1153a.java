package com.google.android.gms.iid;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import com.appboy.support.ValidationUtils;
import com.etsy.android.lib.models.finds.FindsModule;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.google.android.gms.iid.a */
public class C1153a {
    static Map<String, C1153a> f4712a;
    static String f4713f;
    private static C1157f f4714g;
    private static C1156e f4715h;
    Context f4716b;
    KeyPair f4717c;
    String f4718d;
    long f4719e;

    static {
        f4712a = new HashMap();
    }

    protected C1153a(Context context, String str, Bundle bundle) {
        this.f4718d = StringUtils.EMPTY;
        this.f4716b = context.getApplicationContext();
        this.f4718d = str;
    }

    static int m6297a(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("InstanceID", new StringBuilder(String.valueOf(valueOf).length() + 38).append("Never happens: can't find own package ").append(valueOf).toString());
            return i;
        }
    }

    public static synchronized C1153a m6298a(Context context, Bundle bundle) {
        C1153a c1153a;
        synchronized (C1153a.class) {
            String string = bundle == null ? StringUtils.EMPTY : bundle.getString("subtype");
            String str = string == null ? StringUtils.EMPTY : string;
            Context applicationContext = context.getApplicationContext();
            if (f4714g == null) {
                f4714g = new C1157f(applicationContext);
                f4715h = new C1156e(applicationContext);
            }
            f4713f = Integer.toString(C1153a.m6297a(applicationContext));
            c1153a = (C1153a) f4712a.get(str);
            if (c1153a == null) {
                c1153a = new C1153a(applicationContext, str, bundle);
                f4712a.put(str, c1153a);
            }
        }
        return c1153a;
    }

    static String m6299a(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) (((digest[0] & 15) + 112) & ValidationUtils.APPBOY_STRING_MAX_LENGTH);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException e) {
            Log.w("InstanceID", "Unexpected error, device missing required alghorithms");
            return null;
        }
    }

    static String m6300a(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    static String m6301b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("InstanceID", new StringBuilder(String.valueOf(valueOf).length() + 38).append("Never happens: can't find own package ").append(valueOf).toString());
            return null;
        }
    }

    public static C1153a m6302c(Context context) {
        return C1153a.m6298a(context, null);
    }

    public String m6303a(String str, String str2, Bundle bundle) {
        Object obj = null;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        Object obj2 = 1;
        String a = m6310f() ? null : f4714g.m6333a(this.f4718d, str, str2);
        if (a == null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            if (bundle.getString("ttl") != null) {
                obj2 = null;
            }
            if (!"jwt".equals(bundle.getString(FindsModule.FIELD_TYPE))) {
                obj = obj2;
            }
            a = m6306b(str, str2, bundle);
            if (!(a == null || r1 == null)) {
                f4714g.m6336a(this.f4718d, str, str2, a, f4713f);
            }
        }
        return a;
    }

    KeyPair m6304a() {
        if (this.f4717c == null) {
            this.f4717c = f4714g.m6340c(this.f4718d);
        }
        if (this.f4717c == null) {
            this.f4719e = System.currentTimeMillis();
            this.f4717c = f4714g.m6334a(this.f4718d, this.f4719e);
        }
        return this.f4717c;
    }

    public String m6305b() {
        return C1153a.m6299a(m6304a());
    }

    public String m6306b(String str, String str2, Bundle bundle) {
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("sender", str);
        String str3 = StringUtils.EMPTY.equals(this.f4718d) ? str : this.f4718d;
        if (!bundle.containsKey("legacy.register")) {
            bundle.putString("subscription", str);
            bundle.putString("subtype", str3);
            bundle.putString("X-subscription", str);
            bundle.putString("X-subtype", str3);
        }
        return f4715h.m6326b(f4715h.m6320a(bundle, m6304a()));
    }

    public void m6307c() {
        this.f4719e = 0;
        f4714g.m6341d(this.f4718d);
        this.f4717c = null;
    }

    public C1157f m6308d() {
        return f4714g;
    }

    public C1156e m6309e() {
        return f4715h;
    }

    boolean m6310f() {
        String a = f4714g.m6331a("appVersion");
        if (a == null || !a.equals(f4713f)) {
            return true;
        }
        a = f4714g.m6331a("lastToken");
        if (a == null) {
            return true;
        }
        return (System.currentTimeMillis() / 1000) - Long.valueOf(Long.parseLong(a)).longValue() > 604800;
    }
}
