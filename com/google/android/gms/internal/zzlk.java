package com.google.android.gms.internal;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.internal.zzz;
import java.net.URI;
import java.net.URISyntaxException;

@jw
public class zzlk extends WebViewClient {
    private final no zzBb;
    private final zzhs zzJY;
    private final String zzTd;
    private boolean zzTe;

    public zzlk(zzhs com_google_android_gms_internal_zzhs, no noVar, String str) {
        this.zzTd = zzbe(str);
        this.zzTe = false;
        this.zzBb = noVar;
        this.zzJY = com_google_android_gms_internal_zzhs;
    }

    private String zzbe(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                if (str.endsWith("/")) {
                    str = str.substring(0, str.length() - 1);
                }
            } catch (IndexOutOfBoundsException e) {
                C1129c.m6188b(e.getMessage());
            }
        }
        return str;
    }

    public void onLoadResource(WebView webView, String str) {
        String str2 = "JavascriptAdWebViewClient::onLoadResource: ";
        String valueOf = String.valueOf(str);
        C1129c.m6185a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        if (!zzbd(str)) {
            this.zzBb.m7262l().onLoadResource(this.zzBb.m7235a(), str);
        }
    }

    public void onPageFinished(WebView webView, String str) {
        String str2 = "JavascriptAdWebViewClient::onPageFinished: ";
        String valueOf = String.valueOf(str);
        C1129c.m6185a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        if (!this.zzTe) {
            this.zzJY.m7346a();
            this.zzTe = true;
        }
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String str2 = "JavascriptAdWebViewClient::shouldOverrideUrlLoading: ";
        String valueOf = String.valueOf(str);
        C1129c.m6185a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        if (!zzbd(str)) {
            return this.zzBb.m7262l().shouldOverrideUrlLoading(this.zzBb.m7235a(), str);
        }
        C1129c.m6185a("shouldOverrideUrlLoading: received passback url");
        return true;
    }

    protected boolean zzbd(String str) {
        Object zzbe = zzbe(str);
        if (TextUtils.isEmpty(zzbe)) {
            return false;
        }
        try {
            URI uri = new URI(zzbe);
            if ("passback".equals(uri.getScheme())) {
                C1129c.m6185a("Passback received");
                this.zzJY.m7349b();
                return true;
            } else if (TextUtils.isEmpty(this.zzTd)) {
                return false;
            } else {
                URI uri2 = new URI(this.zzTd);
                String host = uri2.getHost();
                String host2 = uri.getHost();
                String path = uri2.getPath();
                String path2 = uri.getPath();
                if (!zzz.equal(host, host2) || !zzz.equal(path, path2)) {
                    return false;
                }
                C1129c.m6185a("Passback received");
                this.zzJY.m7349b();
                return true;
            }
        } catch (URISyntaxException e) {
            C1129c.m6188b(e.getMessage());
            return false;
        }
    }
}
