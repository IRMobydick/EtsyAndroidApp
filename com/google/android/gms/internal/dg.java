package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;

@jw
public class dg {
    private final int f4752a;
    private final int f4753b;
    private final int f4754c;
    private final dl f4755d;
    private final Object f4756e;
    private ArrayList<String> f4757f;
    private ArrayList<String> f4758g;
    private int f4759h;
    private int f4760i;
    private int f4761j;
    private int f4762k;
    private String f4763l;
    private String f4764m;

    public dg(int i, int i2, int i3, int i4) {
        this.f4756e = new Object();
        this.f4757f = new ArrayList();
        this.f4758g = new ArrayList();
        this.f4759h = 0;
        this.f4760i = 0;
        this.f4761j = 0;
        this.f4763l = StringUtils.EMPTY;
        this.f4764m = StringUtils.EMPTY;
        this.f4752a = i;
        this.f4753b = i2;
        this.f4754c = i3;
        this.f4755d = new dl(i4);
    }

    private String m6361a(ArrayList<String> arrayList, int i) {
        if (arrayList.isEmpty()) {
            return StringUtils.EMPTY;
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append((String) it.next());
            stringBuffer.append(' ');
            if (stringBuffer.length() > i) {
                break;
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        String stringBuffer2 = stringBuffer.toString();
        return stringBuffer2.length() >= i ? stringBuffer2.substring(0, i) : stringBuffer2;
    }

    private void m6362c(String str, boolean z) {
        if (str != null && str.length() >= this.f4754c) {
            synchronized (this.f4756e) {
                this.f4757f.add(str);
                this.f4759h += str.length();
                if (z) {
                    this.f4758g.add(str);
                }
            }
        }
    }

    int m6363a(int i, int i2) {
        return (this.f4752a * i) + (this.f4753b * i2);
    }

    public void m6364a(int i) {
        this.f4760i = i;
    }

    public void m6365a(String str, boolean z) {
        m6362c(str, z);
        synchronized (this.f4756e) {
            if (this.f4761j < 0) {
                C1129c.m6185a("ActivityContent: negative number of WebViews.");
            }
            m6373g();
        }
    }

    public boolean m6366a() {
        boolean z;
        synchronized (this.f4756e) {
            z = this.f4761j == 0;
        }
        return z;
    }

    public String m6367b() {
        return this.f4763l;
    }

    public void m6368b(String str, boolean z) {
        m6362c(str, z);
    }

    public String m6369c() {
        return this.f4764m;
    }

    public void m6370d() {
        synchronized (this.f4756e) {
            this.f4762k -= 100;
        }
    }

    public void m6371e() {
        synchronized (this.f4756e) {
            this.f4761j--;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof dg)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        dg dgVar = (dg) obj;
        return dgVar.m6367b() != null && dgVar.m6367b().equals(m6367b());
    }

    public void m6372f() {
        synchronized (this.f4756e) {
            this.f4761j++;
        }
    }

    public void m6373g() {
        synchronized (this.f4756e) {
            int a = m6363a(this.f4759h, this.f4760i);
            if (a > this.f4762k) {
                this.f4762k = a;
                this.f4763l = this.f4755d.m6398a(this.f4757f);
                this.f4764m = this.f4755d.m6398a(this.f4758g);
            }
        }
    }

    public int m6374h() {
        return this.f4762k;
    }

    public int hashCode() {
        return m6367b().hashCode();
    }

    int m6375i() {
        return this.f4759h;
    }

    public String toString() {
        int i = this.f4760i;
        int i2 = this.f4762k;
        int i3 = this.f4759h;
        String valueOf = String.valueOf(m6361a(this.f4757f, 100));
        String valueOf2 = String.valueOf(m6361a(this.f4758g, 100));
        String str = this.f4763l;
        String str2 = this.f4764m;
        return new StringBuilder((((String.valueOf(valueOf).length() + 133) + String.valueOf(valueOf2).length()) + String.valueOf(str).length()) + String.valueOf(str2).length()).append("ActivityContent fetchId: ").append(i).append(" score:").append(i2).append(" total_length:").append(i3).append("\n text: ").append(valueOf).append("\n viewableText").append(valueOf2).append("\n signture: ").append(str).append("\n viewableSignture: ").append(str2).toString();
    }
}
