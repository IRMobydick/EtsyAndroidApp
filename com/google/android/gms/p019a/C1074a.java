package com.google.android.gms.p019a;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzz;
import java.util.List;

/* renamed from: com.google.android.gms.a.a */
public final class C1074a {
    private final Uri f4373a;

    private C1074a(Uri uri) {
        this.f4373a = uri;
    }

    public static C1074a m5846a(Uri uri) {
        C1074a c1074a = new C1074a(uri);
        if (C1074a.m5848a(c1074a)) {
            return c1074a;
        }
        throw new IllegalArgumentException("AndroidAppUri validation failed.");
    }

    public static C1074a m5847a(String str, Uri uri) {
        Builder authority = new Builder().scheme("android-app").authority(str);
        if (uri != null) {
            authority.appendPath(uri.getScheme());
            if (uri.getAuthority() != null) {
                authority.appendPath(uri.getAuthority());
            }
            for (String appendPath : uri.getPathSegments()) {
                authority.appendPath(appendPath);
            }
            authority.encodedQuery(uri.getEncodedQuery()).encodedFragment(uri.getEncodedFragment());
        }
        return new C1074a(authority.build());
    }

    private static boolean m5848a(C1074a c1074a) {
        if (!"android-app".equals(c1074a.f4373a.getScheme())) {
            throw new IllegalArgumentException("android-app scheme is required.");
        } else if (TextUtils.isEmpty(c1074a.m5850b())) {
            throw new IllegalArgumentException("Package name is empty.");
        } else {
            if (c1074a.f4373a.equals(C1074a.m5847a(c1074a.m5850b(), c1074a.m5851c()).m5849a())) {
                return true;
            }
            throw new IllegalArgumentException("URI is not canonical.");
        }
    }

    public Uri m5849a() {
        return this.f4373a;
    }

    public String m5850b() {
        return this.f4373a.getAuthority();
    }

    public Uri m5851c() {
        List pathSegments = this.f4373a.getPathSegments();
        if (pathSegments.size() <= 0) {
            return null;
        }
        String str = (String) pathSegments.get(0);
        Builder builder = new Builder();
        builder.scheme(str);
        if (pathSegments.size() > 1) {
            builder.authority((String) pathSegments.get(1));
            for (int i = 2; i < pathSegments.size(); i++) {
                builder.appendPath((String) pathSegments.get(i));
            }
        }
        builder.encodedQuery(this.f4373a.getEncodedQuery());
        builder.encodedFragment(this.f4373a.getEncodedFragment());
        return builder.build();
    }

    public boolean equals(Object obj) {
        return obj instanceof C1074a ? this.f4373a.equals(((C1074a) obj).f4373a) : false;
    }

    public int hashCode() {
        return zzz.hashCode(new Object[]{this.f4373a});
    }

    public String toString() {
        return this.f4373a.toString();
    }
}
