package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import org.parceler.ax;

public class Offering$$Parcelable implements android.os.Parcelable, ax<Offering> {
    public static final af CREATOR;
    private Offering offering$$9;

    static {
        CREATOR = new af();
    }

    public Offering$$Parcelable(Parcel parcel) {
        Offering offering;
        if (parcel.readInt() == -1) {
            offering = null;
        } else {
            offering = readcom_etsy_android_lib_models_apiv3_Offering(parcel);
        }
        this.offering$$9 = offering;
    }

    public Offering$$Parcelable(Offering offering) {
        this.offering$$9 = offering;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.offering$$9 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_Offering(this.offering$$9, parcel, i);
    }

    private Offering readcom_etsy_android_lib_models_apiv3_Offering(Parcel parcel) {
        EtsyId etsyId;
        OfferingPrice offeringPrice;
        EtsyId etsyId2 = null;
        Offering offering = new Offering();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        offering.mOfferingId = etsyId;
        if (parcel.readInt() == -1) {
            offeringPrice = null;
        } else {
            offeringPrice = readcom_etsy_android_lib_models_apiv3_OfferingPrice(parcel);
        }
        offering.mPrice = offeringPrice;
        offering.mQuantity = parcel.readInt();
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        offering.mProductId = etsyId2;
        return offering;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private OfferingPrice readcom_etsy_android_lib_models_apiv3_OfferingPrice(Parcel parcel) {
        OfferingPrice offeringPrice = new OfferingPrice();
        offeringPrice.mCurrencyFormattedShort = parcel.readString();
        offeringPrice.mCurrencyCode = parcel.readString();
        offeringPrice.mCurrencyFormattedRaw = parcel.readString();
        offeringPrice.mCurrencyFormattedLong = parcel.readString();
        return offeringPrice;
    }

    private void writecom_etsy_android_lib_models_apiv3_Offering(Offering offering, Parcel parcel, int i) {
        if (offering.mOfferingId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(offering.mOfferingId, parcel, i);
        }
        if (offering.mPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingPrice(offering.mPrice, parcel, i);
        }
        parcel.writeInt(offering.mQuantity);
        if (offering.mProductId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(offering.mProductId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
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

    public Offering getParcel() {
        return this.offering$$9;
    }
}
