package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.view.TextureView;
import com.etsy.android.lib.models.finds.FindsModule;
import com.foresee.mobileReplay.recorder.ScreenRecorder;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.ed;
import com.google.android.gms.internal.eg;
import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.mh;
import com.google.android.gms.internal.mi;
import com.google.android.gms.internal.mj;
import java.util.concurrent.TimeUnit;

@jw
/* renamed from: com.google.android.gms.ads.internal.overlay.t */
public class C1110t {
    private final Context f4562a;
    private final String f4563b;
    private final VersionInfoParcel f4564c;
    @Nullable
    private final eg f4565d;
    @Nullable
    private final ei f4566e;
    private final mh f4567f;
    private final long[] f4568g;
    private final String[] f4569h;
    @Nullable
    private eg f4570i;
    @Nullable
    private eg f4571j;
    @Nullable
    private eg f4572k;
    @Nullable
    private eg f4573l;
    private boolean f4574m;
    private zzi f4575n;
    private boolean f4576o;
    private boolean f4577p;
    private long f4578q;

    public C1110t(Context context, VersionInfoParcel versionInfoParcel, String str, @Nullable ei eiVar, @Nullable eg egVar) {
        this.f4567f = new mj().a("min_1", Double.MIN_VALUE, 1.0d).a("1_5", 1.0d, 5.0d).a("5_10", 5.0d, ScreenRecorder.ORIENTATION_SAMPLING_THRESHOLD).a("10_20", ScreenRecorder.ORIENTATION_SAMPLING_THRESHOLD, 20.0d).a("20_30", 20.0d, 30.0d).a("30_max", 30.0d, Double.MAX_VALUE).a();
        this.f4578q = -1;
        this.f4562a = context;
        this.f4564c = versionInfoParcel;
        this.f4563b = str;
        this.f4566e = eiVar;
        this.f4565d = egVar;
        String str2 = (String) dz.f4849w.m6433c();
        if (str2 == null) {
            this.f4569h = new String[0];
            this.f4568g = new long[0];
            return;
        }
        String[] split = TextUtils.split(str2, ",");
        this.f4569h = new String[split.length];
        this.f4568g = new long[split.length];
        for (int i = 0; i < split.length; i++) {
            try {
                this.f4568g[i] = Long.parseLong(split[i]);
            } catch (Throwable e) {
                C1129c.m6193d("Unable to parse frame hash target time number.", e);
                this.f4568g[i] = -1;
            }
        }
    }

    private void m6079c(zzi com_google_android_gms_ads_internal_overlay_zzi) {
        long longValue = ((Long) dz.f4850x.m6433c()).longValue();
        long currentPosition = (long) com_google_android_gms_ads_internal_overlay_zzi.getCurrentPosition();
        int i = 0;
        while (i < this.f4569h.length) {
            if (this.f4569h[i] == null && longValue > Math.abs(currentPosition - this.f4568g[i])) {
                this.f4569h[i] = m6081a((TextureView) com_google_android_gms_ads_internal_overlay_zzi);
                return;
            }
            i++;
        }
    }

    private void m6080e() {
        if (this.f4572k != null && this.f4573l == null) {
            ed.m6464a(this.f4566e, this.f4572k, "vff");
            ed.m6464a(this.f4566e, this.f4565d, "vtt");
            this.f4573l = ed.m6461a(this.f4566e);
        }
        long nanoTime = C1101o.m6045i().nanoTime();
        if (this.f4574m && this.f4577p && this.f4578q != -1) {
            this.f4567f.m7176a(((double) TimeUnit.SECONDS.toNanos(1)) / ((double) (nanoTime - this.f4578q)));
        }
        this.f4577p = this.f4574m;
        this.f4578q = nanoTime;
    }

    String m6081a(TextureView textureView) {
        Bitmap bitmap = textureView.getBitmap(8, 8);
        long j = 0;
        long j2 = 63;
        int i = 0;
        while (i < 8) {
            long j3 = j;
            j = j2;
            for (int i2 = 0; i2 < 8; i2++) {
                int pixel = bitmap.getPixel(i2, i);
                j3 |= (Color.green(pixel) + (Color.blue(pixel) + Color.red(pixel)) > AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS ? 1 : 0) << ((int) j);
                j--;
            }
            i++;
            j2 = j;
            j = j3;
        }
        return String.format("%016X", new Object[]{Long.valueOf(j)});
    }

    public void m6082a() {
        if (this.f4570i != null && this.f4571j == null) {
            ed.m6464a(this.f4566e, this.f4570i, "vfr");
            this.f4571j = ed.m6461a(this.f4566e);
        }
    }

    public void m6083a(zzi com_google_android_gms_ads_internal_overlay_zzi) {
        ed.m6464a(this.f4566e, this.f4565d, "vpc");
        this.f4570i = ed.m6461a(this.f4566e);
        if (this.f4566e != null) {
            this.f4566e.m6477a("vpn", com_google_android_gms_ads_internal_overlay_zzi.zzgc());
        }
        this.f4575n = com_google_android_gms_ads_internal_overlay_zzi;
    }

    public void m6084b() {
        if (((Boolean) dz.f4848v.m6433c()).booleanValue() && !this.f4576o) {
            String valueOf;
            String valueOf2;
            Bundle bundle = new Bundle();
            bundle.putString(FindsModule.FIELD_TYPE, "native-player-metrics");
            bundle.putString("request", this.f4563b);
            bundle.putString("player", this.f4575n.zzgc());
            for (mi miVar : this.f4567f.m7175a()) {
                valueOf = String.valueOf("fps_c_");
                valueOf2 = String.valueOf(miVar.a);
                bundle.putString(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), Integer.toString(miVar.e));
                valueOf = String.valueOf("fps_p_");
                valueOf2 = String.valueOf(miVar.a);
                bundle.putString(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), Double.toString(miVar.d));
            }
            for (int i = 0; i < this.f4568g.length; i++) {
                valueOf2 = this.f4569h[i];
                if (valueOf2 != null) {
                    String valueOf3 = String.valueOf("fh_");
                    valueOf = String.valueOf(Long.valueOf(this.f4568g[i]));
                    bundle.putString(new StringBuilder((String.valueOf(valueOf3).length() + 0) + String.valueOf(valueOf).length()).append(valueOf3).append(valueOf).toString(), valueOf2);
                }
            }
            C1101o.m6041e().m7104a(this.f4562a, this.f4564c.afmaVersion, "gmob-apps", bundle, true);
            this.f4576o = true;
        }
    }

    public void m6085b(zzi com_google_android_gms_ads_internal_overlay_zzi) {
        m6080e();
        m6079c(com_google_android_gms_ads_internal_overlay_zzi);
    }

    public void m6086c() {
        this.f4574m = true;
        if (this.f4571j != null && this.f4572k == null) {
            ed.m6464a(this.f4566e, this.f4571j, "vfp");
            this.f4572k = ed.m6461a(this.f4566e);
        }
    }

    public void m6087d() {
        this.f4574m = false;
    }
}
