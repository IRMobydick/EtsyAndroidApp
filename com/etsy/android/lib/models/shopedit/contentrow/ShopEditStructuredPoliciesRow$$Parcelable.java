package com.etsy.android.lib.models.shopedit.contentrow;

import android.os.Parcel;
import com.etsy.android.lib.models.EditStructuredPoliciesShopContext;
import com.etsy.android.lib.models.EditStructuredPoliciesShopContext$$PackageHelper;
import com.etsy.android.lib.models.apiv3.NonRefundableItem;
import com.etsy.android.lib.models.apiv3.PrivacyFlags;
import com.etsy.android.lib.models.apiv3.StructuredShopCharLimits;
import com.etsy.android.lib.models.apiv3.StructuredShopCharLimits$$PackageHelper;
import com.etsy.android.lib.models.apiv3.StructuredShopPayments;
import com.etsy.android.lib.models.apiv3.StructuredShopPayments$$PackageHelper;
import com.etsy.android.lib.models.apiv3.StructuredShopPolicies;
import com.etsy.android.lib.models.apiv3.StructuredShopPolicies$$PackageHelper;
import com.etsy.android.lib.models.apiv3.StructuredShopPrivacy;
import com.etsy.android.lib.models.apiv3.StructuredShopPrivacy$$PackageHelper;
import com.etsy.android.lib.models.apiv3.StructuredShopRefunds;
import com.etsy.android.lib.models.apiv3.StructuredShopRefunds$$PackageHelper;
import com.etsy.android.lib.models.apiv3.StructuredShopShipping;
import com.etsy.android.lib.models.apiv3.StructuredShopShipping$$PackageHelper;
import com.etsy.android.lib.models.apiv3.StructuredShopShippingEstimate;
import com.etsy.android.lib.models.apiv3.StructuredShopShippingEstimate$$PackageHelper;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import com.etsy.android.lib.models.shopedit.contentrow.ShopEditBasicFieldRow.EditInfo;
import com.etsy.android.lib.util.as;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.uikit.util.MachineTranslationViewState$$PackageHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.parceler.ax;

public class ShopEditStructuredPoliciesRow$$Parcelable implements android.os.Parcelable, ax<ShopEditStructuredPoliciesRow> {
    public static final ShopEditStructuredPoliciesRow$$Parcelable CREATOR;
    private ShopEditStructuredPoliciesRow shopEditStructuredPoliciesRow$$0;

    static {
        CREATOR = new ShopEditStructuredPoliciesRow$$Parcelable();
    }

    public ShopEditStructuredPoliciesRow$$Parcelable(Parcel parcel) {
        ShopEditStructuredPoliciesRow shopEditStructuredPoliciesRow;
        if (parcel.readInt() == -1) {
            shopEditStructuredPoliciesRow = null;
        } else {
            shopEditStructuredPoliciesRow = m2853xa4902da5(parcel);
        }
        this.shopEditStructuredPoliciesRow$$0 = shopEditStructuredPoliciesRow;
    }

    public ShopEditStructuredPoliciesRow$$Parcelable(ShopEditStructuredPoliciesRow shopEditStructuredPoliciesRow) {
        this.shopEditStructuredPoliciesRow$$0 = shopEditStructuredPoliciesRow;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopEditStructuredPoliciesRow$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        m2859x71e035fc(this.shopEditStructuredPoliciesRow$$0, parcel, i);
    }

