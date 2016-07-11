package com.etsy.android.lib.models.apiv3;

import java.util.List;

public class StructuredShopPayments$$PackageHelper {
    public static List accessStructuredShopPayments$FG$mManualPaymentMethods(StructuredShopPayments structuredShopPayments) {
        return structuredShopPayments.mManualPaymentMethods;
    }

    public static List accessStructuredShopPayments$FG$mAcceptedPaymentMethods(StructuredShopPayments structuredShopPayments) {
        return structuredShopPayments.mAcceptedPaymentMethods;
    }

    public static boolean accessStructuredShopPayments$FG$mAcceptsDirectCheckout(StructuredShopPayments structuredShopPayments) {
        return structuredShopPayments.mAcceptsDirectCheckout;
    }

    public static List accessStructuredShopPayments$FG$mProtectedPaymentMethods(StructuredShopPayments structuredShopPayments) {
        return structuredShopPayments.mProtectedPaymentMethods;
    }

    public static void accessStructuredShopPayments$FS$mManualPaymentMethods(StructuredShopPayments structuredShopPayments, List list) {
        structuredShopPayments.mManualPaymentMethods = list;
    }

    public static void accessStructuredShopPayments$FS$mAcceptedPaymentMethods(StructuredShopPayments structuredShopPayments, List list) {
        structuredShopPayments.mAcceptedPaymentMethods = list;
    }

    public static void accessStructuredShopPayments$FS$mAcceptsDirectCheckout(StructuredShopPayments structuredShopPayments, boolean z) {
        structuredShopPayments.mAcceptsDirectCheckout = z;
    }

    public static void accessStructuredShopPayments$FS$mProtectedPaymentMethods(StructuredShopPayments structuredShopPayments, List list) {
        structuredShopPayments.mProtectedPaymentMethods = list;
    }
}
