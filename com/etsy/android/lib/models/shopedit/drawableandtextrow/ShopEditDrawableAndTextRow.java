package com.etsy.android.lib.models.shopedit.drawableandtextrow;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import org.parceler.Parcel;

@Parcel
public class ShopEditDrawableAndTextRow implements ShopEditPresentationItem {
    Drawable mDrawable;
    CharSequence mText;

    ShopEditDrawableAndTextRow() {
    }

    @NonNull
    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public void setDrawable(@NonNull Drawable drawable) {
        this.mDrawable = drawable;
    }

    @NonNull
    public CharSequence getText() {
        return this.mText;
    }

    public void setText(@NonNull CharSequence charSequence) {
        this.mText = charSequence;
    }

    public int getViewType() {
        return 6;
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
    }
}
