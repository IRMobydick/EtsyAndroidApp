package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import org.parceler.ax;

public class OfferingPrice$$Parcelable implements android.os.Parcelable, ax<OfferingPrice> {
    public static final ah CREATOR;
    private OfferingPrice offeringPrice$$33;

    static {
        CREATOR = new ah();
    }

    public OfferingPrice$$Parcelable(Parcel parcel) {
        OfferingPrice offeringPrice;
        if (parcel.readInt() == -1) {
            offeringPrice = null;
        } else {
            offeringPrice = readcom_etsy_android_lib_models_apiv3_OfferingPrice(parcel);
        }
        this.offeringPrice$$33 = offeringPrice;
    }

    public OfferingPrice$$Parcelable(OfferingPrice offeringPrice) {
        this.offeringPrice$$33 = offeringPrice;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.offeringPrice$$33 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_OfferingPrice(this.offeringPrice$$33, parcel, i);
    }

    private OfferingPrice readcom_etsy_android_lib_models_apiv3_OfferingPrice(Parcel parcel) {
        OfferingPrice offeringPrice = new OfferingPrice();
        offeringPrice.mCurrencyFormattedShort = parcel.readString();
        offeringPrice.mCurrencyCode = parcel.readString();
        offeringPrice.mCurrencyFormattedRaw = parcel.readString();
        offeringPrice.mCurrencyFormattedLong = parcel.readString();
        return offeringPrice;
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingPrice(OfferingPrice offeringPrice, Parcel parcel, int i) {
        parcel.writeString(offeringPrice.mCurrencyFormattedShort);
        parcel.writeString(offeringPrice.mCurrencyCode);
        parcel.writeString(offeringPrice.mCurrencyFormattedRaw);
        parcel.writeString(offeringPrice.mCurrencyFormattedLong);
    }

    public int describeContents() {
        return 0;
    }

    public OfferingPrice getParcel() {
        return this.offeringPrice$$33;
    }
}
