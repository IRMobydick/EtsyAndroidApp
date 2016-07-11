package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.datatypes.EtsyId;

public class Offering$$PackageHelper {
    public static int accessOffering$FG$mQuantity(Offering offering) {
        return offering.mQuantity;
    }

    public static OfferingPrice accessOffering$FG$mPrice(Offering offering) {
        return offering.mPrice;
    }

    public static EtsyId accessOffering$FG$mOfferingId(Offering offering) {
        return offering.mOfferingId;
    }

    public static EtsyId accessOffering$FG$mProductId(Offering offering) {
        return offering.mProductId;
    }

    public static void accessOffering$FS$mQuantity(Offering offering, int i) {
        offering.mQuantity = i;
    }

    public static void accessOffering$FS$mPrice(Offering offering, OfferingPrice offeringPrice) {
        offering.mPrice = offeringPrice;
    }

    public static void accessOffering$FS$mOfferingId(Offering offering, EtsyId etsyId) {
        offering.mOfferingId = etsyId;
    }

    public static void accessOffering$FS$mProductId(Offering offering, EtsyId etsyId) {
        offering.mProductId = etsyId;
    }
}
