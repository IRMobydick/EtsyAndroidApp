package com.etsy.android.uikit.ui.toast;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.util.TrackingOnClickListener;

/* renamed from: com.etsy.android.uikit.ui.toast.a */
public abstract class PersistentToastPopup {
    protected final Context f3983b;
    protected final PersistentToastView f3984c;
    protected PersistentToastPopup f3985d;
    protected int f3986e;
    protected int f3987f;

    /* renamed from: com.etsy.android.uikit.ui.toast.a.1 */
    class PersistentToastPopup extends TrackingOnClickListener {
        final /* synthetic */ PersistentToastPopup f4122a;

        PersistentToastPopup(PersistentToastPopup persistentToastPopup) {
            this.f4122a = persistentToastPopup;
        }

        public void onViewClick(View view) {
            this.f4122a.m5361e();
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.toast.a.2 */
    class PersistentToastPopup extends TrackingOnClickListener {
        final /* synthetic */ PersistentToastPopup f4123a;

        PersistentToastPopup(PersistentToastPopup persistentToastPopup) {
            this.f4123a = persistentToastPopup;
        }

        public void onViewClick(View view) {
            this.f4123a.m5363g();
        }
    }

    @LayoutRes
    public abstract int m5353a();

    public PersistentToastPopup(Activity activity) {
        this(activity.getWindow());
    }

    public PersistentToastPopup(Window window) {
        this.f3986e = 300;
        this.f3983b = window.getContext();
        this.f3987f = m5353a();
        this.f3984c = m5354a(window);
        this.f3984c.setActionClickListener(new PersistentToastPopup(this));
        this.f3984c.setDismissClickListener(new PersistentToastPopup(this));
        m5358b(false);
    }

    public void m5355a(PersistentToastPopup persistentToastPopup) {
        this.f3985d = persistentToastPopup;
    }

    public void m5357b() {
        m5356a(true);
    }

    public void m5356a(boolean z) {
        this.f3984c.setVisibility(0);
        if (z) {
            this.f3984c.animateIn((long) this.f3986e);
        } else {
            this.f3984c.show();
        }
    }

    public void m5359c() {
        m5358b(true);
    }

    public void m5358b(boolean z) {
        if (z) {
            this.f3984c.animateOut((long) this.f3986e);
            return;
        }
        this.f3984c.hide();
        this.f3984c.setVisibility(4);
    }

    public void m5360d() {
        m5359c();
    }

    protected void m5361e() {
        m5362f();
        m5358b(true);
    }

    protected void m5362f() {
        if (this.f3985d != null) {
            this.f3985d.m3445a();
        }
    }

    protected void m5363g() {
        m5364h();
        m5358b(true);
    }

    protected void m5364h() {
        if (this.f3985d != null) {
            this.f3985d.m3446b();
        }
    }

    protected PersistentToastView m5354a(Window window) {
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(16908290);
        if (viewGroup2 == null) {
            viewGroup2 = viewGroup;
        }
        PersistentToastView persistentToastView = (PersistentToastView) viewGroup2.findViewById(R.persistent_toast_root);
        if (persistentToastView != null) {
            return persistentToastView;
        }
        persistentToastView = (PersistentToastView) LayoutInflater.from(viewGroup2.getContext()).inflate(this.f3987f, viewGroup2, false);
        persistentToastView.setPersistentToastPopup(this);
        viewGroup2.addView(persistentToastView);
        return persistentToastView;
    }
}
