package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import org.parceler.ax;

public class ReceiptShipping$$Parcelable implements android.os.Parcelable, ax<ReceiptShipping> {
    public static final ap CREATOR;
    private ReceiptShipping receiptShipping$$0;

    static {
        CREATOR = new ap();
    }

    public ReceiptShipping$$Parcelable(Parcel parcel) {
        ReceiptShipping receiptShipping;
        if (parcel.readInt() == -1) {
            receiptShipping = null;
        } else {
            receiptShipping = readcom_etsy_android_lib_models_apiv3_ReceiptShipping(parcel);
        }
        this.receiptShipping$$0 = receiptShipping;
    }

    public ReceiptShipping$$Parcelable(ReceiptShipping receiptShipping) {
        this.receiptShipping$$0 = receiptShipping;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.receiptShipping$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_ReceiptShipping(this.receiptShipping$$0, parcel, i);
    }

    private ReceiptShipping readcom_etsy_android_lib_models_apiv3_ReceiptShipping(Parcel parcel) {
        UserAddressV3 userAddressV3;
        boolean z;
        UserAddressV3 userAddressV32 = null;
        boolean z2 = true;
        ReceiptShipping receiptShipping = new ReceiptShipping();
        receiptShipping.mUpgradeName = parcel.readString();
        if (parcel.readInt() == -1) {
            userAddressV3 = null;
        } else {
            userAddressV3 = readcom_etsy_android_lib_models_apiv3_UserAddressV3(parcel);
        }
        receiptShipping.mToAddress = userAddressV3;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        receiptShipping.mCanMarkAsShipped = z;
        if (parcel.readInt() != -1) {
            userAddressV32 = readcom_etsy_android_lib_models_apiv3_UserAddressV3(parcel);
        }
        receiptShipping.mFromAddress = userAddressV32;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        receiptShipping.mHasUpgrade = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        receiptShipping.mIsFutureShipment = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        receiptShipping.mHasProcessing = z;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        receiptShipping.mWasShipped = z2;
        return receiptShipping;
    }

    private UserAddressV3 readcom_etsy_android_lib_models_apiv3_UserAddressV3(Parcel parcel) {
        EtsyId etsyId;
        boolean z = true;
        EtsyId etsyId2 = null;
        UserAddressV3 userAddressV3 = new UserAddressV3();
        if (parcel.readInt() != 1) {
            z = false;
        }
        userAddressV3.mIsAvailableForMarket = z;
        userAddressV3.mPhone = parcel.readString();
        userAddressV3.mFirstLine = parcel.readString();
        userAddressV3.mCity = parcel.readString();
        userAddressV3.mCountryName = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userAddressV3.mCountryId = etsyId;
        userAddressV3.mState = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userAddressV3.mUserAddressId = etsyId;
        userAddressV3.mSecondLine = parcel.readString();
        userAddressV3.mName = parcel.readString();
        userAddressV3.mZip = parcel.readString();
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userAddressV3.mUserId = etsyId2;
        return userAddressV3;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private void writecom_etsy_android_lib_models_apiv3_ReceiptShipping(ReceiptShipping receiptShipping, Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(receiptShipping.mUpgradeName);
        if (receiptShipping.mToAddress == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_UserAddressV3(receiptShipping.mToAddress, parcel, i);
        }
        if (receiptShipping.mCanMarkAsShipped) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (receiptShipping.mFromAddress == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_UserAddressV3(receiptShipping.mFromAddress, parcel, i);
        }
        if (receiptShipping.mHasUpgrade) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (receiptShipping.mIsFutureShipment) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (receiptShipping.mHasProcessing) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!receiptShipping.mWasShipped) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    private void writecom_etsy_android_lib_models_apiv3_UserAddressV3(UserAddressV3 userAddressV3, Parcel parcel, int i) {
        parcel.writeInt(userAddressV3.mIsAvailableForMarket ? 1 : 0);
        parcel.writeString(userAddressV3.mPhone);
        parcel.writeString(userAddressV3.mFirstLine);
        parcel.writeString(userAddressV3.mCity);
        parcel.writeString(userAddressV3.mCountryName);
        if (userAddressV3.mCountryId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(userAddressV3.mCountryId, parcel, i);
        }
        parcel.writeString(userAddressV3.mState);
        if (userAddressV3.mUserAddressId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(userAddressV3.mUserAddressId, parcel, i);
        }
        parcel.writeString(userAddressV3.mSecondLine);
        parcel.writeString(userAddressV3.mName);
        parcel.writeString(userAddressV3.mZip);
        if (userAddressV3.mUserId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(userAddressV3.mUserId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    public int describeContents() {
        return 0;
    }

    public ReceiptShipping getParcel() {
        return this.receiptShipping$$0;
    }
}
