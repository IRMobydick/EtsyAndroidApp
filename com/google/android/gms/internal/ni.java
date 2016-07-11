package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.google.android.gms.ads.internal.C1101o;
import java.lang.ref.WeakReference;

@jw
class ni extends nk implements OnGlobalLayoutListener {
    private final WeakReference<OnGlobalLayoutListener> f5479a;

    public ni(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        super(view);
        this.f5479a = new WeakReference(onGlobalLayoutListener);
    }

    protected void m7223a(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnGlobalLayoutListener(this);
    }

    protected void m7224b(ViewTreeObserver viewTreeObserver) {
        C1101o.m6043g().m7154a(viewTreeObserver, (OnGlobalLayoutListener) this);
    }

    public void onGlobalLayout() {
        OnGlobalLayoutListener onGlobalLayoutListener = (OnGlobalLayoutListener) this.f5479a.get();
        if (onGlobalLayoutListener != null) {
            onGlobalLayoutListener.onGlobalLayout();
        } else {
            m7220b();
        }
    }
}
