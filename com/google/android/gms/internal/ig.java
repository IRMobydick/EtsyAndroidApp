package com.google.android.gms.internal;

import android.app.Activity;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.gcm.Task;
import java.util.Map;
import java.util.Set;

@jw
public class ig extends im {
    static final Set<String> f5101a;
    private String f5102b;
    private boolean f5103c;
    private int f5104d;
    private int f5105e;
    private int f5106f;
    private int f5107g;
    private int f5108h;
    private int f5109i;
    private final Object f5110j;
    private final no f5111k;
    private final Activity f5112l;
    private AdSizeParcel f5113m;
    private ImageView f5114n;
    private LinearLayout f5115o;
    private in f5116p;
    private PopupWindow f5117q;
    private RelativeLayout f5118r;
    private ViewGroup f5119s;

    static {
        f5101a = zzf.zzc(new String[]{"top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center"});
    }

    public ig(no noVar, in inVar) {
        super(noVar, "resize");
        this.f5102b = "top-right";
        this.f5103c = true;
        this.f5104d = 0;
        this.f5105e = 0;
        this.f5106f = -1;
        this.f5107g = 0;
        this.f5108h = 0;
        this.f5109i = -1;
        this.f5110j = new Object();
        this.f5111k = noVar;
        this.f5112l = noVar.m7256f();
        this.f5116p = inVar;
    }

    private void m6767b(Map<String, String> map) {
        if (!TextUtils.isEmpty((CharSequence) map.get(ResponseConstants.WIDTH))) {
            this.f5109i = C1101o.m6041e().m7119b((String) map.get(ResponseConstants.WIDTH));
        }
        if (!TextUtils.isEmpty((CharSequence) map.get(ResponseConstants.HEIGHT))) {
            this.f5106f = C1101o.m6041e().m7119b((String) map.get(ResponseConstants.HEIGHT));
        }
        if (!TextUtils.isEmpty((CharSequence) map.get("offsetX"))) {
            this.f5107g = C1101o.m6041e().m7119b((String) map.get("offsetX"));
        }
        if (!TextUtils.isEmpty((CharSequence) map.get("offsetY"))) {
            this.f5108h = C1101o.m6041e().m7119b((String) map.get("offsetY"));
        }
        if (!TextUtils.isEmpty((CharSequence) map.get("allowOffscreen"))) {
            this.f5103c = Boolean.parseBoolean((String) map.get("allowOffscreen"));
        }
        String str = (String) map.get("customClosePosition");
        if (!TextUtils.isEmpty(str)) {
            this.f5102b = str;
        }
    }

    private int[] m6768d() {
        if (!m6777c()) {
            return null;
        }
        if (this.f5103c) {
            return new int[]{this.f5104d + this.f5107g, this.f5105e + this.f5108h};
        }
        int[] b = C1101o.m6041e().m7124b(this.f5112l);
        int[] d = C1101o.m6041e().m7131d(this.f5112l);
        int i = b[0];
        int i2 = this.f5104d + this.f5107g;
        int i3 = this.f5105e + this.f5108h;
        if (i2 < 0) {
            i2 = 0;
        } else if (this.f5109i + i2 > i) {
            i2 = i - this.f5109i;
        }
        if (i3 < d[0]) {
            i3 = d[0];
        } else if (this.f5106f + i3 > d[1]) {
            i3 = d[1] - this.f5106f;
        }
        return new int[]{i2, i3};
    }

    void m6769a(int i, int i2) {
        if (this.f5116p != null) {
            this.f5116p.zza(i, i2, this.f5109i, this.f5106f);
        }
    }

