package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.uikit.util.MachineTranslationViewState$$PackageHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.parceler.ax;

public class StructuredShopPolicies$$Parcelable implements android.os.Parcelable, ax<StructuredShopPolicies> {
    public static final bh CREATOR;
    private StructuredShopPolicies structuredShopPolicies$$3;

    static {
        CREATOR = new bh();
    }

    public StructuredShopPolicies$$Parcelable(Parcel parcel) {
        StructuredShopPolicies structuredShopPolicies;
        if (parcel.readInt() == -1) {
            structuredShopPolicies = null;
        } else {
            structuredShopPolicies = readcom_etsy_android_lib_models_apiv3_StructuredShopPolicies(parcel);
        }
        this.structuredShopPolicies$$3 = structuredShopPolicies;
    }

    public StructuredShopPolicies$$Parcelable(StructuredShopPolicies structuredShopPolicies) {
        this.structuredShopPolicies$$3 = structuredShopPolicies;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.structuredShopPolicies$$3 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_StructuredShopPolicies(this.structuredShopPolicies$$3, parcel, i);
    }

    private StructuredShopPolicies readcom_etsy_android_lib_models_apiv3_StructuredShopPolicies(Parcel parcel) {
        StructuredShopShipping structuredShopShipping;
        StructuredShopPrivacy structuredShopPrivacy;
        StructuredShopPayments structuredShopPayments;
        EtsyId etsyId;
        StructuredShopRefunds structuredShopRefunds;
        StructuredShopCharLimits structuredShopCharLimits = null;
        StructuredShopPolicies structuredShopPolicies = new StructuredShopPolicies();
        if (parcel.readInt() == -1) {
            structuredShopShipping = null;
        } else {
            structuredShopShipping = readcom_etsy_android_lib_models_apiv3_StructuredShopShipping(parcel);
        }
        structuredShopPolicies.mShipping = structuredShopShipping;
        if (parcel.readInt() == -1) {
            structuredShopPrivacy = null;
        } else {
            structuredShopPrivacy = readcom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(parcel);
        }
        structuredShopPolicies.mPrivacy = structuredShopPrivacy;
        if (parcel.readInt() == -1) {
            structuredShopPayments = null;
        } else {
            structuredShopPayments = readcom_etsy_android_lib_models_apiv3_StructuredShopPayments(parcel);
        }
        structuredShopPolicies.mPayments = structuredShopPayments;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        structuredShopPolicies.mPoliciesId = etsyId;
        structuredShopPolicies.mAdditionalTermsAndConditions = parcel.readString();
        if (parcel.readInt() == -1) {
            structuredShopRefunds = null;
        } else {
            structuredShopRefunds = readcom_etsy_android_lib_models_apiv3_StructuredShopRefunds(parcel);
        }
        structuredShopPolicies.mRefunds = structuredShopRefunds;
        structuredShopPolicies.mShopInGermany = parcel.readInt() == 1;
        if (parcel.readInt() != -1) {
            structuredShopCharLimits = readcom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(parcel);
        }
        structuredShopPolicies.mCharacterLimits = structuredShopCharLimits;
        structuredShopPolicies.mUpdateDate = (Date) parcel.readSerializable();
        return structuredShopPolicies;
    }

