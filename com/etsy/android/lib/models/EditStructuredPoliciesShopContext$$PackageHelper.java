package com.etsy.android.lib.models;

import com.etsy.android.lib.models.datatypes.EtsyId;

public class EditStructuredPoliciesShopContext$$PackageHelper {
    public static EditStructuredPoliciesShopContext accessEditStructuredPoliciesShopContext$INIT() {
        return new EditStructuredPoliciesShopContext();
    }

    public static boolean m2310xbe8e11f7(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext) {
        return editStructuredPoliciesShopContext.mIsUsingStructuredPolicies;
    }

    public static String accessEditStructuredPoliciesShopContext$FG$mShopName(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext) {
        return editStructuredPoliciesShopContext.mShopName;
    }

    public static boolean m2309xe118bdf0(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext) {
        return editStructuredPoliciesShopContext.mHasUnstructuredPolicies;
    }

    public static boolean m2307xe7d63ac5(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext) {
        return editStructuredPoliciesShopContext.mHasOnlyDigitalListings;
    }

    public static EtsyId accessEditStructuredPoliciesShopContext$FG$mShopId(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext) {
        return editStructuredPoliciesShopContext.mShopId;
    }

    public static boolean m2308xa407bcd9(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext) {
        return editStructuredPoliciesShopContext.mHasPrivateReceiptInfo;
    }

    public static int accessEditStructuredPoliciesShopContext$FG$mDigitalListingCount(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext) {
        return editStructuredPoliciesShopContext.mDigitalListingCount;
    }

    public static void m2314x85a6a6b(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext, boolean z) {
        editStructuredPoliciesShopContext.mIsUsingStructuredPolicies = z;
    }

    public static void accessEditStructuredPoliciesShopContext$FS$mShopName(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext, String str) {
        editStructuredPoliciesShopContext.mShopName = str;
    }

    public static void m2313xf748a364(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext, boolean z) {
        editStructuredPoliciesShopContext.mHasUnstructuredPolicies = z;
    }

    public static void m2311x1a19d6d1(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext, boolean z) {
        editStructuredPoliciesShopContext.mHasOnlyDigitalListings = z;
    }

    public static void accessEditStructuredPoliciesShopContext$FS$mShopId(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext, EtsyId etsyId) {
        editStructuredPoliciesShopContext.mShopId = etsyId;
    }

    public static void m2312x741a6f4d(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext, boolean z) {
        editStructuredPoliciesShopContext.mHasPrivateReceiptInfo = z;
    }

    public static void accessEditStructuredPoliciesShopContext$FS$mDigitalListingCount(EditStructuredPoliciesShopContext editStructuredPoliciesShopContext, int i) {
        editStructuredPoliciesShopContext.mDigitalListingCount = i;
    }
}
