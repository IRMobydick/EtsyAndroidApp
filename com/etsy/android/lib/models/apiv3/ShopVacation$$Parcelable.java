package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import org.parceler.ax;

public class ShopVacation$$Parcelable implements android.os.Parcelable, ax<ShopVacation> {
    public static final be CREATOR;
    private ShopVacation shopVacation$$0;

    static {
        CREATOR = new be();
    }

    public ShopVacation$$Parcelable(Parcel parcel) {
        ShopVacation shopVacation;
        if (parcel.readInt() == -1) {
            shopVacation = null;
        } else {
            shopVacation = readcom_etsy_android_lib_models_apiv3_ShopVacation(parcel);
        }
        this.shopVacation$$0 = shopVacation;
    }

    public ShopVacation$$Parcelable(ShopVacation shopVacation) {
        this.shopVacation$$0 = shopVacation;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopVacation$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_ShopVacation(this.shopVacation$$0, parcel, i);
    }

    private ShopVacation readcom_etsy_android_lib_models_apiv3_ShopVacation(Parcel parcel) {
        Boolean bool;
        boolean z = true;
        ShopVacation shopVacation = new ShopVacation();
        shopVacation.mVacationAutoReply = parcel.readString();
        if (parcel.readInt() < 0) {
            bool = null;
        } else {
            if (parcel.readInt() != 1) {
                z = false;
            }
            bool = Boolean.valueOf(z);
        }
        shopVacation.mVacationEnabled = bool;
        shopVacation.mVacationMsg = parcel.readString();
        return shopVacation;
    }

    private void writecom_etsy_android_lib_models_apiv3_ShopVacation(ShopVacation shopVacation, Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeString(shopVacation.mVacationAutoReply);
        if (shopVacation.mVacationEnabled == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            if (!shopVacation.mVacationEnabled.booleanValue()) {
                i2 = 0;
            }
            parcel.writeInt(i2);
        }
        parcel.writeString(shopVacation.mVacationMsg);
    }

    public int describeContents() {
        return 0;
    }

    public ShopVacation getParcel() {
        return this.shopVacation$$0;
    }
}
