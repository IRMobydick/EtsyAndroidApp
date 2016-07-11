package com.etsy.android.lib.core.http.request;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.body.BaseHttpBody;
import com.etsy.android.lib.core.http.url.BaseHttpUrl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.etsy.android.lib.core.http.request.a */
public abstract class BaseHttpRequest<ResultType extends HttpResult, UrlBuilderTarget extends BaseHttpUrl, UrlBuilderClass extends BaseHttpUrl<UrlBuilderTarget, UrlBuilderClass>, BuilderTarget extends BaseHttpRequest<BuilderTarget, ResultType, UrlBuilderTarget>, BuilderClass extends BaseHttpRequest<ResultType, UrlBuilderTarget, UrlBuilderClass, BuilderTarget, BuilderClass>> {
    protected int f1562a;
    @NonNull
    protected final UrlBuilderClass f1563b;
    @Nullable
    protected BaseHttpBody f1564c;
    @NonNull
    private final HashMap<String, String> f1565d;
    private boolean f1566e;

    protected abstract BuilderClass m1390b();

    protected abstract BuilderTarget m1392c();

    public BaseHttpRequest(@NonNull UrlBuilderClass urlBuilderClass) {
        this.f1565d = new HashMap(5);
        this.f1566e = false;
        this.f1563b = urlBuilderClass;
        this.f1562a = 0;
    }

    public BuilderClass m1382a(int i) {
        this.f1562a = i;
        return m1390b();
    }

    public BuilderClass m1384a(String str) {
        int i = 0;
        if (!BaseHttpRequest.GET.equalsIgnoreCase(str)) {
            if (BaseHttpRequest.POST.equalsIgnoreCase(str)) {
                i = 1;
            } else if (BaseHttpRequest.DELETE.equalsIgnoreCase(str)) {
                i = 3;
            } else if (BaseHttpRequest.PUT.equalsIgnoreCase(str)) {
                i = 2;
            } else if (BaseHttpRequest.HEAD.equalsIgnoreCase(str)) {
                i = 4;
            } else if (BaseHttpRequest.OPTIONS.equalsIgnoreCase(str)) {
                i = 5;
            } else if (BaseHttpRequest.TRACE.equalsIgnoreCase(str)) {
                i = 6;
            } else if (BaseHttpRequest.PATCH.equalsIgnoreCase(str)) {
                i = 7;
            }
        }
        return m1382a(i);
    }

    public BuilderClass m1383a(@Nullable BaseHttpBody baseHttpBody) {
        this.f1564c = baseHttpBody;
        return m1390b();
    }

    public BuilderClass m1385a(@Nullable String str, @Nullable String str2) {
        this.f1563b.m1527a(str, str2);
        return m1390b();
    }

    public BuilderClass m1387a(@Nullable Map<String, String> map) {
        this.f1563b.m1529a((Map) map);
        return m1390b();
    }

    public BuilderClass m1386a(@Nullable String str, @NonNull List<String> list) {
        this.f1563b.m1528a(str, (List) list);
        return m1390b();
    }

    public BuilderClass m1391b(@Nullable String str, @Nullable String str2) {
        this.f1565d.put(str, str2);
        return m1390b();
    }

    public BuilderClass m1388a(boolean z) {
        this.f1566e = z;
        return m1390b();
    }

    @NonNull
    protected final UrlBuilderTarget m1389a() {
        return this.f1563b.m1531c();
    }

    @CallSuper
    public BuilderTarget m1393d() {
        return m1392c();
    }
}
