package com.etsy.android.lib.models.apiv3.cart;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class PaymentOptions extends BaseFieldModel {
    private static final long serialVersionUID = 4524698058026555486L;
    protected List<PaymentMethod> mPaymentMethods;

    public PaymentOptions() {
        this.mPaymentMethods = new ArrayList();
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (!ResponseConstants.PAYMENT_METHODS.equals(str)) {
            return false;
        }
        this.mPaymentMethods = BaseModel.parseArray(jsonParser, PaymentMethod.class);
        return true;
    }

    @NonNull
    public List<PaymentMethod> getPaymentMethods() {
        return this.mPaymentMethods;
    }
}
