package com.etsy.android.lib.models.datatypes;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.datatypes.b */
final class EtsyId$$Parcelable implements Creator<EtsyId$$Parcelable> {
    private EtsyId$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2615a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2616a(i);
    }

    public EtsyId$$Parcelable m2615a(Parcel parcel) {
        return new EtsyId$$Parcelable(parcel);
    }

    public EtsyId$$Parcelable[] m2616a(int i) {
        return new EtsyId$$Parcelable[i];
    }
}
