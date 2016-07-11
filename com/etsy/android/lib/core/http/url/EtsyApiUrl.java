package com.etsy.android.lib.core.http.url;

import android.support.annotation.NonNull;

/* renamed from: com.etsy.android.lib.core.http.url.b */
public abstract class EtsyApiUrl<BuilderTarget extends EtsyApiUrl, BuilderClass extends EtsyApiUrl<BuilderTarget, BuilderClass>> extends BaseHttpUrl<BuilderTarget, BuilderClass> {
    protected EtsyApiUrl(@NonNull String str, @NonNull String str2) {
        super(str, str2);
    }

    public BuilderClass m1532a(int i) {
        m1527a("offset", String.valueOf(i));
        return (EtsyApiUrl) m1526a();
    }

    public BuilderClass m1533b(int i) {
        m1527a("limit", String.valueOf(i));
        return (EtsyApiUrl) m1526a();
    }
}
