package com.etsy.android.uikit.adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.util.ab;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.view.ListingFullImageView;

/* renamed from: com.etsy.android.uikit.adapter.d */
public abstract class BaseCardRowGenerator {
    protected static final int f2153b;
    protected static final int f2154c;
    private Activity f2155a;
    protected int f2156d;
    protected int f2157e;
    protected int f2158f;
    protected ab f2159g;
    protected TabletSupportUtil f2160h;
    protected int f2161i;
    protected int f2162j;
    protected int f2163k;
    protected int f2164l;
    protected int f2165m;
    private Resources f2166n;
    private LayoutInflater f2167o;
    private ImageBatch f2168p;

    public abstract View m3503a(View view);

    static {
        f2153b = R.multi_listing_columns_count;
        f2154c = R.bg_card;
    }

    protected Resources m3510g() {
        return this.f2166n;
    }

    protected Activity m3511h() {
        return this.f2155a;
    }

    protected LayoutInflater m3512i() {
        return this.f2167o;
    }

    protected ImageBatch m3513j() {
        return this.f2168p;
    }

    public BaseCardRowGenerator(Activity activity, ImageBatch imageBatch, int i) {
        this.f2158f = i;
        this.f2155a = activity;
        this.f2166n = activity.getResources();
        this.f2167o = activity.getLayoutInflater();
        this.f2168p = imageBatch;
        this.f2160h = new TabletSupportUtil(activity);
        this.f2156d = activity.getResources().getDimensionPixelSize(R.listing_card_shadow_padding);
    }

    public void m3504a(Activity activity) {
        this.f2155a = activity;
        this.f2166n = activity.getResources();
        this.f2167o = activity.getLayoutInflater();
    }

    public void m3505b() {
        m3509f();
    }

    public int m3502a() {
        return m3510g().getInteger(f2153b);
    }

    public int m3514k() {
        return this.f2157e;
    }

    protected void m3509f() {
        this.f2159g = new ab(this.f2155a.getApplicationContext());
        int c = m3506c();
        this.f2157e = this.f2159g.m3184g() ? 1 : 0;
        this.f2161i = m3507d();
        this.f2162j = m3508e();
        this.f2163k = ((c - (this.f2161i * 2)) / m3502a()) - (this.f2162j * 2);
        this.f2165m = this.f2163k - (this.f2156d * 2);
        this.f2164l = (int) (((float) this.f2165m) * ListingFullImageView.ASPECT_RATIO_STANDARD);
    }

    protected int m3506c() {
        return this.f2159g.m3182e();
    }

    public int m3507d() {
        int dimensionPixelSize = m3510g().getDimensionPixelSize(R.padding_large) - (m3508e() * 2);
        if (this.f2160h.m5623c()) {
            return dimensionPixelSize + m3510g().getDimensionPixelOffset(R.listing_card_shadow_padding);
        }
        return dimensionPixelSize;
    }

    protected int m3508e() {
        return m3510g().getDimensionPixelSize(R.padding_medium) - m3510g().getDimensionPixelOffset(R.listing_card_shadow_padding);
    }
}
