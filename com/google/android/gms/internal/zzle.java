package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.etsy.android.lib.R;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.foresee.sdk.configuration.MeasureConfiguration;
import com.google.android.gms.ads.internal.C1077a;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.l;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.util.zzs;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

@jw
class zzle extends WebView implements OnGlobalLayoutListener, DownloadListener, no {
    private mu f5513A;
    private int f5514B;
    private int f5515C;
    private int f5516D;
    private int f5517E;
    private Map<String, gd> f5518F;
    private final WindowManager f5519G;
    boolean f5520a;
    private final zza f5521b;
    private final Object f5522c;
    private final bu f5523d;
    private final VersionInfoParcel f5524e;
    private final l f5525f;
    private final C1077a f5526g;
    private zzlb f5527h;
    private zzd f5528i;
    private AdSizeParcel f5529j;
    private boolean f5530k;
    private boolean f5531l;
    private boolean f5532m;
    private boolean f5533n;
    private Boolean f5534o;
    private int f5535p;
    private boolean f5536q;
    private String f5537r;
    private zzlf f5538s;
    private boolean f5539t;
    private eg f5540u;
    private eg f5541v;
    private eg f5542w;
    private eh f5543x;
    private WeakReference<OnClickListener> f5544y;
    private zzd f5545z;

    @jw
    public class zza extends MutableContextWrapper {
        private Activity zzRn;
        private Context zzSO;
        private Context zztm;

        public zza(Context context) {
            super(context);
            setBaseContext(context);
        }

        public Object getSystemService(String str) {
            return this.zzSO.getSystemService(str);
        }

        public void setBaseContext(Context context) {
            this.zztm = context.getApplicationContext();
            this.zzRn = context instanceof Activity ? (Activity) context : null;
            this.zzSO = context;
            super.setBaseContext(this.zztm);
        }

        public void startActivity(Intent intent) {
            if (this.zzRn != null) {
                this.zzRn.startActivity(intent);
                return;
            }
            intent.setFlags(268435456);
            this.zztm.startActivity(intent);
        }

        public Activity zzjy() {
            return this.zzRn;
        }

        public Context zzjz() {
            return this.zzSO;
        }
    }

    protected zzle(zza com_google_android_gms_internal_zzle_zza, AdSizeParcel adSizeParcel, boolean z, boolean z2, bu buVar, VersionInfoParcel versionInfoParcel, ei eiVar, l lVar, C1077a c1077a) {
        super(com_google_android_gms_internal_zzle_zza);
        this.f5522c = new Object();
        this.f5536q = true;
        this.f5520a = false;
        this.f5537r = StringUtils.EMPTY;
        this.f5514B = -1;
        this.f5515C = -1;
        this.f5516D = -1;
        this.f5517E = -1;
        this.f5521b = com_google_android_gms_internal_zzle_zza;
        this.f5529j = adSizeParcel;
        this.f5532m = z;
        this.f5535p = -1;
        this.f5523d = buVar;
        this.f5524e = versionInfoParcel;
        this.f5525f = lVar;
        this.f5526g = c1077a;
        this.f5519G = (WindowManager) getContext().getSystemService("window");
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        C1101o.m6041e().m7102a((Context) com_google_android_gms_internal_zzle_zza, versionInfoParcel.afmaVersion, settings);
        C1101o.m6043g().m7156a(getContext(), settings);
        setDownloadListener(this);
        m7389H();
        if (zzs.zzvc()) {
            addJavascriptInterface(new nv(this), "googleAdsJsInterface");
        }
        if (zzs.zzuX()) {
            removeJavascriptInterface("accessibility");
            removeJavascriptInterface("accessibilityTraversal");
        }
        this.f5513A = new mu(this.f5521b.zzjy(), this, this, null);
        m7395a(eiVar);
    }

    private void m7387F() {
        synchronized (this.f5522c) {
            this.f5534o = C1101o.m6044h().m7035j();
            if (this.f5534o == null) {
                try {
                    evaluateJavascript("(function(){})()", null);
                    m7411a(Boolean.valueOf(true));
                } catch (IllegalStateException e) {
                    m7411a(Boolean.valueOf(false));
                }
            }
        }
    }

