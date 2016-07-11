package com.etsy.android.lib.models.shopedit.contentrow;

import android.support.annotation.NonNull;

/* renamed from: com.etsy.android.lib.models.shopedit.contentrow.c */
abstract class ShopEditComposedContentRow implements IShopEditContentRow {
    @NonNull
    abstract ShopEditContentRow getContentRow();

    ShopEditComposedContentRow() {
    }

    @NonNull
    public CharSequence getContent() {
        return getContentRow().getContent();
    }

    public int getContentMaxLines() {
        return getContentRow().getContentMaxLines();
    }

    @NonNull
    public CharSequence getHint() {
        return getContentRow().getHint();
    }

    @NonNull
    public CharSequence getTitle() {
        return getContentRow().getTitle();
    }

    public boolean shouldIncludeBottomExtraPadding() {
        return getContentRow().shouldIncludeBottomExtraPadding();
    }

    public int getViewType() {
        return getContentRow().getViewType();
    }
}
