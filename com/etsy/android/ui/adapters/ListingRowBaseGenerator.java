package com.etsy.android.ui.adapters;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.uikit.adapter.BaseCardRowGenerator;
import java.util.ArrayList;

/* renamed from: com.etsy.android.ui.adapters.e */
public abstract class ListingRowBaseGenerator<T> extends BaseCardRowGenerator {
    protected int f2169a;
    private int f2170n;
    private int f2171o;
    private int f2172p;
    private final int f2173q;
    private boolean f2174r;
    private int f2175s;
    private int f2176t;
    private int f2177u;
    private int f2178v;
    private int f2179w;
    private int f2180x;
    private int f2181y;

    protected abstract void m3522a(int i, int i2, ListingRowBaseGenerator<T> listingRowBaseGenerator, T t);

    public ListingRowBaseGenerator(FragmentActivity fragmentActivity, ImageBatch imageBatch, int i) {
        this(fragmentActivity, imageBatch, i, 2131558409);
    }

    public ListingRowBaseGenerator(FragmentActivity fragmentActivity, ImageBatch imageBatch, int i, int i2) {
        super(fragmentActivity, imageBatch, i);
        this.f2171o = -1;
        this.f2172p = -1;
        this.f2173q = i2;
        this.f2175s = 0;
        this.f2176t = 0;
        this.f2169a = c;
        m3509f();
    }

    public int m3516a() {
        return m3510g().getInteger(this.f2173q);
    }

    public void m3521a(int i) {
        this.f2175s = i;
    }

    public void m3523a(boolean z) {
        this.f2174r = z;
        m3509f();
        if (this.f2176t > 0) {
            m3515l();
        }
    }

    public void m3525b(int i) {
        this.f2169a = i;
    }

    public void m3528c(int i) {
        this.f2170n = i;
    }

    public void m3530d(int i) {
        this.d = i;
    }

    public void m3532e(int i) {
        this.f2171o = i;
    }

    public void m3533f(int i) {
        this.f2172p = i;
    }

    public void m3524b() {
        super.m3509f();
        if (this.f2176t > 0) {
            m3515l();
        }
    }

    public View m3517a(View view) {
        return m3519a(view, m3516a(), this.k, this.m, this.l);
    }

    public View m3518a(View view, int i) {
        return m3519a(view, i, this.k, this.m, this.l);
    }

    protected View m3519a(View view, int i, int i2, int i3, int i4) {
        if (view == null || view.getTag() == null || ((ListingRowBaseGenerator) view.getTag()).f2191b != i) {
            ListingRowBaseGenerator listingRowBaseGenerator = new ListingRowBaseGenerator();
            listingRowBaseGenerator.f2190a = new ArrayList(i);
            listingRowBaseGenerator.f2191b = i;
            view = new LinearLayout(m3511h());
            view.setLayoutParams(new LayoutParams(-1, -2));
            view.setPadding(this.i, this.j, this.i, this.j);
            view.setOrientation(0);
            for (int i5 = 0; i5 < i; i5++) {
                View inflate = m3512i().inflate(this.f, null);
                ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, -2);
                layoutParams.setMargins(this.j, 0, this.j, 0);
                inflate.setLayoutParams(layoutParams);
                view.addView(inflate);
                ListingRowBaseGenerator listingRowBaseGenerator2 = new ListingRowBaseGenerator();
                listingRowBaseGenerator2.f2183b = inflate.findViewById(R.listing_matte);
                listingRowBaseGenerator2.f2184c = (ImageView) inflate.findViewById(R.listing_image);
                listingRowBaseGenerator2.f2184c.getLayoutParams().height = i4;
                listingRowBaseGenerator2.f2184c.getLayoutParams().width = i3;
                listingRowBaseGenerator2.f2185d = (ImageView) inflate.findViewById(R.btn_fav);
                listingRowBaseGenerator2.f2186e = (ImageView) inflate.findViewById(R.btn_lists);
                listingRowBaseGenerator2.f2187f = (TextView) inflate.findViewById(R.listing_title);
                listingRowBaseGenerator2.f2188g = (TextView) inflate.findViewById(R.listing_price);
                listingRowBaseGenerator2.f2189h = (TextView) inflate.findViewById(2131756011);
                listingRowBaseGenerator.f2190a.add(listingRowBaseGenerator2);
            }
            view.setTag(listingRowBaseGenerator);
        }
        return view;
    }

    public void m3526b(View view, int i) {
        if (i == 0 && this.h.m5623c() && this.h.m5624d()) {
            view.setPadding(this.i, this.j * 2, this.i, this.j);
        } else if (i == 0) {
            view.setPadding(this.i, this.j + this.i, this.i, this.j);
        } else {
            view.setPadding(this.i, this.j, this.i, this.j);
        }
    }

    public ListingRowBaseGenerator<T> m3520a(Object obj, T t, int i) {
        ListingRowBaseGenerator listingRowBaseGenerator = (ListingRowBaseGenerator) obj;
        if (listingRowBaseGenerator.f2190a.size() <= i) {
            return null;
        }
        ListingRowBaseGenerator<T> listingRowBaseGenerator2 = (ListingRowBaseGenerator) listingRowBaseGenerator.f2190a.get(i);
        listingRowBaseGenerator2.f2182a = t;
        if (t != null) {
            m3522a(this.m, this.l, listingRowBaseGenerator2, t);
            return listingRowBaseGenerator2;
        }
        listingRowBaseGenerator2.f2183b.setVisibility(8);
        return listingRowBaseGenerator2;
    }

    private void m3515l() {
        int c = m3527c();
        this.f2178v = m3510g().getInteger(2131558417);
        this.f2177u = this.f2176t / this.f2178v;
        if (this.f2177u * this.f2178v < this.f2176t) {
            this.f2177u++;
        }
        this.f2179w = ((c - (this.i * 2)) / this.f2178v) - (this.j * 2);
        this.f2180x = this.f2179w - (m3510g().getDimensionPixelSize(R.listing_card_shadow_padding) * 2);
        this.f2181y = (int) (((float) this.f2180x) * 0.5625f);
    }

    protected int m3527c() {
        if (this.f2170n > 0) {
            return this.f2170n;
        }
        if (this.h.m5626f()) {
            return this.g.m3182e() - this.f2175s;
        }
        return this.g.m3182e();
    }

    public int m3529d() {
        if (this.f2171o != -1) {
            return this.f2171o;
        }
        int dimensionPixelSize = m3510g().getDimensionPixelSize(R.padding_large) - (m3531e() * 2);
        if (!this.h.m5623c()) {
            return dimensionPixelSize;
        }
        dimensionPixelSize += m3510g().getDimensionPixelOffset(R.listing_card_shadow_padding);
        if (this.f2174r && this.h.m5624d()) {
            return dimensionPixelSize + m3510g().getDimensionPixelOffset(R.listview_extra_tablet_padding);
        }
        return dimensionPixelSize;
    }

    protected int m3531e() {
        if (this.f2172p != -1) {
            return this.f2172p;
        }
        return super.m3508e();
    }
}
