package com.etsy.android.uikit.view;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.etsy.android.lib.R;

public class ClickableImageView extends AppCompatImageView {
    private static final int COLOR_RES;
    private final int mColor;

    static {
        COLOR_RES = R.ten_percent_black;
    }

    public ClickableImageView(Context context) {
        super(context);
        this.mColor = getResources().getColor(COLOR_RES);
    }

    public ClickableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mColor = getResources().getColor(COLOR_RES);
    }

    public ClickableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mColor = getResources().getColor(COLOR_RES);
    }

    protected void drawableStateChanged() {
        Object obj = null;
        super.drawableStateChanged();
        Drawable drawable = getDrawable();
        if (drawable != null) {
            for (int i : getDrawableState()) {
                if (i == 16842919) {
                    obj = 1;
                    break;
                }
            }
            if (obj != null) {
                drawable.setColorFilter(this.mColor, Mode.SRC_ATOP);
            } else {
                drawable.clearColorFilter();
            }
            invalidate();
        }
    }
}
