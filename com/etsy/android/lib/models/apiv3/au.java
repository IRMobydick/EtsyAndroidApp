package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: SellerDetails$$Parcelable */
final class au implements Creator<SellerDetails$$Parcelable> {
    private au() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2429a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2430a(i);
    }

    public SellerDetails$$Parcelable m2429a(Parcel parcel) {
        return new SellerDetails$$Parcelable(parcel);
    }

    public SellerDetails$$Parcelable[] m2430a(int i) {
        return new SellerDetails$$Parcelable[i];
    }
}
