package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.a */
final class AWSSession$$Parcelable implements Creator<AWSSession$$Parcelable> {
    private AWSSession$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2387a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2388a(i);
    }

    public AWSSession$$Parcelable m2387a(Parcel parcel) {
        return new AWSSession$$Parcelable(parcel);
    }

    public AWSSession$$Parcelable[] m2388a(int i) {
        return new AWSSession$$Parcelable[i];
    }
}
