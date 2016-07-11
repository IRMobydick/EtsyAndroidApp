package com.google.android.gms.analytics.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Command implements Parcelable {
    @Deprecated
    public static final Creator<Command> CREATOR;
    private String mValue;
    private String zzBc;
    private String zzXo;

    /* renamed from: com.google.android.gms.analytics.internal.Command.1 */
    final class C11361 implements Creator<Command> {
        C11361() {
        }

        @Deprecated
        public Command m6219a(Parcel parcel) {
            return new Command(parcel);
        }

        @Deprecated
        public Command[] m6220a(int i) {
            return new Command[i];
        }

        @Deprecated
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m6219a(parcel);
        }

        @Deprecated
        public /* synthetic */ Object[] newArray(int i) {
            return m6220a(i);
        }
    }

    static {
        CREATOR = new C11361();
    }

    @Deprecated
    Command(Parcel parcel) {
        readFromParcel(parcel);
    }

    @Deprecated
    private void readFromParcel(Parcel parcel) {
        this.zzBc = parcel.readString();
        this.zzXo = parcel.readString();
        this.mValue = parcel.readString();
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.zzBc;
    }

    public String getValue() {
        return this.mValue;
    }

    @Deprecated
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzBc);
        parcel.writeString(this.zzXo);
        parcel.writeString(this.mValue);
    }
}
