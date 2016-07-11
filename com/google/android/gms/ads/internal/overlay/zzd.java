package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.appboy.Constants;
import com.google.android.gms.ads.internal.C1077a;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.no;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Future;

@jw
public class zzd extends com.google.android.gms.internal.zzgz.zza implements q {
    static final int zzHg;
    private final Activity mActivity;
    no zzBb;
    AdOverlayInfoParcel zzHh;
    C1104c zzHi;
    zzo zzHj;
    boolean zzHk;
    FrameLayout zzHl;
    CustomViewCallback zzHm;
    boolean zzHn;
    boolean zzHo;
    C1103b zzHp;
    boolean zzHq;
    int zzHr;
    i zzHs;
    private boolean zzHt;
    private boolean zzHu;
    private boolean zzHv;

    @jw
    final class zza extends Exception {
        public zza(String str) {
            super(str);
        }
    }

    static {
        zzHg = Color.argb(0, 0, 0, 0);
    }

    public zzd(Activity activity) {
        this.zzHk = false;
        this.zzHn = false;
        this.zzHo = false;
        this.zzHq = false;
        this.zzHr = 0;
        this.zzHu = false;
        this.zzHv = true;
        this.mActivity = activity;
        this.zzHs = new o();
    }

    public void close() {
        this.zzHr = 2;
        this.mActivity.finish();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onBackPressed() {
        this.zzHr = 0;
    }

    public void onCreate(Bundle bundle) {
        boolean z = false;
        this.mActivity.requestWindowFeature(1);
        if (bundle != null) {
            z = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.zzHn = z;
        try {
            this.zzHh = AdOverlayInfoParcel.zzb(this.mActivity.getIntent());
            if (this.zzHh == null) {
                throw new zza("Could not get info for ad overlay.");
            }
            if (this.zzHh.zzsx.zzRD > 7500000) {
                this.zzHr = 3;
            }
            if (this.mActivity.getIntent() != null) {
                this.zzHv = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
            }
            if (this.zzHh.zzHO != null) {
                this.zzHo = this.zzHh.zzHO.zzrf;
            } else {
                this.zzHo = false;
            }
            if (((Boolean) dz.bb.m6433c()).booleanValue() && this.zzHo && this.zzHh.zzHO.zzrk != -1) {
                Future future = (Future) new C1105d().zzhs();
            }
            if (bundle == null) {
                if (this.zzHh.zzHE != null && this.zzHv) {
                    this.zzHh.zzHE.zzbt();
                }
                if (!(this.zzHh.zzHL == 1 || this.zzHh.zzHD == null)) {
                    this.zzHh.zzHD.onAdClicked();
                }
            }
            this.zzHp = new C1103b(this.mActivity, this.zzHh.zzHN);
            this.zzHp.setId(Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS);
            switch (this.zzHh.zzHL) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    zzz(false);
                case Task.NETWORK_STATE_ANY /*2*/:
                    this.zzHi = new C1104c(this.zzHh.zzHF);
                    zzz(false);
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    zzz(true);
                case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    if (this.zzHn) {
                        this.zzHr = 3;
                        this.mActivity.finish();
                    } else if (!C1101o.m6038b().m6064a(this.mActivity, this.zzHh.zzHC, this.zzHh.zzHK)) {
                        this.zzHr = 3;
                        this.mActivity.finish();
                    }
                default:
                    throw new zza("Could not determine ad overlay type.");
            }
        } catch (zza e) {
            C1129c.m6192d(e.getMessage());
            this.zzHr = 3;
            this.mActivity.finish();
        }
    }

    public void onDestroy() {
        if (this.zzBb != null) {
            this.zzHp.removeView(this.zzBb.m7247b());
        }
        zzgs();
    }

    public void onPause() {
        this.zzHs.a();
        zzgo();
        if (this.zzHh.zzHE != null) {
            this.zzHh.zzHE.onPause();
        }
        if (this.zzBb != null && (!this.mActivity.isFinishing() || this.zzHi == null)) {
            C1101o.m6043g().m7159a(this.zzBb);
        }
        zzgs();
    }

    public void onRestart() {
    }

    public void onResume() {
        if (this.zzHh != null && this.zzHh.zzHL == 4) {
            if (this.zzHn) {
                this.zzHr = 3;
                this.mActivity.finish();
            } else {
                this.zzHn = true;
            }
        }
        if (this.zzHh.zzHE != null) {
            this.zzHh.zzHE.onResume();
        }
        if (this.zzBb == null || this.zzBb.m7268r()) {
            C1129c.m6192d("The webview does not exit. Ignoring action.");
        } else {
            C1101o.m6043g().m7163b(this.zzBb);
        }
        this.zzHs.b();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzHn);
    }

    public void onStart() {
    }

    public void onStop() {
        zzgs();
    }

    public void setRequestedOrientation(int i) {
        this.mActivity.setRequestedOrientation(i);
    }

    protected void zzD(int i) {
        this.zzBb.m7236a(i);
    }

    public void zza(View view, CustomViewCallback customViewCallback) {
        this.zzHl = new FrameLayout(this.mActivity);
        this.zzHl.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.zzHl.addView(view, -1, -1);
        this.mActivity.setContentView(this.zzHl);
        zzaW();
        this.zzHm = customViewCallback;
        this.zzHk = true;
    }

    public void zza(boolean z, boolean z2) {
        if (this.zzHj != null) {
            this.zzHj.zza(z, z2);
        }
    }

    public void zzaW() {
        this.zzHt = true;
    }

    public void zzf(no noVar, Map<String, String> map) {
        this.zzHs.a(noVar, map);
    }

