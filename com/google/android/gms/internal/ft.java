package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONObject;

@jw
public class ft implements fk {
    final HashMap<String, mv<JSONObject>> f4932a;

    public ft() {
        this.f4932a = new HashMap();
    }

    public Future<JSONObject> m6540a(String str) {
        Object mvVar = new mv();
        this.f4932a.put(str, mvVar);
        return mvVar;
    }

    public void m6541a(no noVar, Map<String, String> map) {
        m6542a((String) map.get("request_id"), (String) map.get("fetched_ad"));
    }

    public void m6542a(String str, String str2) {
        C1129c.m6185a("Received ad from the cache.");
        mv mvVar = (mv) this.f4932a.get(str);
        if (mvVar == null) {
            C1129c.m6188b("Could not find the ad request for the corresponding ad response.");
            return;
        }
        try {
            mvVar.m7200b(new JSONObject(str2));
        } catch (Throwable e) {
            C1129c.m6189b("Failed constructing JSON object from value passed from javascript", e);
            mvVar.m7200b(null);
        } finally {
            this.f4932a.remove(str);
        }
    }

    public void m6543b(String str) {
        mv mvVar = (mv) this.f4932a.get(str);
        if (mvVar == null) {
            C1129c.m6188b("Could not find the ad request for the corresponding ad response.");
            return;
        }
        if (!mvVar.isDone()) {
            mvVar.cancel(true);
        }
        this.f4932a.remove(str);
    }
}
