package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@jw
public abstract class zzbv implements OnGlobalLayoutListener, OnScrollChangedListener {
    protected final Object zzpp;
    private boolean zzrF;
    private final fk zztA;
    private mr zztc;
    private final WeakReference<lb> zzti;
    private WeakReference<ViewTreeObserver> zztj;
    private final df zztk;
    protected final db zztl;
    private final Context zztm;
    private final WindowManager zztn;
    private final PowerManager zzto;
    private final KeyguardManager zztp;
    private dd zztq;
    private boolean zztr;
    private boolean zzts;
    private boolean zztt;
    private boolean zztu;
    private boolean zztv;
    BroadcastReceiver zztw;
    private final HashSet<da> zztx;
    private final fk zzty;
    private final fk zztz;

    public zzbv(Context context, AdSizeParcel adSizeParcel, lb lbVar, VersionInfoParcel versionInfoParcel, df dfVar) {
        this.zzpp = new Object();
        this.zzrF = false;
        this.zzts = false;
        this.zztx = new HashSet();
        this.zzty = new 2(this);
        this.zztz = new 3(this);
        this.zztA = new 4(this);
        this.zzti = new WeakReference(lbVar);
        this.zztk = dfVar;
        this.zztj = new WeakReference(null);
        this.zztt = true;
        this.zztv = false;
        this.zztc = new mr(200);
        this.zztl = new db(UUID.randomUUID().toString(), versionInfoParcel, adSizeParcel.zzvs, lbVar.f5335j, lbVar.m6989a(), adSizeParcel.zzvv);
        this.zztn = (WindowManager) context.getSystemService("window");
        this.zzto = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.zztp = (KeyguardManager) context.getSystemService("keyguard");
        this.zztm = context;
    }

    protected void destroy() {
        synchronized (this.zzpp) {
            zzcW();
            zzcR();
            this.zztt = false;
            zzcT();
        }
    }

    boolean isScreenOn() {
        return this.zzto.isScreenOn();
    }

    public void onGlobalLayout() {
        zzi(2);
    }

    public void onScrollChanged() {
        zzi(1);
    }

    public void pause() {
        synchronized (this.zzpp) {
            this.zzrF = true;
            zzi(3);
        }
    }

    public void resume() {
        synchronized (this.zzpp) {
            this.zzrF = false;
            zzi(3);
        }
    }

    public void stop() {
        synchronized (this.zzpp) {
            this.zzts = true;
            zzi(3);
        }
    }

    protected int zza(int i, DisplayMetrics displayMetrics) {
        return (int) (((float) i) / displayMetrics.density);
    }

    protected void zza(View view, Map<String, String> map) {
        zzi(3);
    }

    public void zza(da daVar) {
        this.zztx.add(daVar);
    }

    public void zza(dd ddVar) {
        synchronized (this.zzpp) {
            this.zztq = ddVar;
        }
    }

