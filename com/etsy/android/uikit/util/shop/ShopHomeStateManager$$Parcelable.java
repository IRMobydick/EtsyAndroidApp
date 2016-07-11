package com.etsy.android.uikit.util.shop;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.uikit.util.shop.c */
final class ShopHomeStateManager$$Parcelable implements Creator<ShopHomeStateManager$$Parcelable> {
    private ShopHomeStateManager$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5613a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5614a(i);
    }

    public ShopHomeStateManager$$Parcelable m5613a(Parcel parcel) {
        return new ShopHomeStateManager$$Parcelable(parcel);
    }

    public ShopHomeStateManager$$Parcelable[] m5614a(int i) {
        return new ShopHomeStateManager$$Parcelable[i];
    }
}
