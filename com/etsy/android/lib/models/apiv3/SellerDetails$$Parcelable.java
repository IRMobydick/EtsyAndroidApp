package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import org.parceler.ax;

public class SellerDetails$$Parcelable implements android.os.Parcelable, ax<SellerDetails> {
    public static final au CREATOR;
    private SellerDetails sellerDetails$$0;

    static {
        CREATOR = new au();
    }

    public SellerDetails$$Parcelable(Parcel parcel) {
        SellerDetails sellerDetails;
        if (parcel.readInt() == -1) {
            sellerDetails = null;
        } else {
            sellerDetails = readcom_etsy_android_lib_models_apiv3_SellerDetails(parcel);
        }
        this.sellerDetails$$0 = sellerDetails;
    }

    public SellerDetails$$Parcelable(SellerDetails sellerDetails) {
        this.sellerDetails$$0 = sellerDetails;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.sellerDetails$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_SellerDetails(this.sellerDetails$$0, parcel, i);
    }

    private SellerDetails readcom_etsy_android_lib_models_apiv3_SellerDetails(Parcel parcel) {
        SellerDetails sellerDetails = new SellerDetails();
        sellerDetails.mPhone = parcel.readString();
        sellerDetails.mFormattedString = parcel.readString();
        sellerDetails.mCity = parcel.readString();
        sellerDetails.mCountry = parcel.readString();
        sellerDetails.mState = parcel.readString();
        sellerDetails.mFirstName = parcel.readString();
        sellerDetails.mAddressLine2 = parcel.readString();
        sellerDetails.mAddressLine1 = parcel.readString();
        sellerDetails.mPostalCode = parcel.readString();
        sellerDetails.mVatNumber = parcel.readString();
        sellerDetails.mLastName = parcel.readString();
        sellerDetails.mEmail = parcel.readString();
        return sellerDetails;
    }

    private void writecom_etsy_android_lib_models_apiv3_SellerDetails(SellerDetails sellerDetails, Parcel parcel, int i) {
        parcel.writeString(sellerDetails.mPhone);
        parcel.writeString(sellerDetails.mFormattedString);
        parcel.writeString(sellerDetails.mCity);
        parcel.writeString(sellerDetails.mCountry);
        parcel.writeString(sellerDetails.mState);
        parcel.writeString(sellerDetails.mFirstName);
        parcel.writeString(sellerDetails.mAddressLine2);
        parcel.writeString(sellerDetails.mAddressLine1);
        parcel.writeString(sellerDetails.mPostalCode);
        parcel.writeString(sellerDetails.mVatNumber);
        parcel.writeString(sellerDetails.mLastName);
        parcel.writeString(sellerDetails.mEmail);
    }

    public int describeContents() {
        return 0;
    }

    public SellerDetails getParcel() {
        return this.sellerDetails$$0;
    }
}
