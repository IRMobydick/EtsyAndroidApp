package com.google.android.gms.internal;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.internal.C1101o;

@jw
public final class mu {
    private final View f5456a;
    private Activity f5457b;
    private boolean f5458c;
    private boolean f5459d;
    private boolean f5460e;
    private OnGlobalLayoutListener f5461f;
    private OnScrollChangedListener f5462g;

    public mu(Activity activity, View view, OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        this.f5457b = activity;
        this.f5456a = view;
        this.f5461f = onGlobalLayoutListener;
        this.f5462g = onScrollChangedListener;
    }

    private void m7192e() {
        if (!this.f5458c) {
            if (this.f5461f != null) {
                if (this.f5457b != null) {
                    C1101o.m6041e().m7099a(this.f5457b, this.f5461f);
                }
                C1101o.m6061y().m7216a(this.f5456a, this.f5461f);
            }
            if (this.f5462g != null) {
                if (this.f5457b != null) {
                    C1101o.m6041e().m7100a(this.f5457b, this.f5462g);
                }
                C1101o.m6061y().m7217a(this.f5456a, this.f5462g);
            }
            this.f5458c = true;
        }
    }

    private void m7193f() {
        if (this.f5457b != null && this.f5458c) {
            if (!(this.f5461f == null || this.f5457b == null)) {
                C1101o.m6043g().m7152a(this.f5457b, this.f5461f);
            }
            if (!(this.f5462g == null || this.f5457b == null)) {
                C1101o.m6041e().m7121b(this.f5457b, this.f5462g);
            }
            this.f5458c = false;
        }
    }

    public void m7194a() {
        this.f5460e = true;
        if (this.f5459d) {
            m7192e();
        }
    }

    public void m7195a(Activity activity) {
        this.f5457b = activity;
    }

    public void m7196b() {
        this.f5460e = false;
        m7193f();
    }

    public void m7197c() {
        this.f5459d = true;
        if (this.f5460e) {
            m7192e();
        }
    }

    public void m7198d() {
        this.f5459d = false;
        m7193f();
    }
}
