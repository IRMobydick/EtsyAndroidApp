package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@jw
public class iu implements it {
    final Set<WebView> f5143a;
    private final Context f5144b;

    public iu(Context context) {
        this.f5143a = Collections.synchronizedSet(new HashSet());
        this.f5144b = context;
    }

    public WebView m6801a() {
        WebView webView = new WebView(this.f5144b);
        webView.getSettings().setJavaScriptEnabled(true);
        return webView;
    }

    public void m6802a(String str, String str2, String str3) {
        C1129c.m6185a("Fetching assets for the given html");
        lt.f5423a.post(new 1(this, str2, str3));
    }
}
