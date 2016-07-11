package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

@jw
class fp {
    private final String f4927a;
    private final int f4928b;
    private final List<fm> f4929c;
    private final String f4930d;

    public fp(String str, int i, List<fm> list, String str2) {
        this.f4927a = str;
        this.f4928b = i;
        if (list == null) {
            this.f4929c = new ArrayList();
        } else {
            this.f4929c = list;
        }
        this.f4930d = str2;
    }

    public String m6535a() {
        return this.f4927a;
    }

    public int m6536b() {
        return this.f4928b;
    }

    public Iterable<fm> m6537c() {
        return this.f4929c;
    }

    public String m6538d() {
        return this.f4930d;
    }
}