    public void m6770a(int i, int i2, boolean z) {
        synchronized (this.f5110j) {
            this.f5104d = i;
            this.f5105e = i2;
            if (this.f5117q != null && z) {
                int[] d = m6768d();
                if (d != null) {
                    this.f5117q.update(C1089r.m5951a().m6166a(this.f5112l, d[0]), C1089r.m5951a().m6166a(this.f5112l, d[1]), this.f5117q.getWidth(), this.f5117q.getHeight());
                    m6774b(d[0], d[1]);
                } else {
                    m6772a(true);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m6771a(java.util.Map<java.lang.String, java.lang.String> r13) {
        /*
        r12 = this;
        r4 = -1;
        r5 = 1;
        r3 = 0;
        r6 = r12.f5110j;
        monitor-enter(r6);
        r1 = r12.f5112l;	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0011;
    L_0x000a:
        r1 = "Not an activity context. Cannot resize.";
        r12.m6758b(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
    L_0x0010:
        return;
    L_0x0011:
        r1 = r12.f5111k;	 Catch:{ all -> 0x0020 }
        r1 = r1.m7261k();	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0023;
    L_0x0019:
        r1 = "Webview is not yet available, size is not set.";
        r12.m6758b(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0020:
        r1 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        throw r1;
    L_0x0023:
        r1 = r12.f5111k;	 Catch:{ all -> 0x0020 }
        r1 = r1.m7261k();	 Catch:{ all -> 0x0020 }
        r1 = r1.zzvt;	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0034;
    L_0x002d:
        r1 = "Is interstitial. Cannot resize an interstitial.";
        r12.m6758b(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0034:
        r1 = r12.f5111k;	 Catch:{ all -> 0x0020 }
        r1 = r1.m7266p();	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0043;
    L_0x003c:
        r1 = "Cannot resize an expanded banner.";
        r12.m6758b(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0043:
        r12.m6767b(r13);	 Catch:{ all -> 0x0020 }
        r1 = r12.m6773a();	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0053;
    L_0x004c:
        r1 = "Invalid width and height options. Cannot resize.";
        r12.m6758b(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0053:
        r1 = r12.f5112l;	 Catch:{ all -> 0x0020 }
        r7 = r1.getWindow();	 Catch:{ all -> 0x0020 }
        if (r7 == 0) goto L_0x0061;
    L_0x005b:
        r1 = r7.getDecorView();	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0068;
    L_0x0061:
        r1 = "Activity context is not ready, cannot get window or decor view.";
        r12.m6758b(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0068:
        r8 = r12.m6768d();	 Catch:{ all -> 0x0020 }
        if (r8 != 0) goto L_0x0075;
    L_0x006e:
        r1 = "Resize location out of screen or close button is not visible.";
        r12.m6758b(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0075:
        r1 = com.google.android.gms.ads.internal.client.C1089r.m5951a();	 Catch:{ all -> 0x0020 }
        r2 = r12.f5112l;	 Catch:{ all -> 0x0020 }
        r9 = r12.f5109i;	 Catch:{ all -> 0x0020 }
        r9 = r1.m6166a(r2, r9);	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.ads.internal.client.C1089r.m5951a();	 Catch:{ all -> 0x0020 }
        r2 = r12.f5112l;	 Catch:{ all -> 0x0020 }
        r10 = r12.f5106f;	 Catch:{ all -> 0x0020 }
        r10 = r1.m6166a(r2, r10);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5111k;	 Catch:{ all -> 0x0020 }
        r1 = r1.m7247b();	 Catch:{ all -> 0x0020 }
        r2 = r1.getParent();	 Catch:{ all -> 0x0020 }
        if (r2 == 0) goto L_0x01d5;
    L_0x0099:
        r1 = r2 instanceof android.view.ViewGroup;	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x01d5;
    L_0x009d:
        r0 = r2;
        r0 = (android.view.ViewGroup) r0;	 Catch:{ all -> 0x0020 }
        r1 = r0;
        r11 = r12.f5111k;	 Catch:{ all -> 0x0020 }
        r11 = r11.m7247b();	 Catch:{ all -> 0x0020 }
        r1.removeView(r11);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5117q;	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x01ce;
    L_0x00ae:
        r2 = (android.view.ViewGroup) r2;	 Catch:{ all -> 0x0020 }
        r12.f5119s = r2;	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.ads.internal.C1101o.m6041e();	 Catch:{ all -> 0x0020 }
        r2 = r12.f5111k;	 Catch:{ all -> 0x0020 }
        r2 = r2.m7247b();	 Catch:{ all -> 0x0020 }
        r1 = r1.m7086a(r2);	 Catch:{ all -> 0x0020 }
        r2 = new android.widget.ImageView;	 Catch:{ all -> 0x0020 }
        r11 = r12.f5112l;	 Catch:{ all -> 0x0020 }
        r2.<init>(r11);	 Catch:{ all -> 0x0020 }
        r12.f5114n = r2;	 Catch:{ all -> 0x0020 }
        r2 = r12.f5114n;	 Catch:{ all -> 0x0020 }
        r2.setImageBitmap(r1);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5111k;	 Catch:{ all -> 0x0020 }
        r1 = r1.m7261k();	 Catch:{ all -> 0x0020 }
        r12.f5113m = r1;	 Catch:{ all -> 0x0020 }
        r1 = r12.f5119s;	 Catch:{ all -> 0x0020 }
        r2 = r12.f5114n;	 Catch:{ all -> 0x0020 }
        r1.addView(r2);	 Catch:{ all -> 0x0020 }
    L_0x00dd:
        r1 = new android.widget.RelativeLayout;	 Catch:{ all -> 0x0020 }
        r2 = r12.f5112l;	 Catch:{ all -> 0x0020 }
        r1.<init>(r2);	 Catch:{ all -> 0x0020 }
        r12.f5118r = r1;	 Catch:{ all -> 0x0020 }
        r1 = r12.f5118r;	 Catch:{ all -> 0x0020 }
        r2 = 0;
        r1.setBackgroundColor(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5118r;	 Catch:{ all -> 0x0020 }
        r2 = new android.view.ViewGroup$LayoutParams;	 Catch:{ all -> 0x0020 }
        r2.<init>(r9, r10);	 Catch:{ all -> 0x0020 }
        r1.setLayoutParams(r2);	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.ads.internal.C1101o.m6041e();	 Catch:{ all -> 0x0020 }
        r2 = r12.f5118r;	 Catch:{ all -> 0x0020 }
        r11 = 0;
        r1 = r1.m7088a(r2, r9, r10, r11);	 Catch:{ all -> 0x0020 }
        r12.f5117q = r1;	 Catch:{ all -> 0x0020 }
        r1 = r12.f5117q;	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r1.setOutsideTouchable(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5117q;	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r1.setTouchable(r2);	 Catch:{ all -> 0x0020 }
        r2 = r12.f5117q;	 Catch:{ all -> 0x0020 }
        r1 = r12.f5103c;	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x01dd;
    L_0x0115:
        r1 = r5;
    L_0x0116:
        r2.setClippingEnabled(r1);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5118r;	 Catch:{ all -> 0x0020 }
        r2 = r12.f5111k;	 Catch:{ all -> 0x0020 }
        r2 = r2.m7247b();	 Catch:{ all -> 0x0020 }
        r9 = -1;
        r10 = -1;
        r1.addView(r2, r9, r10);	 Catch:{ all -> 0x0020 }
        r1 = new android.widget.LinearLayout;	 Catch:{ all -> 0x0020 }
        r2 = r12.f5112l;	 Catch:{ all -> 0x0020 }
        r1.<init>(r2);	 Catch:{ all -> 0x0020 }
        r12.f5115o = r1;	 Catch:{ all -> 0x0020 }
        r2 = new android.widget.RelativeLayout$LayoutParams;	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.ads.internal.client.C1089r.m5951a();	 Catch:{ all -> 0x0020 }
        r9 = r12.f5112l;	 Catch:{ all -> 0x0020 }
        r10 = 50;
        r1 = r1.m6166a(r9, r10);	 Catch:{ all -> 0x0020 }
        r9 = com.google.android.gms.ads.internal.client.C1089r.m5951a();	 Catch:{ all -> 0x0020 }
        r10 = r12.f5112l;	 Catch:{ all -> 0x0020 }
        r11 = 50;
        r9 = r9.m6166a(r10, r11);	 Catch:{ all -> 0x0020 }
        r2.<init>(r1, r9);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5102b;	 Catch:{ all -> 0x0020 }
        r9 = r1.hashCode();	 Catch:{ all -> 0x0020 }
        switch(r9) {
            case -1364013995: goto L_0x01f6;
            case -1012429441: goto L_0x01e0;
            case -655373719: goto L_0x0201;
            case 1163912186: goto L_0x0217;
            case 1288627767: goto L_0x020c;
            case 1755462605: goto L_0x01eb;
            default: goto L_0x0155;
        };	 Catch:{ all -> 0x0020 }
    L_0x0155:
        r1 = r4;
    L_0x0156:
        switch(r1) {
            case 0: goto L_0x0222;
            case 1: goto L_0x022e;
            case 2: goto L_0x023a;
            case 3: goto L_0x0241;
            case 4: goto L_0x024d;
            case 5: goto L_0x0259;
            default: goto L_0x0159;
        };	 Catch:{ all -> 0x0020 }
    L_0x0159:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 11;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
    L_0x0163:
        r1 = r12.f5115o;	 Catch:{ all -> 0x0020 }
        r3 = new com.google.android.gms.internal.ig$1;	 Catch:{ all -> 0x0020 }
        r3.<init>(r12);	 Catch:{ all -> 0x0020 }
        r1.setOnClickListener(r3);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5115o;	 Catch:{ all -> 0x0020 }
        r3 = "Close button";
        r1.setContentDescription(r3);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5118r;	 Catch:{ all -> 0x0020 }
        r3 = r12.f5115o;	 Catch:{ all -> 0x0020 }
        r1.addView(r3, r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5117q;	 Catch:{ RuntimeException -> 0x0265 }
        r2 = r7.getDecorView();	 Catch:{ RuntimeException -> 0x0265 }
        r3 = 0;
        r4 = com.google.android.gms.ads.internal.client.C1089r.m5951a();	 Catch:{ RuntimeException -> 0x0265 }
        r5 = r12.f5112l;	 Catch:{ RuntimeException -> 0x0265 }
        r7 = 0;
        r7 = r8[r7];	 Catch:{ RuntimeException -> 0x0265 }
        r4 = r4.m6166a(r5, r7);	 Catch:{ RuntimeException -> 0x0265 }
        r5 = com.google.android.gms.ads.internal.client.C1089r.m5951a();	 Catch:{ RuntimeException -> 0x0265 }
        r7 = r12.f5112l;	 Catch:{ RuntimeException -> 0x0265 }
        r9 = 1;
        r9 = r8[r9];	 Catch:{ RuntimeException -> 0x0265 }
        r5 = r5.m6166a(r7, r9);	 Catch:{ RuntimeException -> 0x0265 }
        r1.showAtLocation(r2, r3, r4, r5);	 Catch:{ RuntimeException -> 0x0265 }
        r1 = 0;
        r1 = r8[r1];	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r2 = r8[r2];	 Catch:{ all -> 0x0020 }
        r12.m6769a(r1, r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5111k;	 Catch:{ all -> 0x0020 }
        r2 = new com.google.android.gms.ads.internal.client.AdSizeParcel;	 Catch:{ all -> 0x0020 }
        r3 = r12.f5112l;	 Catch:{ all -> 0x0020 }
        r4 = new com.google.android.gms.ads.f;	 Catch:{ all -> 0x0020 }
        r5 = r12.f5109i;	 Catch:{ all -> 0x0020 }
        r7 = r12.f5106f;	 Catch:{ all -> 0x0020 }
        r4.<init>(r5, r7);	 Catch:{ all -> 0x0020 }
        r2.<init>(r3, r4);	 Catch:{ all -> 0x0020 }
        r1.m7239a(r2);	 Catch:{ all -> 0x0020 }
        r1 = 0;
        r1 = r8[r1];	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r2 = r8[r2];	 Catch:{ all -> 0x0020 }
        r12.m6774b(r1, r2);	 Catch:{ all -> 0x0020 }
        r1 = "resized";
        r12.m6760d(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x01ce:
        r1 = r12.f5117q;	 Catch:{ all -> 0x0020 }
        r1.dismiss();	 Catch:{ all -> 0x0020 }
        goto L_0x00dd;
    L_0x01d5:
        r1 = "Webview is detached, probably in the middle of a resize or expand.";
        r12.m6758b(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x01dd:
        r1 = r3;
        goto L_0x0116;
    L_0x01e0:
        r5 = "top-left";
        r1 = r1.equals(r5);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x01e8:
        r1 = r3;
        goto L_0x0156;
    L_0x01eb:
        r3 = "top-center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x01f3:
        r1 = r5;
        goto L_0x0156;
    L_0x01f6:
        r3 = "center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x01fe:
        r1 = 2;
        goto L_0x0156;
    L_0x0201:
        r3 = "bottom-left";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x0209:
        r1 = 3;
        goto L_0x0156;
    L_0x020c:
        r3 = "bottom-center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x0214:
        r1 = 4;
        goto L_0x0156;
    L_0x0217:
        r3 = "bottom-right";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x021f:
        r1 = 5;
        goto L_0x0156;
    L_0x0222:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 9;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x022e:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 14;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x023a:
        r1 = 13;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x0241:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 9;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x024d:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 14;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x0259:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 11;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x0265:
        r1 = move-exception;
        r2 = "Cannot show popup window: ";
        r1 = r1.getMessage();	 Catch:{ all -> 0x0020 }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ all -> 0x0020 }
        r3 = r1.length();	 Catch:{ all -> 0x0020 }
        if (r3 == 0) goto L_0x02a8;
    L_0x0276:
        r1 = r2.concat(r1);	 Catch:{ all -> 0x0020 }
    L_0x027a:
        r12.m6758b(r1);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5118r;	 Catch:{ all -> 0x0020 }
        r2 = r12.f5111k;	 Catch:{ all -> 0x0020 }
        r2 = r2.m7247b();	 Catch:{ all -> 0x0020 }
        r1.removeView(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5119s;	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x02a5;
    L_0x028c:
        r1 = r12.f5119s;	 Catch:{ all -> 0x0020 }
        r2 = r12.f5114n;	 Catch:{ all -> 0x0020 }
        r1.removeView(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5119s;	 Catch:{ all -> 0x0020 }
        r2 = r12.f5111k;	 Catch:{ all -> 0x0020 }
        r2 = r2.m7247b();	 Catch:{ all -> 0x0020 }
        r1.addView(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.f5111k;	 Catch:{ all -> 0x0020 }
        r2 = r12.f5113m;	 Catch:{ all -> 0x0020 }
        r1.m7239a(r2);	 Catch:{ all -> 0x0020 }
    L_0x02a5:
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x02a8:
        r1 = new java.lang.String;	 Catch:{ all -> 0x0020 }
        r1.<init>(r2);	 Catch:{ all -> 0x0020 }
        goto L_0x027a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ig.a(java.util.Map):void");
    }

    public void m6772a(boolean z) {
        synchronized (this.f5110j) {
            if (this.f5117q != null) {
                this.f5117q.dismiss();
                this.f5118r.removeView(this.f5111k.m7247b());
                if (this.f5119s != null) {
                    this.f5119s.removeView(this.f5114n);
                    this.f5119s.addView(this.f5111k.m7247b());
                    this.f5111k.m7239a(this.f5113m);
                }
                if (z) {
                    m6760d("default");
                    if (this.f5116p != null) {
                        this.f5116p.zzbE();
                    }
                }
                this.f5117q = null;
                this.f5118r = null;
                this.f5119s = null;
                this.f5115o = null;
            }
        }
    }

    boolean m6773a() {
        return this.f5109i > -1 && this.f5106f > -1;
    }

    void m6774b(int i, int i2) {
        m6755a(i, i2 - C1101o.m6041e().m7131d(this.f5112l)[0], this.f5109i, this.f5106f);
    }

    public boolean m6775b() {
        boolean z;
        synchronized (this.f5110j) {
            z = this.f5117q != null;
        }
        return z;
    }

    public void m6776c(int i, int i2) {
        this.f5104d = i;
        this.f5105e = i2;
    }

    boolean m6777c() {
        int[] b = C1101o.m6041e().m7124b(this.f5112l);
        int[] d = C1101o.m6041e().m7131d(this.f5112l);
        int i = b[0];
        int i2 = b[1];
        if (this.f5109i < 50 || this.f5109i > i) {
            C1129c.m6192d("Width is too small or too large.");
            return false;
        } else if (this.f5106f < 50 || this.f5106f > i2) {
            C1129c.m6192d("Height is too small or too large.");
            return false;
        } else if (this.f5106f == i2 && this.f5109i == i) {
            C1129c.m6192d("Cannot resize to a full-screen ad.");
            return false;
        } else {
            if (this.f5103c) {
                int i3;
                String str = this.f5102b;
                boolean z = true;
                switch (str.hashCode()) {
                    case -1364013995:
                        if (str.equals("center")) {
                            z = true;
                            break;
                        }
                        break;
                    case -1012429441:
                        if (str.equals("top-left")) {
                            z = false;
                            break;
                        }
                        break;
                    case -655373719:
                        if (str.equals("bottom-left")) {
                            z = true;
                            break;
                        }
                        break;
                    case 1163912186:
                        if (str.equals("bottom-right")) {
                            z = true;
                            break;
                        }
                        break;
                    case 1288627767:
                        if (str.equals("bottom-center")) {
                            z = true;
                            break;
                        }
                        break;
                    case 1755462605:
                        if (str.equals("top-center")) {
                            z = true;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        i3 = this.f5107g + this.f5104d;
                        i2 = this.f5105e + this.f5108h;
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        i3 = ((this.f5104d + this.f5107g) + (this.f5109i / 2)) - 25;
                        i2 = this.f5105e + this.f5108h;
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        i3 = ((this.f5104d + this.f5107g) + (this.f5109i / 2)) - 25;
                        i2 = ((this.f5105e + this.f5108h) + (this.f5106f / 2)) - 25;
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        i3 = this.f5107g + this.f5104d;
                        i2 = ((this.f5105e + this.f5108h) + this.f5106f) - 50;
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        i3 = ((this.f5104d + this.f5107g) + (this.f5109i / 2)) - 25;
                        i2 = ((this.f5105e + this.f5108h) + this.f5106f) - 50;
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        i3 = ((this.f5104d + this.f5107g) + this.f5109i) - 50;
                        i2 = ((this.f5105e + this.f5108h) + this.f5106f) - 50;
                        break;
                    default:
                        i3 = ((this.f5104d + this.f5107g) + this.f5109i) - 50;
                        i2 = this.f5105e + this.f5108h;
                        break;
                }
                if (i3 < 0 || i3 + 50 > i || r2 < d[0] || r2 + 50 > d[1]) {
                    return false;
                }
            }
            return true;
        }
    }
}
