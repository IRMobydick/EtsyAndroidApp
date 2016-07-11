package com.etsy.android.ui.core.listingpanel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.Resources;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.TrackingEtsyConfigMap;
import com.etsy.android.lib.core.EtsyRequestQueue;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.util.ab;
import com.etsy.android.lib.util.fonts.StandardFontIcon;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.util.AnimationUtil;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.ViewTreeObserverHelper;

/* renamed from: com.etsy.android.ui.core.listingpanel.a */
public abstract class ListingPanelBase {
    private static final String f2799k;
    protected BaseActivity f2800a;
    protected Listing f2801b;
    protected View f2802c;
    protected LinearLayout f2803d;
    protected View f2804e;
    protected View f2805f;
    protected ImageView f2806g;
    protected boolean f2807h;
    protected boolean f2808i;
    protected boolean f2809j;
    private int f2810l;
    private int f2811m;
    private int f2812n;
    private int f2813o;
    private IconDrawable f2814p;
    private IconDrawable f2815q;
    private int f2816r;
    private int f2817s;
    @NonNull
    private ad f2818t;
    private ListingPanelBase f2819u;
    private OnGlobalLayoutListener f2820v;

    /* renamed from: com.etsy.android.ui.core.listingpanel.a.1 */
    class ListingPanelBase extends AnimatorListenerAdapter {
        final /* synthetic */ ListingPanelBase f2793a;

        ListingPanelBase(ListingPanelBase listingPanelBase) {
            this.f2793a = listingPanelBase;
        }

        public void onAnimationEnd(Animator animator) {
            this.f2793a.m4043l();
        }

