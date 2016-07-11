package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class StructuredShopShipping$$Parcelable implements android.os.Parcelable, ax<StructuredShopShipping> {
    public static final bk CREATOR;
    private StructuredShopShipping structuredShopShipping$$0;

    static {
        CREATOR = new bk();
    }

    public StructuredShopShipping$$Parcelable(Parcel parcel) {
        StructuredShopShipping structuredShopShipping;
        if (parcel.readInt() == -1) {
            structuredShopShipping = null;
        } else {
            structuredShopShipping = readcom_etsy_android_lib_models_apiv3_StructuredShopShipping(parcel);
        }
        this.structuredShopShipping$$0 = structuredShopShipping;
    }

    public StructuredShopShipping$$Parcelable(StructuredShopShipping structuredShopShipping) {
        this.structuredShopShipping$$0 = structuredShopShipping;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.structuredShopShipping$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_StructuredShopShipping(this.structuredShopShipping$$0, parcel, i);
    }

    private StructuredShopShipping readcom_etsy_android_lib_models_apiv3_StructuredShopShipping(Parcel parcel) {
        List list = null;
        int i = 0;
        boolean z = true;
        StructuredShopShipping structuredShopShipping = new StructuredShopShipping();
        structuredShopShipping.mHasShippingUpgrades = parcel.readInt() == 1;
        structuredShopShipping.mProcessingTimeText = parcel.readString();
        if (parcel.readInt() != 1) {
            z = false;
        }
        structuredShopShipping.mShipsInternational = z;
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            while (i < readInt) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = m2383x10cd231a(parcel);
                }
                arrayList.add(obj);
                i++;
            }
            Object obj2 = arrayList;
        }
        structuredShopShipping.mEstimates = list;
        return structuredShopShipping;
    }

    private StructuredShopShippingEstimate m2383x10cd231a(Parcel parcel) {
        Integer num;
        StructuredShopShippingEstimate structuredShopShippingEstimate = new StructuredShopShippingEstimate();
        structuredShopShippingEstimate.mType = parcel.readString();
        if (parcel.readInt() < 0) {
            num = null;
        } else {
            num = Integer.valueOf(parcel.readInt());
        }
        structuredShopShippingEstimate.mCountryId = num;
        structuredShopShippingEstimate.mDisplayName = parcel.readString();
        structuredShopShippingEstimate.mMax = parcel.readInt();
        structuredShopShippingEstimate.mMin = parcel.readInt();
        structuredShopShippingEstimate.mUnit = parcel.readString();
        structuredShopShippingEstimate.mRegionCode = parcel.readString();
        return structuredShopShippingEstimate;
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopShipping(StructuredShopShipping structuredShopShipping, Parcel parcel, int i) {
        int i2 = 0;
        parcel.writeInt(structuredShopShipping.mHasShippingUpgrades ? 1 : 0);
        parcel.writeString(structuredShopShipping.mProcessingTimeText);
        if (structuredShopShipping.mShipsInternational) {
            i2 = 1;
        }
        parcel.writeInt(i2);
        if (structuredShopShipping.mEstimates == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(structuredShopShipping.mEstimates.size());
        for (StructuredShopShippingEstimate structuredShopShippingEstimate : structuredShopShipping.mEstimates) {
            if (structuredShopShippingEstimate == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                m2384xdc035963(structuredShopShippingEstimate, parcel, i);
            }
        }
    }

    private void m2384xdc035963(StructuredShopShippingEstimate structuredShopShippingEstimate, Parcel parcel, int i) {
        parcel.writeString(structuredShopShippingEstimate.mType);
        if (structuredShopShippingEstimate.mCountryId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(structuredShopShippingEstimate.mCountryId.intValue());
        }
        parcel.writeString(structuredShopShippingEstimate.mDisplayName);
        parcel.writeInt(structuredShopShippingEstimate.mMax);
        parcel.writeInt(structuredShopShippingEstimate.mMin);
        parcel.writeString(structuredShopShippingEstimate.mUnit);
        parcel.writeString(structuredShopShippingEstimate.mRegionCode);
    }

    public int describeContents() {
        return 0;
    }

    public StructuredShopShipping getParcel() {
        return this.structuredShopShipping$$0;
    }
}
