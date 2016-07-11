package com.etsy.android.lib.models.homescreen;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.homescreen.c */
final class MessageCard$$Parcelable implements Creator<MessageCard$$Parcelable> {
    private MessageCard$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2784a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2785a(i);
    }

    public MessageCard$$Parcelable m2784a(Parcel parcel) {
        return new MessageCard$$Parcelable(parcel);
    }

    public MessageCard$$Parcelable[] m2785a(int i) {
        return new MessageCard$$Parcelable[i];
    }
}