    private StructuredShopShipping readcom_etsy_android_lib_models_apiv3_StructuredShopShipping(Parcel parcel) {
        List list = null;
        int i = 0;
        boolean z = true;
        StructuredShopShipping structuredShopShipping = new StructuredShopShipping();
        structuredShopShipping.mHasShippingUpgrades = parcel.readInt() == 1;
        structuredShopShipping.mProcessingTimeText = parcel.readString();
        if (parcel.readInt() != 1) {
            z = false;
        }
        structuredShopShipping.mShipsInternational = z;
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            while (i < readInt) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = m2381x10cd231a(parcel);
                }
                arrayList.add(obj);
                i++;
            }
            Object obj2 = arrayList;
        }
        structuredShopShipping.mEstimates = list;
        return structuredShopShipping;
    }

    private StructuredShopShippingEstimate m2381x10cd231a(Parcel parcel) {
        Integer num;
        StructuredShopShippingEstimate structuredShopShippingEstimate = new StructuredShopShippingEstimate();
        structuredShopShippingEstimate.mType = parcel.readString();
        if (parcel.readInt() < 0) {
            num = null;
        } else {
            num = Integer.valueOf(parcel.readInt());
        }
        structuredShopShippingEstimate.mCountryId = num;
        structuredShopShippingEstimate.mDisplayName = parcel.readString();
        structuredShopShippingEstimate.mMax = parcel.readInt();
        structuredShopShippingEstimate.mMin = parcel.readInt();
        structuredShopShippingEstimate.mUnit = parcel.readString();
        structuredShopShippingEstimate.mRegionCode = parcel.readString();
        return structuredShopShippingEstimate;
    }

    private StructuredShopPrivacy readcom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(Parcel parcel) {
        MachineTranslationViewState machineTranslationViewState;
        StructuredShopPrivacy structuredShopPrivacy = new StructuredShopPrivacy();
        structuredShopPrivacy.mTranslatedOtherText = parcel.readString();
        structuredShopPrivacy.mHeader = parcel.readString();
        if (parcel.readInt() == -1) {
            machineTranslationViewState = null;
        } else {
            machineTranslationViewState = readcom_etsy_android_uikit_util_MachineTranslationViewState(parcel);
        }
        structuredShopPrivacy.mOtherTranslationState = machineTranslationViewState;
        structuredShopPrivacy.mFlags = (PrivacyFlags) parcel.readSerializable();
        return structuredShopPrivacy;
    }

    private MachineTranslationViewState readcom_etsy_android_uikit_util_MachineTranslationViewState(Parcel parcel) {
        boolean z = true;
        MachineTranslationViewState machineTranslationViewState = new MachineTranslationViewState();
        MachineTranslationViewState$$PackageHelper.m5555a(machineTranslationViewState, parcel.readInt());
        if (parcel.readInt() != 1) {
            z = false;
        }
        MachineTranslationViewState$$PackageHelper.m5556a(machineTranslationViewState, z);
        return machineTranslationViewState;
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

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
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

    private StructuredShopCharLimits readcom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(Parcel parcel) {
        StructuredShopCharLimits structuredShopCharLimits = new StructuredShopCharLimits();
        structuredShopCharLimits.mPrivacyPolicyOtherLimit = parcel.readInt();
        return structuredShopCharLimits;
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopPolicies(StructuredShopPolicies structuredShopPolicies, Parcel parcel, int i) {
        if (structuredShopPolicies.mShipping == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopShipping(structuredShopPolicies.mShipping, parcel, i);
        }
        if (structuredShopPolicies.mPrivacy == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(structuredShopPolicies.mPrivacy, parcel, i);
        }
        if (structuredShopPolicies.mPayments == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopPayments(structuredShopPolicies.mPayments, parcel, i);
        }
        if (structuredShopPolicies.mPoliciesId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(structuredShopPolicies.mPoliciesId, parcel, i);
        }
        parcel.writeString(structuredShopPolicies.mAdditionalTermsAndConditions);
        if (structuredShopPolicies.mRefunds == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopRefunds(structuredShopPolicies.mRefunds, parcel, i);
        }
        parcel.writeInt(structuredShopPolicies.mShopInGermany ? 1 : 0);
        if (structuredShopPolicies.mCharacterLimits == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(structuredShopPolicies.mCharacterLimits, parcel, i);
        }
        parcel.writeSerializable(structuredShopPolicies.mUpdateDate);
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopShipping(StructuredShopShipping structuredShopShipping, Parcel parcel, int i) {
        int i2 = 0;
        parcel.writeInt(structuredShopShipping.mHasShippingUpgrades ? 1 : 0);
        parcel.writeString(structuredShopShipping.mProcessingTimeText);
        if (structuredShopShipping.mShipsInternational) {
            i2 = 1;
        }
        parcel.writeInt(i2);
        if (structuredShopShipping.mEstimates == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(structuredShopShipping.mEstimates.size());
        for (StructuredShopShippingEstimate structuredShopShippingEstimate : structuredShopShipping.mEstimates) {
            if (structuredShopShippingEstimate == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                m2382xdc035963(structuredShopShippingEstimate, parcel, i);
            }
        }
    }

    private void m2382xdc035963(StructuredShopShippingEstimate structuredShopShippingEstimate, Parcel parcel, int i) {
        parcel.writeString(structuredShopShippingEstimate.mType);
        if (structuredShopShippingEstimate.mCountryId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(structuredShopShippingEstimate.mCountryId.intValue());
        }
        parcel.writeString(structuredShopShippingEstimate.mDisplayName);
        parcel.writeInt(structuredShopShippingEstimate.mMax);
        parcel.writeInt(structuredShopShippingEstimate.mMin);
        parcel.writeString(structuredShopShippingEstimate.mUnit);
        parcel.writeString(structuredShopShippingEstimate.mRegionCode);
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(StructuredShopPrivacy structuredShopPrivacy, Parcel parcel, int i) {
        parcel.writeString(structuredShopPrivacy.mTranslatedOtherText);
        parcel.writeString(structuredShopPrivacy.mHeader);
        if (structuredShopPrivacy.mOtherTranslationState == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_uikit_util_MachineTranslationViewState(structuredShopPrivacy.mOtherTranslationState, parcel, i);
        }
        parcel.writeSerializable(structuredShopPrivacy.mFlags);
    }

    private void writecom_etsy_android_uikit_util_MachineTranslationViewState(MachineTranslationViewState machineTranslationViewState, Parcel parcel, int i) {
        parcel.writeInt(MachineTranslationViewState$$PackageHelper.m5558b(machineTranslationViewState));
        parcel.writeInt(MachineTranslationViewState$$PackageHelper.m5557a(machineTranslationViewState) ? 1 : 0);
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

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
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

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(StructuredShopCharLimits structuredShopCharLimits, Parcel parcel, int i) {
        parcel.writeInt(structuredShopCharLimits.mPrivacyPolicyOtherLimit);
    }

    public int describeContents() {
        return 0;
    }

    public StructuredShopPolicies getParcel() {
        return this.structuredShopPolicies$$3;
    }
}
