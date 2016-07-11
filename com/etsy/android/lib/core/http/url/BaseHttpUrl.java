package com.etsy.android.lib.core.http.url;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.requests.HttpUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.core.http.url.a */
public abstract class BaseHttpUrl<BuilderTarget extends BaseHttpUrl, BuilderClass extends BaseHttpUrl<BuilderTarget, BuilderClass>> {
    final String f1595a;
    final String f1596b;
    private final HashMap<String, List<String>> f1597c;
    private String f1598d;
    private String f1599e;

    protected abstract BuilderClass m1526a();

    protected abstract BuilderTarget m1530b();

    public BaseHttpUrl(@NonNull String str, @NonNull String str2) {
        this.f1595a = m1521a(str);
        this.f1596b = HttpUtil.getPathPart(str2);
        this.f1597c = HttpUtil.parseQueryParamsAsList(str2);
    }

    public BuilderClass m1527a(@Nullable String str, @Nullable String str2) {
        HttpUtil.addQueryParamAsList(this.f1597c, str, str2);
        return m1526a();
    }

    public BuilderClass m1529a(@Nullable Map<String, String> map) {
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                m1527a((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return m1526a();
    }

    public BuilderClass m1528a(@Nullable String str, @NonNull List<String> list) {
        HttpUtil.addQueryParamAsList(this.f1597c, str, (List) list);
        return m1526a();
    }

    private String m1525d() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f1595a);
        stringBuilder.append(this.f1596b);
        String createUrlEncodingParamsFromList = HttpUtil.createUrlEncodingParamsFromList(this.f1597c);
        this.f1598d = StringUtils.EMPTY;
        if (!createUrlEncodingParamsFromList.isEmpty()) {
            if (this.f1596b.indexOf(63) == -1) {
                this.f1598d = "?";
            } else if (this.f1596b.charAt(this.f1596b.length() - 1) != '&') {
                this.f1598d = "&";
            }
            this.f1598d += createUrlEncodingParamsFromList;
            stringBuilder.append(this.f1598d);
        }
        return stringBuilder.toString();
    }

    private String m1521a(@NonNull String str) {
        if (str.length() <= 0 || str.charAt(str.length() - 1) != '/') {
            return str;
        }
        return str.substring(0, str.length() - 1);
    }

    public final BuilderTarget m1531c() {
        this.f1599e = m1525d();
        return m1530b();
    }
}
