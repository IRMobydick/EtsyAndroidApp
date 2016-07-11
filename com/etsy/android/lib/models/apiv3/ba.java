package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: ShopListingsSearchResult$$Parcelable */
final class ba implements Creator<ShopListingsSearchResult$$Parcelable> {
    private ba() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2443a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2444a(i);
    }

    public ShopListingsSearchResult$$Parcelable m2443a(Parcel parcel) {
        return new ShopListingsSearchResult$$Parcelable(parcel);
    }

    public ShopListingsSearchResult$$Parcelable[] m2444a(int i) {
        return new ShopListingsSearchResult$$Parcelable[i];
    }
}
