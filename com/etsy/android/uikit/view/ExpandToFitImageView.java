package com.etsy.android.uikit.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;

public class ExpandToFitImageView extends ImageView {
    private static final int MATCH_PARENT = -1;
    private static final int WRAP_CONTENT = -2;

    public ExpandToFitImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int i3 = getLayoutParams().width;
            int i4 = getLayoutParams().height;
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth == MATCH_PARENT || intrinsicHeight == MATCH_PARENT) {
                super.onMeasure(i, i2);
                return;
            }
            float f = ((float) intrinsicWidth) / ((float) intrinsicHeight);
            if (i3 == MATCH_PARENT && i4 == WRAP_CONTENT) {
                i3 = MeasureSpec.getSize(i);
                setMeasuredDimension(i3, (int) Math.ceil((double) (((float) i3) / f)));
                return;
            } else if (i4 == MATCH_PARENT && i3 == WRAP_CONTENT) {
                i3 = MeasureSpec.getSize(i2);
                setMeasuredDimension((int) Math.ceil((double) (f * ((float) i3))), i3);
                return;
            } else {
                super.onMeasure(i, i2);
                return;
            }
        }
        super.onMeasure(i, i2);
    }
}
