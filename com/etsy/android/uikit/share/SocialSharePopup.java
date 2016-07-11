package com.etsy.android.uikit.share;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.etsy.android.uikit.util.TrackingOnClickListener;

/* renamed from: com.etsy.android.uikit.share.f */
public class SocialSharePopup {
    protected final Context f4036a;
    protected final SocialShareView f4037b;
    protected SocialSharePopup f4038c;
    protected CharSequence f4039d;
    protected int f4040e;
    protected int f4041f;

    /* renamed from: com.etsy.android.uikit.share.f.1 */
    class SocialSharePopup extends TrackingOnClickListener {
        final /* synthetic */ SocialSharePopup f4035a;

        SocialSharePopup(SocialSharePopup socialSharePopup) {
            this.f4035a = socialSharePopup;
        }

        public void onViewClick(View view) {
            this.f4035a.m5412d();
        }
    }

    public SocialSharePopup(Activity activity) {
        this(activity.getWindow());
    }

    public SocialSharePopup(Window window) {
        this(window, -1);
    }

    public SocialSharePopup(Window window, @LayoutRes int i) {
        this.f4040e = 300;
        if (i == -1) {
            i = R.popup_social_share;
        }
        this.f4036a = window.getContext();
        this.f4041f = i;
        this.f4037b = m5404a(window);
        this.f4037b.setOnShareListener(new SocialSharePopup(this));
        m5410b(false);
    }

    public void m5406a(int i) {
        this.f4039d = this.f4036a.getString(i);
    }

    public void m5407a(SocialSharePopup socialSharePopup) {
        this.f4038c = socialSharePopup;
    }

    public void m5405a() {
        m5408a(true);
    }

    public void m5408a(boolean z) {
        this.f4037b.setMessage(this.f4039d);
        this.f4037b.setVisibility(0);
        if (z) {
            this.f4037b.animateIn((long) this.f4040e);
        } else {
            this.f4037b.show();
        }
        EventTracker.m2006a(AdHocEventCompatBuilder.m5506b(this.f4037b));
    }

    public void m5409b() {
        m5410b(true);
    }

    public void m5410b(boolean z) {
        if (z) {
            this.f4037b.animateOut((long) this.f4040e);
            return;
        }
        this.f4037b.hide();
        this.f4037b.setVisibility(4);
        this.f4039d = null;
    }

    public void m5411c() {
        m5409b();
        EventTracker.m2026b(AdHocEventCompatBuilder.m5506b(this.f4037b));
    }

    protected void m5412d() {
        m5413e();
        m5410b(true);
        EventTracker.m2003a();
    }

    protected void m5413e() {
        if (this.f4038c != null) {
            this.f4038c.m5149a();
        }
    }

    protected SocialShareView m5404a(Window window) {
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(16908290);
        if (viewGroup2 == null) {
            viewGroup2 = viewGroup;
        }
        SocialShareView socialShareView = (SocialShareView) viewGroup2.findViewById(R.toast_layout_root);
        if (socialShareView != null) {
            return socialShareView;
        }
        socialShareView = (SocialShareView) LayoutInflater.from(viewGroup2.getContext()).inflate(this.f4041f, viewGroup2, false);
        socialShareView.setSocialShareToast(this);
        viewGroup2.addView(socialShareView);
        return socialShareView;
    }
}
