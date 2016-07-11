package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.C1101o;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@jw
public class ei {
    boolean f4875a;
    private final List<eg> f4876b;
    private final Map<String, String> f4877c;
    private final Object f4878d;
    private String f4879e;
    private eg f4880f;
    private ei f4881g;

    public ei(boolean z, String str, String str2) {
        this.f4876b = new LinkedList();
        this.f4877c = new LinkedHashMap();
        this.f4878d = new Object();
        this.f4875a = z;
        this.f4877c.put("action", str);
        this.f4877c.put("ad_format", str2);
    }

    public eg m6473a() {
        return m6474a(C1101o.m6045i().elapsedRealtime());
    }

    @Nullable
    public eg m6474a(long j) {
        return !this.f4875a ? null : new eg(j, null, null);
    }

    public void m6475a(ei eiVar) {
        synchronized (this.f4878d) {
            this.f4881g = eiVar;
        }
    }

    public void m6476a(String str) {
        if (this.f4875a) {
            synchronized (this.f4878d) {
                this.f4879e = str;
            }
        }
    }

    public void m6477a(String str, String str2) {
        if (this.f4875a && !TextUtils.isEmpty(str2)) {
            eb e = C1101o.m6044h().m7030e();
            if (e != null) {
                synchronized (this.f4878d) {
                    e.m6455a(str).m6466a(this.f4877c, str, str2);
                }
            }
        }
    }

    public boolean m6478a(eg egVar, long j, String... strArr) {
        synchronized (this.f4878d) {
            for (String egVar2 : strArr) {
                this.f4876b.add(new eg(j, egVar2, egVar));
            }
        }
        return true;
    }

    public boolean m6479a(eg egVar, String... strArr) {
        return (!this.f4875a || egVar == null) ? false : m6478a(egVar, C1101o.m6045i().elapsedRealtime(), strArr);
    }

    public void m6480b() {
        synchronized (this.f4878d) {
            this.f4880f = m6473a();
        }
    }

    public String m6481c() {
        String stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        synchronized (this.f4878d) {
            for (eg egVar : this.f4876b) {
                long a = egVar.m6467a();
                String b = egVar.m6468b();
                eg egVar2 = egVar2.m6469c();
                if (egVar2 != null && a > 0) {
                    stringBuilder2.append(b).append('.').append(a - egVar2.m6467a()).append(',');
                }
            }
            this.f4876b.clear();
            if (!TextUtils.isEmpty(this.f4879e)) {
                stringBuilder2.append(this.f4879e);
            } else if (stringBuilder2.length() > 0) {
                stringBuilder2.setLength(stringBuilder2.length() - 1);
            }
            stringBuilder = stringBuilder2.toString();
        }
        return stringBuilder;
    }

    Map<String, String> m6482d() {
        Map<String, String> map;
        synchronized (this.f4878d) {
            eb e = C1101o.m6044h().m7030e();
            if (e == null || this.f4881g == null) {
                map = this.f4877c;
            } else {
                map = e.m6457a(this.f4877c, this.f4881g.m6482d());
            }
        }
        return map;
    }

    public eg m6483e() {
        eg egVar;
        synchronized (this.f4878d) {
            egVar = this.f4880f;
        }
        return egVar;
    }
}
