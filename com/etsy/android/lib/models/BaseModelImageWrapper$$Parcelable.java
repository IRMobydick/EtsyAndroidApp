package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.b */
final class BaseModelImageWrapper$$Parcelable implements Creator<BaseModelImageWrapper$$Parcelable> {
    private BaseModelImageWrapper$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2581a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2582a(i);
    }

    public BaseModelImageWrapper$$Parcelable m2581a(Parcel parcel) {
        return new BaseModelImageWrapper$$Parcelable(parcel);
    }

    public BaseModelImageWrapper$$Parcelable[] m2582a(int i) {
        return new BaseModelImageWrapper$$Parcelable[i];
    }
}
