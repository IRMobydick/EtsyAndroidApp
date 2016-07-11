package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import org.parceler.ax;

public class OfferingRangeSelect$$Parcelable implements android.os.Parcelable, ax<OfferingRangeSelect> {
    public static final ai CREATOR;
    private OfferingRangeSelect offeringRangeSelect$$21;

    static {
        CREATOR = new ai();
    }

    public OfferingRangeSelect$$Parcelable(Parcel parcel) {
        OfferingRangeSelect offeringRangeSelect;
        if (parcel.readInt() == -1) {
            offeringRangeSelect = null;
        } else {
            offeringRangeSelect = readcom_etsy_android_lib_models_apiv3_OfferingRangeSelect(parcel);
        }
        this.offeringRangeSelect$$21 = offeringRangeSelect;
    }

    public OfferingRangeSelect$$Parcelable(OfferingRangeSelect offeringRangeSelect) {
        this.offeringRangeSelect$$21 = offeringRangeSelect;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.offeringRangeSelect$$21 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_OfferingRangeSelect(this.offeringRangeSelect$$21, parcel, i);
    }

    private OfferingRangeSelect readcom_etsy_android_lib_models_apiv3_OfferingRangeSelect(Parcel parcel) {
        boolean z = true;
        OfferingRangeSelect offeringRangeSelect = new OfferingRangeSelect();
        offeringRangeSelect.mMax = parcel.readInt();
        offeringRangeSelect.mMin = parcel.readInt();
        if (parcel.readInt() != 1) {
            z = false;
        }
        offeringRangeSelect.mEnabled = z;
        offeringRangeSelect.mStep = parcel.readInt();
        offeringRangeSelect.mLabel = parcel.readString();
        return offeringRangeSelect;
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingRangeSelect(OfferingRangeSelect offeringRangeSelect, Parcel parcel, int i) {
        parcel.writeInt(offeringRangeSelect.mMax);
        parcel.writeInt(offeringRangeSelect.mMin);
        parcel.writeInt(offeringRangeSelect.mEnabled ? 1 : 0);
        parcel.writeInt(offeringRangeSelect.mStep);
        parcel.writeString(offeringRangeSelect.mLabel);
    }

    public int describeContents() {
        return 0;
    }

    public OfferingRangeSelect getParcel() {
        return this.offeringRangeSelect$$21;
    }
}
