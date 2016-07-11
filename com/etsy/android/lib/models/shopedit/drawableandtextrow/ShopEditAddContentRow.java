package com.etsy.android.lib.models.shopedit.drawableandtextrow;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;

/* renamed from: com.etsy.android.lib.models.shopedit.drawableandtextrow.a */
abstract class ShopEditAddContentRow extends ShopEditDrawableAndTextRow implements ShopEditActionableItem {
    ShopEditAddContentRow() {
    }

    public ShopEditAddContentRow(@StringRes int i, @NonNull Context context) {
        this.mDrawable = ShopEditAddContentRow.buildAddDrawable(context);
        CharSequence spannableString = new SpannableString(context.getString(i));
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.blue)), 0, spannableString.length(), 33);
        this.mText = spannableString;
    }

    @NonNull
    private static Drawable buildAddDrawable(@NonNull Context context) {
        return IconDrawable.m775a(context.getResources()).m780a(EtsyFontIcons.PLUS).m782c(R.shop_edit_text_row_icon_size).m781b(R.blue).m777a();
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
        this.mDrawable = ShopEditAddContentRow.buildAddDrawable(context);
    }

    public boolean isActionEnabled() {
        return true;
    }
}
