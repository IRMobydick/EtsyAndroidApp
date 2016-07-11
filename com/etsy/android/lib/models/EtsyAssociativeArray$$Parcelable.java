package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.g */
final class EtsyAssociativeArray$$Parcelable implements Creator<EtsyAssociativeArray$$Parcelable> {
    private EtsyAssociativeArray$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2769a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2770a(i);
    }

    public EtsyAssociativeArray$$Parcelable m2769a(Parcel parcel) {
        return new EtsyAssociativeArray$$Parcelable(parcel);
    }

    public EtsyAssociativeArray$$Parcelable[] m2770a(int i) {
        return new EtsyAssociativeArray$$Parcelable[i];
    }
}
