package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: UserAvatar$$Parcelable */
final class bq implements Creator<UserAvatar$$Parcelable> {
    private bq() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2475a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2476a(i);
    }

    public UserAvatar$$Parcelable m2475a(Parcel parcel) {
        return new UserAvatar$$Parcelable(parcel);
    }

    public UserAvatar$$Parcelable[] m2476a(int i) {
        return new UserAvatar$$Parcelable[i];
    }
}