        public void onAnimationCancel(Animator animator) {
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.a.2 */
    class ListingPanelBase extends AnimatorListenerAdapter {
        final /* synthetic */ ListingPanelBase f2794a;

        ListingPanelBase(ListingPanelBase listingPanelBase) {
            this.f2794a = listingPanelBase;
        }

        public void onAnimationEnd(Animator animator) {
            this.f2794a.m4041j();
        }

        public void onAnimationCancel(Animator animator) {
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.a.3 */
    class ListingPanelBase implements OnGlobalLayoutListener {
        final /* synthetic */ boolean f2795a;
        final /* synthetic */ ListingPanelBase f2796b;

        ListingPanelBase(ListingPanelBase listingPanelBase, boolean z) {
            this.f2796b = listingPanelBase;
            this.f2795a = z;
        }

        public void onGlobalLayout() {
            this.f2796b.m4023s();
            this.f2796b.f2816r = this.f2796b.f2803d.getMeasuredHeight();
            if (this.f2795a) {
                this.f2796b.f2806g.setImageDrawable(this.f2796b.f2814p);
                this.f2796b.f2803d.setVisibility(8);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.a.4 */
    class ListingPanelBase extends TrackingOnClickListener {
        final /* synthetic */ ListingPanelBase f2797a;

        ListingPanelBase(ListingPanelBase listingPanelBase, ITrackedObject... iTrackedObjectArr) {
            this.f2797a = listingPanelBase;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f2797a.m4037f();
            this.f2797a.m4022r();
        }
    }

    /* renamed from: com.etsy.android.ui.core.listingpanel.a.5 */
    class ListingPanelBase extends TrackingOnClickListener {
        final /* synthetic */ ListingPanelBase f2798a;

        ListingPanelBase(ListingPanelBase listingPanelBase, ITrackedObject... iTrackedObjectArr) {
            this.f2798a = listingPanelBase;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f2798a.m4035d();
        }
    }

    protected abstract void m4034c();

    static {
        f2799k = EtsyDebug.m1891a(ListingPanelBase.class);
    }

    protected ListingPanelBase(Listing listing, BaseActivity baseActivity, @NonNull ad adVar) {
        this.f2807h = true;
        this.f2808i = false;
        this.f2801b = listing;
        this.f2800a = baseActivity;
        this.f2818t = adVar;
        this.f2809j = new TabletSupportUtil(this.f2800a).m5621a();
        this.f2817s = new ab(this.f2800a).m3183f();
    }

    protected void m4025a(@IdRes int i, @IdRes int i2, @IdRes int i3, @IdRes int i4) {
        this.f2810l = i;
        this.f2811m = i2;
        this.f2813o = i3;
        this.f2812n = i4;
    }

    public void m4028a(Listing listing) {
        this.f2801b = listing;
    }

    public void m4030a(BaseActivity baseActivity) {
        this.f2800a = baseActivity;
    }

    public void m4024a(int i) {
        this.f2816r = i;
    }

    public boolean m4032a() {
        return this.f2808i;
    }

    public void m4029a(ListingPanelBase listingPanelBase) {
        this.f2819u = listingPanelBase;
    }

    public void m4027a(View view, boolean z) {
        this.f2802c = view;
        this.f2803d = (LinearLayout) this.f2802c.findViewById(this.f2810l);
        this.f2804e = this.f2802c.findViewById(this.f2811m);
        this.f2806g = (ImageView) this.f2802c.findViewById(this.f2813o);
        this.f2805f = this.f2802c.findViewById(this.f2812n);
        m4019o();
        m4034c();
        if (this.f2809j) {
            if (!z) {
                this.f2803d.setVisibility(8);
            }
            m4026a(this.f2804e);
        } else {
            m4031a(!z);
            m4026a(this.f2804e);
            m4026a(this.f2806g);
        }
        if (z && this.f2807h) {
            if (!this.f2809j) {
                this.f2806g.setImageDrawable(this.f2815q);
            }
            m4020p();
        } else if (this.f2807h && !this.f2809j) {
            this.f2806g.setImageDrawable(this.f2814p);
        }
        this.f2808i = true;
    }

    @CallSuper
    public void m4033b() {
        m4044m().m1700a((Object) this);
        m4023s();
    }

    private void m4019o() {
        Resources resources = this.f2802c.getResources();
        this.f2814p = IconDrawable.m775a(resources).m780a(StandardFontIcon.NAVIGATE_DOWN).m779a(resources.getColor(R.actionbar_bottom_line)).m778a((float) resources.getDimensionPixelSize(R.expand_collapse_icon)).m777a();
        this.f2815q = IconDrawable.m775a(resources).m780a(StandardFontIcon.NAVIGATE_UP).m779a(resources.getColor(R.actionbar_bottom_line)).m778a((float) resources.getDimensionPixelSize(R.expand_collapse_icon)).m777a();
    }

    public void m4035d() {
        if (m4039h()) {
            m4036e();
        } else {
            m4037f();
        }
    }

    public void m4036e() {
        if (m4039h()) {
            m4042k();
            if (this.f2809j) {
                this.f2803d.setVisibility(8);
                this.f2804e.setSelected(false);
                m4043l();
                return;
            }
            m4021q();
        }
    }

    public void m4037f() {
        if (!m4039h()) {
            m4040i();
            if (this.f2809j) {
                m4020p();
                m4041j();
                return;
            }
            m4021q();
        }
    }

    public void m4038g() {
        this.f2807h = false;
        this.f2803d.setVisibility(8);
        this.f2804e.setVisibility(8);
    }

    public boolean m4039h() {
        return this.f2803d.getVisibility() == 0;
    }

    private void m4020p() {
        this.f2803d.setVisibility(0);
        if (this.f2809j) {
            this.f2804e.setSelected(true);
        }
    }

    private void m4021q() {
        if (m4039h()) {
            if (this.f2803d.getHeight() > this.f2817s) {
                EtsyDebug.m1912c(f2799k, "Not animating panel since the panel is larger than the window. In some cases this will cause a crash on texture size");
                this.f2803d.setVisibility(8);
                m4043l();
            } else {
                AnimationUtil.m5519a(this.f2803d, new ListingPanelBase(this));
            }
            this.f2806g.setImageDrawable(this.f2814p);
            return;
        }
        AnimationUtil.m5528c(this.f2803d, this.f2816r, new ListingPanelBase(this));
        m4020p();
        this.f2806g.setImageDrawable(this.f2815q);
    }

    private void m4022r() {
        if (this.f2819u != null) {
            this.f2819u.m3969a(this);
        }
    }

    protected void m4040i() {
    }

    protected void m4041j() {
    }

    protected void m4042k() {
    }

    protected void m4043l() {
    }

    protected void m4031a(boolean z) {
        m4023s();
        this.f2820v = new ListingPanelBase(this, z);
        ViewTreeObserverHelper.m5636a(this.f2803d.getViewTreeObserver(), this.f2820v);
    }

    protected void m4026a(View view) {
        if (view == null) {
            return;
        }
        if (this.f2809j) {
            view.setOnClickListener(new ListingPanelBase(this, this.f2801b));
            return;
        }
        view.setOnClickListener(new ListingPanelBase(this, this.f2801b));
    }

    protected EtsyRequestQueue m4044m() {
        return aj.m1101a().m1123i();
    }

    protected TrackingEtsyConfigMap m4045n() {
        return this.f2818t.m1864f();
    }

    private void m4023s() {
        if (this.f2803d != null && this.f2820v != null) {
            ViewTreeObserverHelper.m5639b(this.f2803d.getViewTreeObserver(), this.f2820v);
            this.f2820v = null;
        }
    }
}
