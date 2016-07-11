package com.etsy.android.lib.util;

import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public enum PaymentMethod {
    PAYPAL(com.etsy.android.lib.models.apiv3.cart.PaymentMethod.TYPE_PAYPAL, R.payment_method_label_paypal),
    MONEY_ORDER("mo", R.payment_method_label_money_order),
    OTHER(ResponseConstants.OTHER, R.payment_method_label_other),
    CHECK(com.etsy.android.lib.models.apiv3.cart.PaymentMethod.TYPE_CHECK, R.payment_method_label_check),
    CREDIT_CARD(com.etsy.android.lib.models.apiv3.cart.PaymentMethod.TYPE_CC, R.payment_method_label_credit_card),
    BANK_TRANSFER("bt", R.payment_method_label_bank_transfer);
    
    private static final Map<String, PaymentMethod> f1988a;
    private final int mDisplayLabel;
    private final String mName;

    static {
        f1988a = new HashMap();
        PaymentMethod[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            PaymentMethod paymentMethod = values[i];
            f1988a.put(paymentMethod.toString(), paymentMethod);
            i++;
        }
    }

    private PaymentMethod(String str, int i) {
        this.mName = str;
        this.mDisplayLabel = i;
    }

    public String toString() {
        return this.mName;
    }

    public String getName() {
        return this.mName;
    }

    public int getDisplayLabel() {
        return this.mDisplayLabel;
    }

    public static PaymentMethod getPaymentMethod(String str) {
        return (PaymentMethod) f1988a.get(str);
    }
}
