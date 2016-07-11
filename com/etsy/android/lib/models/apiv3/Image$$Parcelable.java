package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.n */
final class Image$$Parcelable implements Creator<Image$$Parcelable> {
    private Image$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2545a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2546a(i);
    }

    public Image$$Parcelable m2545a(Parcel parcel) {
        return new Image$$Parcelable(parcel);
    }

    public Image$$Parcelable[] m2546a(int i) {
        return new Image$$Parcelable[i];
    }
}
