package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.uikit.text.typeface.TypefaceCache;

public class GuardianTextView extends TextView {
    private static final String TAG;
    private int mStyleType;

    static {
        TAG = EtsyDebug.m1891a(GuardianTextView.class);
    }

    public GuardianTextView(Context context) {
        super(context);
    }

    public GuardianTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getStyleAttribute(context, attributeSet);
    }

    public GuardianTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        getStyleAttribute(context, attributeSet);
    }

    private void getStyleAttribute(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16842903}, 0, 0);
        try {
            this.mStyleType = obtainStyledAttributes.getInteger(0, 0);
            setTypeface(null, this.mStyleType);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void setTypeface(Typeface typeface) {
        if (!isInEditMode()) {
            super.setTypeface(TypefaceCache.m5414a().m5415a(getContext(), getTypeFaceNameForType(this.mStyleType)));
        }
    }

    public void setTypeface(Typeface typeface, int i) {
        if (!isInEditMode()) {
            super.setTypeface(TypefaceCache.m5414a().m5415a(getContext(), getTypeFaceNameForType(this.mStyleType)));
        }
    }

    private String getTypeFaceNameForType(int i) {
        return getContext().getString(R.typeface_guardian_medium_app);
    }
}
