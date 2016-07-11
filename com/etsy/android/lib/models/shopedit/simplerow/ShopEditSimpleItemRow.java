package com.etsy.android.lib.models.shopedit.simplerow;

import android.content.Context;
import android.support.annotation.NonNull;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import org.parceler.Parcel;

@Parcel
public class ShopEditSimpleItemRow implements ShopEditPresentationItem {
    int mViewType;

    public ShopEditSimpleItemRow(int i) {
        this.mViewType = i;
    }

    public int getViewType() {
        return this.mViewType;
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
    }
}
