package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.C1129c;
import org.json.JSONObject;

@jw
public class ij {
    private final boolean f5125a;
    private final boolean f5126b;
    private final boolean f5127c;
    private final boolean f5128d;
    private final boolean f5129e;

    private ij(ik ikVar) {
        this.f5125a = ik.a(ikVar);
        this.f5126b = ik.b(ikVar);
        this.f5127c = ik.c(ikVar);
        this.f5128d = ik.d(ikVar);
        this.f5129e = ik.e(ikVar);
    }

    public JSONObject m6783a() {
        try {
            return new JSONObject().put("sms", this.f5125a).put("tel", this.f5126b).put("calendar", this.f5127c).put("storePicture", this.f5128d).put("inlineVideo", this.f5129e);
        } catch (Throwable e) {
            C1129c.m6189b("Error occured while obtaining the MRAID capabilities.", e);
            return null;
        }
    }
}
