package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.core.EtsyMoney$$PackageHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import org.parceler.ax;

public class Variation$$Parcelable implements android.os.Parcelable, ax<Variation> {
    public static final au CREATOR;
    private Variation variation$$118;

    static {
        CREATOR = new au();
    }

    public Variation$$Parcelable(Parcel parcel) {
        Variation variation;
        if (parcel.readInt() == -1) {
            variation = null;
        } else {
            variation = readcom_etsy_android_lib_models_Variation(parcel);
        }
        this.variation$$118 = variation;
    }

    public Variation$$Parcelable(Variation variation) {
        this.variation$$118 = variation;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.variation$$118 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_Variation(this.variation$$118, parcel, i);
    }

    private Variation readcom_etsy_android_lib_models_Variation(Parcel parcel) {
        Option option;
        boolean z = true;
        int i = 0;
        List list = null;
        Variation variation = new Variation();
        if (parcel.readInt() != 1) {
            z = false;
        }
        variation.mIsValid = z;
        if (parcel.readInt() == -1) {
            option = null;
        } else {
            option = readcom_etsy_android_lib_models_Option(parcel);
        }
        variation.mSelectedOption = option;
        variation.mFormattedValue = parcel.readString();
        variation.mFormattedName = parcel.readString();
        variation.mPropertyId = parcel.readLong();
        variation.mValueId = parcel.readLong();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            while (i < readInt) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_Option(parcel);
                }
                arrayList.add(obj);
                i++;
            }
            Object obj2 = arrayList;
        }
        variation.mOptions = list;
        return variation;
    }

    private Option readcom_etsy_android_lib_models_Option(Parcel parcel) {
        EtsyMoney etsyMoney;
        EtsyMoney etsyMoney2 = null;
        Option option = new Option();
        option.mVariationPropertyId = parcel.readLong();
        option.mFormattedValue = parcel.readString();
        option.mValueId = parcel.readLong();
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        option.mPrice = etsyMoney;
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        option.mPriceDiff = etsyMoney;
        if (parcel.readInt() != -1) {
            etsyMoney2 = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        option.mConvertedPrice = etsyMoney2;
        option.mIsAvailable = parcel.readInt() == 1;
        return option;
    }

    private EtsyMoney readcom_etsy_android_lib_core_EtsyMoney(Parcel parcel) {
        Integer num;
        EtsyMoney etsyMoney = new EtsyMoney((BigDecimal) parcel.readSerializable(), (Currency) parcel.readSerializable());
        if (parcel.readInt() < 0) {
            num = null;
        } else {
            num = Integer.valueOf(parcel.readInt());
        }
        EtsyMoney$$PackageHelper.m1649a(etsyMoney, num);
        return etsyMoney;
    }

    private void writecom_etsy_android_lib_models_Variation(Variation variation, Parcel parcel, int i) {
        parcel.writeInt(variation.mIsValid ? 1 : 0);
        if (variation.mSelectedOption == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_Option(variation.mSelectedOption, parcel, i);
        }
        parcel.writeString(variation.mFormattedValue);
        parcel.writeString(variation.mFormattedName);
        parcel.writeLong(variation.mPropertyId);
        parcel.writeLong(variation.mValueId);
        if (variation.mOptions == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(variation.mOptions.size());
        for (Option option : variation.mOptions) {
            if (option == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_Option(option, parcel, i);
            }
        }
    }

    private void writecom_etsy_android_lib_models_Option(Option option, Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeLong(option.mVariationPropertyId);
        parcel.writeString(option.mFormattedValue);
        parcel.writeLong(option.mValueId);
        if (option.mPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(option.mPrice, parcel, i);
        }
        if (option.mPriceDiff == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(option.mPriceDiff, parcel, i);
        }
        if (option.mConvertedPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(option.mConvertedPrice, parcel, i);
        }
        if (!option.mIsAvailable) {
            i2 = 0;
        }
        parcel.writeInt(i2);
    }

    private void writecom_etsy_android_lib_core_EtsyMoney(EtsyMoney etsyMoney, Parcel parcel, int i) {
        parcel.writeSerializable(etsyMoney.getAmount());
        parcel.writeSerializable(etsyMoney.getCurrency());
        if (EtsyMoney$$PackageHelper.m1648a(etsyMoney) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        parcel.writeInt(EtsyMoney$$PackageHelper.m1648a(etsyMoney).intValue());
    }

    public int describeContents() {
        return 0;
    }

    public Variation getParcel() {
        return this.variation$$118;
    }
}
