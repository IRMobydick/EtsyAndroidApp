package com.etsy.android.lib.models.finds;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.finds.h */
final class FindsSearchCategory$$Parcelable implements Creator<FindsSearchCategory$$Parcelable> {
    private FindsSearchCategory$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2749a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2750a(i);
    }

    public FindsSearchCategory$$Parcelable m2749a(Parcel parcel) {
        return new FindsSearchCategory$$Parcelable(parcel);
    }

    public FindsSearchCategory$$Parcelable[] m2750a(int i) {
        return new FindsSearchCategory$$Parcelable[i];
    }
}
