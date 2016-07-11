package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class OfferingOption$$Parcelable implements android.os.Parcelable, ax<OfferingOption> {
    public static final ag CREATOR;
    private OfferingOption offeringOption$$10;

    static {
        CREATOR = new ag();
    }

    public OfferingOption$$Parcelable(Parcel parcel) {
        OfferingOption offeringOption;
        if (parcel.readInt() == -1) {
            offeringOption = null;
        } else {
            offeringOption = readcom_etsy_android_lib_models_apiv3_OfferingOption(parcel);
        }
        this.offeringOption$$10 = offeringOption;
    }

    public OfferingOption$$Parcelable(OfferingOption offeringOption) {
        this.offeringOption$$10 = offeringOption;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.offeringOption$$10 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_OfferingOption(this.offeringOption$$10, parcel, i);
    }

    private OfferingOption readcom_etsy_android_lib_models_apiv3_OfferingOption(Parcel parcel) {
        EtsyId etsyId;
        boolean z;
        FormattedMoney formattedMoney = null;
        boolean z2 = true;
        OfferingOption offeringOption = new OfferingOption();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        offeringOption.mValue = etsyId;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        offeringOption.mSelected = z;
        if (parcel.readInt() != -1) {
            formattedMoney = readcom_etsy_android_lib_models_apiv3_FormattedMoney(parcel);
        }
        offeringOption.mDisplayValue = formattedMoney;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        offeringOption.mEnabled = z2;
        return offeringOption;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private FormattedMoney readcom_etsy_android_lib_models_apiv3_FormattedMoney(Parcel parcel) {
        List list = null;
        FormattedMoney formattedMoney = new FormattedMoney();
        formattedMoney.mFormatString = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_Money(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        formattedMoney.mArguments = list;
        return formattedMoney;
    }

    private Money readcom_etsy_android_lib_models_apiv3_Money(Parcel parcel) {
        Money money = new Money();
        money.mDivisor = parcel.readInt();
        money.mCurrencyCode = parcel.readString();
        money.mAmount = parcel.readString();
        return money;
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingOption(OfferingOption offeringOption, Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        if (offeringOption.mValue == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(offeringOption.mValue, parcel, i);
        }
        if (offeringOption.mSelected) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (offeringOption.mDisplayValue == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_FormattedMoney(offeringOption.mDisplayValue, parcel, i);
        }
        if (!offeringOption.mEnabled) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    private void writecom_etsy_android_lib_models_apiv3_FormattedMoney(FormattedMoney formattedMoney, Parcel parcel, int i) {
        parcel.writeString(formattedMoney.mFormatString);
        if (formattedMoney.mArguments == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(formattedMoney.mArguments.size());
        for (Money money : formattedMoney.mArguments) {
            if (money == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_apiv3_Money(money, parcel, i);
            }
        }
    }

    private void writecom_etsy_android_lib_models_apiv3_Money(Money money, Parcel parcel, int i) {
        parcel.writeInt(money.mDivisor);
        parcel.writeString(money.mCurrencyCode);
        parcel.writeString(money.mAmount);
    }

    public int describeContents() {
        return 0;
    }

    public OfferingOption getParcel() {
        return this.offeringOption$$10;
    }
}
