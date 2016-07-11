package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.mf;

@jw
/* renamed from: com.google.android.gms.ads.internal.overlay.b */
class C1103b extends RelativeLayout {
    mf f4547a;
    boolean f4548b;

    public C1103b(Context context, String str) {
        super(context);
        this.f4547a = new mf(context, str);
    }

    void m6065a() {
        this.f4548b = true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f4548b) {
            this.f4547a.m7172a(motionEvent);
        }
        return false;
    }
}
