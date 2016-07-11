package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: TaxonomyCategory$$Parcelable */
final class bm implements Creator<TaxonomyCategory$$Parcelable> {
    private bm() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2467a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2468a(i);
    }

    public TaxonomyCategory$$Parcelable m2467a(Parcel parcel) {
        return new TaxonomyCategory$$Parcelable(parcel);
    }

    public TaxonomyCategory$$Parcelable[] m2468a(int i) {
        return new TaxonomyCategory$$Parcelable[i];
    }
}
