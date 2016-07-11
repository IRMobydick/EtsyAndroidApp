package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.adjust.sdk.Constants;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@TargetApi(11)
@jw
public class zzli extends zzlb {
    public zzli(no noVar, boolean z) {
        super(noVar, z);
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        Exception e;
        String valueOf;
        if (this.zzSk != null) {
            this.zzSk.b(str);
        }
        String str2;
        try {
            if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
                return super.shouldInterceptRequest(webView, str);
            }
            if (webView instanceof no) {
                no noVar = (no) webView;
                noVar.m7262l().zzgr();
                str2 = noVar.m7261k().zzvt ? (String) dz.f4807G.m6433c() : noVar.m7266p() ? (String) dz.f4806F.m6433c() : (String) dz.f4805E.m6433c();
                lo.m7056e(new StringBuilder(String.valueOf(str2).length() + 24).append("shouldInterceptRequest(").append(str2).append(")").toString());
                return zzd(noVar.getContext(), this.zzBb.m7265o().afmaVersion, str2);
            }
            C1129c.m6192d("Tried to intercept request from a WebView that wasn't an AdWebView.");
            return super.shouldInterceptRequest(webView, str);
        } catch (IOException e2) {
            e = e2;
            str2 = "Could not fetch MRAID JS. ";
            valueOf = String.valueOf(e.getMessage());
            C1129c.m6192d(valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2));
            return super.shouldInterceptRequest(webView, str);
        } catch (ExecutionException e3) {
            e = e3;
            str2 = "Could not fetch MRAID JS. ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
            }
            C1129c.m6192d(valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2));
            return super.shouldInterceptRequest(webView, str);
        } catch (InterruptedException e4) {
            e = e4;
            str2 = "Could not fetch MRAID JS. ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
            }
            C1129c.m6192d(valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2));
            return super.shouldInterceptRequest(webView, str);
        } catch (TimeoutException e5) {
            e = e5;
            str2 = "Could not fetch MRAID JS. ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
            }
            C1129c.m6192d(valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2));
            return super.shouldInterceptRequest(webView, str);
        }
    }

    protected WebResourceResponse zzd(Context context, String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("User-Agent", C1101o.m6041e().m7092a(context, str));
        hashMap.put("Cache-Control", "max-stale=3600");
        String str3 = (String) new mk(context).m7180a(str2, hashMap).get(60, TimeUnit.SECONDS);
        return str3 == null ? null : new WebResourceResponse("application/javascript", Constants.ENCODING, new ByteArrayInputStream(str3.getBytes(Constants.ENCODING)));
    }
}
