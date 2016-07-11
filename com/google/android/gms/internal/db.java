package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import org.json.JSONObject;

@jw
public final class db {
    private final String f4740a;
    private final JSONObject f4741b;
    private final String f4742c;
    private final String f4743d;
    private final boolean f4744e;
    private final boolean f4745f;

    public db(String str, VersionInfoParcel versionInfoParcel, String str2, JSONObject jSONObject, boolean z, boolean z2) {
        this.f4743d = versionInfoParcel.afmaVersion;
        this.f4741b = jSONObject;
        this.f4742c = str;
        this.f4740a = str2;
        this.f4744e = z;
        this.f4745f = z2;
    }

    public String m6344a() {
        return this.f4740a;
    }

    public String m6345b() {
        return this.f4743d;
    }

    public JSONObject m6346c() {
        return this.f4741b;
    }

    public String m6347d() {
        return this.f4742c;
    }

    public boolean m6348e() {
        return this.f4744e;
    }

    public boolean m6349f() {
        return this.f4745f;
    }
}
