package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.appboy.Constants;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.internal.eg;
import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lt;
import com.google.android.gms.internal.no;
import java.util.HashMap;
import java.util.Map;

@jw
public class zzk extends FrameLayout implements g {
    private final no zzBb;
    private String zzCv;
    private final FrameLayout zzHP;
    private final C1111u zzHQ;
    @Nullable
    private zzi zzHR;
    private boolean zzHS;
    private boolean zzHT;
    private TextView zzHU;
    private long zzHV;
    private long zzHW;
    private String zzHX;

    public zzk(Context context, no noVar, int i, boolean z, ei eiVar, eg egVar) {
        super(context);
        this.zzBb = noVar;
        this.zzHP = new FrameLayout(context);
        addView(this.zzHP, new LayoutParams(-1, -1));
        zzb.zzv(noVar.m7258h());
        this.zzHR = noVar.m7258h().f4379b.m6068a(context, noVar, i, z, eiVar, egVar);
        if (this.zzHR != null) {
            this.zzHP.addView(this.zzHR, new LayoutParams(-1, -1, 17));
        }
        this.zzHU = new TextView(context);
        this.zzHU.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        zzgJ();
        this.zzHQ = new C1111u(this);
        this.zzHQ.m6089b();
        if (this.zzHR != null) {
            this.zzHR.zza((g) this);
        }
        if (this.zzHR == null) {
            zzj("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }

    private void zza(String str, String... strArr) {
        Map hashMap = new HashMap();
        hashMap.put(NotificationCompatApi21.CATEGORY_EVENT, str);
        int length = strArr.length;
        int i = 0;
        Object obj = null;
        while (i < length) {
            Object obj2 = strArr[i];
            if (obj != null) {
                hashMap.put(obj, obj2);
                obj2 = null;
            }
            i++;
            obj = obj2;
        }
        this.zzBb.m7244a("onVideoEvent", hashMap);
    }

    private void zzgJ() {
        if (!zzgL()) {
            this.zzHP.addView(this.zzHU, new LayoutParams(-1, -1));
            this.zzHP.bringChildToFront(this.zzHU);
        }
    }

    private void zzgK() {
        if (zzgL()) {
            this.zzHP.removeView(this.zzHU);
        }
    }

    private boolean zzgL() {
        return this.zzHU.getParent() != null;
    }

    private void zzgM() {
        if (this.zzBb.m7256f() != null && !this.zzHS) {
            this.zzHT = (this.zzBb.m7256f().getWindow().getAttributes().flags & AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) != 0;
            if (!this.zzHT) {
                this.zzBb.m7256f().getWindow().addFlags(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                this.zzHS = true;
            }
        }
    }

    private void zzgN() {
        if (this.zzBb.m7256f() != null && this.zzHS && !this.zzHT) {
            this.zzBb.m7256f().getWindow().clearFlags(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            this.zzHS = false;
        }
    }

    public static void zzh(no noVar) {
        Map hashMap = new HashMap();
        hashMap.put(NotificationCompatApi21.CATEGORY_EVENT, "no_video_view");
        noVar.m7244a("onVideoEvent", hashMap);
    }

    public void destroy() {
        this.zzHQ.m6088a();
        if (this.zzHR != null) {
            this.zzHR.stop();
        }
        zzgN();
    }

    public void onPaused() {
        zza("pause", new String[0]);
        zzgN();
    }

    public void pause() {
        if (this.zzHR != null) {
            this.zzHR.pause();
        }
    }

    public void play() {
        if (this.zzHR != null) {
            this.zzHR.play();
        }
    }

    public void seekTo(int i) {
        if (this.zzHR != null) {
            this.zzHR.seekTo(i);
        }
    }

    public void setMimeType(String str) {
        this.zzHX = str;
    }

    public void zza(float f) {
        if (this.zzHR != null) {
            this.zzHR.zza(f);
        }
    }

    public void zza(float f, float f2) {
        if (this.zzHR != null) {
            this.zzHR.zza(f, f2);
        }
    }

    public void zzav(String str) {
        this.zzCv = str;
    }

    public void zzd(int i, int i2, int i3, int i4) {
        if (i3 != 0 && i4 != 0) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(i3 + 2, i4 + 2);
            layoutParams.setMargins(i - 1, i2 - 1, 0, 0);
            this.zzHP.setLayoutParams(layoutParams);
            requestLayout();
        }
    }

    public void zzd(MotionEvent motionEvent) {
        if (this.zzHR != null) {
            this.zzHR.dispatchTouchEvent(motionEvent);
        }
    }

    public void zzfp() {
        if (this.zzHR != null) {
            if (TextUtils.isEmpty(this.zzCv)) {
                zza("no_src", new String[0]);
                return;
            }
            this.zzHR.setMimeType(this.zzHX);
            this.zzHR.setVideoPath(this.zzCv);
        }
    }

    public void zzgC() {
        lt.f5423a.post(new 1(this));
    }

    public void zzgD() {
        if (this.zzHR != null && this.zzHW == 0) {
            float duration = ((float) this.zzHR.getDuration()) / 1000.0f;
            int videoWidth = this.zzHR.getVideoWidth();
            int videoHeight = this.zzHR.getVideoHeight();
            zza("canplaythrough", "duration", String.valueOf(duration), "videoWidth", String.valueOf(videoWidth), "videoHeight", String.valueOf(videoHeight));
        }
    }

    public void zzgE() {
        zzgM();
    }

    public void zzgF() {
        zza("ended", new String[0]);
        zzgN();
    }

    public void zzgG() {
        zzgJ();
        this.zzHW = this.zzHV;
        lt.f5423a.post(new 2(this));
    }

    public void zzgH() {
        if (this.zzHR != null) {
            View textView = new TextView(this.zzHR.getContext());
            String str = "AdMob - ";
            String valueOf = String.valueOf(this.zzHR.zzgc());
            textView.setText(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            textView.setTextColor(SupportMenu.CATEGORY_MASK);
            textView.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
            this.zzHP.addView(textView, new LayoutParams(-2, -2, 17));
            this.zzHP.bringChildToFront(textView);
        }
    }

    void zzgI() {
        if (this.zzHR != null) {
            long currentPosition = (long) this.zzHR.getCurrentPosition();
            if (this.zzHV != currentPosition && currentPosition > 0) {
                zzgK();
                float f = ((float) currentPosition) / 1000.0f;
                zza("timeupdate", Constants.APPBOY_LOCATION_TIME_INTERVAL_KEY, String.valueOf(f));
                this.zzHV = currentPosition;
            }
        }
    }

    public void zzgi() {
        if (this.zzHR != null) {
            this.zzHR.zzgi();
        }
    }

    public void zzgj() {
        if (this.zzHR != null) {
            this.zzHR.zzgj();
        }
    }

    public void zzj(String str, String str2) {
        zza(BaseMessage.TYPE_ERROR, "what", str, Constants.APPBOY_PUSH_EXTRAS_KEY, str2);
    }
}
