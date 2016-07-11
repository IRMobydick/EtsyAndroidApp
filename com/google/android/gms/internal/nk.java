package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

@jw
abstract class nk {
    private final WeakReference<View> f5478a;

    public nk(View view) {
        this.f5478a = new WeakReference(view);
    }

    public final void m7218a() {
        ViewTreeObserver c = m7222c();
        if (c != null) {
            m7219a(c);
        }
    }

    protected abstract void m7219a(ViewTreeObserver viewTreeObserver);

    public final void m7220b() {
        ViewTreeObserver c = m7222c();
        if (c != null) {
            m7221b(c);
        }
    }

    protected abstract void m7221b(ViewTreeObserver viewTreeObserver);

    protected ViewTreeObserver m7222c() {
        View view = (View) this.f5478a.get();
        if (view == null) {
            return null;
        }
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        return (viewTreeObserver == null || !viewTreeObserver.isAlive()) ? null : viewTreeObserver;
    }
}
