package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: UserProfilePage$$Parcelable */
final class bs implements Creator<UserProfilePage$$Parcelable> {
    private bs() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2479a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2480a(i);
    }

    public UserProfilePage$$Parcelable m2479a(Parcel parcel) {
        return new UserProfilePage$$Parcelable(parcel);
    }

    public UserProfilePage$$Parcelable[] m2480a(int i) {
        return new UserProfilePage$$Parcelable[i];
    }
}
