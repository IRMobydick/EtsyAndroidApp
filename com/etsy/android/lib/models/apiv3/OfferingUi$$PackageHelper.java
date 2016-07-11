package com.etsy.android.lib.models.apiv3;

import java.util.List;

public class OfferingUi$$PackageHelper {
    public static FormattedMoney accessOfferingUi$FG$mPrice(OfferingUi offeringUi) {
        return offeringUi.mPrice;
    }

    public static boolean accessOfferingUi$FG$mHasVariableQuantity(OfferingUi offeringUi) {
        return offeringUi.mHasVariableQuantity;
    }

    public static OfferingRangeSelect accessOfferingUi$FG$mQuantity(OfferingUi offeringUi) {
        return offeringUi.mQuantity;
    }

    public static List accessOfferingUi$FG$mSelects(OfferingUi offeringUi) {
        return offeringUi.mSelects;
    }

    public static void accessOfferingUi$FS$mPrice(OfferingUi offeringUi, FormattedMoney formattedMoney) {
        offeringUi.mPrice = formattedMoney;
    }

    public static void accessOfferingUi$FS$mHasVariableQuantity(OfferingUi offeringUi, boolean z) {
        offeringUi.mHasVariableQuantity = z;
    }

    public static void accessOfferingUi$FS$mQuantity(OfferingUi offeringUi, OfferingRangeSelect offeringRangeSelect) {
        offeringUi.mQuantity = offeringRangeSelect;
    }

    public static void accessOfferingUi$FS$mSelects(OfferingUi offeringUi, List list) {
        offeringUi.mSelects = list;
    }
}
