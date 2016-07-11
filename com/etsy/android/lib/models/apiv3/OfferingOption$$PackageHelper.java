package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.datatypes.EtsyId;

public class OfferingOption$$PackageHelper {
    public static boolean accessOfferingOption$FG$mEnabled(OfferingOption offeringOption) {
        return offeringOption.mEnabled;
    }

    public static EtsyId accessOfferingOption$FG$mValue(OfferingOption offeringOption) {
        return offeringOption.mValue;
    }

    public static FormattedMoney accessOfferingOption$FG$mDisplayValue(OfferingOption offeringOption) {
        return offeringOption.mDisplayValue;
    }

    public static boolean accessOfferingOption$FG$mSelected(OfferingOption offeringOption) {
        return offeringOption.mSelected;
    }

    public static void accessOfferingOption$FS$mEnabled(OfferingOption offeringOption, boolean z) {
        offeringOption.mEnabled = z;
    }

    public static void accessOfferingOption$FS$mValue(OfferingOption offeringOption, EtsyId etsyId) {
        offeringOption.mValue = etsyId;
    }

    public static void accessOfferingOption$FS$mDisplayValue(OfferingOption offeringOption, FormattedMoney formattedMoney) {
        offeringOption.mDisplayValue = formattedMoney;
    }

    public static void accessOfferingOption$FS$mSelected(OfferingOption offeringOption, boolean z) {
        offeringOption.mSelected = z;
    }
}
