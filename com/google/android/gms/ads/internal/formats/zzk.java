package com.google.android.gms.ads.internal.formats;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lt;
import com.google.android.gms.internal.zzdl.zza;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@jw
public class zzk extends zza implements OnClickListener, OnTouchListener, OnGlobalLayoutListener, OnScrollChangedListener {
    private d zzAL;
    private final FrameLayout zzBo;
    private Map<String, WeakReference<View>> zzBp;
    private C1094b zzBq;
    boolean zzBr;
    int zzBs;
    int zzBt;
    private FrameLayout zzpC;
    private final Object zzpp;

    public zzk(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzpp = new Object();
        this.zzBp = new HashMap();
        this.zzBr = false;
        this.zzBo = frameLayout;
        this.zzpC = frameLayout2;
        C1101o.m6061y().m7216a(this.zzBo, (OnGlobalLayoutListener) this);
        C1101o.m6061y().m7217a(this.zzBo, (OnScrollChangedListener) this);
        this.zzBo.setOnTouchListener(this);
        this.zzBo.setOnClickListener(this);
    }

    private void zzeX() {
        this.zzBo.setOnTouchListener(this);
        this.zzBo.setClickable(true);
        this.zzBo.setOnClickListener(this);
        for (Entry value : this.zzBp.entrySet()) {
            View view = (View) ((WeakReference) value.getValue()).get();
            if (view != null) {
                view.setOnTouchListener(this);
                view.setClickable(true);
                view.setOnClickListener(this);
            }
        }
    }

    public void destroy() {
        synchronized (this.zzpp) {
            if (this.zzpC != null) {
                this.zzpC.removeAllViews();
            }
            this.zzpC = null;
            this.zzBp = null;
            this.zzBq = null;
            this.zzAL = null;
        }
    }

    int getMeasuredHeight() {
        return this.zzBo.getMeasuredHeight();
    }

