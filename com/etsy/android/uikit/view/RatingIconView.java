package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;

public class RatingIconView extends LinearLayout {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final int DEFAULT_NUM_STARS = 5;
    private Drawable mEmptyStar;
    private Drawable mFullStar;
    private Drawable mHalfStar;
    private int mNumStars;
    private float mRating;
    private int mStarSize;

    static {
        $assertionsDisabled = !RatingIconView.class.desiredAssertionStatus() ? true : $assertionsDisabled;
    }

    public RatingIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mNumStars = DEFAULT_NUM_STARS;
        init(attributeSet);
    }

    public RatingIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mNumStars = DEFAULT_NUM_STARS;
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        if (!isInEditMode()) {
            setOrientation(0);
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.RatingStarBar);
            if ($assertionsDisabled || obtainStyledAttributes != null) {
                this.mStarSize = obtainStyledAttributes.getDimensionPixelSize(R.RatingStarBar_starSize, 0);
                obtainStyledAttributes.recycle();
                Resources resources = getResources();
                this.mEmptyStar = IconDrawable.m775a(resources).m780a(EtsyFontIcons.STAR).m779a(resources.getColor(R.star_empty)).m778a((float) this.mStarSize).m777a();
                this.mFullStar = IconDrawable.m775a(resources).m780a(EtsyFontIcons.STAR).m779a(resources.getColor(R.star_filled)).m778a((float) this.mStarSize).m777a();
                IconDrawable a = IconDrawable.m775a(resources).m780a(EtsyFontIcons.HALF_STAR).m779a(resources.getColor(R.star_filled)).m778a((float) this.mStarSize).m784e(19).m777a();
                this.mHalfStar = new LayerDrawable(new Drawable[]{IconDrawable.m775a(resources).m780a(EtsyFontIcons.STAR).m779a(resources.getColor(R.star_empty)).m778a((float) this.mStarSize).m777a(), a});
                return;
            }
            throw new AssertionError();
        }
    }

    public void setNumStars(int i) {
        this.mNumStars = i;
        updateView();
    }

    public void setRating(float f) {
        this.mRating = f;
        if (this.mRating < 0.0f) {
            this.mRating = 0.0f;
        }
        updateView();
    }

    public float getRating() {
        return this.mRating;
    }

    private void updateView() {
        removeAllViews();
        if (this.mNumStars > 0) {
            int round = Math.round(this.mRating * 2.0f);
            for (int i = 0; i < this.mNumStars * 2; i += 2) {
                View imageView = new ImageView(getContext());
                imageView.setLayoutParams(new LayoutParams(this.mStarSize, this.mStarSize));
                if (i + 2 <= round) {
                    imageView.setImageDrawable(this.mFullStar);
                } else if (i > round) {
                    imageView.setImageDrawable(this.mEmptyStar);
                } else if (i + 1 == round) {
                    imageView.setImageDrawable(this.mHalfStar);
                } else {
                    imageView.setImageDrawable(this.mEmptyStar);
                }
                addView(imageView);
            }
        }
    }
}
