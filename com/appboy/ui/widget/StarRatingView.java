package com.appboy.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.C0401R;
import com.etsy.android.uikit.view.ListingFullImageView;
import java.util.ArrayList;
import java.util.List;

public class StarRatingView extends LinearLayout {
    private static final int MAX_NUMBER_OF_STARS = 5;
    private static final String TAG;
    private float mRating;
    private List<ImageView> mStarRating;

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, StarRatingView.class.getName()});
    }

    public StarRatingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRating = 0.0f;
        setOrientation(0);
        this.mStarRating = new ArrayList(MAX_NUMBER_OF_STARS);
        for (int i = 0; i < MAX_NUMBER_OF_STARS; i++) {
            View imageView = new ImageView(context);
            imageView.setTag(Integer.valueOf(0));
            addView(imageView, new LayoutParams(-2, -2));
            this.mStarRating.add(imageView);
        }
        setRating(this.mRating);
    }

    public float getRating() {
        return this.mRating;
    }

    public boolean setRating(float f) {
        if (f < 0.0f || f > 5.0f) {
            AppboyLogger.m664e(TAG, String.format("Unable to set rating to %f. Rating must be between 0 and %d", new Object[]{Float.valueOf(f), Integer.valueOf(MAX_NUMBER_OF_STARS)}));
            return false;
        }
        this.mRating = f;
        int floor = (int) Math.floor((double) this.mRating);
        for (int i = 0; i < floor; i++) {
            ImageView imageView = (ImageView) this.mStarRating.get(i);
            imageView.setTag(Integer.valueOf(C0401R.drawable.com_appboy_rating_full_star));
            imageView.setImageResource(C0401R.drawable.com_appboy_rating_full_star);
        }
        for (int ceil = (int) Math.ceil((double) this.mRating); ceil < this.mStarRating.size(); ceil++) {
            imageView = (ImageView) this.mStarRating.get(ceil);
            imageView.setTag(Integer.valueOf(C0401R.drawable.com_appboy_rating_empty_star));
            imageView.setImageResource(C0401R.drawable.com_appboy_rating_empty_star);
        }
        float f2 = f - ((float) floor);
        if (f2 > 0.0f) {
            imageView = (ImageView) this.mStarRating.get(floor);
            if (f2 < 0.25f) {
                imageView.setTag(Integer.valueOf(C0401R.drawable.com_appboy_rating_empty_star));
                imageView.setImageResource(C0401R.drawable.com_appboy_rating_empty_star);
            } else if (f2 < ListingFullImageView.ASPECT_RATIO_STANDARD) {
                imageView.setTag(Integer.valueOf(C0401R.drawable.com_appboy_rating_half_star));
                imageView.setImageResource(C0401R.drawable.com_appboy_rating_half_star);
            } else {
                imageView.setTag(Integer.valueOf(C0401R.drawable.com_appboy_rating_full_star));
                imageView.setImageResource(C0401R.drawable.com_appboy_rating_full_star);
            }
        }
        return true;
    }

    List<ImageView> getImageViewList() {
        return this.mStarRating;
    }
}
