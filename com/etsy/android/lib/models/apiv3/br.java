package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: UserCard$$Parcelable */
final class br implements Creator<UserCard$$Parcelable> {
    private br() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2477a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2478a(i);
    }

    public UserCard$$Parcelable m2477a(Parcel parcel) {
        return new UserCard$$Parcelable(parcel);
    }

    public UserCard$$Parcelable[] m2478a(int i) {
        return new UserCard$$Parcelable[i];
    }
}
