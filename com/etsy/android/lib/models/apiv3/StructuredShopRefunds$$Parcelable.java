package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class StructuredShopRefunds$$Parcelable implements android.os.Parcelable, ax<StructuredShopRefunds> {
    public static final bj CREATOR;
    private StructuredShopRefunds structuredShopRefunds$$0;

    static {
        CREATOR = new bj();
    }

    public StructuredShopRefunds$$Parcelable(Parcel parcel) {
        StructuredShopRefunds structuredShopRefunds;
        if (parcel.readInt() == -1) {
            structuredShopRefunds = null;
        } else {
            structuredShopRefunds = readcom_etsy_android_lib_models_apiv3_StructuredShopRefunds(parcel);
        }
        this.structuredShopRefunds$$0 = structuredShopRefunds;
    }

    public StructuredShopRefunds$$Parcelable(StructuredShopRefunds structuredShopRefunds) {
        this.structuredShopRefunds$$0 = structuredShopRefunds;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.structuredShopRefunds$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_StructuredShopRefunds(this.structuredShopRefunds$$0, parcel, i);
    }

    private StructuredShopRefunds readcom_etsy_android_lib_models_apiv3_StructuredShopRefunds(Parcel parcel) {
        boolean z;
        List list;
        boolean z2 = true;
        StructuredShopRefunds structuredShopRefunds = new StructuredShopRefunds();
        structuredShopRefunds.mCancellationWindowType = parcel.readString();
        structuredShopRefunds.mAcceptedSummaryString = parcel.readString();
        structuredShopRefunds.mCancelWithinHours = parcel.readInt();
        structuredShopRefunds.mReturnWithinDays = parcel.readInt();
        structuredShopRefunds.mAcceptsCancellations = parcel.readInt() == 1;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        structuredShopRefunds.mExchanges = z;
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                arrayList.add((NonRefundableItem) parcel.readSerializable());
            }
            Object obj = arrayList;
        }
        structuredShopRefunds.mNonRefundableItems = list;
        structuredShopRefunds.mContactWithinDays = parcel.readInt();
        structuredShopRefunds.mNotAcceptedSummaryString = parcel.readString();
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        structuredShopRefunds.mAcceptsReturns = z2;
        return structuredShopRefunds;
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopRefunds(StructuredShopRefunds structuredShopRefunds, Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(structuredShopRefunds.mCancellationWindowType);
        parcel.writeString(structuredShopRefunds.mAcceptedSummaryString);
        parcel.writeInt(structuredShopRefunds.mCancelWithinHours);
        parcel.writeInt(structuredShopRefunds.mReturnWithinDays);
        parcel.writeInt(structuredShopRefunds.mAcceptsCancellations ? 1 : 0);
        if (structuredShopRefunds.mExchanges) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (structuredShopRefunds.mNonRefundableItems == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(structuredShopRefunds.mNonRefundableItems.size());
            for (NonRefundableItem writeSerializable : structuredShopRefunds.mNonRefundableItems) {
                parcel.writeSerializable(writeSerializable);
            }
        }
        parcel.writeInt(structuredShopRefunds.mContactWithinDays);
        parcel.writeString(structuredShopRefunds.mNotAcceptedSummaryString);
        if (!structuredShopRefunds.mAcceptsReturns) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    public int describeContents() {
        return 0;
    }

    public StructuredShopRefunds getParcel() {
        return this.structuredShopRefunds$$0;
    }
}
