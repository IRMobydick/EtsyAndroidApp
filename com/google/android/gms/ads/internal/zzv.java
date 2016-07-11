package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.purchase.C1115e;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.bu;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lb;
import com.google.android.gms.internal.lc;
import com.google.android.gms.internal.ld;
import com.google.android.gms.internal.ll;
import com.google.android.gms.internal.ln;
import com.google.android.gms.internal.me;
import com.google.android.gms.internal.mr;
import com.google.android.gms.internal.zzdg;
import com.google.android.gms.internal.zzdt;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.internal.zzdv;
import com.google.android.gms.internal.zzdw;
import com.google.android.gms.internal.zzhh;
import com.google.android.gms.internal.zzhl;
import com.google.android.gms.internal.zzlb;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

@jw
public final class zzv implements OnGlobalLayoutListener, OnScrollChangedListener {
    public final Context zzov;
    boolean zzrf;
    @Nullable
    public me zzsA;
    public AdSizeParcel zzsB;
    public lb zzsC;
    public lc zzsD;
    public ld zzsE;
    zzp zzsF;
    zzq zzsG;
    zzw zzsH;
    zzy zzsI;
    zzhh zzsJ;
    zzhl zzsK;
    zzdt zzsL;
    zzdu zzsM;
    SimpleArrayMap<String, zzdv> zzsN;
    SimpleArrayMap<String, zzdw> zzsO;
    NativeAdOptionsParcel zzsP;
    @Nullable
    VideoOptionsParcel zzsQ;
    @Nullable
    zzdg zzsR;
    @Nullable
    zzd zzsS;
    List<String> zzsT;
    C1115e zzsU;
    public ll zzsV;
    View zzsW;
    public int zzsX;
    boolean zzsY;
    private HashSet<ld> zzsZ;
    final String zzsu;
    public String zzsv;
    final bu zzsw;
    public final VersionInfoParcel zzsx;
    zza zzsy;
    @Nullable
    public ln zzsz;
    private int zzta;
    private int zztb;
    private mr zztc;
    private boolean zztd;
    private boolean zzte;
    private boolean zztf;

    public zzv(Context context, AdSizeParcel adSizeParcel, String str, VersionInfoParcel versionInfoParcel) {
        this(context, adSizeParcel, str, versionInfoParcel, null);
    }

    zzv(Context context, AdSizeParcel adSizeParcel, String str, VersionInfoParcel versionInfoParcel, bu buVar) {
        this.zzsV = null;
        this.zzsW = null;
        this.zzsX = 0;
        this.zzsY = false;
        this.zzrf = false;
        this.zzsZ = null;
        this.zzta = -1;
        this.zztb = -1;
        this.zztd = true;
        this.zzte = true;
        this.zztf = false;
        dz.m6444a(context);
        if (C1101o.m6044h().m7030e() != null) {
            List b = dz.m6445b();
            if (versionInfoParcel.zzRC != 0) {
                b.add(Integer.toString(versionInfoParcel.zzRC));
            }
            C1101o.m6044h().m7030e().m6458a(b);
        }
        this.zzsu = UUID.randomUUID().toString();
        if (adSizeParcel.zzvt || adSizeParcel.zzvv) {
            this.zzsy = null;
        } else {
            this.zzsy = new zza(context, this, this);
            this.zzsy.setMinimumWidth(adSizeParcel.widthPixels);
            this.zzsy.setMinimumHeight(adSizeParcel.heightPixels);
            this.zzsy.setVisibility(4);
        }
        this.zzsB = adSizeParcel;
        this.zzsv = str;
        this.zzov = context;
        this.zzsx = versionInfoParcel;
        if (buVar == null) {
            buVar = new bu(new C1092f(this));
        }
        this.zzsw = buVar;
        this.zztc = new mr(200);
        this.zzsO = new SimpleArrayMap();
    }

    private void zzcM() {
        View findViewById = this.zzsy.getRootView().findViewById(16908290);
        if (findViewById != null) {
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            this.zzsy.getGlobalVisibleRect(rect);
            findViewById.getGlobalVisibleRect(rect2);
            if (rect.top != rect2.top) {
                this.zztd = false;
            }
            if (rect.bottom != rect2.bottom) {
                this.zzte = false;
            }
        }
    }

    private void zzg(boolean z) {
        boolean z2 = true;
        if (this.zzsy != null && this.zzsC != null && this.zzsC.f5327b != null) {
            if (!z || this.zztc.m7188a()) {
                if (this.zzsC.f5327b.m7262l().zzdi()) {
                    int[] iArr = new int[2];
                    this.zzsy.getLocationOnScreen(iArr);
                    int b = C1089r.m5951a().m6178b(this.zzov, iArr[0]);
                    int b2 = C1089r.m5951a().m6178b(this.zzov, iArr[1]);
                    if (!(b == this.zzta && b2 == this.zztb)) {
                        this.zzta = b;
                        this.zztb = b2;
                        zzlb l = this.zzsC.f5327b.m7262l();
                        b = this.zzta;
                        int i = this.zztb;
                        if (z) {
                            z2 = false;
                        }
                        l.zza(b, i, z2);
                    }
                }
                zzcM();
            }
        }
    }

    public void destroy() {
        zzcL();
        this.zzsG = null;
        this.zzsH = null;
        this.zzsK = null;
        this.zzsJ = null;
        this.zzsR = null;
        this.zzsI = null;
        zzh(false);
        if (this.zzsy != null) {
            this.zzsy.removeAllViews();
        }
        zzcG();
        zzcI();
        this.zzsC = null;
    }

    public void onGlobalLayout() {
        zzg(false);
    }

    public void onScrollChanged() {
        zzg(true);
        this.zztf = true;
    }

    public void zza(HashSet<ld> hashSet) {
        this.zzsZ = hashSet;
    }

    public HashSet<ld> zzcF() {
        return this.zzsZ;
    }

    public void zzcG() {
        if (this.zzsC != null && this.zzsC.f5327b != null) {
            this.zzsC.f5327b.destroy();
        }
    }

    public void zzcH() {
        if (this.zzsC != null && this.zzsC.f5327b != null) {
            this.zzsC.f5327b.stopLoading();
        }
    }

    public void zzcI() {
        if (this.zzsC != null && this.zzsC.f5341p != null) {
            try {
                this.zzsC.f5341p.destroy();
            } catch (RemoteException e) {
                C1129c.m6192d("Could not destroy mediation adapter.");
            }
        }
    }

    public boolean zzcJ() {
        return this.zzsX == 0;
    }

    public boolean zzcK() {
        return this.zzsX == 1;
    }

    public void zzcL() {
        if (this.zzsy != null) {
            this.zzsy.zzcL();
        }
    }

    public String zzcN() {
        return (this.zztd && this.zzte) ? StringUtils.EMPTY : this.zztd ? this.zztf ? "top-scrollable" : "top-locked" : this.zzte ? this.zztf ? "bottom-scrollable" : "bottom-locked" : StringUtils.EMPTY;
    }

    public void zzcO() {
        this.zzsE.m6991a(this.zzsC.f5319A);
        this.zzsE.m6995b(this.zzsC.f5320B);
        this.zzsE.m6993a(this.zzsB.zzvt);
        this.zzsE.m6996b(this.zzsC.f5339n);
    }

    public void zzh(boolean z) {
        if (this.zzsX == 0) {
            zzcH();
        }
        if (this.zzsz != null) {
            this.zzsz.cancel();
        }
        if (this.zzsA != null) {
            this.zzsA.cancel();
        }
        if (z) {
            this.zzsC = null;
        }
    }
}
