package com.etsy.android.lib.models.shopedit.switchrow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import org.parceler.Parcel;

@Parcel
public class ShopEditVacationSwitchRow extends ShopEditSwitchRow {
    public ShopEditVacationSwitchRow(@NonNull CharSequence charSequence, boolean z) {
        super(charSequence, z);
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        this.mToggledOn = !this.mToggledOn;
        shopEditDelegate.toggleVacationSwitch(this);
    }

    public boolean isActionEnabled() {
        return true;
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
    }
}
