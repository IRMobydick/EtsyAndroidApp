package com.etsy.android.lib.models.shopedit.imageanddescriptionrow;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView.ScaleType;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.uikit.view.FullImageView;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShopEditImageAndDescriptionRow implements IShopEditImageAndDescriptionRow {
    @Nullable
    CharSequence mDescription;
    @Nullable
    Drawable mDrawable;
    float mHeightRatio;
    @Nullable
    CharSequence mHint;
    @Nullable
    Image mImage;
    int mImageShape;
    boolean mLoading;
    ScaleType mScaleType;

    public class Builder {
        CharSequence mDescription;
        Drawable mDrawable;
        float mHeightRatio;
        CharSequence mHint;
        Image mImage;
        ScaleType mScaleType;
        int mShape;

        public Builder(@Nullable Drawable drawable) {
            this.mDescription = StringUtils.EMPTY;
            this.mHint = StringUtils.EMPTY;
            this.mHeightRatio = FullImageView.ASPECT_RATIO_SQUARE;
            this.mShape = 2;
            this.mScaleType = ScaleType.CENTER_CROP;
            this.mDrawable = drawable;
        }

        public Builder(@Nullable Image image) {
            this.mDescription = StringUtils.EMPTY;
            this.mHint = StringUtils.EMPTY;
            this.mHeightRatio = FullImageView.ASPECT_RATIO_SQUARE;
            this.mShape = 2;
            this.mScaleType = ScaleType.CENTER_CROP;
            this.mImage = image;
        }

        public Builder heightRatio(float f) {
            this.mHeightRatio = f;
            return this;
        }

        public Builder description(@NonNull CharSequence charSequence) {
            this.mDescription = charSequence;
            return this;
        }

        public Builder hint(@NonNull CharSequence charSequence) {
            this.mHint = charSequence;
            return this;
        }

        public Builder imageShape(int i) {
            this.mShape = i;
            return this;
        }

        public Builder scaleType(ScaleType scaleType) {
            this.mScaleType = scaleType;
            return this;
        }

        public ShopEditImageAndDescriptionRow build() {
            return new ShopEditImageAndDescriptionRow();
        }
    }

    ShopEditImageAndDescriptionRow() {
        this.mLoading = false;
    }

    private ShopEditImageAndDescriptionRow(Builder builder) {
        this.mLoading = false;
        this.mDescription = builder.mDescription;
        this.mHint = builder.mHint;
        this.mImageShape = builder.mShape;
        this.mImage = builder.mImage;
        this.mHeightRatio = builder.mHeightRatio;
        this.mDrawable = builder.mDrawable;
        this.mScaleType = builder.mScaleType;
    }

    @Nullable
    public CharSequence getDescription() {
        return this.mDescription;
    }

    @Nullable
    public CharSequence getHint() {
        return this.mHint;
    }

    @Nullable
    public Drawable getDrawable() {
        return this.mDrawable;
    }

    @Nullable
    public IFullImage getImage() {
        return this.mImage;
    }

    public int getImageShape() {
        return this.mImageShape;
    }

    public int getImageType() {
        return this.mDrawable != null ? 2 : 1;
    }

    public float getHeightRatio() {
        return this.mHeightRatio;
    }

    public ScaleType getScaleType() {
        return this.mScaleType;
    }

    public int getViewType() {
        return 5;
    }

    public static ShopEditImageAndDescriptionRow newAddImageRow(float f, int i, @StringRes int i2, @NonNull Context context) {
        CharSequence spannableString = new SpannableString(context.getString(i2));
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.blue)), 0, spannableString.length(), 33);
        return new Builder(buildAddImageRowDrawable(i, context)).description(spannableString).heightRatio(f).scaleType(ScaleType.FIT_XY).build();
    }

    public static Drawable buildAddImageRowDrawable(int i, @NonNull Context context) {
        int i2;
        switch (i) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                i2 = R.bg_blue_circle_outline;
                break;
            default:
                i2 = R.bg_blue_outline;
                break;
        }
        IconDrawable a = IconDrawable.m775a(context.getResources()).m780a(EtsyFontIcons.PLUS).m779a(ContextCompat.getColor(context, R.blue)).m782c(R.shop_edit_text_row_icon_size).m784e(17).m777a();
        return new LayerDrawable(new Drawable[]{ContextCompat.getDrawable(context, i2), a});
    }

    public static ShopEditImageAndDescriptionRow newErrorRow(float f, int i, @StringRes int i2, @NonNull Context context) {
        return new Builder(buildErrorDrawable(context)).heightRatio(f).imageShape(i).scaleType(ScaleType.FIT_XY).description(context.getString(i2)).build();
    }

    public static Drawable buildErrorDrawable(@NonNull Context context) {
        return IconDrawable.m775a(context.getResources()).m780a(EtsyFontIcons.DELETE).m782c(R.shop_edit_text_row_icon_size).m779a(ContextCompat.getColor(context, R.red)).m777a();
    }

    public boolean isLoading() {
        return this.mLoading;
    }

    public void setLoading(boolean z) {
        this.mLoading = z;
    }

    void setDrawable(@Nullable Drawable drawable) {
        this.mDrawable = drawable;
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
    }
}
