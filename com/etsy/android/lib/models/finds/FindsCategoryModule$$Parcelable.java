package com.etsy.android.lib.models.finds;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.finds.a */
final class FindsCategoryModule$$Parcelable implements Creator<FindsCategoryModule$$Parcelable> {
    private FindsCategoryModule$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2735a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2736a(i);
    }

    public FindsCategoryModule$$Parcelable m2735a(Parcel parcel) {
        return new FindsCategoryModule$$Parcelable(parcel);
    }

    public FindsCategoryModule$$Parcelable[] m2736a(int i) {
        return new FindsCategoryModule$$Parcelable[i];
    }
}
