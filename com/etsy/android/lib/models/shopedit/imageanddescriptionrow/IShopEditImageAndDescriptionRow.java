package com.etsy.android.lib.models.shopedit.imageanddescriptionrow;

import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.uikit.viewholder.c;

public interface IShopEditImageAndDescriptionRow extends ShopEditPresentationItem, c {
    boolean isLoading();

    void setLoading(boolean z);
}
