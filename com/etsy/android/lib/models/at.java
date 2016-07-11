package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: UserNote$$Parcelable */
final class at implements Creator<UserNote$$Parcelable> {
    private at() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2577a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2578a(i);
    }

    public UserNote$$Parcelable m2577a(Parcel parcel) {
        return new UserNote$$Parcelable(parcel);
    }

    public UserNote$$Parcelable[] m2578a(int i) {
        return new UserNote$$Parcelable[i];
    }
}
