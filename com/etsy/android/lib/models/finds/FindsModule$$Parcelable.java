package com.etsy.android.lib.models.finds;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.finds.f */
final class FindsModule$$Parcelable implements Creator<FindsModule$$Parcelable> {
    private FindsModule$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2745a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2746a(i);
    }

    public FindsModule$$Parcelable m2745a(Parcel parcel) {
        return new FindsModule$$Parcelable(parcel);
    }

    public FindsModule$$Parcelable[] m2746a(int i) {
        return new FindsModule$$Parcelable[i];
    }
}
