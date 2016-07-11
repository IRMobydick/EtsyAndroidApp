package com.google.android.gms.internal;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

@jw
public final class ka {
    private boolean f5229A;
    private String f5230B;
    private List<String> f5231C;
    private String f5232D;
    private boolean f5233E;
    private final AdRequestInfoParcel f5234F;
    private String f5235a;
    private String f5236b;
    private String f5237c;
    private List<String> f5238d;
    private String f5239e;
    private String f5240f;
    private List<String> f5241g;
    private long f5242h;
    private boolean f5243i;
    private final long f5244j;
    private List<String> f5245k;
    private long f5246l;
    private int f5247m;
    private boolean f5248n;
    private boolean f5249o;
    private boolean f5250p;
    private boolean f5251q;
    private int f5252r;
    private String f5253s;
    private boolean f5254t;
    private boolean f5255u;
    private RewardItemParcel f5256v;
    private List<String> f5257w;
    private List<String> f5258x;
    private boolean f5259y;
    private AutoClickProtectionConfigurationParcel f5260z;

    public ka(AdRequestInfoParcel adRequestInfoParcel) {
        this.f5242h = -1;
        this.f5243i = false;
        this.f5244j = -1;
        this.f5246l = -1;
        this.f5247m = -1;
        this.f5248n = false;
        this.f5249o = false;
        this.f5250p = false;
        this.f5251q = true;
        this.f5252r = 0;
        this.f5253s = StringUtils.EMPTY;
        this.f5254t = false;
        this.f5255u = false;
        this.f5259y = false;
        this.f5229A = false;
        this.f5234F = adRequestInfoParcel;
    }

    private void m6911A(Map<String, List<String>> map) {
        Object a = m6914a(map, "X-Afma-Auto-Protection-Configuration");
        if (a == null || TextUtils.isEmpty(a)) {
            Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204").buildUpon();
            buildUpon.appendQueryParameter(ResponseConstants.ID, "gmob-apps-blocked-navigation");
            if (!TextUtils.isEmpty(this.f5239e)) {
                buildUpon.appendQueryParameter("debugDialog", this.f5239e);
            }
            boolean booleanValue = ((Boolean) dz.f4836j.m6433c()).booleanValue();
            String[] strArr = new String[1];
            String valueOf = String.valueOf(buildUpon.toString());
            String valueOf2 = String.valueOf("navigationURL");
            strArr[0] = new StringBuilder((String.valueOf(valueOf).length() + 18) + String.valueOf(valueOf2).length()).append(valueOf).append("&").append(valueOf2).append("={NAVIGATION_URL}").toString();
            this.f5260z = new AutoClickProtectionConfigurationParcel(booleanValue, Arrays.asList(strArr));
            return;
        }
        try {
            this.f5260z = AutoClickProtectionConfigurationParcel.zzg(new JSONObject(a));
        } catch (Throwable e) {
            C1129c.m6193d("Error parsing configuration JSON", e);
            this.f5260z = new AutoClickProtectionConfigurationParcel();
        }
    }

    private void m6912B(Map<String, List<String>> map) {
        this.f5230B = m6914a(map, "Set-Cookie");
    }

    private void m6913C(Map<String, List<String>> map) {
        this.f5232D = m6914a(map, "X-Afma-Safe-Browsing");
    }

    static String m6914a(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        return (list == null || list.isEmpty()) ? null : (String) list.get(0);
    }

    static long m6915b(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (!(list == null || list.isEmpty())) {
            String str2 = (String) list.get(0);
            try {
                return (long) (Float.parseFloat(str2) * 1000.0f);
            } catch (NumberFormatException e) {
                C1129c.m6192d(new StringBuilder((String.valueOf(str).length() + 36) + String.valueOf(str2).length()).append("Could not parse float from ").append(str).append(" header: ").append(str2).toString());
            }
        }
        return -1;
    }

    private void m6916b(Map<String, List<String>> map) {
        this.f5235a = m6914a(map, "X-Afma-Ad-Size");
    }

    static List<String> m6917c(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (!(list == null || list.isEmpty())) {
            String str2 = (String) list.get(0);
            if (str2 != null) {
                return Arrays.asList(str2.trim().split("\\s+"));
            }
        }
        return null;
    }

    private void m6918c(Map<String, List<String>> map) {
        List c = m6917c(map, "X-Afma-Click-Tracking-Urls");
        if (c != null) {
            this.f5238d = c;
        }
    }

    private void m6919d(Map<String, List<String>> map) {
        List list = (List) map.get("X-Afma-Debug-Dialog");
        if (list != null && !list.isEmpty()) {
            this.f5239e = (String) list.get(0);
        }
    }

    private boolean m6920d(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        return (list == null || list.isEmpty() || !Boolean.valueOf((String) list.get(0)).booleanValue()) ? false : true;
    }

    private void m6921e(Map<String, List<String>> map) {
        List c = m6917c(map, "X-Afma-Tracking-Urls");
        if (c != null) {
            this.f5241g = c;
        }
    }

    private void m6922f(Map<String, List<String>> map) {
        long b = m6915b(map, "X-Afma-Interstitial-Timeout");
        if (b != -1) {
            this.f5242h = b;
        }
    }

    private void m6923g(Map<String, List<String>> map) {
        this.f5240f = m6914a(map, "X-Afma-ActiveView");
    }

    private void m6924h(Map<String, List<String>> map) {
        this.f5249o = "native".equals(m6914a(map, "X-Afma-Ad-Format"));
    }

