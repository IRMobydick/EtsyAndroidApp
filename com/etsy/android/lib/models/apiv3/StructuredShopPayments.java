package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.PaymentMethod;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class StructuredShopPayments extends BaseModel {
    private static final long serialVersionUID = -6420935690069913796L;
    protected List<String> mAcceptedPaymentMethods;
    protected boolean mAcceptsDirectCheckout;
    protected List<String> mManualPaymentMethods;
    protected List<String> mProtectedPaymentMethods;

    public StructuredShopPayments() {
        this.mAcceptedPaymentMethods = new ArrayList();
        this.mProtectedPaymentMethods = new ArrayList();
        this.mManualPaymentMethods = new ArrayList();
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case 107824392:
                        if (currentName.equals(ResponseConstants.ACCEPTS_DIRECT_CHECKOUT)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case 181044705:
                        if (currentName.equals(ResponseConstants.ACCEPTED_PAYMENT_METHODS)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 1107518440:
                        if (currentName.equals(ResponseConstants.PROTECTED_PAYMENT_METHODS)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 1565044736:
                        if (currentName.equals(ResponseConstants.MANUAL_PAYMENT_METHODS)) {
                            obj = 2;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mAcceptedPaymentMethods = BaseModel.parseStringArray(jsonParser);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mProtectedPaymentMethods = BaseModel.parseStringArray(jsonParser);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mManualPaymentMethods = BaseModel.parseStringArray(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mAcceptsDirectCheckout = jsonParser.getValueAsBoolean();
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    @NonNull
    public List<String> getAcceptedPaymentMethods() {
        return this.mAcceptedPaymentMethods;
    }

    @NonNull
    public List<String> getProtectedPaymentMethods() {
        return this.mProtectedPaymentMethods;
    }

    @NonNull
    public List<String> getManualPaymentMethods() {
        return this.mManualPaymentMethods;
    }

    public boolean acceptsDirectCheckout() {
        return this.mAcceptsDirectCheckout;
    }

    public boolean hasPaymentMethods() {
        return (!this.mAcceptsDirectCheckout && this.mAcceptedPaymentMethods.isEmpty() && this.mProtectedPaymentMethods.isEmpty() && this.mManualPaymentMethods.isEmpty()) ? false : true;
    }

    public boolean acceptsPayPal() {
        return getProtectedPaymentMethods().contains(PaymentMethod.PAYPAL.getName());
    }
}