    public void zzgo() {
        if (this.zzHh != null && this.zzHk) {
            setRequestedOrientation(this.zzHh.orientation);
        }
        if (this.zzHl != null) {
            this.mActivity.setContentView(this.zzHp);
            zzaW();
            this.zzHl.removeAllViews();
            this.zzHl = null;
        }
        if (this.zzHm != null) {
            this.zzHm.onCustomViewHidden();
            this.zzHm = null;
        }
        this.zzHk = false;
    }

    public void zzgp() {
        this.zzHr = 1;
        this.mActivity.finish();
    }

    public boolean zzgq() {
        boolean z = true;
        this.zzHr = 0;
        if (this.zzBb != null) {
            if (!(this.zzBb.m7270t() && this.zzHs.d())) {
                z = false;
            }
            if (!z) {
                this.zzBb.m7244a("onbackblocked", Collections.emptyMap());
            }
        }
        return z;
    }

    public void zzgr() {
        this.zzHp.removeView(this.zzHj);
        zzy(true);
    }

    protected void zzgs() {
        if (this.mActivity.isFinishing() && !this.zzHu) {
            this.zzHu = true;
            if (this.zzBb != null) {
                zzD(this.zzHr);
                this.zzHp.removeView(this.zzBb.m7247b());
                if (this.zzHi != null) {
                    this.zzBb.m7237a(this.zzHi.f4552d);
                    this.zzBb.m7246a(false);
                    this.zzHi.f4551c.addView(this.zzBb.m7247b(), this.zzHi.f4549a, this.zzHi.f4550b);
                    this.zzHi = null;
                } else if (this.mActivity.getApplicationContext() != null) {
                    this.zzBb.m7237a(this.mActivity.getApplicationContext());
                }
                this.zzBb = null;
            }
            if (!(this.zzHh == null || this.zzHh.zzHE == null)) {
                this.zzHh.zzHE.zzbs();
            }
            this.zzHs.c();
        }
    }

    public void zzgt() {
        if (this.zzHq) {
            this.zzHq = false;
            zzgu();
        }
    }

    protected void zzgu() {
        this.zzBb.m7254d();
    }

    public void zzgv() {
        this.zzHp.m6065a();
    }

    public void zzy(boolean z) {
        this.zzHj = new zzo(this.mActivity, z ? 50 : 32, this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        this.zzHj.zza(z, this.zzHh.zzHI);
        this.zzHp.addView(this.zzHj, layoutParams);
    }

    protected void zzz(boolean z) {
        if (!this.zzHt) {
            this.mActivity.requestWindowFeature(1);
        }
        Window window = this.mActivity.getWindow();
        if (window == null) {
            throw new zza("Invalid activity, no window available.");
        }
        if (!this.zzHo || (this.zzHh.zzHO != null && this.zzHh.zzHO.zzrg)) {
            window.setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        }
        boolean zzdi = this.zzHh.zzHF.m7262l().zzdi();
        this.zzHq = false;
        if (zzdi) {
            if (this.zzHh.orientation == C1101o.m6043g().m7146a()) {
                this.zzHq = this.mActivity.getResources().getConfiguration().orientation == 1;
            } else if (this.zzHh.orientation == C1101o.m6043g().m7160b()) {
                this.zzHq = this.mActivity.getResources().getConfiguration().orientation == 2;
            }
        }
        C1129c.m6185a("Delay onShow to next orientation change: " + this.zzHq);
        setRequestedOrientation(this.zzHh.orientation);
        if (C1101o.m6043g().m7158a(window)) {
            C1129c.m6185a("Hardware acceleration on the AdActivity window enabled.");
        }
        if (this.zzHo) {
            this.zzHp.setBackgroundColor(zzHg);
        } else {
            this.zzHp.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
        this.mActivity.setContentView(this.zzHp);
        zzaW();
        if (z) {
            this.zzBb = C1101o.m6042f().m7278a(this.mActivity, this.zzHh.zzHF.m7261k(), true, zzdi, null, this.zzHh.zzsx, null, null, this.zzHh.zzHF.m7258h());
            this.zzBb.m7262l().zza(null, null, this.zzHh.zzHG, this.zzHh.zzHK, true, this.zzHh.zzHM, null, this.zzHh.zzHF.m7262l().zzjR(), null, null);
            this.zzBb.m7262l().zza(new 1(this));
            if (this.zzHh.url != null) {
                this.zzBb.loadUrl(this.zzHh.url);
            } else if (this.zzHh.zzHJ != null) {
                this.zzBb.loadDataWithBaseURL(this.zzHh.zzHH, this.zzHh.zzHJ, "text/html", com.adjust.sdk.Constants.ENCODING, null);
            } else {
                throw new zza("No URL or HTML to display in ad overlay.");
            }
            if (this.zzHh.zzHF != null) {
                this.zzHh.zzHF.m7249b(this);
            }
        } else {
            this.zzBb = this.zzHh.zzHF;
            this.zzBb.m7237a(this.mActivity);
        }
        this.zzBb.m7240a(this);
        ViewParent parent = this.zzBb.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.zzBb.m7247b());
        }
        if (this.zzHo) {
            this.zzBb.setBackgroundColor(zzHg);
        }
        this.zzHp.addView(this.zzBb.m7247b(), -1, -1);
        if (!(z || this.zzHq)) {
            zzgu();
        }
        zzy(zzdi);
        if (this.zzBb.m7263m()) {
            zza(zzdi, true);
        }
        C1077a h = this.zzBb.m7258h();
        j jVar = h != null ? h.f4380c : null;
        if (jVar != null) {
            this.zzHs = jVar.a(this.mActivity, this.zzBb, this.zzHp);
        } else {
            C1129c.m6192d("Appstreaming controller is null.");
        }
    }
}
