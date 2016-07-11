package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import org.parceler.ax;

public class StructuredShopCharLimits$$Parcelable implements android.os.Parcelable, ax<StructuredShopCharLimits> {
    public static final bf CREATOR;
    private StructuredShopCharLimits structuredShopCharLimits$$0;

    static {
        CREATOR = new bf();
    }

    public StructuredShopCharLimits$$Parcelable(Parcel parcel) {
        StructuredShopCharLimits structuredShopCharLimits;
        if (parcel.readInt() == -1) {
            structuredShopCharLimits = null;
        } else {
            structuredShopCharLimits = readcom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(parcel);
        }
        this.structuredShopCharLimits$$0 = structuredShopCharLimits;
    }

    public StructuredShopCharLimits$$Parcelable(StructuredShopCharLimits structuredShopCharLimits) {
        this.structuredShopCharLimits$$0 = structuredShopCharLimits;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.structuredShopCharLimits$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(this.structuredShopCharLimits$$0, parcel, i);
    }

    private StructuredShopCharLimits readcom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(Parcel parcel) {
        StructuredShopCharLimits structuredShopCharLimits = new StructuredShopCharLimits();
        structuredShopCharLimits.mPrivacyPolicyOtherLimit = parcel.readInt();
        return structuredShopCharLimits;
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(StructuredShopCharLimits structuredShopCharLimits, Parcel parcel, int i) {
        parcel.writeInt(structuredShopCharLimits.mPrivacyPolicyOtherLimit);
    }

    public int describeContents() {
        return 0;
    }

    public StructuredShopCharLimits getParcel() {
        return this.structuredShopCharLimits$$0;
    }
}
