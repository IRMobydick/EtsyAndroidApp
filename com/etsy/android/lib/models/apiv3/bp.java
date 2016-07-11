package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: UserAddressV3$$Parcelable */
final class bp implements Creator<UserAddressV3$$Parcelable> {
    private bp() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2473a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2474a(i);
    }

    public UserAddressV3$$Parcelable m2473a(Parcel parcel) {
        return new UserAddressV3$$Parcelable(parcel);
    }

    public UserAddressV3$$Parcelable[] m2474a(int i) {
        return new UserAddressV3$$Parcelable[i];
    }
}
