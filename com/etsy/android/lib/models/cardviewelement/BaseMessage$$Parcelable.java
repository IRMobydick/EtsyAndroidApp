package com.etsy.android.lib.models.cardviewelement;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.cardviewelement.a */
final class BaseMessage$$Parcelable implements Creator<BaseMessage$$Parcelable> {
    private BaseMessage$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2595a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2596a(i);
    }

    public BaseMessage$$Parcelable m2595a(Parcel parcel) {
        return new BaseMessage$$Parcelable(parcel);
    }

    public BaseMessage$$Parcelable[] m2596a(int i) {
        return new BaseMessage$$Parcelable[i];
    }
}
