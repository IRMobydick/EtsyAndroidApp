package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.adjust.sdk.Constants;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.C1078b;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.a;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.C1106e;
import com.google.android.gms.ads.internal.overlay.f;
import com.google.android.gms.ads.internal.overlay.l;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.lang3.StringUtils;

@jw
public class zzlb extends WebViewClient {
    private static final String[] zzSb;
    private static final String[] zzSc;
    private fg zzBH;
    protected no zzBb;
    private fs zzCl;
    private C1078b zzCn;
    private ig zzCo;
    private fq zzCq;
    private in zzGg;
    private np zzJS;
    private final HashMap<String, List<fk>> zzSd;
    private f zzSe;
    private nq zzSf;
    private boolean zzSg;
    private boolean zzSh;
    private l zzSi;
    private final il zzSj;
    @Nullable
    protected kv zzSk;
    private boolean zzSl;
    private boolean zzSm;
    private boolean zzSn;
    private int zzSo;
    private final Object zzpp;
    private boolean zztL;
    private a zzuL;

    static {
        zzSb = new String[]{"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
        zzSc = new String[]{"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
    }

    public zzlb(no noVar, boolean z) {
        this(noVar, z, new il(noVar, noVar.m7257g(), new dr(noVar.getContext())), null);
    }

    zzlb(no noVar, boolean z, il ilVar, ig igVar) {
        this.zzSd = new HashMap();
        this.zzpp = new Object();
        this.zzSg = false;
        this.zzBb = noVar;
        this.zztL = z;
        this.zzSj = ilVar;
        this.zzCo = igVar;
    }

    private void zza(Context context, String str, String str2, String str3) {
        if (((Boolean) dz.ay.m6433c()).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putString(NotificationCompatApi21.CATEGORY_ERROR, str);
            bundle.putString(ResponseConstants.CODE, str2);
            bundle.putString("host", zzaZ(str3));
            C1101o.m6041e().m7104a(context, this.zzBb.m7265o().afmaVersion, "gmob-apps", bundle, true);
        }
    }

    private String zzaZ(String str) {
        if (TextUtils.isEmpty(str)) {
            return StringUtils.EMPTY;
        }
        Uri parse = Uri.parse(str);
        return parse.getHost() != null ? parse.getHost() : StringUtils.EMPTY;
    }

    private static boolean zzh(Uri uri) {
        String scheme = uri.getScheme();
        return "http".equalsIgnoreCase(scheme) || Constants.SCHEME.equalsIgnoreCase(scheme);
    }

    private void zzjV() {
        synchronized (this.zzpp) {
            this.zzSh = true;
        }
        this.zzSo++;
        zzjY();
    }

    private void zzjW() {
        this.zzSo--;
        zzjY();
    }

    private void zzjX() {
        this.zzSn = true;
        zzjY();
    }

    public final void onLoadResource(WebView webView, String str) {
        String str2 = "Loading resource: ";
        String valueOf = String.valueOf(str);
        lo.m7056e(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzi(parse);
        }
    }

    public final void onPageFinished(WebView webView, String str) {
        synchronized (this.zzpp) {
            if (this.zzSl) {
                lo.m7056e("Blank page loaded, 1...");
                this.zzBb.m7269s();
                return;
            }
            this.zzSm = true;
            zzjY();
        }
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        String valueOf = (i >= 0 || (-i) - 1 >= zzSb.length) ? String.valueOf(i) : zzSb[(-i) - 1];
        zza(this.zzBb.getContext(), "http_err", valueOf, str2);
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            String valueOf = (primaryError < 0 || primaryError >= zzSc.length) ? String.valueOf(primaryError) : zzSc[primaryError];
            zza(this.zzBb.getContext(), "ssl_err", valueOf, C1101o.m6043g().m7150a(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    public final void reset() {
        if (this.zzSk != null) {
            this.zzSk.a();
            this.zzSk = null;
        }
        synchronized (this.zzpp) {
            this.zzSd.clear();
            this.zzuL = null;
            this.zzSe = null;
            this.zzJS = null;
            this.zzBH = null;
            this.zzSg = false;
            this.zztL = false;
            this.zzSh = false;
            this.zzCq = null;
            this.zzSi = null;
            this.zzSf = null;
            if (this.zzCo != null) {
                this.zzCo.m6772a(true);
                this.zzCo = null;
            }
        }
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case R.AppCompatTheme_panelMenuListWidth /*79*/:
            case R.AppCompatTheme_colorControlNormal /*85*/:
            case R.AppCompatTheme_colorControlActivated /*86*/:
            case R.AppCompatTheme_colorControlHighlight /*87*/:
            case R.AppCompatTheme_colorButtonNormal /*88*/:
            case R.AppCompatTheme_colorSwitchThumbNormal /*89*/:
            case R.AppCompatTheme_controlBackground /*90*/:
            case R.AppCompatTheme_alertDialogStyle /*91*/:
            case TransportMediator.KEYCODE_MEDIA_PLAY /*126*/:
            case TransportMediator.KEYCODE_MEDIA_PAUSE /*127*/:
            case AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS /*128*/:
            case 129:
            case TransportMediator.KEYCODE_MEDIA_RECORD /*130*/:
            case 222:
                return true;
            default:
                return false;
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String str2 = "AdWebView shouldOverrideUrlLoading: ";
        String valueOf = String.valueOf(str);
        lo.m7056e(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzi(parse);
        } else if (this.zzSg && webView == this.zzBb.m7235a() && zzh(parse)) {
            if (this.zzuL != null && ((Boolean) dz.f4824X.m6433c()).booleanValue()) {
                this.zzuL.onAdClicked();
                if (this.zzSk != null) {
                    this.zzSk.a(str);
                }
                this.zzuL = null;
            }
            return super.shouldOverrideUrlLoading(webView, str);
        } else if (this.zzBb.m7235a().willNotDraw()) {
            str2 = "AdWebView unable to handle URL: ";
            valueOf = String.valueOf(str);
            C1129c.m6192d(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else {
            Uri uri;
            try {
                bu n = this.zzBb.m7264n();
                if (n != null && n.c(parse)) {
                    parse = n.b(parse, this.zzBb.getContext());
                }
                uri = parse;
            } catch (zzar e) {
                String str3 = "Unable to append parameter to URL: ";
                str2 = String.valueOf(str);
                C1129c.m6192d(str2.length() != 0 ? str3.concat(str2) : new String(str3));
                uri = parse;
            }
            if (this.zzCn == null || this.zzCn.m5856b()) {
                zza(new AdLauncherIntentInfoParcel("android.intent.action.VIEW", uri.toString(), null, null, null, null, null));
            } else {
                this.zzCn.m5855a(str);
            }
        }
        return true;
    }

    public void zzJ(boolean z) {
        this.zzSg = z;
    }

    public void zza(int i, int i2, boolean z) {
        this.zzSj.m6788a(i, i2);
        if (this.zzCo != null) {
            this.zzCo.m6770a(i, i2, z);
        }
    }

    public void zza(a aVar, f fVar, fg fgVar, l lVar, boolean z, fq fqVar, @Nullable fs fsVar, C1078b c1078b, in inVar, kv kvVar) {
        if (c1078b == null) {
            c1078b = new C1078b(this.zzBb.getContext());
        }
        this.zzCo = new ig(this.zzBb, inVar);
        this.zzSk = kvVar;
        zza("/appEvent", new ff(fgVar));
        zza("/backButton", fj.f4909k);
        zza("/refresh", fj.f4910l);
        zza("/canOpenURLs", fj.f4900b);
        zza("/canOpenIntents", fj.f4901c);
        zza("/click", fj.f4902d);
        zza("/close", fj.f4903e);
        zza("/customClose", fj.f4905g);
        zza("/instrument", fj.f4914p);
        zza("/delayPageLoaded", new ns(this, null));
        zza("/httpTrack", fj.f4906h);
        zza("/log", fj.f4907i);
        zza("/mraid", new fu(c1078b, this.zzCo));
        zza("/mraidLoaded", this.zzSj);
        zza("/open", new fv(fqVar, c1078b, this.zzCo));
        zza("/precache", fj.f4913o);
        zza("/touch", fj.f4908j);
        zza("/video", fj.f4911m);
        zza("/videoMeta", fj.f4912n);
        zza("/appStreaming", fj.f4904f);
        if (fsVar != null) {
            zza("/setInterstitialProperties", new fr(fsVar));
        }
        this.zzuL = aVar;
        this.zzSe = fVar;
        this.zzBH = fgVar;
        this.zzCq = fqVar;
        this.zzSi = lVar;
        this.zzCn = c1078b;
        this.zzGg = inVar;
        this.zzCl = fsVar;
        zzJ(z);
    }

    public final void zza(AdLauncherIntentInfoParcel adLauncherIntentInfoParcel) {
        f fVar = null;
        boolean p = this.zzBb.m7266p();
        a aVar = (!p || this.zzBb.m7261k().zzvt) ? this.zzuL : null;
        if (!p) {
            fVar = this.zzSe;
        }
        zza(new AdOverlayInfoParcel(adLauncherIntentInfoParcel, aVar, fVar, this.zzSi, this.zzBb.m7265o()));
    }

    public void zza(AdOverlayInfoParcel adOverlayInfoParcel) {
        boolean z = false;
        boolean b = this.zzCo != null ? this.zzCo.m6775b() : false;
        C1106e c = C1101o.m6039c();
        Context context = this.zzBb.getContext();
        if (!b) {
            z = true;
        }
        c.m6067a(context, adOverlayInfoParcel, z);
        if (this.zzSk != null) {
            String str = adOverlayInfoParcel.url;
            if (str == null && adOverlayInfoParcel.zzHC != null) {
                str = adOverlayInfoParcel.zzHC.url;
            }
            this.zzSk.a(str);
        }
    }

    public void zza(np npVar) {
        this.zzJS = npVar;
    }

    public void zza(nq nqVar) {
        this.zzSf = nqVar;
    }

    public void zza(String str, fk fkVar) {
        synchronized (this.zzpp) {
            List list = (List) this.zzSd.get(str);
            if (list == null) {
                list = new CopyOnWriteArrayList();
                this.zzSd.put(str, list);
            }
            list.add(fkVar);
        }
    }

    public final void zza(boolean z, int i) {
        a aVar = (!this.zzBb.m7266p() || this.zzBb.m7261k().zzvt) ? this.zzuL : null;
        zza(new AdOverlayInfoParcel(aVar, this.zzSe, this.zzSi, this.zzBb, z, i, this.zzBb.m7265o()));
    }

    public final void zza(boolean z, int i, String str) {
        f fVar = null;
        boolean p = this.zzBb.m7266p();
        a aVar = (!p || this.zzBb.m7261k().zzvt) ? this.zzuL : null;
        if (!p) {
            fVar = new nr(this.zzBb, this.zzSe);
        }
        zza(new AdOverlayInfoParcel(aVar, fVar, this.zzBH, this.zzSi, this.zzBb, z, i, str, this.zzBb.m7265o(), this.zzCq));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        boolean p = this.zzBb.m7266p();
        a aVar = (!p || this.zzBb.m7261k().zzvt) ? this.zzuL : null;
        zza(new AdOverlayInfoParcel(aVar, p ? null : new nr(this.zzBb, this.zzSe), this.zzBH, this.zzSi, this.zzBb, z, i, str, str2, this.zzBb.m7265o(), this.zzCq));
    }

    public void zzb(String str, fk fkVar) {
        synchronized (this.zzpp) {
            List list = (List) this.zzSd.get(str);
            if (list == null) {
                return;
            }
            list.remove(fkVar);
        }
    }

    public void zzd(int i, int i2) {
        if (this.zzCo != null) {
            this.zzCo.m6776c(i, i2);
        }
    }

    public boolean zzdi() {
        boolean z;
        synchronized (this.zzpp) {
            z = this.zztL;
        }
        return z;
    }

    public final void zzgr() {
        synchronized (this.zzpp) {
            this.zzSg = false;
            this.zztL = true;
            C1101o.m6041e().m7109a(new 2(this));
        }
    }

    public void zzi(Uri uri) {
        String path = uri.getPath();
        List<fk> list = (List) this.zzSd.get(path);
        if (list != null) {
            Map a = C1101o.m6041e().m7096a(uri);
            if (C1129c.m6187a(2)) {
                String str = "Received GMSG: ";
                path = String.valueOf(path);
                lo.m7056e(path.length() != 0 ? str.concat(path) : new String(str));
                for (String path2 : a.keySet()) {
                    str = (String) a.get(path2);
                    lo.m7056e(new StringBuilder((String.valueOf(path2).length() + 4) + String.valueOf(str).length()).append("  ").append(path2).append(": ").append(str).toString());
                }
            }
            for (fk a2 : list) {
                a2.a(this.zzBb, a);
            }
            return;
        }
        String valueOf = String.valueOf(uri);
        lo.m7056e(new StringBuilder(String.valueOf(valueOf).length() + 32).append("No GMSG handler found for GMSG: ").append(valueOf).toString());
    }

    public C1078b zzjR() {
        return this.zzCn;
    }

    public boolean zzjS() {
        boolean z;
        synchronized (this.zzpp) {
            z = this.zzSh;
        }
        return z;
    }

    public void zzjT() {
        synchronized (this.zzpp) {
            lo.m7056e("Loading blank page in WebView, 2...");
            this.zzSl = true;
            this.zzBb.m7242a("about:blank");
        }
    }

    public void zzjU() {
        if (this.zzSk != null) {
            lt.f5423a.post(new 1(this));
        }
    }

    public final void zzjY() {
        if (this.zzJS != null && ((this.zzSm && this.zzSo <= 0) || this.zzSn)) {
            this.zzJS.a(this.zzBb, !this.zzSn);
            this.zzJS = null;
        }
        this.zzBb.m7233B();
    }

    public void zzl(no noVar) {
        this.zzBb = noVar;
    }
}
