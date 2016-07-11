package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.util.au;
import org.parceler.ax;

public class ShopPolicy$$Parcelable implements android.os.Parcelable, ax<ShopPolicy> {
    public static final bb CREATOR;
    private ShopPolicy shopPolicy$$3;

    static {
        CREATOR = new bb();
    }

    public ShopPolicy$$Parcelable(Parcel parcel) {
        ShopPolicy shopPolicy;
        if (parcel.readInt() == -1) {
            shopPolicy = null;
        } else {
            shopPolicy = readcom_etsy_android_lib_models_apiv3_ShopPolicy(parcel);
        }
        this.shopPolicy$$3 = shopPolicy;
    }

    public ShopPolicy$$Parcelable(ShopPolicy shopPolicy) {
        this.shopPolicy$$3 = shopPolicy;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopPolicy$$3 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_ShopPolicy(this.shopPolicy$$3, parcel, i);
    }

    private ShopPolicy readcom_etsy_android_lib_models_apiv3_ShopPolicy(Parcel parcel) {
        boolean z = true;
        ShopPolicy shopPolicy = new ShopPolicy();
        shopPolicy.mWelcomeMessage = parcel.readString();
        shopPolicy.mAdditionalInformationMessage = parcel.readString();
        shopPolicy.mPaymentPolicy = parcel.readString();
        shopPolicy.mShippingPolicy = parcel.readString();
        shopPolicy.mRefundPolicy = parcel.readString();
        if (parcel.readInt() != 1) {
            z = false;
        }
        shopPolicy.mHasNoPolicies = z;
        shopPolicy.mUpdateDate = new au().m3269a(parcel);
        return shopPolicy;
    }

    private void writecom_etsy_android_lib_models_apiv3_ShopPolicy(ShopPolicy shopPolicy, Parcel parcel, int i) {
        parcel.writeString(shopPolicy.mWelcomeMessage);
        parcel.writeString(shopPolicy.mAdditionalInformationMessage);
        parcel.writeString(shopPolicy.mPaymentPolicy);
        parcel.writeString(shopPolicy.mShippingPolicy);
        parcel.writeString(shopPolicy.mRefundPolicy);
        parcel.writeInt(shopPolicy.mHasNoPolicies ? 1 : 0);
        new au().m3270a(shopPolicy.mUpdateDate, parcel);
    }

    public int describeContents() {
        return 0;
    }

    public ShopPolicy getParcel() {
        return this.shopPolicy$$3;
    }
}
