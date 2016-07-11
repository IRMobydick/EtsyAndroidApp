package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.i */
final class FacetCount$$Parcelable implements Creator<FacetCount$$Parcelable> {
    private FacetCount$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2531a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2532a(i);
    }

    public FacetCount$$Parcelable m2531a(Parcel parcel) {
        return new FacetCount$$Parcelable(parcel);
    }

    public FacetCount$$Parcelable[] m2532a(int i) {
        return new FacetCount$$Parcelable[i];
    }
}
