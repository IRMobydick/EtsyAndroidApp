package com.etsy.android.lib.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.core.p */
final class EtsyMoney$$Parcelable implements Creator<EtsyMoney$$Parcelable> {
    private EtsyMoney$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m1650a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m1651a(i);
    }

    public EtsyMoney$$Parcelable m1650a(Parcel parcel) {
        return new EtsyMoney$$Parcelable(parcel);
    }

    public EtsyMoney$$Parcelable[] m1651a(int i) {
        return new EtsyMoney$$Parcelable[i];
    }
}
