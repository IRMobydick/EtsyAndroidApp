package com.etsy.android.lib.core.http.body;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.requests.HttpUtil;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.etsy.android.lib.core.http.body.d */
public abstract class ParamBody<BuilderTarget extends ParamBody, BuilderClass extends ParamBody<BuilderTarget, BuilderClass>> {
    protected final HashMap<String, List<String>> f1545a;
    private byte[] f1546b;

    @NonNull
    protected abstract byte[] m1340a();

    protected abstract BuilderTarget m1343d();

    protected abstract BuilderClass m1344e();

    protected ParamBody() {
        this.f1545a = new LinkedHashMap(0);
    }

    public BuilderClass m1341b(@Nullable String str, @Nullable String str2) {
        HttpUtil.addQueryParamAsList(this.f1545a, str, str2);
        return m1344e();
    }

    public BuilderClass m1339a(@Nullable Map<String, String> map) {
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                m1341b((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return m1344e();
    }

    public BuilderClass m1342b(@Nullable String str, @NonNull List<String> list) {
        HttpUtil.addQueryParamAsList(this.f1545a, str, (List) list);
        return m1344e();
    }

    public final BuilderTarget m1345f() {
        this.f1546b = m1340a();
        return m1343d();
    }
}
