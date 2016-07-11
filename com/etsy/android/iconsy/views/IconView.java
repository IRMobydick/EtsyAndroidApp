package com.etsy.android.iconsy.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.etsy.android.iconsy.AbstractFontIcon;
import com.etsy.android.iconsy.FontSets;
import com.etsy.android.iconsy.R;

public class IconView extends ImageView {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final int NONE = -1;
    int mAlpha;
    int mColor;
    boolean mCreateSelector;
    int mGravity;
    AbstractFontIcon mIcon;

    static {
        $assertionsDisabled = !IconView.class.desiredAssertionStatus() ? true : $assertionsDisabled;
    }

    public IconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
        setupDrawable();
    }

    public IconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
        setupDrawable();
    }

    private void init(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.IconView);
        if ($assertionsDisabled || obtainStyledAttributes != null) {
            int resourceId = obtainStyledAttributes.getResourceId(R.IconView_iconChar, 0);
            if (isInEditMode() || resourceId == 0) {
                this.mIcon = FontSets.m767a(R.ic_demo_example);
            } else {
                this.mIcon = FontSets.m767a(resourceId);
            }
            this.mCreateSelector = obtainStyledAttributes.getBoolean(R.IconView_hasSelector, $assertionsDisabled);
            this.mColor = obtainStyledAttributes.getColor(R.IconView_iconColor, ViewCompat.MEASURED_STATE_MASK);
            this.mAlpha = obtainStyledAttributes.getInteger(R.IconView_alpha, NONE);
            this.mGravity = obtainStyledAttributes.getInt(R.IconView_gravity, NONE);
            obtainStyledAttributes.recycle();
            setScaleType(ScaleType.FIT_XY);
            return;
        }
        throw new AssertionError();
    }

    public void setScaleType(ScaleType scaleType) {
    }

    private void setupDrawable() {
        if (!isInEditMode()) {
            IconDrawable d = IconDrawable.m775a(getResources()).m779a(this.mColor).m780a(this.mIcon).m784e(this.mGravity).m783d(this.mAlpha);
            if (this.mCreateSelector) {
                setImageDrawable(IconSelectorDrawable.create(d));
            } else {
                setImageDrawable(d.m777a());
            }
        }
    }

    public void setIcon(AbstractFontIcon abstractFontIcon) {
        this.mIcon = abstractFontIcon;
        setupDrawable();
    }

    public void setGravity(int i) {
        this.mGravity = i;
        setupDrawable();
    }

    public void setColor(int i) {
        this.mColor = i;
        setupDrawable();
    }
}
