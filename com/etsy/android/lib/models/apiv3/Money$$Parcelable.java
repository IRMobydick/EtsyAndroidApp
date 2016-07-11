package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import org.parceler.ax;

public class Money$$Parcelable implements android.os.Parcelable, ax<Money> {
    public static final ad CREATOR;
    private Money money$$16;

    static {
        CREATOR = new ad();
    }

    public Money$$Parcelable(Parcel parcel) {
        Money money;
        if (parcel.readInt() == -1) {
            money = null;
        } else {
            money = readcom_etsy_android_lib_models_apiv3_Money(parcel);
        }
        this.money$$16 = money;
    }

    public Money$$Parcelable(Money money) {
        this.money$$16 = money;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.money$$16 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_Money(this.money$$16, parcel, i);
    }

    private Money readcom_etsy_android_lib_models_apiv3_Money(Parcel parcel) {
        Money money = new Money();
        money.mDivisor = parcel.readInt();
        money.mCurrencyCode = parcel.readString();
        money.mAmount = parcel.readString();
        return money;
    }

    private void writecom_etsy_android_lib_models_apiv3_Money(Money money, Parcel parcel, int i) {
        parcel.writeInt(money.mDivisor);
        parcel.writeString(money.mCurrencyCode);
        parcel.writeString(money.mAmount);
    }

    public int describeContents() {
        return 0;
    }

    public Money getParcel() {
        return this.money$$16;
    }
}
