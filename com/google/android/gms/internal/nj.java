package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import java.lang.ref.WeakReference;

@jw
class nj extends nk implements OnScrollChangedListener {
    private final WeakReference<OnScrollChangedListener> f5480a;

    public nj(View view, OnScrollChangedListener onScrollChangedListener) {
        super(view);
        this.f5480a = new WeakReference(onScrollChangedListener);
    }

    protected void m7225a(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnScrollChangedListener(this);
    }

    protected void m7226b(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.removeOnScrollChangedListener(this);
    }

    public void onScrollChanged() {
        OnScrollChangedListener onScrollChangedListener = (OnScrollChangedListener) this.f5480a.get();
        if (onScrollChangedListener != null) {
            onScrollChangedListener.onScrollChanged();
        } else {
            m7220b();
        }
    }
}
