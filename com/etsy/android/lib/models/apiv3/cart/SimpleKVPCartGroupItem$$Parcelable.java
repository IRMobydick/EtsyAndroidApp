package com.etsy.android.lib.models.apiv3.cart;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.cart.p */
final class SimpleKVPCartGroupItem$$Parcelable implements Creator<SimpleKVPCartGroupItem$$Parcelable> {
    private SimpleKVPCartGroupItem$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2519a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2520a(i);
    }

    public SimpleKVPCartGroupItem$$Parcelable m2519a(Parcel parcel) {
        return new SimpleKVPCartGroupItem$$Parcelable(parcel);
    }

    public SimpleKVPCartGroupItem$$Parcelable[] m2520a(int i) {
        return new SimpleKVPCartGroupItem$$Parcelable[i];
    }
}
