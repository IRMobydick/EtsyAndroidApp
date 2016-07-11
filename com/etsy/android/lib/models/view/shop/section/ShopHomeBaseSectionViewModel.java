package com.etsy.android.lib.models.view.shop.section;

import android.support.annotation.NonNull;
import com.etsy.android.uikit.viewholder.i;
import com.etsy.android.uikit.viewholder.j;

public abstract class ShopHomeBaseSectionViewModel implements i {
    protected CharSequence mHeading;
    protected int maxLines;

    public abstract CharSequence getText();

    ShopHomeBaseSectionViewModel() {
    }

    public ShopHomeBaseSectionViewModel(@NonNull CharSequence charSequence) {
        this.mHeading = charSequence;
    }

    public CharSequence getHeading() {
        return this.mHeading;
    }

    public j getListener() {
        return null;
    }
}
