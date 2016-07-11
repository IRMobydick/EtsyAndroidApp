package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import org.parceler.ax;

public class Pseudonym$$Parcelable implements android.os.Parcelable, ax<Pseudonym> {
    public static final an CREATOR;
    private Pseudonym pseudonym$$0;

    static {
        CREATOR = new an();
    }

    public Pseudonym$$Parcelable(Parcel parcel) {
        Pseudonym pseudonym;
        if (parcel.readInt() == -1) {
            pseudonym = null;
        } else {
            pseudonym = readcom_etsy_android_lib_models_apiv3_Pseudonym(parcel);
        }
        this.pseudonym$$0 = pseudonym;
    }

    public Pseudonym$$Parcelable(Pseudonym pseudonym) {
        this.pseudonym$$0 = pseudonym;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.pseudonym$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_Pseudonym(this.pseudonym$$0, parcel, i);
    }

    private Pseudonym readcom_etsy_android_lib_models_apiv3_Pseudonym(Parcel parcel) {
        Pseudonym pseudonym = new Pseudonym();
        pseudonym.mPseudonym = parcel.readString();
        return pseudonym;
    }

    private void writecom_etsy_android_lib_models_apiv3_Pseudonym(Pseudonym pseudonym, Parcel parcel, int i) {
        parcel.writeString(pseudonym.mPseudonym);
    }

    public int describeContents() {
        return 0;
    }

    public Pseudonym getParcel() {
        return this.pseudonym$$0;
    }
}