    private ShopEditStructuredPoliciesRow m2853xa4902da5(Parcel parcel) {
        EditStructuredPoliciesShopContext editStructuredPoliciesShopContext;
        ShopEditContentRow shopEditContentRow;
        StructuredShopPolicies structuredShopPolicies;
        List list = null;
        ShopEditStructuredPoliciesRow shopEditStructuredPoliciesRow = new ShopEditStructuredPoliciesRow();
        if (parcel.readInt() == -1) {
            editStructuredPoliciesShopContext = null;
        } else {
            editStructuredPoliciesShopContext = m2848xb0ca3a99(parcel);
        }
        shopEditStructuredPoliciesRow.mShopContext = editStructuredPoliciesShopContext;
        if (parcel.readInt() == -1) {
            shopEditContentRow = null;
        } else {
            shopEditContentRow = m2852x5bd1a0d5(parcel);
        }
        shopEditStructuredPoliciesRow.mContentRow = shopEditContentRow;
        if (parcel.readInt() == -1) {
            structuredShopPolicies = null;
        } else {
            structuredShopPolicies = readcom_etsy_android_lib_models_apiv3_StructuredShopPolicies(parcel);
        }
        shopEditStructuredPoliciesRow.mShopPolicies = structuredShopPolicies;
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = m2850x955ec21a(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        shopEditStructuredPoliciesRow.mOldPoliciesRowItems = list;
        return shopEditStructuredPoliciesRow;
    }

    private EditStructuredPoliciesShopContext m2848xb0ca3a99(Parcel parcel) {
        EtsyId etsyId;
        boolean z;
        boolean z2 = true;
        EditStructuredPoliciesShopContext accessEditStructuredPoliciesShopContext$INIT = EditStructuredPoliciesShopContext$$PackageHelper.accessEditStructuredPoliciesShopContext$INIT();
        EditStructuredPoliciesShopContext$$PackageHelper.m2314x85a6a6b(accessEditStructuredPoliciesShopContext$INIT, parcel.readInt() == 1);
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        EditStructuredPoliciesShopContext$$PackageHelper.accessEditStructuredPoliciesShopContext$FS$mShopId(accessEditStructuredPoliciesShopContext$INIT, etsyId);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        EditStructuredPoliciesShopContext$$PackageHelper.m2311x1a19d6d1(accessEditStructuredPoliciesShopContext$INIT, z);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        EditStructuredPoliciesShopContext$$PackageHelper.m2312x741a6f4d(accessEditStructuredPoliciesShopContext$INIT, z);
        EditStructuredPoliciesShopContext$$PackageHelper.accessEditStructuredPoliciesShopContext$FS$mShopName(accessEditStructuredPoliciesShopContext$INIT, parcel.readString());
        EditStructuredPoliciesShopContext$$PackageHelper.accessEditStructuredPoliciesShopContext$FS$mDigitalListingCount(accessEditStructuredPoliciesShopContext$INIT, parcel.readInt());
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        EditStructuredPoliciesShopContext$$PackageHelper.m2313xf748a364(accessEditStructuredPoliciesShopContext$INIT, z2);
        return accessEditStructuredPoliciesShopContext$INIT;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private ShopEditContentRow m2852x5bd1a0d5(Parcel parcel) {
        boolean z = true;
        ShopEditContentRow shopEditContentRow = new ShopEditContentRow();
        shopEditContentRow.mContentMaxLines = parcel.readInt();
        shopEditContentRow.mViewType = parcel.readInt();
        shopEditContentRow.mHint = new as().m3265a(parcel);
        if (parcel.readInt() != 1) {
            z = false;
        }
        shopEditContentRow.mIncludeBottomExtraPadding = z;
        shopEditContentRow.mTitle = new as().m3265a(parcel);
        shopEditContentRow.mContent = new as().m3265a(parcel);
        return shopEditContentRow;
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
        StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FS$mShipping(structuredShopPolicies, structuredShopShipping);
        if (parcel.readInt() == -1) {
            structuredShopPrivacy = null;
        } else {
            structuredShopPrivacy = readcom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(parcel);
        }
        StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FS$mPrivacy(structuredShopPolicies, structuredShopPrivacy);
        if (parcel.readInt() == -1) {
            structuredShopPayments = null;
        } else {
            structuredShopPayments = readcom_etsy_android_lib_models_apiv3_StructuredShopPayments(parcel);
        }
        StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FS$mPayments(structuredShopPolicies, structuredShopPayments);
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FS$mPoliciesId(structuredShopPolicies, etsyId);
        StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FS$mAdditionalTermsAndConditions(structuredShopPolicies, parcel.readString());
        if (parcel.readInt() == -1) {
            structuredShopRefunds = null;
        } else {
            structuredShopRefunds = readcom_etsy_android_lib_models_apiv3_StructuredShopRefunds(parcel);
        }
        StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FS$mRefunds(structuredShopPolicies, structuredShopRefunds);
        StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FS$mShopInGermany(structuredShopPolicies, parcel.readInt() == 1);
        if (parcel.readInt() != -1) {
            structuredShopCharLimits = readcom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(parcel);
        }
        StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FS$mCharacterLimits(structuredShopPolicies, structuredShopCharLimits);
        StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FS$mUpdateDate(structuredShopPolicies, (Date) parcel.readSerializable());
        return structuredShopPolicies;
    }

    private StructuredShopShipping readcom_etsy_android_lib_models_apiv3_StructuredShopShipping(Parcel parcel) {
        List list = null;
        int i = 0;
        boolean z = true;
        StructuredShopShipping structuredShopShipping = new StructuredShopShipping();
        StructuredShopShipping$$PackageHelper.accessStructuredShopShipping$FS$mHasShippingUpgrades(structuredShopShipping, parcel.readInt() == 1);
        StructuredShopShipping$$PackageHelper.accessStructuredShopShipping$FS$mProcessingTimeText(structuredShopShipping, parcel.readString());
        if (parcel.readInt() != 1) {
            z = false;
        }
        StructuredShopShipping$$PackageHelper.accessStructuredShopShipping$FS$mShipsInternational(structuredShopShipping, z);
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            while (i < readInt) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = m2849x10cd231a(parcel);
                }
                arrayList.add(obj);
                i++;
            }
            Object obj2 = arrayList;
        }
        StructuredShopShipping$$PackageHelper.accessStructuredShopShipping$FS$mEstimates(structuredShopShipping, list);
        return structuredShopShipping;
    }

    private StructuredShopShippingEstimate m2849x10cd231a(Parcel parcel) {
        Integer num;
        StructuredShopShippingEstimate structuredShopShippingEstimate = new StructuredShopShippingEstimate();
        StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FS$mType(structuredShopShippingEstimate, parcel.readString());
        if (parcel.readInt() < 0) {
            num = null;
        } else {
            num = Integer.valueOf(parcel.readInt());
        }
        StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FS$mCountryId(structuredShopShippingEstimate, num);
        StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FS$mDisplayName(structuredShopShippingEstimate, parcel.readString());
        StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FS$mMax(structuredShopShippingEstimate, parcel.readInt());
        StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FS$mMin(structuredShopShippingEstimate, parcel.readInt());
        StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FS$mUnit(structuredShopShippingEstimate, parcel.readString());
        StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FS$mRegionCode(structuredShopShippingEstimate, parcel.readString());
        return structuredShopShippingEstimate;
    }

    private StructuredShopPrivacy readcom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(Parcel parcel) {
        MachineTranslationViewState machineTranslationViewState;
        StructuredShopPrivacy structuredShopPrivacy = new StructuredShopPrivacy();
        StructuredShopPrivacy$$PackageHelper.accessStructuredShopPrivacy$FS$mTranslatedOtherText(structuredShopPrivacy, parcel.readString());
        StructuredShopPrivacy$$PackageHelper.accessStructuredShopPrivacy$FS$mHeader(structuredShopPrivacy, parcel.readString());
        if (parcel.readInt() == -1) {
            machineTranslationViewState = null;
        } else {
            machineTranslationViewState = readcom_etsy_android_uikit_util_MachineTranslationViewState(parcel);
        }
        StructuredShopPrivacy$$PackageHelper.accessStructuredShopPrivacy$FS$mOtherTranslationState(structuredShopPrivacy, machineTranslationViewState);
        StructuredShopPrivacy$$PackageHelper.accessStructuredShopPrivacy$FS$mFlags(structuredShopPrivacy, (PrivacyFlags) parcel.readSerializable());
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
        StructuredShopPayments$$PackageHelper.accessStructuredShopPayments$FS$mManualPaymentMethods(structuredShopPayments, list);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        StructuredShopPayments$$PackageHelper.accessStructuredShopPayments$FS$mAcceptsDirectCheckout(structuredShopPayments, z);
        i = parcel.readInt();
        if (i < 0) {
            list = null;
        } else {
            list = new ArrayList();
            for (int i3 = 0; i3 < i; i3++) {
                list.add(parcel.readString());
            }
        }
        StructuredShopPayments$$PackageHelper.accessStructuredShopPayments$FS$mProtectedPaymentMethods(structuredShopPayments, list);
        int readInt2 = parcel.readInt();
        if (readInt2 >= 0) {
            list2 = new ArrayList();
            while (i2 < readInt2) {
                list2.add(parcel.readString());
                i2++;
            }
        }
        StructuredShopPayments$$PackageHelper.accessStructuredShopPayments$FS$mAcceptedPaymentMethods(structuredShopPayments, list2);
        return structuredShopPayments;
    }

    private StructuredShopRefunds readcom_etsy_android_lib_models_apiv3_StructuredShopRefunds(Parcel parcel) {
        boolean z;
        List list;
        boolean z2 = true;
        StructuredShopRefunds structuredShopRefunds = new StructuredShopRefunds();
        StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FS$mCancellationWindowType(structuredShopRefunds, parcel.readString());
        StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FS$mAcceptedSummaryString(structuredShopRefunds, parcel.readString());
        StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FS$mCancelWithinHours(structuredShopRefunds, parcel.readInt());
        StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FS$mReturnWithinDays(structuredShopRefunds, parcel.readInt());
        StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FS$mAcceptsCancellations(structuredShopRefunds, parcel.readInt() == 1);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FS$mExchanges(structuredShopRefunds, z);
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
        StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FS$mNonRefundableItems(structuredShopRefunds, list);
        StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FS$mContactWithinDays(structuredShopRefunds, parcel.readInt());
        StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FS$mNotAcceptedSummaryString(structuredShopRefunds, parcel.readString());
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FS$mAcceptsReturns(structuredShopRefunds, z2);
        return structuredShopRefunds;
    }

    private StructuredShopCharLimits readcom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(Parcel parcel) {
        StructuredShopCharLimits structuredShopCharLimits = new StructuredShopCharLimits();
        StructuredShopCharLimits$$PackageHelper.accessStructuredShopCharLimits$FS$mPrivacyPolicyOtherLimit(structuredShopCharLimits, parcel.readInt());
        return structuredShopCharLimits;
    }

    private ShopEditBasicFieldRow m2850x955ec21a(Parcel parcel) {
        EditInfo editInfo;
        ShopEditContentRow shopEditContentRow = null;
        ShopEditBasicFieldRow shopEditBasicFieldRow = new ShopEditBasicFieldRow();
        if (parcel.readInt() == -1) {
            editInfo = null;
        } else {
            editInfo = m2851x48832d42(parcel);
        }
        shopEditBasicFieldRow.mEditInfo = editInfo;
        if (parcel.readInt() != -1) {
            shopEditContentRow = m2852x5bd1a0d5(parcel);
        }
        shopEditBasicFieldRow.mContentRow = shopEditContentRow;
        shopEditBasicFieldRow.mFieldType = parcel.readInt();
        return shopEditBasicFieldRow;
    }

    private EditInfo m2851x48832d42(Parcel parcel) {
        EditInfo editInfo = new EditInfo();
        editInfo.mApiField = parcel.readString();
        editInfo.mPageTitleRes = parcel.readInt();
        editInfo.mHint = new as().m3265a(parcel);
        editInfo.mTitle = new as().m3265a(parcel);
        editInfo.mPageInView = parcel.readString();
        editInfo.mContent = new as().m3265a(parcel);
        return editInfo;
    }

    private void m2859x71e035fc(ShopEditStructuredPoliciesRow shopEditStructuredPoliciesRow, Parcel parcel, int i) {
        if (shopEditStructuredPoliciesRow.mShopContext == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            m2854xae4bd0f0(shopEditStructuredPoliciesRow.mShopContext, parcel, i);
        }
        if (shopEditStructuredPoliciesRow.mContentRow == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            m2858x325368de(shopEditStructuredPoliciesRow.mContentRow, parcel, i);
        }
        if (shopEditStructuredPoliciesRow.mShopPolicies == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopPolicies(shopEditStructuredPoliciesRow.mShopPolicies, parcel, i);
        }
        if (shopEditStructuredPoliciesRow.mOldPoliciesRowItems == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(shopEditStructuredPoliciesRow.mOldPoliciesRowItems.size());
        for (ShopEditBasicFieldRow shopEditBasicFieldRow : shopEditStructuredPoliciesRow.mOldPoliciesRowItems) {
            if (shopEditBasicFieldRow == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                m2856xfe2c1171(shopEditBasicFieldRow, parcel, i);
            }
        }
    }

    private void m2854xae4bd0f0(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext, Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeInt(EditStructuredPoliciesShopContext$$PackageHelper.m2310xbe8e11f7(editStructuredPoliciesShopContext) ? 1 : 0);
        if (EditStructuredPoliciesShopContext$$PackageHelper.accessEditStructuredPoliciesShopContext$FG$mShopId(editStructuredPoliciesShopContext) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(EditStructuredPoliciesShopContext$$PackageHelper.accessEditStructuredPoliciesShopContext$FG$mShopId(editStructuredPoliciesShopContext), parcel, i);
        }
        if (EditStructuredPoliciesShopContext$$PackageHelper.m2307xe7d63ac5(editStructuredPoliciesShopContext)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (EditStructuredPoliciesShopContext$$PackageHelper.m2308xa407bcd9(editStructuredPoliciesShopContext)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(EditStructuredPoliciesShopContext$$PackageHelper.accessEditStructuredPoliciesShopContext$FG$mShopName(editStructuredPoliciesShopContext));
        parcel.writeInt(EditStructuredPoliciesShopContext$$PackageHelper.accessEditStructuredPoliciesShopContext$FG$mDigitalListingCount(editStructuredPoliciesShopContext));
        if (!EditStructuredPoliciesShopContext$$PackageHelper.m2309xe118bdf0(editStructuredPoliciesShopContext)) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    private void m2858x325368de(ShopEditContentRow shopEditContentRow, Parcel parcel, int i) {
        parcel.writeInt(shopEditContentRow.mContentMaxLines);
        parcel.writeInt(shopEditContentRow.mViewType);
        new as().m3266a(shopEditContentRow.mHint, parcel);
        parcel.writeInt(shopEditContentRow.mIncludeBottomExtraPadding ? 1 : 0);
        new as().m3266a(shopEditContentRow.mTitle, parcel);
        new as().m3266a(shopEditContentRow.mContent, parcel);
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopPolicies(StructuredShopPolicies structuredShopPolicies, Parcel parcel, int i) {
        if (StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mShipping(structuredShopPolicies) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopShipping(StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mShipping(structuredShopPolicies), parcel, i);
        }
        if (StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mPrivacy(structuredShopPolicies) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mPrivacy(structuredShopPolicies), parcel, i);
        }
        if (StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mPayments(structuredShopPolicies) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopPayments(StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mPayments(structuredShopPolicies), parcel, i);
        }
        if (StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mPoliciesId(structuredShopPolicies) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mPoliciesId(structuredShopPolicies), parcel, i);
        }
        parcel.writeString(StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mAdditionalTermsAndConditions(structuredShopPolicies));
        if (StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mRefunds(structuredShopPolicies) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopRefunds(StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mRefunds(structuredShopPolicies), parcel, i);
        }
        parcel.writeInt(StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mShopInGermany(structuredShopPolicies) ? 1 : 0);
        if (StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mCharacterLimits(structuredShopPolicies) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mCharacterLimits(structuredShopPolicies), parcel, i);
        }
        parcel.writeSerializable(StructuredShopPolicies$$PackageHelper.accessStructuredShopPolicies$FG$mUpdateDate(structuredShopPolicies));
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopShipping(StructuredShopShipping structuredShopShipping, Parcel parcel, int i) {
        int i2 = 0;
        parcel.writeInt(StructuredShopShipping$$PackageHelper.accessStructuredShopShipping$FG$mHasShippingUpgrades(structuredShopShipping) ? 1 : 0);
        parcel.writeString(StructuredShopShipping$$PackageHelper.accessStructuredShopShipping$FG$mProcessingTimeText(structuredShopShipping));
        if (StructuredShopShipping$$PackageHelper.accessStructuredShopShipping$FG$mShipsInternational(structuredShopShipping)) {
            i2 = 1;
        }
        parcel.writeInt(i2);
        if (StructuredShopShipping$$PackageHelper.accessStructuredShopShipping$FG$mEstimates(structuredShopShipping) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(StructuredShopShipping$$PackageHelper.accessStructuredShopShipping$FG$mEstimates(structuredShopShipping).size());
        for (StructuredShopShippingEstimate structuredShopShippingEstimate : StructuredShopShipping$$PackageHelper.accessStructuredShopShipping$FG$mEstimates(structuredShopShipping)) {
            if (structuredShopShippingEstimate == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                m2855xdc035963(structuredShopShippingEstimate, parcel, i);
            }
        }
    }

    private void m2855xdc035963(StructuredShopShippingEstimate structuredShopShippingEstimate, Parcel parcel, int i) {
        parcel.writeString(StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FG$mType(structuredShopShippingEstimate));
        if (StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FG$mCountryId(structuredShopShippingEstimate) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FG$mCountryId(structuredShopShippingEstimate).intValue());
        }
        parcel.writeString(StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FG$mDisplayName(structuredShopShippingEstimate));
        parcel.writeInt(StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FG$mMax(structuredShopShippingEstimate));
        parcel.writeInt(StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FG$mMin(structuredShopShippingEstimate));
        parcel.writeString(StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FG$mUnit(structuredShopShippingEstimate));
        parcel.writeString(StructuredShopShippingEstimate$$PackageHelper.accessStructuredShopShippingEstimate$FG$mRegionCode(structuredShopShippingEstimate));
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(StructuredShopPrivacy structuredShopPrivacy, Parcel parcel, int i) {
        parcel.writeString(StructuredShopPrivacy$$PackageHelper.accessStructuredShopPrivacy$FG$mTranslatedOtherText(structuredShopPrivacy));
        parcel.writeString(StructuredShopPrivacy$$PackageHelper.accessStructuredShopPrivacy$FG$mHeader(structuredShopPrivacy));
        if (StructuredShopPrivacy$$PackageHelper.accessStructuredShopPrivacy$FG$mOtherTranslationState(structuredShopPrivacy) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_uikit_util_MachineTranslationViewState(StructuredShopPrivacy$$PackageHelper.accessStructuredShopPrivacy$FG$mOtherTranslationState(structuredShopPrivacy), parcel, i);
        }
        parcel.writeSerializable(StructuredShopPrivacy$$PackageHelper.accessStructuredShopPrivacy$FG$mFlags(structuredShopPrivacy));
    }

    private void writecom_etsy_android_uikit_util_MachineTranslationViewState(MachineTranslationViewState machineTranslationViewState, Parcel parcel, int i) {
        parcel.writeInt(MachineTranslationViewState$$PackageHelper.m5558b(machineTranslationViewState));
        parcel.writeInt(MachineTranslationViewState$$PackageHelper.m5557a(machineTranslationViewState) ? 1 : 0);
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopPayments(StructuredShopPayments structuredShopPayments, Parcel parcel, int i) {
        if (StructuredShopPayments$$PackageHelper.accessStructuredShopPayments$FG$mManualPaymentMethods(structuredShopPayments) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(StructuredShopPayments$$PackageHelper.accessStructuredShopPayments$FG$mManualPaymentMethods(structuredShopPayments).size());
            for (String writeString : StructuredShopPayments$$PackageHelper.accessStructuredShopPayments$FG$mManualPaymentMethods(structuredShopPayments)) {
                parcel.writeString(writeString);
            }
        }
        parcel.writeInt(StructuredShopPayments$$PackageHelper.accessStructuredShopPayments$FG$mAcceptsDirectCheckout(structuredShopPayments) ? 1 : 0);
        if (StructuredShopPayments$$PackageHelper.accessStructuredShopPayments$FG$mProtectedPaymentMethods(structuredShopPayments) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(StructuredShopPayments$$PackageHelper.accessStructuredShopPayments$FG$mProtectedPaymentMethods(structuredShopPayments).size());
            for (String writeString2 : StructuredShopPayments$$PackageHelper.accessStructuredShopPayments$FG$mProtectedPaymentMethods(structuredShopPayments)) {
                parcel.writeString(writeString2);
            }
        }
        if (StructuredShopPayments$$PackageHelper.accessStructuredShopPayments$FG$mAcceptedPaymentMethods(structuredShopPayments) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(StructuredShopPayments$$PackageHelper.accessStructuredShopPayments$FG$mAcceptedPaymentMethods(structuredShopPayments).size());
        for (String writeString22 : StructuredShopPayments$$PackageHelper.accessStructuredShopPayments$FG$mAcceptedPaymentMethods(structuredShopPayments)) {
            parcel.writeString(writeString22);
        }
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopRefunds(StructuredShopRefunds structuredShopRefunds, Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FG$mCancellationWindowType(structuredShopRefunds));
        parcel.writeString(StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FG$mAcceptedSummaryString(structuredShopRefunds));
        parcel.writeInt(StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FG$mCancelWithinHours(structuredShopRefunds));
        parcel.writeInt(StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FG$mReturnWithinDays(structuredShopRefunds));
        parcel.writeInt(StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FG$mAcceptsCancellations(structuredShopRefunds) ? 1 : 0);
        if (StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FG$mExchanges(structuredShopRefunds)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FG$mNonRefundableItems(structuredShopRefunds) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FG$mNonRefundableItems(structuredShopRefunds).size());
            for (NonRefundableItem writeSerializable : StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FG$mNonRefundableItems(structuredShopRefunds)) {
                parcel.writeSerializable(writeSerializable);
            }
        }
        parcel.writeInt(StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FG$mContactWithinDays(structuredShopRefunds));
        parcel.writeString(StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FG$mNotAcceptedSummaryString(structuredShopRefunds));
        if (!StructuredShopRefunds$$PackageHelper.accessStructuredShopRefunds$FG$mAcceptsReturns(structuredShopRefunds)) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(StructuredShopCharLimits structuredShopCharLimits, Parcel parcel, int i) {
        parcel.writeInt(StructuredShopCharLimits$$PackageHelper.accessStructuredShopCharLimits$FG$mPrivacyPolicyOtherLimit(structuredShopCharLimits));
    }

    private void m2856xfe2c1171(ShopEditBasicFieldRow shopEditBasicFieldRow, Parcel parcel, int i) {
        if (shopEditBasicFieldRow.mEditInfo == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            m2857x25342fcb(shopEditBasicFieldRow.mEditInfo, parcel, i);
        }
        if (shopEditBasicFieldRow.mContentRow == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            m2858x325368de(shopEditBasicFieldRow.mContentRow, parcel, i);
        }
        parcel.writeInt(shopEditBasicFieldRow.mFieldType);
    }

    private void m2857x25342fcb(EditInfo editInfo, Parcel parcel, int i) {
        parcel.writeString(editInfo.mApiField);
        parcel.writeInt(editInfo.mPageTitleRes);
        new as().m3266a(editInfo.mHint, parcel);
        new as().m3266a(editInfo.mTitle, parcel);
        parcel.writeString(editInfo.mPageInView);
        new as().m3266a(editInfo.mContent, parcel);
    }

    public int describeContents() {
        return 0;
    }

    public ShopEditStructuredPoliciesRow getParcel() {
        return this.shopEditStructuredPoliciesRow$$0;
    }
}
