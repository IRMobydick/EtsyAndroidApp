package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: ShopCard$$Parcelable */
final class av implements Creator<ShopCard$$Parcelable> {
    private av() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2431a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2432a(i);
    }

    public ShopCard$$Parcelable m2431a(Parcel parcel) {
        return new ShopCard$$Parcelable(parcel);
    }

    public ShopCard$$Parcelable[] m2432a(int i) {
        return new ShopCard$$Parcelable[i];
    }
}
