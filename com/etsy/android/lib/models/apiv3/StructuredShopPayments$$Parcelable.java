package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class StructuredShopPayments$$Parcelable implements android.os.Parcelable, ax<StructuredShopPayments> {
    public static final bg CREATOR;
    private StructuredShopPayments structuredShopPayments$$0;

    static {
        CREATOR = new bg();
    }

    public StructuredShopPayments$$Parcelable(Parcel parcel) {
        StructuredShopPayments structuredShopPayments;
        if (parcel.readInt() == -1) {
            structuredShopPayments = null;
        } else {
            structuredShopPayments = readcom_etsy_android_lib_models_apiv3_StructuredShopPayments(parcel);
        }
        this.structuredShopPayments$$0 = structuredShopPayments;
    }

    public StructuredShopPayments$$Parcelable(StructuredShopPayments structuredShopPayments) {
        this.structuredShopPayments$$0 = structuredShopPayments;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.structuredShopPayments$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_StructuredShopPayments(this.structuredShopPayments$$0, parcel, i);
    }

    private StructuredShopPayments readcom_etsy_android_lib_models_apiv3_StructuredShopPayments(Parcel parcel) {
        List list;
        int i;
        boolean z;
        List list2 = null;
        int i2 = 0;
        StructuredShopPayments structuredShopPayments = new StructuredShopPayments();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            list = new ArrayList();
            for (i = 0; i < readInt; i++) {
                list.add(parcel.readString());
            }
        }
        structuredShopPayments.mManualPaymentMethods = list;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        structuredShopPayments.mAcceptsDirectCheckout = z;
        i = parcel.readInt();
        if (i < 0) {
            list = null;
        } else {
            list = new ArrayList();
            for (int i3 = 0; i3 < i; i3++) {
                list.add(parcel.readString());
            }
        }
        structuredShopPayments.mProtectedPaymentMethods = list;
        int readInt2 = parcel.readInt();
        if (readInt2 >= 0) {
            list2 = new ArrayList();
            while (i2 < readInt2) {
                list2.add(parcel.readString());
                i2++;
            }
        }
        structuredShopPayments.mAcceptedPaymentMethods = list2;
        return structuredShopPayments;
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopPayments(StructuredShopPayments structuredShopPayments, Parcel parcel, int i) {
        if (structuredShopPayments.mManualPaymentMethods == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(structuredShopPayments.mManualPaymentMethods.size());
            for (String writeString : structuredShopPayments.mManualPaymentMethods) {
                parcel.writeString(writeString);
            }
        }
        parcel.writeInt(structuredShopPayments.mAcceptsDirectCheckout ? 1 : 0);
        if (structuredShopPayments.mProtectedPaymentMethods == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(structuredShopPayments.mProtectedPaymentMethods.size());
            for (String writeString2 : structuredShopPayments.mProtectedPaymentMethods) {
                parcel.writeString(writeString2);
            }
        }
        if (structuredShopPayments.mAcceptedPaymentMethods == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(structuredShopPayments.mAcceptedPaymentMethods.size());
        for (String writeString22 : structuredShopPayments.mAcceptedPaymentMethods) {
            parcel.writeString(writeString22);
        }
    }

    public int describeContents() {
        return 0;
    }

    public StructuredShopPayments getParcel() {
        return this.structuredShopPayments$$0;
    }
}
