package com.etsy.android.lib.models.shopedit.contentrow;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;

public interface IShopEditContentRow extends ShopEditPresentationItem {
    @NonNull
    CharSequence getContent();

    int getContentMaxLines();

    @NonNull
    CharSequence getHint();

    @NonNull
    CharSequence getTitle();

    boolean shouldIncludeBottomExtraPadding();
}
