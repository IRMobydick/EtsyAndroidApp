package com.google.android.gms.internal;

import android.text.TextUtils;
import com.appboy.Constants;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.Map;

@jw
public final class fi implements fk {
    private long m6516a(long j) {
        return (j - C1101o.m6045i().currentTimeMillis()) + C1101o.m6045i().elapsedRealtime();
    }

    private void m6517b(no noVar, Map<String, String> map) {
        String str = (String) map.get(ResponseConstants.LABEL);
        String str2 = (String) map.get("start_label");
        String str3 = (String) map.get("timestamp");
        if (TextUtils.isEmpty(str)) {
            C1129c.m6192d("No label given for CSI tick.");
        } else if (TextUtils.isEmpty(str3)) {
            C1129c.m6192d("No timestamp given for CSI tick.");
        } else {
            try {
                long a = m6516a(Long.parseLong(str3));
                if (TextUtils.isEmpty(str2)) {
                    str2 = "native:view_load";
                }
                noVar.m7275y().m6472a(str, str2, a);
            } catch (Throwable e) {
                C1129c.m6193d("Malformed timestamp for CSI tick.", e);
            }
        }
    }

    private void m6518c(no noVar, Map<String, String> map) {
        String str = (String) map.get(ResponseConstants.VALUE);
        if (TextUtils.isEmpty(str)) {
            C1129c.m6192d("No value given for CSI experiment.");
            return;
        }
        ei a = noVar.m7275y().m6470a();
        if (a == null) {
            C1129c.m6192d("No ticker for WebView, dropping experiment ID.");
        } else {
            a.m6477a("e", str);
        }
    }

    private void m6519d(no noVar, Map<String, String> map) {
        String str = (String) map.get(ResponseConstants.NAME);
        String str2 = (String) map.get(ResponseConstants.VALUE);
        if (TextUtils.isEmpty(str2)) {
            C1129c.m6192d("No value given for CSI extra.");
        } else if (TextUtils.isEmpty(str)) {
            C1129c.m6192d("No name given for CSI extra.");
        } else {
            ei a = noVar.m7275y().m6470a();
            if (a == null) {
                C1129c.m6192d("No ticker for WebView, dropping extra parameter.");
            } else {
                a.m6477a(str, str2);
            }
        }
    }

    public void m6520a(no noVar, Map<String, String> map) {
        String str = (String) map.get("action");
        if ("tick".equals(str)) {
            m6517b(noVar, map);
        } else if ("experiment".equals(str)) {
            m6518c(noVar, map);
        } else if (Constants.APPBOY_PUSH_EXTRAS_KEY.equals(str)) {
            m6519d(noVar, map);
        }
    }
}
