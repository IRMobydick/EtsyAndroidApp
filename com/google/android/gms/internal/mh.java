package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

@jw
public class mh {
    private final String[] f5436a;
    private final double[] f5437b;
    private final double[] f5438c;
    private final int[] f5439d;
    private int f5440e;

    private mh(mj mjVar) {
        int size = mj.a(mjVar).size();
        this.f5436a = (String[]) mj.b(mjVar).toArray(new String[size]);
        this.f5437b = m7174a(mj.a(mjVar));
        this.f5438c = m7174a(mj.c(mjVar));
        this.f5439d = new int[size];
        this.f5440e = 0;
    }

    private double[] m7174a(List<Double> list) {
        double[] dArr = new double[list.size()];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = ((Double) list.get(i)).doubleValue();
        }
        return dArr;
    }

    public List<mi> m7175a() {
        List<mi> arrayList = new ArrayList(this.f5436a.length);
        for (int i = 0; i < this.f5436a.length; i++) {
            arrayList.add(new mi(this.f5436a[i], this.f5438c[i], this.f5437b[i], ((double) this.f5439d[i]) / ((double) this.f5440e), this.f5439d[i]));
        }
        return arrayList;
    }

    public void m7176a(double d) {
        this.f5440e++;
        int i = 0;
        while (i < this.f5438c.length) {
            if (this.f5438c[i] <= d && d < this.f5437b[i]) {
                int[] iArr = this.f5439d;
                iArr[i] = iArr[i] + 1;
            }
            if (d >= this.f5438c[i]) {
                i++;
            } else {
                return;
            }
        }
    }
}
