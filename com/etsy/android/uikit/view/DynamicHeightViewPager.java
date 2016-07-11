package com.etsy.android.uikit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;

public class DynamicHeightViewPager extends FixedViewPager {
    private double mHeightRatio;

    public DynamicHeightViewPager(Context context) {
        super(context);
    }

    public DynamicHeightViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setHeightRatio(double d) {
        if (d != this.mHeightRatio) {
            this.mHeightRatio = d;
            requestLayout();
        }
    }

    public double getHeightRatio() {
        return this.mHeightRatio;
    }

    protected void onMeasure(int i, int i2) {
        if (this.mHeightRatio > 0.0d) {
            i2 = MeasureSpec.makeMeasureSpec((int) (((double) MeasureSpec.getSize(i)) * this.mHeightRatio), 1073741824);
        }
        super.onMeasure(i, i2);
    }
}
