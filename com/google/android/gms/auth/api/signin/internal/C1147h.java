package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.etsy.android.lib.convos.Draft;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.zzaa;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

/* renamed from: com.google.android.gms.auth.api.signin.internal.h */
public class C1147h {
    private static final Lock f4700a;
    private static C1147h f4701b;
    private final Lock f4702c;
    private final SharedPreferences f4703d;

    static {
        f4700a = new ReentrantLock();
    }

    C1147h(Context context) {
        this.f4702c = new ReentrantLock();
        this.f4703d = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static C1147h m6267a(Context context) {
        zzaa.zzz(context);
        f4700a.lock();
        try {
            if (f4701b == null) {
                f4701b = new C1147h(context.getApplicationContext());
            }
            C1147h c1147h = f4701b;
            return c1147h;
        } finally {
            f4700a.unlock();
        }
    }

    private String m6268b(String str, String str2) {
        String valueOf = String.valueOf(Draft.IMAGE_DELIMITER);
        return new StringBuilder(((String.valueOf(str).length() + 0) + String.valueOf(valueOf).length()) + String.valueOf(str2).length()).append(str).append(valueOf).append(str2).toString();
    }

    public GoogleSignInAccount m6269a() {
        return m6270a(m6276c("defaultGoogleSignInAccount"));
    }

    GoogleSignInAccount m6270a(String str) {
        GoogleSignInAccount googleSignInAccount = null;
        if (!TextUtils.isEmpty(str)) {
            String c = m6276c(m6268b("googleSignInAccount", str));
            if (c != null) {
                try {
                    googleSignInAccount = GoogleSignInAccount.zzcm(c);
                } catch (JSONException e) {
                }
            }
        }
        return googleSignInAccount;
    }

    void m6271a(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        zzaa.zzz(googleSignInAccount);
        zzaa.zzz(googleSignInOptions);
        String zzpf = googleSignInAccount.zzpf();
        m6272a(m6268b("googleSignInAccount", zzpf), googleSignInAccount.zzph());
        m6272a(m6268b("googleSignInOptions", zzpf), googleSignInOptions.zzpg());
    }

    protected void m6272a(String str, String str2) {
        this.f4702c.lock();
        try {
            this.f4703d.edit().putString(str, str2).apply();
        } finally {
            this.f4702c.unlock();
        }
    }

    public GoogleSignInOptions m6273b() {
        return m6274b(m6276c("defaultGoogleSignInAccount"));
    }

    GoogleSignInOptions m6274b(String str) {
        GoogleSignInOptions googleSignInOptions = null;
        if (!TextUtils.isEmpty(str)) {
            String c = m6276c(m6268b("googleSignInOptions", str));
            if (c != null) {
                try {
                    googleSignInOptions = GoogleSignInOptions.zzco(c);
                } catch (JSONException e) {
                }
            }
        }
        return googleSignInOptions;
    }

    public void m6275b(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        zzaa.zzz(googleSignInAccount);
        zzaa.zzz(googleSignInOptions);
        m6272a("defaultGoogleSignInAccount", googleSignInAccount.zzpf());
        m6271a(googleSignInAccount, googleSignInOptions);
    }

    protected String m6276c(String str) {
        this.f4702c.lock();
        try {
            String string = this.f4703d.getString(str, null);
            return string;
        } finally {
            this.f4702c.unlock();
        }
    }

    public void m6277c() {
        String c = m6276c("defaultGoogleSignInAccount");
        m6279e("defaultGoogleSignInAccount");
        m6278d(c);
    }

    void m6278d(String str) {
        if (!TextUtils.isEmpty(str)) {
            m6279e(m6268b("googleSignInAccount", str));
            m6279e(m6268b("googleSignInOptions", str));
        }
    }

    protected void m6279e(String str) {
        this.f4702c.lock();
        try {
            this.f4703d.edit().remove(str).apply();
        } finally {
            this.f4702c.unlock();
        }
    }
}