    int getMeasuredWidth() {
        return this.zzBo.getMeasuredWidth();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r10) {
        /*
        r9 = this;
        r6 = r9.zzpp;
        monitor-enter(r6);
        r0 = r9.zzAL;	 Catch:{ all -> 0x0090 }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r6);	 Catch:{ all -> 0x0090 }
    L_0x0008:
        return;
    L_0x0009:
        r3 = new org.json.JSONObject;	 Catch:{ all -> 0x0090 }
        r3.<init>();	 Catch:{ all -> 0x0090 }
        r0 = r9.zzBp;	 Catch:{ all -> 0x0090 }
        r0 = r0.entrySet();	 Catch:{ all -> 0x0090 }
        r2 = r0.iterator();	 Catch:{ all -> 0x0090 }
    L_0x0018:
        r0 = r2.hasNext();	 Catch:{ all -> 0x0090 }
        if (r0 == 0) goto L_0x0099;
    L_0x001e:
        r0 = r2.next();	 Catch:{ all -> 0x0090 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0090 }
        r1 = r0.getValue();	 Catch:{ all -> 0x0090 }
        r1 = (java.lang.ref.WeakReference) r1;	 Catch:{ all -> 0x0090 }
        r1 = r1.get();	 Catch:{ all -> 0x0090 }
        r1 = (android.view.View) r1;	 Catch:{ all -> 0x0090 }
        if (r1 == 0) goto L_0x0018;
    L_0x0032:
        r4 = r9.zzj(r1);	 Catch:{ all -> 0x0090 }
        r5 = new org.json.JSONObject;	 Catch:{ all -> 0x0090 }
        r5.<init>();	 Catch:{ all -> 0x0090 }
        r7 = "width";
        r8 = r1.getWidth();	 Catch:{ JSONException -> 0x0075 }
        r8 = r9.zzv(r8);	 Catch:{ JSONException -> 0x0075 }
        r5.put(r7, r8);	 Catch:{ JSONException -> 0x0075 }
        r7 = "height";
        r1 = r1.getHeight();	 Catch:{ JSONException -> 0x0075 }
        r1 = r9.zzv(r1);	 Catch:{ JSONException -> 0x0075 }
        r5.put(r7, r1);	 Catch:{ JSONException -> 0x0075 }
        r1 = "x";
        r7 = r4.x;	 Catch:{ JSONException -> 0x0075 }
        r7 = r9.zzv(r7);	 Catch:{ JSONException -> 0x0075 }
        r5.put(r1, r7);	 Catch:{ JSONException -> 0x0075 }
        r1 = "y";
        r4 = r4.y;	 Catch:{ JSONException -> 0x0075 }
        r4 = r9.zzv(r4);	 Catch:{ JSONException -> 0x0075 }
        r5.put(r1, r4);	 Catch:{ JSONException -> 0x0075 }
        r1 = r0.getKey();	 Catch:{ JSONException -> 0x0075 }
        r1 = (java.lang.String) r1;	 Catch:{ JSONException -> 0x0075 }
        r3.put(r1, r5);	 Catch:{ JSONException -> 0x0075 }
        goto L_0x0018;
    L_0x0075:
        r1 = move-exception;
        r1 = "Unable to get view rectangle for view ";
        r0 = r0.getKey();	 Catch:{ all -> 0x0090 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0090 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x0090 }
        r4 = r0.length();	 Catch:{ all -> 0x0090 }
        if (r4 == 0) goto L_0x0093;
    L_0x0088:
        r0 = r1.concat(r0);	 Catch:{ all -> 0x0090 }
    L_0x008c:
        com.google.android.gms.ads.internal.util.client.C1129c.m6192d(r0);	 Catch:{ all -> 0x0090 }
        goto L_0x0018;
    L_0x0090:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0090 }
        throw r0;
    L_0x0093:
        r0 = new java.lang.String;	 Catch:{ all -> 0x0090 }
        r0.<init>(r1);	 Catch:{ all -> 0x0090 }
        goto L_0x008c;
    L_0x0099:
        r4 = new org.json.JSONObject;	 Catch:{ all -> 0x0090 }
        r4.<init>();	 Catch:{ all -> 0x0090 }
        r0 = "x";
        r1 = r9.zzBs;	 Catch:{ JSONException -> 0x00ed }
        r1 = r9.zzv(r1);	 Catch:{ JSONException -> 0x00ed }
        r4.put(r0, r1);	 Catch:{ JSONException -> 0x00ed }
        r0 = "y";
        r1 = r9.zzBt;	 Catch:{ JSONException -> 0x00ed }
        r1 = r9.zzv(r1);	 Catch:{ JSONException -> 0x00ed }
        r4.put(r0, r1);	 Catch:{ JSONException -> 0x00ed }
    L_0x00b4:
        r5 = new org.json.JSONObject;	 Catch:{ all -> 0x0090 }
        r5.<init>();	 Catch:{ all -> 0x0090 }
        r0 = "width";
        r1 = r9.getMeasuredWidth();	 Catch:{ JSONException -> 0x00f4 }
        r1 = r9.zzv(r1);	 Catch:{ JSONException -> 0x00f4 }
        r5.put(r0, r1);	 Catch:{ JSONException -> 0x00f4 }
        r0 = "height";
        r1 = r9.getMeasuredHeight();	 Catch:{ JSONException -> 0x00f4 }
        r1 = r9.zzv(r1);	 Catch:{ JSONException -> 0x00f4 }
        r5.put(r0, r1);	 Catch:{ JSONException -> 0x00f4 }
    L_0x00d3:
        r0 = r9.zzBq;	 Catch:{ all -> 0x0090 }
        if (r0 == 0) goto L_0x00fb;
    L_0x00d7:
        r0 = r9.zzBq;	 Catch:{ all -> 0x0090 }
        r0 = r0.m5980a();	 Catch:{ all -> 0x0090 }
        r0 = r0.equals(r10);	 Catch:{ all -> 0x0090 }
        if (r0 == 0) goto L_0x00fb;
    L_0x00e3:
        r0 = r9.zzAL;	 Catch:{ all -> 0x0090 }
        r1 = "1007";
        r0.a(r1, r3, r4, r5);	 Catch:{ all -> 0x0090 }
    L_0x00ea:
        monitor-exit(r6);	 Catch:{ all -> 0x0090 }
        goto L_0x0008;
    L_0x00ed:
        r0 = move-exception;
        r0 = "Unable to get click location";
        com.google.android.gms.ads.internal.util.client.C1129c.m6192d(r0);	 Catch:{ all -> 0x0090 }
        goto L_0x00b4;
    L_0x00f4:
        r0 = move-exception;
        r0 = "Unable to get native ad view bounding box";
        com.google.android.gms.ads.internal.util.client.C1129c.m6192d(r0);	 Catch:{ all -> 0x0090 }
        goto L_0x00d3;
    L_0x00fb:
        r0 = r9.zzAL;	 Catch:{ all -> 0x0090 }
        r2 = r9.zzBp;	 Catch:{ all -> 0x0090 }
        r1 = r10;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0090 }
        goto L_0x00ea;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.formats.zzk.onClick(android.view.View):void");
    }

    public void onGlobalLayout() {
        synchronized (this.zzpp) {
            if (this.zzBr) {
                int measuredWidth = getMeasuredWidth();
                int measuredHeight = getMeasuredHeight();
                if (!(measuredWidth == 0 || measuredHeight == 0 || this.zzpC == null)) {
                    this.zzpC.setLayoutParams(new LayoutParams(measuredWidth, measuredHeight));
                    this.zzBr = false;
                }
            }
            if (this.zzAL != null) {
                this.zzAL.b(this.zzBo);
            }
        }
    }

    public void onScrollChanged() {
        synchronized (this.zzpp) {
            if (this.zzAL != null) {
                this.zzAL.b(this.zzBo);
            }
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.zzpp) {
            if (this.zzAL == null) {
            } else {
                Point zzc = zzc(motionEvent);
                this.zzBs = zzc.x;
                this.zzBt = zzc.y;
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setLocation((float) zzc.x, (float) zzc.y);
                this.zzAL.a(obtain);
                obtain.recycle();
            }
        }
        return false;
    }

    public zzd zzP(String str) {
        zzd zzD;
        synchronized (this.zzpp) {
            Object obj;
            WeakReference weakReference = (WeakReference) this.zzBp.get(str);
            if (weakReference == null) {
                obj = null;
            } else {
                View view = (View) weakReference.get();
            }
            zzD = zze.zzD(obj);
        }
        return zzD;
    }

    Point zzc(MotionEvent motionEvent) {
        int[] iArr = new int[2];
        this.zzBo.getLocationOnScreen(iArr);
        return new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
    }

    C1094b zzc(C1095f c1095f) {
        return c1095f.m5984a((OnClickListener) this);
    }

    public void zzc(String str, zzd com_google_android_gms_dynamic_zzd) {
        View view = (View) zze.zzx(com_google_android_gms_dynamic_zzd);
        synchronized (this.zzpp) {
            if (view == null) {
                this.zzBp.remove(str);
            } else {
                this.zzBp.put(str, new WeakReference(view));
                view.setOnTouchListener(this);
                view.setClickable(true);
                view.setOnClickListener(this);
            }
        }
    }

    public void zze(zzd com_google_android_gms_dynamic_zzd) {
        synchronized (this.zzpp) {
            zzeX();
            if (this.zzpC != null) {
                this.zzpC.setLayoutParams(new LayoutParams(0, 0));
                this.zzBo.requestLayout();
            }
            this.zzBr = true;
            zzi(null);
            Object zzx = zze.zzx(com_google_android_gms_dynamic_zzd);
            if (zzx instanceof C1095f) {
                C1095f c1095f = (C1095f) zzx;
                if ((this.zzAL instanceof C1096c) && ((C1096c) this.zzAL).m6002b()) {
                    ((C1096c) this.zzAL).m6001a((d) c1095f);
                } else {
                    this.zzAL = c1095f;
                    if (this.zzAL instanceof C1096c) {
                        ((C1096c) this.zzAL).m6001a(null);
                    }
                }
                this.zzpC.removeAllViews();
                this.zzBq = zzc(c1095f);
                if (this.zzBq != null) {
                    this.zzBp.put("1007", new WeakReference(this.zzBq.m5980a()));
                    this.zzpC.addView(this.zzBq);
                }
                lt.f5423a.post(new 1(this, c1095f));
                c1095f.m5987a(this.zzBo);
                zzi(this.zzBo);
                return;
            }
            C1129c.m6192d("Not an instance of native engine. This is most likely a transient error");
        }
    }

    void zzi(View view) {
        if (this.zzAL != null) {
            d c = this.zzAL instanceof C1096c ? ((C1096c) this.zzAL).m6003c() : this.zzAL;
            if (c != null) {
                c.c(view);
            }
        }
    }

    Point zzj(View view) {
        if (this.zzBq == null || !this.zzBq.m5980a().equals(view)) {
            Point point = new Point();
            view.getGlobalVisibleRect(new Rect(), point);
            return point;
        }
        Point point2 = new Point();
        this.zzBo.getGlobalVisibleRect(new Rect(), point2);
        Point point3 = new Point();
        view.getGlobalVisibleRect(new Rect(), point3);
        return new Point(point3.x - point2.x, point3.y - point2.y);
    }

    int zzv(int i) {
        return C1089r.m5951a().m6178b(this.zzAL.f(), i);
    }
}
