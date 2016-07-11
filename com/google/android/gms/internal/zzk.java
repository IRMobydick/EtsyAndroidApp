package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

public abstract class zzk<T> implements Comparable<zzk<T>> {
    private final sq f5500a;
    private final int f5501b;
    private final String f5502c;
    private final int f5503d;
    private final om f5504e;
    private Integer f5505f;
    private nm f5506g;
    private boolean f5507h;
    private boolean f5508i;
    private boolean f5509j;
    private long f5510k;
    private py f5511l;
    private cc f5512m;

    public zzk(int i, String str, om omVar) {
        this.f5500a = sq.a ? new sq() : null;
        this.f5507h = true;
        this.f5508i = false;
        this.f5509j = false;
        this.f5510k = 0;
        this.f5512m = null;
        this.f5501b = i;
        this.f5502c = str;
        this.f5504e = omVar;
        m7361a(new ef());
        this.f5503d = m7352a(str);
    }

    private static int m7352a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    return host.hashCode();
                }
            }
        }
        return 0;
    }

    private byte[] m7353a(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : map.entrySet()) {
                stringBuilder.append(URLEncoder.encode((String) entry.getKey(), str));
                stringBuilder.append('=');
                stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str));
                stringBuilder.append('&');
            }
            return stringBuilder.toString().getBytes(str);
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = "Encoding not supported: ";
            String valueOf = String.valueOf(str);
            throw new RuntimeException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
        }
    }

    public int m7355a() {
        return this.f5501b;
    }

    public int m7356a(zzk<T> com_google_android_gms_internal_zzk_T) {
        zza q = m7382q();
        zza q2 = com_google_android_gms_internal_zzk_T.m7382q();
        return q == q2 ? this.f5505f.intValue() - com_google_android_gms_internal_zzk_T.f5505f.intValue() : q2.ordinal() - q.ordinal();
    }

    protected abstract ol<T> m7357a(jm jmVar);

    public final zzk<?> m7358a(int i) {
        this.f5505f = Integer.valueOf(i);
        return this;
    }

    public zzk<?> m7359a(cc ccVar) {
        this.f5512m = ccVar;
        return this;
    }

    public zzk<?> m7360a(nm nmVar) {
        this.f5506g = nmVar;
        return this;
    }

    public zzk<?> m7361a(py pyVar) {
        this.f5511l = pyVar;
        return this;
    }

    protected zzr m7362a(zzr com_google_android_gms_internal_zzr) {
        return com_google_android_gms_internal_zzr;
    }

    protected abstract void m7363a(T t);

    public int m7364b() {
        return this.f5503d;
    }

    public void m7365b(zzr com_google_android_gms_internal_zzr) {
        if (this.f5504e != null) {
            this.f5504e.a(com_google_android_gms_internal_zzr);
        }
    }

    public void m7366b(String str) {
        if (sq.a) {
            this.f5500a.a(str, Thread.currentThread().getId());
        } else if (this.f5510k == 0) {
            this.f5510k = SystemClock.elapsedRealtime();
        }
    }

    public String m7367c() {
        return this.f5502c;
    }

    void m7368c(String str) {
        if (this.f5506g != null) {
            this.f5506g.b(this);
        }
        if (sq.a) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new 1(this, str, id));
                return;
            }
            this.f5500a.a(str, id);
            this.f5500a.a(toString());
            return;
        }
        if (SystemClock.elapsedRealtime() - this.f5510k >= 3000) {
            sp.b("%d ms: %s", new Object[]{Long.valueOf(SystemClock.elapsedRealtime() - this.f5510k), toString()});
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m7356a((zzk) obj);
    }

    public String m7369d() {
        return m7367c();
    }

    public cc m7370e() {
        return this.f5512m;
    }

    public boolean m7371f() {
        return false;
    }

    public Map<String, String> m7372g() {
        return Collections.emptyMap();
    }

    @Deprecated
    protected Map<String, String> m7373h() {
        return m7377l();
    }

    @Deprecated
    protected String m7374i() {
        return m7378m();
    }

    @Deprecated
    public String m7375j() {
        return m7379n();
    }

    @Deprecated
    public byte[] m7376k() {
        Map h = m7373h();
        return (h == null || h.size() <= 0) ? null : m7353a(h, m7374i());
    }

    protected Map<String, String> m7377l() {
        return null;
    }

    protected String m7378m() {
        return Constants.ENCODING;
    }

    public String m7379n() {
        String str = "application/x-www-form-urlencoded; charset=";
        String valueOf = String.valueOf(m7378m());
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    public byte[] m7380o() {
        Map l = m7377l();
        return (l == null || l.size() <= 0) ? null : m7353a(l, m7378m());
    }

    public final boolean m7381p() {
        return this.f5507h;
    }

    public zza m7382q() {
        return zza.zzT;
    }

    public final int m7383r() {
        return this.f5511l.a();
    }

    public py m7384s() {
        return this.f5511l;
    }

    public void m7385t() {
        this.f5509j = true;
    }

    public String toString() {
        String str = "0x";
        String valueOf = String.valueOf(Integer.toHexString(m7364b()));
        valueOf = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        str = "[ ] ";
        String valueOf2 = String.valueOf(m7367c());
        String valueOf3 = String.valueOf(m7382q());
        String valueOf4 = String.valueOf(this.f5505f);
        return new StringBuilder(((((String.valueOf(str).length() + 3) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf).length()) + String.valueOf(valueOf3).length()) + String.valueOf(valueOf4).length()).append(str).append(valueOf2).append(" ").append(valueOf).append(" ").append(valueOf3).append(" ").append(valueOf4).toString();
    }

    public boolean m7386u() {
        return this.f5509j;
    }
}
