package com.etsy.android.uikit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;

public class MaxHeightFrameLayout extends LinearLayout {
    public int mMaxHeight;

    public MaxHeightFrameLayout(Context context) {
        super(context);
    }

    public MaxHeightFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MaxHeightFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    public void setMaxHeight(int i) {
        this.mMaxHeight = i;
    }

    protected void onMeasure(int i, int i2) {
        if (this.mMaxHeight > 0 && MeasureSpec.getSize(i2) > this.mMaxHeight) {
            i2 = MeasureSpec.makeMeasureSpec(this.mMaxHeight, 1073741824);
        }
        super.onMeasure(i, i2);
    }
}
