package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;
import org.apache.commons.lang3.StringUtils;

@jw
public class dl {
    private final int f4785a;
    private final int f4786b;
    private final int f4787c;
    private final dk f4788d;

    public dl(int i) {
        this.f4788d = new C1158do();
        this.f4786b = i;
        this.f4785a = 6;
        this.f4787c = 0;
    }

    dm m6396a() {
        return new dm();
    }

    String m6397a(String str) {
        String[] split = str.split("\n");
        if (split.length == 0) {
            return StringUtils.EMPTY;
        }
        dm a = m6396a();
        PriorityQueue priorityQueue = new PriorityQueue(this.f4786b, new 1(this));
        for (String b : split) {
            String[] b2 = dn.m6403b(b);
            if (b2.length != 0) {
                dp.m6413a(b2, this.f4786b, this.f4785a, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                a.a(this.f4788d.m6395a(((dq) it.next()).b));
            } catch (Throwable e) {
                C1129c.m6189b("Error while writing hash to byteStream", e);
            }
        }
        return a.toString();
    }

    public String m6398a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(((String) it.next()).toLowerCase(Locale.US));
            stringBuffer.append('\n');
        }
        return m6397a(stringBuffer.toString());
    }
}
