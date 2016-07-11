package com.google.android.gms.ads.search;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.a;
import com.google.android.gms.ads.f;
import com.google.android.gms.ads.internal.client.C1080g;
import com.google.android.gms.internal.jw;

@jw
public final class SearchAdView extends ViewGroup {
    private final C1080g zzpl;

    public SearchAdView(Context context) {
        super(context);
        this.zzpl = new C1080g(this);
    }

    public SearchAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzpl = new C1080g(this, attributeSet, false);
    }

    public SearchAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzpl = new C1080g(this, attributeSet, false);
    }

    public void destroy() {
        this.zzpl.m5878a();
    }

    public a getAdListener() {
        return this.zzpl.m5892b();
    }

    public f getAdSize() {
        return this.zzpl.m5894c();
    }

    public String getAdUnitId() {
        return this.zzpl.m5896e();
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(a aVar) {
        if (f.i.equals(getAdSize())) {
            this.zzpl.m5884a(aVar.a());
            return;
        }
        throw new IllegalStateException("You must use AdSize.SEARCH for a DynamicHeightSearchAdRequest");
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(b bVar) {
        this.zzpl.m5884a(bVar.o());
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getVisibility() != 8) {
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i5 = ((i3 - i) - measuredWidth) / 2;
            int i6 = ((i4 - i2) - measuredHeight) / 2;
            childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
        }
    }

    protected void onMeasure(int i, int i2) {
        int b;
        int i3 = 0;
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            f adSize = getAdSize();
            if (adSize != null) {
                Context context = getContext();
                b = adSize.b(context);
                i3 = adSize.a(context);
            } else {
                b = 0;
            }
        } else {
            measureChild(childAt, i, i2);
            b = childAt.getMeasuredWidth();
            i3 = childAt.getMeasuredHeight();
        }
        setMeasuredDimension(View.resolveSize(Math.max(b, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(i3, getSuggestedMinimumHeight()), i2));
    }

    public void pause() {
        this.zzpl.m5900i();
    }

    public void resume() {
        this.zzpl.m5902k();
    }

    public void setAdListener(a aVar) {
        this.zzpl.m5879a(aVar);
    }

    public void setAdSize(f fVar) {
        this.zzpl.m5890a(fVar);
    }

    public void setAdUnitId(String str) {
        this.zzpl.m5888a(str);
    }
}
