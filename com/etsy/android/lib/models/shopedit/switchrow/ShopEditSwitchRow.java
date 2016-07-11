package com.etsy.android.lib.models.shopedit.switchrow;

import android.content.Context;
import android.support.annotation.NonNull;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;

public abstract class ShopEditSwitchRow implements ShopEditActionableItem, ShopEditPresentationItem {
    @NonNull
    CharSequence mTitle;
    boolean mToggledOn;

    ShopEditSwitchRow() {
    }

    public ShopEditSwitchRow(@NonNull CharSequence charSequence, boolean z) {
        this.mTitle = charSequence;
        this.mToggledOn = z;
    }

    @NonNull
    public CharSequence getTitle() {
        return this.mTitle;
    }

    public boolean isToggledOn() {
        return this.mToggledOn;
    }

    public void setToggledOn(boolean z) {
        this.mToggledOn = z;
    }

    public int getViewType() {
        return 4;
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
    }
}
