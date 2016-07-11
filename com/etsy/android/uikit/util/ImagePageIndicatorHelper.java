package com.etsy.android.uikit.util;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.Adapter;
import com.etsy.android.uikit.view.CustomViewPageIndicator;
import com.viewpagerindicator.CirclePageIndicator;

/* renamed from: com.etsy.android.uikit.util.m */
public class ImagePageIndicatorHelper {
    private CustomViewPageIndicator f4163a;
    private CirclePageIndicator f4164b;
    private TabletSupportUtil f4165c;

    /* renamed from: com.etsy.android.uikit.util.m.1 */
    class ImagePageIndicatorHelper implements CustomViewPageIndicator {
        final /* synthetic */ ImagePageIndicatorHelper f4162a;

        ImagePageIndicatorHelper(ImagePageIndicatorHelper imagePageIndicatorHelper) {
            this.f4162a = imagePageIndicatorHelper;
        }

        public void m5550a(int i) {
            this.f4162a.f4163a.setCurrentItem(i);
        }
    }

    public ImagePageIndicatorHelper(Context context, View view, int i) {
        this.f4165c = new TabletSupportUtil(context);
        if (this.f4165c.m5621a()) {
            this.f4163a = (CustomViewPageIndicator) view.findViewById(i);
        } else {
            this.f4164b = (CirclePageIndicator) view.findViewById(i);
        }
    }

    public void m5554a(ViewPager viewPager, OnPageChangeListener onPageChangeListener, Adapter adapter) {
        if (this.f4165c.m5621a()) {
            this.f4163a.setViewPager(viewPager);
            this.f4163a.setOnPageChangeListener(onPageChangeListener);
            this.f4163a.setIndicatorClickListener(new ImagePageIndicatorHelper(this));
            this.f4163a.setAdapter(adapter);
        } else {
            this.f4164b.setViewPager(viewPager);
            this.f4164b.setOnPageChangeListener(onPageChangeListener);
        }
        m5553a(viewPager.getChildCount());
    }

    public void m5552a() {
        if (this.f4163a != null) {
            this.f4163a.setAdapter(null);
        }
        if (this.f4164b != null) {
            this.f4164b.setOnPageChangeListener(null);
        }
    }

    public void m5553a(int i) {
        (this.f4165c.m5621a() ? this.f4163a : this.f4164b).setVisibility(i <= 1 ? 8 : 0);
    }
}
