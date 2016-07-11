package com.etsy.android.lib.models.shopedit;

import android.os.Parcel;
import org.parceler.ax;

public class ShopVideoShareData$$Parcelable implements android.os.Parcelable, ax<ShopVideoShareData> {
    public static final ShopVideoShareData$$Parcelable CREATOR;
    private ShopVideoShareData shopVideoShareData$$6;

    static {
        CREATOR = new ShopVideoShareData$$Parcelable();
    }

    public ShopVideoShareData$$Parcelable(Parcel parcel) {
        ShopVideoShareData shopVideoShareData;
        if (parcel.readInt() == -1) {
            shopVideoShareData = null;
        } else {
            shopVideoShareData = readcom_etsy_android_lib_models_shopedit_ShopVideoShareData(parcel);
        }
        this.shopVideoShareData$$6 = shopVideoShareData;
    }

    public ShopVideoShareData$$Parcelable(ShopVideoShareData shopVideoShareData) {
        this.shopVideoShareData$$6 = shopVideoShareData;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopVideoShareData$$6 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_shopedit_ShopVideoShareData(this.shopVideoShareData$$6, parcel, i);
    }

    private ShopVideoShareData readcom_etsy_android_lib_models_shopedit_ShopVideoShareData(Parcel parcel) {
        ShopVideoShareData shopVideoShareData = new ShopVideoShareData();
        shopVideoShareData.mShareUrl = parcel.readString();
        return shopVideoShareData;
    }

    private void writecom_etsy_android_lib_models_shopedit_ShopVideoShareData(ShopVideoShareData shopVideoShareData, Parcel parcel, int i) {
        parcel.writeString(shopVideoShareData.mShareUrl);
    }

    public int describeContents() {
        return 0;
    }

    public ShopVideoShareData getParcel() {
        return this.shopVideoShareData$$6;
    }
}
