package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import org.parceler.ax;

public class StructuredShopShippingEstimate$$Parcelable implements android.os.Parcelable, ax<StructuredShopShippingEstimate> {
    public static final bl CREATOR;
    private StructuredShopShippingEstimate structuredShopShippingEstimate$$4;

    static {
        CREATOR = new bl();
    }

    public StructuredShopShippingEstimate$$Parcelable(Parcel parcel) {
        StructuredShopShippingEstimate structuredShopShippingEstimate;
        if (parcel.readInt() == -1) {
            structuredShopShippingEstimate = null;
        } else {
            structuredShopShippingEstimate = m2385x10cd231a(parcel);
        }
        this.structuredShopShippingEstimate$$4 = structuredShopShippingEstimate;
    }

    public StructuredShopShippingEstimate$$Parcelable(StructuredShopShippingEstimate structuredShopShippingEstimate) {
        this.structuredShopShippingEstimate$$4 = structuredShopShippingEstimate;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.structuredShopShippingEstimate$$4 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        m2386xdc035963(this.structuredShopShippingEstimate$$4, parcel, i);
    }

    private StructuredShopShippingEstimate m2385x10cd231a(Parcel parcel) {
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

    private void m2386xdc035963(StructuredShopShippingEstimate structuredShopShippingEstimate, Parcel parcel, int i) {
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

    public StructuredShopShippingEstimate getParcel() {
        return this.structuredShopShippingEstimate$$4;
    }
}
