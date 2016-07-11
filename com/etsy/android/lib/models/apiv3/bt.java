package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: UserProfileV3$$Parcelable */
final class bt implements Creator<UserProfileV3$$Parcelable> {
    private bt() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2481a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2482a(i);
    }

    public UserProfileV3$$Parcelable m2481a(Parcel parcel) {
        return new UserProfileV3$$Parcelable(parcel);
    }

    public UserProfileV3$$Parcelable[] m2482a(int i) {
        return new UserProfileV3$$Parcelable[i];
    }
}