    private void m7388G() {
        ed.m6464a(this.f5543x.m6470a(), this.f5540u, "aeh");
    }

    private void m7389H() {
        synchronized (this.f5522c) {
            if (this.f5532m || this.f5529j.zzvt) {
                if (VERSION.SDK_INT < 14) {
                    C1129c.m6185a("Disabling hardware acceleration on an overlay.");
                    m7390I();
                } else {
                    C1129c.m6185a("Enabling hardware acceleration on an overlay.");
                    m7391J();
                }
            } else if (VERSION.SDK_INT < 18) {
                C1129c.m6185a("Disabling hardware acceleration on an AdView.");
                m7390I();
            } else {
                C1129c.m6185a("Enabling hardware acceleration on an AdView.");
                m7391J();
            }
        }
    }

    private void m7390I() {
        synchronized (this.f5522c) {
            if (!this.f5533n) {
                C1101o.m6043g().m7166c((View) this);
            }
            this.f5533n = true;
        }
    }

    private void m7391J() {
        synchronized (this.f5522c) {
            if (this.f5533n) {
                C1101o.m6043g().m7162b((View) this);
            }
            this.f5533n = false;
        }
    }

    private void m7392K() {
        synchronized (this.f5522c) {
            this.f5518F = null;
        }
    }

    private void m7393L() {
        if (this.f5543x != null) {
            ei a = this.f5543x.m6470a();
            if (a != null && C1101o.m6044h().m7030e() != null) {
                C1101o.m6044h().m7030e().m6459a(a);
            }
        }
    }

    static zzle m7394a(Context context, AdSizeParcel adSizeParcel, boolean z, boolean z2, bu buVar, VersionInfoParcel versionInfoParcel, ei eiVar, l lVar, C1077a c1077a) {
        return new zzle(new zza(context), adSizeParcel, z, z2, buVar, versionInfoParcel, eiVar, lVar, c1077a);
    }

    private void m7395a(ei eiVar) {
        m7393L();
        this.f5543x = new eh(new ei(true, "make_wv", this.f5529j.zzvs));
        this.f5543x.m6470a().m6475a(eiVar);
        this.f5541v = ed.m6461a(this.f5543x.m6470a());
        this.f5543x.m6471a("native:view_create", this.f5541v);
        this.f5542w = null;
        this.f5540u = null;
    }

    private void m7397d(boolean z) {
        Map hashMap = new HashMap();
        hashMap.put("isVisible", z ? "1" : "0");
        m7416a("onAdVisibilityChanged", hashMap);
    }

    public void m7398A() {
        this.f5513A.m7194a();
    }

    public void m7399B() {
        if (this.f5542w == null) {
            this.f5542w = ed.m6461a(this.f5543x.m6470a());
            this.f5543x.m6471a("native:view_load", this.f5542w);
        }
    }

    public OnClickListener m7400C() {
        return (OnClickListener) this.f5544y.get();
    }

    public boolean m7401D() {
        if (!m7438l().zzdi()) {
            return false;
        }
        int i;
        int i2;
        DisplayMetrics a = C1101o.m6041e().m7087a(this.f5519G);
        int b = C1089r.m5951a().m6179b(a, a.widthPixels);
        int b2 = C1089r.m5951a().m6179b(a, a.heightPixels);
        Activity f = m7432f();
        if (f == null || f.getWindow() == null) {
            i = b2;
            i2 = b;
        } else {
            int[] a2 = C1101o.m6041e().m7117a(f);
            i2 = C1089r.m5951a().m6179b(a, a2[0]);
            i = C1089r.m5951a().m6179b(a, a2[1]);
        }
        if (this.f5515C == b && this.f5514B == b2 && this.f5516D == i2 && this.f5517E == i) {
            return false;
        }
        boolean z = (this.f5515C == b && this.f5514B == b2) ? false : true;
        this.f5515C = b;
        this.f5514B = b2;
        this.f5516D = i2;
        this.f5517E = i;
        new im(this).m6756a(b, b2, i2, i, a.density, this.f5519G.getDefaultDisplay().getRotation());
        return z;
    }

