package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

@TargetApi(8)
@jw
public class lv {
    private lv() {
    }

    public static lv m7145a(int i) {
        return i >= 21 ? new md() : i >= 19 ? new mc() : i >= 18 ? new ma() : i >= 17 ? new lz() : i >= 16 ? new mb() : i >= 14 ? new ly() : i >= 11 ? new lx() : i >= 9 ? new lw() : new lv();
    }

    public int m7146a() {
        return 0;
    }

    public Drawable m7147a(Context context, Bitmap bitmap, boolean z, float f) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public zzlb m7148a(no noVar, boolean z) {
        return new zzlb(noVar, z);
    }

    public String m7149a(Context context) {
        return StringUtils.EMPTY;
    }

    public String m7150a(SslError sslError) {
        return StringUtils.EMPTY;
    }

    public Set<String> m7151a(Uri uri) {
        if (uri.isOpaque()) {
            return Collections.emptySet();
        }
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null) {
            return Collections.emptySet();
        }
        Set linkedHashSet = new LinkedHashSet();
        int i = 0;
        do {
            int indexOf = encodedQuery.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = encodedQuery.length();
            }
            int indexOf2 = encodedQuery.indexOf(61, i);
            if (indexOf2 > indexOf || indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            linkedHashSet.add(Uri.decode(encodedQuery.substring(i, indexOf2)));
            i = indexOf + 1;
        } while (i < encodedQuery.length());
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public void m7152a(Activity activity, OnGlobalLayoutListener onGlobalLayoutListener) {
        Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            m7154a(window.getDecorView().getViewTreeObserver(), onGlobalLayoutListener);
        }
    }

    public void m7153a(View view, Drawable drawable) {
        view.setBackgroundDrawable(drawable);
    }

    public void m7154a(ViewTreeObserver viewTreeObserver, OnGlobalLayoutListener onGlobalLayoutListener) {
        viewTreeObserver.removeGlobalOnLayoutListener(onGlobalLayoutListener);
    }

    public boolean m7155a(Request request) {
        return false;
    }

    public boolean m7156a(Context context, WebSettings webSettings) {
        return false;
    }

    public boolean m7157a(View view) {
        return (view.getWindowToken() == null && view.getWindowVisibility() == 8) ? false : true;
    }

    public boolean m7158a(Window window) {
        return false;
    }

    public boolean m7159a(no noVar) {
        if (noVar == null) {
            return false;
        }
        noVar.onPause();
        return true;
    }

    public int m7160b() {
        return 1;
    }

    public CookieManager m7161b(Context context) {
        CookieSyncManager.createInstance(context);
        return CookieManager.getInstance();
    }

    public boolean m7162b(View view) {
        return false;
    }

    public boolean m7163b(no noVar) {
        if (noVar == null) {
            return false;
        }
        noVar.onResume();
        return true;
    }

    public int m7164c() {
        return 5;
    }

    public WebChromeClient m7165c(no noVar) {
        return null;
    }

    public boolean m7166c(View view) {
        return false;
    }

    public LayoutParams m7167d() {
        return new LayoutParams(-2, -2);
    }
}
