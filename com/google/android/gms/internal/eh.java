package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@jw
public class eh {
    private final Map<String, eg> f4873a;
    @Nullable
    private final ei f4874b;

    public eh(@Nullable ei eiVar) {
        this.f4874b = eiVar;
        this.f4873a = new HashMap();
    }

    @Nullable
    public ei m6470a() {
        return this.f4874b;
    }

    public void m6471a(String str, eg egVar) {
        this.f4873a.put(str, egVar);
    }

    public void m6472a(String str, String str2, long j) {
        ed.m6463a(this.f4874b, (eg) this.f4873a.get(str2), j, str);
        this.f4873a.put(str, ed.m6462a(this.f4874b, j));
    }
}
