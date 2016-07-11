package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: SearchGroup$$Parcelable */
final class as implements Creator<SearchGroup$$Parcelable> {
    private as() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2425a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2426a(i);
    }

    public SearchGroup$$Parcelable m2425a(Parcel parcel) {
        return new SearchGroup$$Parcelable(parcel);
    }

    public SearchGroup$$Parcelable[] m2426a(int i) {
        return new SearchGroup$$Parcelable[i];
    }
}
