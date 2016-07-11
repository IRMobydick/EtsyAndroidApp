package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.c */
final class AppreciationPhoto$$Parcelable implements Creator<AppreciationPhoto$$Parcelable> {
    private AppreciationPhoto$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2483a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2484a(i);
    }

    public AppreciationPhoto$$Parcelable m2483a(Parcel parcel) {
        return new AppreciationPhoto$$Parcelable(parcel);
    }

    public AppreciationPhoto$$Parcelable[] m2484a(int i) {
        return new AppreciationPhoto$$Parcelable[i];
    }
}