    private void m6925i(Map<String, List<String>> map) {
        this.f5248n |= m6920d(map, "X-Afma-Custom-Rendering-Allowed");
    }

    private void m6926j(Map<String, List<String>> map) {
        this.f5243i |= m6920d(map, "X-Afma-Mediation");
    }

    private void m6927k(Map<String, List<String>> map) {
        this.f5233E |= m6920d(map, "X-Afma-Render-In-Browser");
    }

    private void m6928l(Map<String, List<String>> map) {
        List c = m6917c(map, "X-Afma-Manual-Tracking-Urls");
        if (c != null) {
            this.f5245k = c;
        }
    }

    private void m6929m(Map<String, List<String>> map) {
        long b = m6915b(map, "X-Afma-Refresh-Rate");
        if (b != -1) {
            this.f5246l = b;
        }
    }

    private void m6930n(Map<String, List<String>> map) {
        List list = (List) map.get("X-Afma-Orientation");
        if (list != null && !list.isEmpty()) {
            String str = (String) list.get(0);
            if ("portrait".equalsIgnoreCase(str)) {
                this.f5247m = C1101o.m6043g().m7160b();
            } else if ("landscape".equalsIgnoreCase(str)) {
                this.f5247m = C1101o.m6043g().m7146a();
            }
        }
    }

    private void m6931o(Map<String, List<String>> map) {
        List list = (List) map.get("X-Afma-Use-HTTPS");
        if (list != null && !list.isEmpty()) {
            this.f5250p = Boolean.valueOf((String) list.get(0)).booleanValue();
        }
    }

    private void m6932p(Map<String, List<String>> map) {
        List list = (List) map.get("X-Afma-Content-Url-Opted-Out");
        if (list != null && !list.isEmpty()) {
            this.f5251q = Boolean.valueOf((String) list.get(0)).booleanValue();
        }
    }

    private void m6933q(Map<String, List<String>> map) {
        List<String> c = m6917c(map, "X-Afma-OAuth-Token-Status");
        this.f5252r = 0;
        if (c != null) {
            for (String str : c) {
                if ("Clear".equalsIgnoreCase(str)) {
                    this.f5252r = 1;
                    return;
                } else if ("No-Op".equalsIgnoreCase(str)) {
                    this.f5252r = 0;
                    return;
                }
            }
        }
    }

    private void m6934r(Map<String, List<String>> map) {
        List list = (List) map.get("X-Afma-Gws-Query-Id");
        if (list != null && !list.isEmpty()) {
            this.f5253s = (String) list.get(0);
        }
    }

    private void m6935s(Map<String, List<String>> map) {
        String a = m6914a(map, "X-Afma-Fluid");
        if (a != null && a.equals(ResponseConstants.HEIGHT)) {
            this.f5254t = true;
        }
    }

    private void m6936t(Map<String, List<String>> map) {
        this.f5255u = "native_express".equals(m6914a(map, "X-Afma-Ad-Format"));
    }

    private void m6937u(Map<String, List<String>> map) {
        this.f5256v = RewardItemParcel.zzaG(m6914a(map, "X-Afma-Rewards"));
    }

    private void m6938v(Map<String, List<String>> map) {
        if (this.f5257w == null) {
            this.f5257w = m6917c(map, "X-Afma-Reward-Video-Start-Urls");
        }
    }

    private void m6939w(Map<String, List<String>> map) {
        if (this.f5258x == null) {
            this.f5258x = m6917c(map, "X-Afma-Reward-Video-Complete-Urls");
        }
    }

    private void m6940x(Map<String, List<String>> map) {
        this.f5259y |= m6920d(map, "X-Afma-Use-Displayed-Impression");
    }

    private void m6941y(Map<String, List<String>> map) {
        this.f5229A |= m6920d(map, "X-Afma-Auto-Collect-Location");
    }

    private void m6942z(Map<String, List<String>> map) {
        List c = m6917c(map, "X-Afma-Remote-Ping-Urls");
        if (c != null) {
            this.f5231C = c;
        }
    }

    public AdResponseParcel m6943a(long j) {
        return new AdResponseParcel(this.f5234F, this.f5236b, this.f5237c, this.f5238d, this.f5241g, this.f5242h, this.f5243i, -1, this.f5245k, this.f5246l, this.f5247m, this.f5235a, j, this.f5239e, this.f5240f, this.f5248n, this.f5249o, this.f5250p, this.f5251q, false, this.f5252r, this.f5253s, this.f5254t, this.f5255u, this.f5256v, this.f5257w, this.f5258x, this.f5259y, this.f5260z, this.f5229A, this.f5230B, this.f5231C, this.f5232D, this.f5233E);
    }

    public void m6944a(String str, Map<String, List<String>> map, String str2) {
        this.f5236b = str;
        this.f5237c = str2;
        m6945a((Map) map);
    }

    public void m6945a(Map<String, List<String>> map) {
        m6916b(map);
        m6918c(map);
        m6919d(map);
        m6921e(map);
        m6922f(map);
        m6926j(map);
        m6928l(map);
        m6929m(map);
        m6930n(map);
        m6923g(map);
        m6931o(map);
        m6925i(map);
        m6924h(map);
        m6932p(map);
        m6933q(map);
        m6934r(map);
        m6935s(map);
        m6936t(map);
        m6937u(map);
        m6938v(map);
        m6939w(map);
        m6940x(map);
        m6941y(map);
        m6912B(map);
        m6911A(map);
        m6942z(map);
        m6913C(map);
        m6927k(map);
    }
}
