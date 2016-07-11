package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ModelFactory;
import com.etsy.android.lib.models.Payment;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class PaymentSuccessResponse extends BaseModel {
    private String mErrorReason;
    private Payment mPaymentObject;
    private boolean mWasSuccessful;

    public boolean wasSuccessful() {
        return this.mWasSuccessful;
    }

    public String getError() {
        return this.mErrorReason;
    }

    public Payment getPaymentObject() {
        return this.mPaymentObject;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (BaseMessage.TYPE_SUCCESS.equals(currentName)) {
                    this.mWasSuccessful = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.REASON.equals(currentName)) {
                    this.mErrorReason = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.PAYMENT.equals(currentName)) {
                    this.mPaymentObject = (Payment) ModelFactory.create(jsonParser, Payment.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
