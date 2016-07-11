package com.google.android.gms.internal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.text.TextUtils;
import com.appboy.Constants;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.C1078b;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@jw
public final class fv implements fk {
    private final fq f4936a;
    private final C1078b f4937b;
    private final ig f4938c;

    public fv(fq fqVar, C1078b c1078b, ig igVar) {
        this.f4936a = fqVar;
        this.f4937b = c1078b;
        this.f4938c = igVar;
    }

    private void m6545a(boolean z) {
        if (this.f4938c != null) {
            this.f4938c.m6772a(z);
        }
    }

    private static boolean m6546a(Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }

    private static int m6547b(Map<String, String> map) {
        String str = (String) map.get("o");
        if (str != null) {
            if (Constants.APPBOY_PUSH_PRIORITY_KEY.equalsIgnoreCase(str)) {
                return C1101o.m6043g().m7160b();
            }
            if ("l".equalsIgnoreCase(str)) {
                return C1101o.m6043g().m7146a();
            }
            if ("c".equalsIgnoreCase(str)) {
                return C1101o.m6043g().m7164c();
            }
        }
        return -1;
    }

    private static void m6548b(no noVar, Map<String, String> map) {
        Context context = noVar.getContext();
        if (TextUtils.isEmpty((String) map.get("u"))) {
            C1129c.m6192d("Destination url cannot be empty.");
            return;
        }
        try {
            noVar.m7262l().zza(new AdLauncherIntentInfoParcel(new fw(noVar).a(context, map)));
        } catch (ActivityNotFoundException e) {
            C1129c.m6192d(e.getMessage());
        }
    }

    public void m6549a(no noVar, Map<String, String> map) {
        String str = (String) map.get(Constants.APPBOY_PUSH_CONTENT_KEY);
        if (str == null) {
            C1129c.m6192d("Action missing from an open GMSG.");
        } else if (this.f4937b == null || this.f4937b.m5856b()) {
            zzlb l = noVar.m7262l();
            if ("expand".equalsIgnoreCase(str)) {
                if (noVar.m7266p()) {
                    C1129c.m6192d("Cannot expand WebView that is already expanded.");
                    return;
                }
                m6545a(false);
                l.zza(m6546a((Map) map), m6547b(map));
            } else if ("webapp".equalsIgnoreCase(str)) {
                str = (String) map.get("u");
                m6545a(false);
                if (str != null) {
                    l.zza(m6546a((Map) map), m6547b(map), str);
                } else {
                    l.zza(m6546a((Map) map), m6547b(map), (String) map.get("html"), (String) map.get("baseurl"));
                }
            } else if ("in_app_purchase".equalsIgnoreCase(str)) {
                str = (String) map.get(ResponseConstants.PRODUCT_ID);
                String str2 = (String) map.get("report_urls");
                if (this.f4936a == null) {
                    return;
                }
                if (str2 == null || str2.isEmpty()) {
                    this.f4936a.zza(str, new ArrayList());
                } else {
                    this.f4936a.zza(str, new ArrayList(Arrays.asList(str2.split(" "))));
                }
            } else if ("app".equalsIgnoreCase(str) && "true".equalsIgnoreCase((String) map.get("system_browser"))) {
                m6545a(true);
                m6548b(noVar, map);
            } else {
                m6545a(true);
                str = (String) map.get("u");
                l.zza(new AdLauncherIntentInfoParcel((String) map.get("i"), !TextUtils.isEmpty(str) ? C1101o.m6041e().m7093a(noVar, str) : str, (String) map.get("m"), (String) map.get(Constants.APPBOY_PUSH_PRIORITY_KEY), (String) map.get("c"), (String) map.get("f"), (String) map.get("e")));
            }
        } else {
            this.f4937b.m5855a((String) map.get("u"));
        }
    }
}
