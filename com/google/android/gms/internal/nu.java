package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.C1077a;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.Map;
import org.json.JSONObject;

@jw
class nu extends FrameLayout implements no {
    private final no f5485a;
    private final nl f5486b;

    public nu(no noVar) {
        super(noVar.getContext());
        this.f5485a = noVar;
        this.f5486b = new nl(noVar.m7257g(), this, this);
        zzlb l = this.f5485a.m7262l();
        if (l != null) {
            l.zzl(this);
        }
        addView(this.f5485a.m7247b());
    }

    public void m7279A() {
        this.f5485a.m7232A();
    }

    public void m7280B() {
        this.f5485a.m7233B();
    }

    public OnClickListener m7281C() {
        return this.f5485a.m7234C();
    }

    public WebView m7282a() {
        return this.f5485a.m7235a();
    }

    public void m7283a(int i) {
        this.f5485a.m7236a(i);
    }

    public void m7284a(Context context) {
        this.f5485a.m7237a(context);
    }

    public void m7285a(Context context, AdSizeParcel adSizeParcel, ei eiVar) {
        this.f5486b.m7231c();
        this.f5485a.m7238a(context, adSizeParcel, eiVar);
    }

    public void m7286a(AdSizeParcel adSizeParcel) {
        this.f5485a.m7239a(adSizeParcel);
    }

    public void m7287a(zzd com_google_android_gms_ads_internal_overlay_zzd) {
        this.f5485a.m7240a(com_google_android_gms_ads_internal_overlay_zzd);
    }

    public void m7288a(zzbv com_google_android_gms_internal_zzbv, boolean z) {
        this.f5485a.a(com_google_android_gms_internal_zzbv, z);
    }

    public void m7289a(zzlf com_google_android_gms_internal_zzlf) {
        this.f5485a.m7241a(com_google_android_gms_internal_zzlf);
    }

    public void m7290a(String str) {
        this.f5485a.m7242a(str);
    }

    public void m7291a(String str, fk fkVar) {
        this.f5485a.a(str, fkVar);
    }

    public void m7292a(String str, String str2) {
        this.f5485a.m7243a(str, str2);
    }

    public void m7293a(String str, Map<String, ?> map) {
        this.f5485a.m7244a(str, (Map) map);
    }

    public void m7294a(String str, JSONObject jSONObject) {
        this.f5485a.m7245a(str, jSONObject);
    }

    public void m7295a(boolean z) {
        this.f5485a.m7246a(z);
    }

    public View m7296b() {
        return this;
    }

    public void m7297b(int i) {
        this.f5485a.m7248b(i);
    }

    public void m7298b(zzd com_google_android_gms_ads_internal_overlay_zzd) {
        this.f5485a.m7249b(com_google_android_gms_ads_internal_overlay_zzd);
    }

    public void m7299b(String str) {
        this.f5485a.m7250b(str);
    }

    public void m7300b(String str, fk fkVar) {
        this.f5485a.b(str, fkVar);
    }

    public void m7301b(String str, JSONObject jSONObject) {
        this.f5485a.b(str, jSONObject);
    }

    public void m7302b(boolean z) {
        this.f5485a.m7251b(z);
    }

    public void m7303c() {
        this.f5485a.m7252c();
    }

    public void m7304c(boolean z) {
        this.f5485a.m7253c(z);
    }

    public void m7305d() {
        this.f5485a.m7254d();
    }

    public void destroy() {
        this.f5485a.destroy();
    }

    public void m7306e() {
        this.f5485a.m7255e();
    }

    public Activity m7307f() {
        return this.f5485a.m7256f();
    }

    public Context m7308g() {
        return this.f5485a.m7257g();
    }

    public C1077a m7309h() {
        return this.f5485a.m7258h();
    }

    public zzd m7310i() {
        return this.f5485a.m7259i();
    }

    public zzd m7311j() {
        return this.f5485a.m7260j();
    }

    public AdSizeParcel m7312k() {
        return this.f5485a.m7261k();
    }

    public zzlb m7313l() {
        return this.f5485a.m7262l();
    }

    public void loadData(String str, String str2, String str3) {
        this.f5485a.loadData(str, str2, str3);
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.f5485a.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    public void loadUrl(String str) {
        this.f5485a.loadUrl(str);
    }

    public boolean m7314m() {
        return this.f5485a.m7263m();
    }

    public bu m7315n() {
        return this.f5485a.m7264n();
    }

    public VersionInfoParcel m7316o() {
        return this.f5485a.m7265o();
    }

    public void onPause() {
        this.f5486b.m7230b();
        this.f5485a.onPause();
    }

    public void onResume() {
        this.f5485a.onResume();
    }

    public boolean m7317p() {
        return this.f5485a.m7266p();
    }

    public int m7318q() {
        return this.f5485a.m7267q();
    }

    public boolean m7319r() {
        return this.f5485a.m7268r();
    }

    public void m7320s() {
        this.f5486b.m7231c();
        this.f5485a.m7269s();
    }

    public void setBackgroundColor(int i) {
        this.f5485a.setBackgroundColor(i);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.f5485a.setOnClickListener(onClickListener);
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.f5485a.setOnTouchListener(onTouchListener);
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        this.f5485a.setWebChromeClient(webChromeClient);
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        this.f5485a.setWebViewClient(webViewClient);
    }

    public void stopLoading() {
        this.f5485a.stopLoading();
    }

    public boolean m7321t() {
        return this.f5485a.m7270t();
    }

    public boolean m7322u() {
        return this.f5485a.m7271u();
    }

    public String m7323v() {
        return this.f5485a.m7272v();
    }

    public nl m7324w() {
        return this.f5486b;
    }

    public eg m7325x() {
        return this.f5485a.m7274x();
    }

    public eh m7326y() {
        return this.f5485a.m7275y();
    }

    public zzlf m7327z() {
        return this.f5485a.m7276z();
    }

    public void zzbA() {
        this.f5485a.zzbA();
    }

    public void zzbB() {
        this.f5485a.zzbB();
    }
}
