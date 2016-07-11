package com.google.android.gms.internal;

import java.net.URL;
import java.util.ArrayList;

@jw
class fn {
    private final String f4919a;
    private final URL f4920b;
    private final ArrayList<fm> f4921c;
    private final String f4922d;

    public fn(String str, URL url, ArrayList<fm> arrayList, String str2) {
        this.f4919a = str;
        this.f4920b = url;
        if (arrayList == null) {
            this.f4921c = new ArrayList();
        } else {
            this.f4921c = arrayList;
        }
        this.f4922d = str2;
    }

    public String m6528a() {
        return this.f4919a;
    }

    public URL m6529b() {
        return this.f4920b;
    }

    public ArrayList<fm> m6530c() {
        return this.f4921c;
    }

    public String m6531d() {
        return this.f4922d;
    }
}