    Boolean m7402E() {
        Boolean bool;
        synchronized (this.f5522c) {
            bool = this.f5534o;
        }
        return bool;
    }

    public WebView m7403a() {
        return this;
    }

    public void m7404a(int i) {
        m7388G();
        Map hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i));
        hashMap.put("version", this.f5524e.afmaVersion);
        m7416a("onhide", hashMap);
    }

    public void m7405a(Context context) {
        this.f5521b.setBaseContext(context);
        this.f5513A.m7195a(this.f5521b.zzjy());
    }

    public void m7406a(Context context, AdSizeParcel adSizeParcel, ei eiVar) {
        synchronized (this.f5522c) {
            this.f5513A.m7196b();
            m7405a(context);
            this.f5528i = null;
            this.f5529j = adSizeParcel;
            this.f5532m = false;
            this.f5530k = false;
            this.f5537r = StringUtils.EMPTY;
            this.f5535p = -1;
            C1101o.m6043g().m7163b((no) this);
            loadUrl("about:blank");
            this.f5527h.reset();
            setOnTouchListener(null);
            setOnClickListener(null);
            this.f5536q = true;
            this.f5520a = false;
            this.f5538s = null;
            m7395a(eiVar);
            this.f5539t = false;
            C1101o.m6060x().m6561a((no) this);
            m7392K();
        }
    }

    public void m7407a(AdSizeParcel adSizeParcel) {
        synchronized (this.f5522c) {
            this.f5529j = adSizeParcel;
            requestLayout();
        }
    }

    public void m7408a(zzd com_google_android_gms_ads_internal_overlay_zzd) {
        synchronized (this.f5522c) {
            this.f5528i = com_google_android_gms_ads_internal_overlay_zzd;
        }
    }

    public void m7409a(zzbv com_google_android_gms_internal_zzbv, boolean z) {
        synchronized (this.f5522c) {
            this.f5539t = z;
        }
        m7397d(z);
    }

    public void m7410a(zzlf com_google_android_gms_internal_zzlf) {
        synchronized (this.f5522c) {
            if (this.f5538s != null) {
                C1129c.m6188b("Attempt to create multiple AdWebViewVideoControllers.");
                return;
            }
            this.f5538s = com_google_android_gms_internal_zzlf;
        }
    }

    void m7411a(Boolean bool) {
        synchronized (this.f5522c) {
            this.f5534o = bool;
        }
        C1101o.m6044h().m7018a(bool);
    }

    public void m7412a(String str) {
        synchronized (this.f5522c) {
            try {
                super.loadUrl(str);
            } catch (Throwable th) {
                String valueOf = String.valueOf(th);
                C1129c.m6192d(new StringBuilder(String.valueOf(valueOf).length() + 24).append("Could not call loadUrl. ").append(valueOf).toString());
            }
        }
    }

    @TargetApi(19)
    protected void m7413a(String str, ValueCallback<String> valueCallback) {
        synchronized (this.f5522c) {
            if (m7444r()) {
                C1129c.m6192d("The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue(null);
                }
            } else {
                evaluateJavascript(str, valueCallback);
            }
        }
    }

    public void m7414a(String str, fk fkVar) {
        if (this.f5527h != null) {
            this.f5527h.zza(str, fkVar);
        }
    }

    public void m7415a(String str, String str2) {
        m7430d(new StringBuilder((String.valueOf(str).length() + 3) + String.valueOf(str2).length()).append(str).append("(").append(str2).append(");").toString());
    }

    public void m7416a(String str, Map<String, ?> map) {
        try {
            m7424b(str, C1101o.m6041e().m7098a((Map) map));
        } catch (JSONException e) {
            C1129c.m6192d("Could not convert parameters to JSON.");
        }
    }

    public void m7417a(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        m7415a(str, jSONObject.toString());
    }

    public void m7418a(boolean z) {
        synchronized (this.f5522c) {
            this.f5532m = z;
            m7389H();
        }
    }

    public View m7419b() {
        return this;
    }

    public void m7420b(int i) {
        synchronized (this.f5522c) {
            this.f5535p = i;
            if (this.f5528i != null) {
                this.f5528i.setRequestedOrientation(this.f5535p);
            }
        }
    }

    public void m7421b(zzd com_google_android_gms_ads_internal_overlay_zzd) {
        synchronized (this.f5522c) {
            this.f5545z = com_google_android_gms_ads_internal_overlay_zzd;
        }
    }

    public void m7422b(String str) {
        synchronized (this.f5522c) {
            if (str == null) {
                str = StringUtils.EMPTY;
            }
            this.f5537r = str;
        }
    }

    public void m7423b(String str, fk fkVar) {
        if (this.f5527h != null) {
            this.f5527h.zzb(str, fkVar);
        }
    }

    public void m7424b(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AFMA_ReceiveMessage('");
        stringBuilder.append(str);
        stringBuilder.append("'");
        stringBuilder.append(",");
        stringBuilder.append(jSONObject2);
        stringBuilder.append(");");
        String str2 = "Dispatching AFMA event: ";
        jSONObject2 = String.valueOf(stringBuilder.toString());
        lo.m7056e(jSONObject2.length() != 0 ? str2.concat(jSONObject2) : new String(str2));
        m7430d(stringBuilder.toString());
    }

    public void m7425b(boolean z) {
        synchronized (this.f5522c) {
            if (this.f5528i != null) {
                this.f5528i.zza(this.f5527h.zzdi(), z);
            } else {
                this.f5530k = z;
            }
        }
    }

    public void m7426c() {
        m7388G();
        Map hashMap = new HashMap(1);
        hashMap.put("version", this.f5524e.afmaVersion);
        m7416a("onhide", hashMap);
    }

    protected void m7427c(String str) {
        synchronized (this.f5522c) {
            if (m7444r()) {
                C1129c.m6192d("The webview is destroyed. Ignoring action.");
            } else {
                loadUrl(str);
            }
        }
    }

    public void m7428c(boolean z) {
        synchronized (this.f5522c) {
            this.f5536q = z;
        }
    }

    public void m7429d() {
        if (this.f5540u == null) {
            ed.m6464a(this.f5543x.m6470a(), this.f5542w, "aes");
            this.f5540u = ed.m6461a(this.f5543x.m6470a());
            this.f5543x.m6471a("native:view_show", this.f5540u);
        }
        Map hashMap = new HashMap(1);
        hashMap.put("version", this.f5524e.afmaVersion);
        m7416a("onshow", hashMap);
    }

    protected void m7430d(String str) {
        if (zzs.zzve()) {
            if (m7402E() == null) {
                m7387F();
            }
            if (m7402E().booleanValue()) {
                m7413a(str, null);
                return;
            }
            String str2 = "javascript:";
            String valueOf = String.valueOf(str);
            m7427c(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return;
        }
        str2 = "javascript:";
        valueOf = String.valueOf(str);
        m7427c(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }

    public void destroy() {
        synchronized (this.f5522c) {
            m7393L();
            this.f5513A.m7196b();
            if (this.f5528i != null) {
                this.f5528i.close();
                this.f5528i.onDestroy();
                this.f5528i = null;
            }
            this.f5527h.reset();
            if (this.f5531l) {
                return;
            }
            C1101o.m6060x().m6561a((no) this);
            m7392K();
            this.f5531l = true;
            lo.m7056e("Initiating WebView self destruct sequence in 3...");
            this.f5527h.zzjT();
        }
    }

    public void m7431e() {
        Map hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(C1101o.m6041e().m7139h()));
        hashMap.put("app_volume", String.valueOf(C1101o.m6041e().m7136g()));
        hashMap.put("device_volume", String.valueOf(C1101o.m6041e().m7142j(getContext())));
        m7416a("volume", hashMap);
    }

    @TargetApi(19)
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        synchronized (this.f5522c) {
            if (m7444r()) {
                C1129c.m6192d("The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue(null);
                }
                return;
            }
            super.evaluateJavascript(str, valueCallback);
        }
    }

    public Activity m7432f() {
        return this.f5521b.zzjy();
    }

    protected void finalize() {
        synchronized (this.f5522c) {
            if (!this.f5531l) {
                this.f5527h.reset();
                C1101o.m6060x().m6561a((no) this);
                m7392K();
            }
        }
        super.finalize();
    }

    public Context m7433g() {
        return this.f5521b.zzjz();
    }

    public C1077a m7434h() {
        return this.f5526g;
    }

    public zzd m7435i() {
        zzd com_google_android_gms_ads_internal_overlay_zzd;
        synchronized (this.f5522c) {
            com_google_android_gms_ads_internal_overlay_zzd = this.f5528i;
        }
        return com_google_android_gms_ads_internal_overlay_zzd;
    }

    public zzd m7436j() {
        zzd com_google_android_gms_ads_internal_overlay_zzd;
        synchronized (this.f5522c) {
            com_google_android_gms_ads_internal_overlay_zzd = this.f5545z;
        }
        return com_google_android_gms_ads_internal_overlay_zzd;
    }

    public AdSizeParcel m7437k() {
        AdSizeParcel adSizeParcel;
        synchronized (this.f5522c) {
            adSizeParcel = this.f5529j;
        }
        return adSizeParcel;
    }

    public zzlb m7438l() {
        return this.f5527h;
    }

    public void loadData(String str, String str2, String str3) {
        synchronized (this.f5522c) {
            if (m7444r()) {
                C1129c.m6192d("The webview is destroyed. Ignoring action.");
            } else {
                super.loadData(str, str2, str3);
            }
        }
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        synchronized (this.f5522c) {
            if (m7444r()) {
                C1129c.m6192d("The webview is destroyed. Ignoring action.");
            } else {
                super.loadDataWithBaseURL(str, str2, str3, str4, str5);
            }
        }
    }

    public void loadUrl(String str) {
        synchronized (this.f5522c) {
            if (m7444r()) {
                C1129c.m6192d("The webview is destroyed. Ignoring action.");
            } else {
                try {
                    super.loadUrl(str);
                } catch (Throwable th) {
                    String valueOf = String.valueOf(th);
                    C1129c.m6192d(new StringBuilder(String.valueOf(valueOf).length() + 24).append("Could not call loadUrl. ").append(valueOf).toString());
                }
            }
        }
    }

    public boolean m7439m() {
        boolean z;
        synchronized (this.f5522c) {
            z = this.f5530k;
        }
        return z;
    }

    public bu m7440n() {
        return this.f5523d;
    }

    public VersionInfoParcel m7441o() {
        return this.f5524e;
    }

    protected void onAttachedToWindow() {
        synchronized (this.f5522c) {
            super.onAttachedToWindow();
            if (!m7444r()) {
                this.f5513A.m7197c();
            }
            m7397d(this.f5539t);
        }
    }

    protected void onDetachedFromWindow() {
        synchronized (this.f5522c) {
            if (!m7444r()) {
                this.f5513A.m7198d();
            }
            super.onDetachedFromWindow();
        }
        m7397d(false);
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            C1101o.m6041e().m7101a(getContext(), intent);
        } catch (ActivityNotFoundException e) {
            C1129c.m6185a(new StringBuilder((String.valueOf(str).length() + 51) + String.valueOf(str4).length()).append("Couldn't find an Activity to view url/mimetype: ").append(str).append(" / ").append(str4).toString());
        }
    }

    @TargetApi(21)
    protected void onDraw(Canvas canvas) {
        if (!m7444r()) {
            if (VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
                super.onDraw(canvas);
            }
        }
    }

    public void onGlobalLayout() {
        boolean D = m7401D();
        zzd i = m7435i();
        if (i != null && D) {
            i.zzgt();
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3 = MeasureConfiguration.DISABLED;
        synchronized (this.f5522c) {
            if (m7444r()) {
                setMeasuredDimension(0, 0);
            } else if (isInEditMode() || this.f5532m || this.f5529j.zzvv || this.f5529j.zzvw) {
                super.onMeasure(i, i2);
            } else if (this.f5529j.zzvt) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                this.f5519G.getDefaultDisplay().getMetrics(displayMetrics);
                setMeasuredDimension(displayMetrics.widthPixels, displayMetrics.heightPixels);
            } else {
                int mode = MeasureSpec.getMode(i);
                int size = MeasureSpec.getSize(i);
                int mode2 = MeasureSpec.getMode(i2);
                int size2 = MeasureSpec.getSize(i2);
                mode = (mode == RtlSpacingHelper.UNDEFINED || mode == 1073741824) ? size : Integer.MAX_VALUE;
                if (mode2 == RtlSpacingHelper.UNDEFINED || mode2 == 1073741824) {
                    i3 = size2;
                }
                if (this.f5529j.widthPixels > mode || this.f5529j.heightPixels > r0) {
                    float f = this.f5521b.getResources().getDisplayMetrics().density;
                    mode2 = (int) (((float) this.f5529j.heightPixels) / f);
                    size = (int) (((float) size) / f);
                    C1129c.m6192d(new StringBuilder(R.AppCompatTheme_checkedTextViewStyle).append("Not enough space to show ad. Needs ").append((int) (((float) this.f5529j.widthPixels) / f)).append(EtsyDialogFragment.OPT_X_BUTTON).append(mode2).append(" dp, but only has ").append(size).append(EtsyDialogFragment.OPT_X_BUTTON).append((int) (((float) size2) / f)).append(" dp.").toString());
                    if (getVisibility() != 8) {
                        setVisibility(4);
                    }
                    setMeasuredDimension(0, 0);
                } else {
                    if (getVisibility() != 8) {
                        setVisibility(0);
                    }
                    setMeasuredDimension(this.f5529j.widthPixels, this.f5529j.heightPixels);
                }
            }
        }
    }

    public void onPause() {
        if (!m7444r()) {
            try {
                if (zzs.zzuX()) {
                    super.onPause();
                }
            } catch (Throwable e) {
                C1129c.m6189b("Could not pause webview.", e);
            }
        }
    }

    public void onResume() {
        if (!m7444r()) {
            try {
                if (zzs.zzuX()) {
                    super.onResume();
                }
            } catch (Throwable e) {
                C1129c.m6189b("Could not resume webview.", e);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f5523d != null) {
            this.f5523d.a(motionEvent);
        }
        return m7444r() ? false : super.onTouchEvent(motionEvent);
    }

    public boolean m7442p() {
        boolean z;
        synchronized (this.f5522c) {
            z = this.f5532m;
        }
        return z;
    }

    public int m7443q() {
        int i;
        synchronized (this.f5522c) {
            i = this.f5535p;
        }
        return i;
    }

    public boolean m7444r() {
        boolean z;
        synchronized (this.f5522c) {
            z = this.f5531l;
        }
        return z;
    }

    public void m7445s() {
        synchronized (this.f5522c) {
            lo.m7056e("Destroying WebView!");
            lt.f5423a.post(new 1(this));
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.f5544y = new WeakReference(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzlb) {
            this.f5527h = (zzlb) webViewClient;
        }
    }

    public void stopLoading() {
        if (!m7444r()) {
            try {
                super.stopLoading();
            } catch (Throwable e) {
                C1129c.m6189b("Could not stop loading webview.", e);
            }
        }
    }

    public boolean m7446t() {
        boolean z;
        synchronized (this.f5522c) {
            ed.m6464a(this.f5543x.m6470a(), this.f5540u, "aebb");
            z = this.f5536q;
        }
        return z;
    }

    public boolean m7447u() {
        boolean z;
        synchronized (this.f5522c) {
            z = this.f5520a;
        }
        return z;
    }

    public String m7448v() {
        String str;
        synchronized (this.f5522c) {
            str = this.f5537r;
        }
        return str;
    }

    public nl m7449w() {
        return null;
    }

    public eg m7450x() {
        return this.f5542w;
    }

    public eh m7451y() {
        return this.f5543x;
    }

    public zzlf m7452z() {
        zzlf com_google_android_gms_internal_zzlf;
        synchronized (this.f5522c) {
            com_google_android_gms_internal_zzlf = this.f5538s;
        }
        return com_google_android_gms_internal_zzlf;
    }

    public void zzbA() {
        synchronized (this.f5522c) {
            this.f5520a = true;
            if (this.f5525f != null) {
                this.f5525f.zzbA();
            }
        }
    }

    public void zzbB() {
        synchronized (this.f5522c) {
            this.f5520a = false;
            if (this.f5525f != null) {
                this.f5525f.zzbB();
            }
        }
    }
}