    protected void zza(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONArray.put(jSONObject);
            jSONObject2.put("units", jSONArray);
            zzb(jSONObject2);
        } catch (Throwable th) {
            C1129c.m6189b("Skipping active view message.", th);
        }
    }

    protected abstract void zzb(JSONObject jSONObject);

    protected boolean zzb(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = (String) map.get("hashCode");
        boolean z = !TextUtils.isEmpty(str) && str.equals(this.zztl.m6347d());
        return z;
    }

    protected void zzc(hd hdVar) {
        hdVar.a("/updateActiveView", this.zzty);
        hdVar.a("/untrackActiveViewUnit", this.zztz);
        hdVar.a("/visibilityChanged", this.zztA);
    }

    protected void zzcQ() {
        synchronized (this.zzpp) {
            if (this.zztw != null) {
                return;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.zztw = new 1(this);
            this.zztm.registerReceiver(this.zztw, intentFilter);
        }
    }

    protected void zzcR() {
        synchronized (this.zzpp) {
            if (this.zztw != null) {
                try {
                    this.zztm.unregisterReceiver(this.zztw);
                } catch (Throwable e) {
                    C1129c.m6189b("Failed trying to unregister the receiver", e);
                } catch (Throwable e2) {
                    C1101o.m6044h().m7021a(e2, true);
                }
                this.zztw = null;
            }
        }
    }

    public void zzcS() {
        synchronized (this.zzpp) {
            if (this.zztt) {
                this.zztu = true;
                try {
                    zza(zzda());
                } catch (Throwable e) {
                    C1129c.m6189b("JSON failure while processing active view data.", e);
                } catch (Throwable e2) {
                    C1129c.m6189b("Failure while processing active view data.", e2);
                }
                String str = "Untracking ad unit: ";
                String valueOf = String.valueOf(this.zztl.m6347d());
                C1129c.m6185a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
    }

    protected void zzcT() {
        if (this.zztq != null) {
            this.zztq.a(this);
        }
    }

    public boolean zzcU() {
        boolean z;
        synchronized (this.zzpp) {
            z = this.zztt;
        }
        return z;
    }

    protected void zzcV() {
        View a = this.zztk.c().a();
        if (a != null) {
            ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zztj.get();
            ViewTreeObserver viewTreeObserver2 = a.getViewTreeObserver();
            if (viewTreeObserver2 != viewTreeObserver) {
                zzcW();
                if (!this.zztr || (viewTreeObserver != null && viewTreeObserver.isAlive())) {
                    this.zztr = true;
                    viewTreeObserver2.addOnScrollChangedListener(this);
                    viewTreeObserver2.addOnGlobalLayoutListener(this);
                }
                this.zztj = new WeakReference(viewTreeObserver2);
            }
        }
    }

    protected void zzcW() {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zztj.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    protected JSONObject zzcX() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.zztl.m6345b()).put("activeViewJSON", this.zztl.m6346c()).put("timestamp", C1101o.m6045i().elapsedRealtime()).put("adFormat", this.zztl.m6344a()).put("hashCode", this.zztl.m6347d()).put("isMraid", this.zztl.m6348e()).put("isStopped", this.zzts).put("isPaused", this.zzrF).put("isScreenOn", isScreenOn()).put("isNative", this.zztl.m6349f());
        return jSONObject;
    }

    protected abstract boolean zzcY();

    protected JSONObject zzcZ() {
        return zzcX().put("isAttachedToWindow", false).put("isScreenOn", isScreenOn()).put("isVisible", false);
    }

    protected JSONObject zzd(View view) {
        if (view == null) {
            return zzcZ();
        }
        boolean a = C1101o.m6043g().m7157a(view);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            view.getLocationOnScreen(iArr);
            view.getLocationInWindow(iArr2);
        } catch (Throwable e) {
            C1129c.m6189b("Failure getting view location.", e);
        }
        DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        rect2.right = this.zztn.getDefaultDisplay().getWidth();
        rect2.bottom = this.zztn.getDefaultDisplay().getHeight();
        Rect rect3 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect3, null);
        Rect rect4 = new Rect();
        boolean localVisibleRect = view.getLocalVisibleRect(rect4);
        Rect rect5 = new Rect();
        view.getHitRect(rect5);
        JSONObject zzcX = zzcX();
        zzcX.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", a).put("viewBox", new JSONObject().put("top", zza(rect2.top, displayMetrics)).put("bottom", zza(rect2.bottom, displayMetrics)).put("left", zza(rect2.left, displayMetrics)).put("right", zza(rect2.right, displayMetrics))).put("adBox", new JSONObject().put("top", zza(rect.top, displayMetrics)).put("bottom", zza(rect.bottom, displayMetrics)).put("left", zza(rect.left, displayMetrics)).put("right", zza(rect.right, displayMetrics))).put("globalVisibleBox", new JSONObject().put("top", zza(rect3.top, displayMetrics)).put("bottom", zza(rect3.bottom, displayMetrics)).put("left", zza(rect3.left, displayMetrics)).put("right", zza(rect3.right, displayMetrics))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put("top", zza(rect4.top, displayMetrics)).put("bottom", zza(rect4.bottom, displayMetrics)).put("left", zza(rect4.left, displayMetrics)).put("right", zza(rect4.right, displayMetrics))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put("top", zza(rect5.top, displayMetrics)).put("bottom", zza(rect5.bottom, displayMetrics)).put("left", zza(rect5.left, displayMetrics)).put("right", zza(rect5.right, displayMetrics))).put("screenDensity", (double) displayMetrics.density).put("isVisible", C1101o.m6041e().m7115a(view, this.zzto, this.zztp));
        return zzcX;
    }

    protected void zzd(hd hdVar) {
        hdVar.b("/visibilityChanged", this.zztA);
        hdVar.b("/untrackActiveViewUnit", this.zztz);
        hdVar.b("/updateActiveView", this.zzty);
    }

    protected JSONObject zzda() {
        JSONObject zzcX = zzcX();
        zzcX.put("doneReasonCode", "u");
        return zzcX;
    }

    protected void zzi(int i) {
        Object obj = null;
        synchronized (this.zzpp) {
            if (zzcY() && this.zztt) {
                View a = this.zztk.a();
                boolean z = a != null && C1101o.m6041e().m7115a(a, this.zzto, this.zztp) && a.getGlobalVisibleRect(new Rect(), null);
                this.zztv = z;
                if (this.zztk.b()) {
                    zzcS();
                    return;
                }
                if (i == 1) {
                    obj = 1;
                }
                if (obj != null) {
                    if (!this.zztc.m7188a() && z == this.zztv) {
                        return;
                    }
                }
                if (z || this.zztv || i != 1) {
                    try {
                        zza(zzd(a));
                    } catch (JSONException e) {
                        Throwable e2 = e;
                        C1129c.m6186a("Active view update failed.", e2);
                        zzcV();
                        zzcT();
                        return;
                    } catch (RuntimeException e3) {
                        e2 = e3;
                        C1129c.m6186a("Active view update failed.", e2);
                        zzcV();
                        zzcT();
                        return;
                    }
                    zzcV();
                    zzcT();
                    return;
                }
                return;
            }
        }
    }

    protected void zzi(boolean z) {
        Iterator it = this.zztx.iterator();
        while (it.hasNext()) {
            ((da) it.next()).a(this, z);
        }
    }
}
