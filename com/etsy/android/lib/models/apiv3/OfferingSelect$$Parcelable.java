package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class OfferingSelect$$Parcelable implements android.os.Parcelable, ax<OfferingSelect> {
    public static final ak CREATOR;
    private OfferingSelect offeringSelect$$28;

    static {
        CREATOR = new ak();
    }

    public OfferingSelect$$Parcelable(Parcel parcel) {
        OfferingSelect offeringSelect;
        if (parcel.readInt() == -1) {
            offeringSelect = null;
        } else {
            offeringSelect = readcom_etsy_android_lib_models_apiv3_OfferingSelect(parcel);
        }
        this.offeringSelect$$28 = offeringSelect;
    }

    public OfferingSelect$$Parcelable(OfferingSelect offeringSelect) {
        this.offeringSelect$$28 = offeringSelect;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.offeringSelect$$28 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_OfferingSelect(this.offeringSelect$$28, parcel, i);
    }

    private OfferingSelect readcom_etsy_android_lib_models_apiv3_OfferingSelect(Parcel parcel) {
        OfferingOption offeringOption;
        boolean z;
        List list = null;
        OfferingSelect offeringSelect = new OfferingSelect();
        if (parcel.readInt() == -1) {
            offeringOption = null;
        } else {
            offeringOption = readcom_etsy_android_lib_models_apiv3_OfferingOption(parcel);
        }
        offeringSelect.mDefaultOption = offeringOption;
        offeringSelect.mPrompt = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_OfferingOption(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        offeringSelect.mOptions = list;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        offeringSelect.mEnabled = z;
        offeringSelect.mLabel = parcel.readString();
        return offeringSelect;
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

    private void writecom_etsy_android_lib_models_apiv3_OfferingSelect(OfferingSelect offeringSelect, Parcel parcel, int i) {
        if (offeringSelect.mDefaultOption == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingOption(offeringSelect.mDefaultOption, parcel, i);
        }
        parcel.writeString(offeringSelect.mPrompt);
        if (offeringSelect.mOptions == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(offeringSelect.mOptions.size());
            for (OfferingOption offeringOption : offeringSelect.mOptions) {
                if (offeringOption == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_OfferingOption(offeringOption, parcel, i);
                }
            }
        }
        parcel.writeInt(offeringSelect.mEnabled ? 1 : 0);
        parcel.writeString(offeringSelect.mLabel);
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

    public OfferingSelect getParcel() {
        return this.offeringSelect$$28;
    }
}
