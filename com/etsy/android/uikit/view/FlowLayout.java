package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.etsy.android.lib.R;

public class FlowLayout extends ViewGroup {
    private int mHorizontalSpacing;
    private int mVerticalSpacing;

    public class LayoutParams extends android.view.ViewGroup.LayoutParams {
        public boolean breakLine;
        int f4211x;
        int f4212y;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.FlowLayout_LayoutParams);
            try {
                this.breakLine = obtainStyledAttributes.getBoolean(R.FlowLayout_LayoutParams_layout_breakLine, false);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.FlowLayout);
        try {
            this.mHorizontalSpacing = obtainStyledAttributes.getDimensionPixelSize(R.FlowLayout_horizontalSpacing, 0);
            this.mVerticalSpacing = obtainStyledAttributes.getDimensionPixelSize(R.FlowLayout_verticalSpacing, 0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public FlowLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected void onMeasure(int i, int i2) {
        Object obj;
        int size = MeasureSpec.getSize(i) - getPaddingRight();
        if (MeasureSpec.getMode(i) != 0) {
            obj = 1;
        } else {
            obj = null;
        }
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        boolean z = false;
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        Object obj2 = null;
        int i5 = 0;
        int i6 = paddingLeft;
        paddingLeft = paddingTop;
        paddingTop = 0;
        while (i3 < childCount) {
            int i7;
            boolean z2;
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 8) {
                i7 = paddingTop;
                paddingTop = paddingLeft;
                paddingLeft = i6;
                i6 = i5;
                z2 = z;
            } else {
                measureChild(childAt, i, i2);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                i7 = this.mHorizontalSpacing;
                if (obj == null || (!z && childAt.getMeasuredWidth() + i6 <= size)) {
                    obj2 = null;
                } else {
                    paddingLeft += this.mVerticalSpacing + i5;
                    i5 = 0;
                    paddingTop = Math.max(paddingTop, i6 - i7);
                    i6 = getPaddingLeft();
                    obj2 = 1;
                }
                layoutParams.f4211x = i6;
                layoutParams.f4212y = paddingLeft;
                i6 += childAt.getMeasuredWidth() + i7;
                int i8 = i7;
                i7 = paddingTop;
                paddingTop = paddingLeft;
                paddingLeft = i6;
                i6 = Math.max(i5, childAt.getMeasuredHeight());
                z2 = layoutParams.breakLine;
                i4 = i8;
            }
            i3++;
            z = z2;
            i5 = i6;
            i6 = paddingLeft;
            paddingLeft = paddingTop;
            paddingTop = i7;
        }
        if (obj2 == null) {
            paddingTop = Math.max(paddingTop, i6 - i4);
        }
        int paddingBottom = (getPaddingBottom() + i5) + paddingLeft;
        setMeasuredDimension(resolveSize(getPaddingRight() + paddingTop, i), resolveSize(paddingBottom, i2));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                childAt.layout(layoutParams.f4211x, layoutParams.f4212y, layoutParams.f4211x + childAt.getMeasuredWidth(), layoutParams.f4212y + childAt.getMeasuredHeight());
            }
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams.width, layoutParams.height);
    }
}
