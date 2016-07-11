package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: ShopPolicy$$Parcelable */
final class bb implements Creator<ShopPolicy$$Parcelable> {
    private bb() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2445a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2446a(i);
    }

    public ShopPolicy$$Parcelable m2445a(Parcel parcel) {
        return new ShopPolicy$$Parcelable(parcel);
    }

    public ShopPolicy$$Parcelable[] m2446a(int i) {
        return new ShopPolicy$$Parcelable[i];
    }
}
