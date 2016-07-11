package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.internal.zzb;
import java.util.Map;
import java.util.concurrent.Future;
import org.apache.commons.lang3.StringUtils;

@jw
public class ge implements fk {
    public void m6574a(no noVar, Map<String, String> map) {
        gc x = C1101o.m6060x();
        if (!map.containsKey("abort")) {
            String str = (String) map.get("src");
            if (str == null) {
                C1129c.m6192d("Precache video action is missing the src parameter.");
                return;
            }
            int parseInt;
            try {
                parseInt = Integer.parseInt((String) map.get("player"));
            } catch (NumberFormatException e) {
                parseInt = 0;
            }
            String str2 = map.containsKey("mimetype") ? (String) map.get("mimetype") : StringUtils.EMPTY;
            if (x.m6563b(noVar)) {
                C1129c.m6192d("Precache task already running.");
                return;
            }
            zzb.zzv(noVar.m7258h());
            Future future = (Future) new gb(noVar, noVar.m7258h().f4378a.a(noVar, parseInt, str2), str).zzhs();
        } else if (!x.m6561a(noVar)) {
            C1129c.m6192d("Precache abort but no preload task running.");
        }
    }
}
