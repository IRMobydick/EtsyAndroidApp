package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.C1077a;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.l;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.Map;
import org.json.JSONObject;

@jw
public interface no extends l, da, hd {
    void m7232A();

    void m7233B();

    OnClickListener m7234C();

    WebView m7235a();

    void m7236a(int i);

    void m7237a(Context context);

    void m7238a(Context context, AdSizeParcel adSizeParcel, ei eiVar);

    void m7239a(AdSizeParcel adSizeParcel);

    void m7240a(zzd com_google_android_gms_ads_internal_overlay_zzd);

    void m7241a(zzlf com_google_android_gms_internal_zzlf);

    void m7242a(String str);

    void m7243a(String str, String str2);

    void m7244a(String str, Map<String, ?> map);

    void m7245a(String str, JSONObject jSONObject);

    void m7246a(boolean z);

    View m7247b();

    void m7248b(int i);

    void m7249b(zzd com_google_android_gms_ads_internal_overlay_zzd);

    void m7250b(String str);

    void m7251b(boolean z);

    void m7252c();

    void m7253c(boolean z);

    void m7254d();

    void destroy();

    void m7255e();

    Activity m7256f();

    Context m7257g();

    Context getContext();

    LayoutParams getLayoutParams();

    void getLocationOnScreen(int[] iArr);

    int getMeasuredHeight();

    int getMeasuredWidth();

    ViewParent getParent();

    C1077a m7258h();

    zzd m7259i();

    zzd m7260j();

    AdSizeParcel m7261k();

    zzlb m7262l();

    void loadData(String str, String str2, String str3);

    void loadDataWithBaseURL(String str, String str2, String str3, String str4, @Nullable String str5);

    void loadUrl(String str);

    boolean m7263m();

    void measure(int i, int i2);

    bu m7264n();

    VersionInfoParcel m7265o();

    void onPause();

    void onResume();

    boolean m7266p();

    int m7267q();

    boolean m7268r();

    void m7269s();

    void setBackgroundColor(int i);

    void setOnClickListener(OnClickListener onClickListener);

    void setOnTouchListener(OnTouchListener onTouchListener);

    void setWebChromeClient(WebChromeClient webChromeClient);

    void setWebViewClient(WebViewClient webViewClient);

    void stopLoading();

    boolean m7270t();

    boolean m7271u();

    String m7272v();

    @Nullable
    nl m7273w();

    @Nullable
    eg m7274x();

    eh m7275y();

    @Nullable
    zzlf m7276z();
}
