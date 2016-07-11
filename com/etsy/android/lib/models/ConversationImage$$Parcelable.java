package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.c */
final class ConversationImage$$Parcelable implements Creator<ConversationImage$$Parcelable> {
    private ConversationImage$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2583a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2584a(i);
    }

    public ConversationImage$$Parcelable m2583a(Parcel parcel) {
        return new ConversationImage$$Parcelable(parcel);
    }

    public ConversationImage$$Parcelable[] m2584a(int i) {
        return new ConversationImage$$Parcelable[i];
    }
}
