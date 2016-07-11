package com.etsy.android.lib.models.apiv3.cart;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.cart.g */
final class MessageToSeller$$Parcelable implements Creator<MessageToSeller$$Parcelable> {
    private MessageToSeller$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2501a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2502a(i);
    }

    public MessageToSeller$$Parcelable m2501a(Parcel parcel) {
        return new MessageToSeller$$Parcelable(parcel);
    }

    public MessageToSeller$$Parcelable[] m2502a(int i) {
        return new MessageToSeller$$Parcelable[i];
    }
}
