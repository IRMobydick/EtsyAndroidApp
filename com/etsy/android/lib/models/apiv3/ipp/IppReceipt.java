package com.etsy.android.lib.models.apiv3.ipp;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import java.util.Locale;

public class IppReceipt extends BaseModel {
    private static final String CLIENT_SENT_DATE = "client_sent_date";
    private static final String CONFIRMATION_DATE = "confirmation_date";
    private static final String FINALIZE_KEY = "finalize_key";
    private static final String UPDATE_DATE = "update_date";
    private long mClientSentDate;
    private long mConfirmationDate;
    private long mCreateDate;
    private String mFinalizeKey;
    private PaymentType mPaymentType;
    private EtsyId mReceiptId;
    private EtsyId mShopId;
    private long mUpdateDate;

    public enum PaymentType {
        SWIPE,
        MANUAL,
        CASH,
        NA
    }

    public IppReceipt() {
        this.mShopId = new EtsyId();
        this.mReceiptId = new EtsyId();
    }

    public EtsyId getReceiptId() {
        return this.mReceiptId;
    }

    public String getFinalizeKey() {
        return this.mFinalizeKey;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.SHOP_ID.equals(currentName)) {
                    this.mShopId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.RECEIPT_ID.equals(currentName)) {
                    this.mReceiptId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.IN_PERSON_PAYMENT_TYPE.equals(currentName)) {
                    try {
                        this.mPaymentType = PaymentType.valueOf(BaseModel.parseStringURL(jsonParser).toUpperCase(Locale.US));
                    } catch (IllegalArgumentException e) {
                        EtsyDebug.m1916d(EtsyDebug.m1891a(IppReceipt.class), "IPP_PAYMENT_TYPE" + e.getMessage());
                        this.mPaymentType = PaymentType.NA;
                    }
                } else if (CLIENT_SENT_DATE.equals(currentName)) {
                    this.mClientSentDate = jsonParser.getValueAsLong() * 1000;
                } else if (CONFIRMATION_DATE.equals(currentName)) {
                    this.mConfirmationDate = jsonParser.getValueAsLong() * 1000;
                } else if (ResponseConstants.CREATE_DATE.equals(currentName)) {
                    this.mCreateDate = jsonParser.getValueAsLong() * 1000;
                } else if (UPDATE_DATE.equals(currentName)) {
                    this.mUpdateDate = jsonParser.getValueAsLong() * 1000;
                } else if (FINALIZE_KEY.equals(currentName)) {
                    this.mFinalizeKey = BaseModel.parseStringURL(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.RECEIPT_ID, this.mReceiptId.getId());
        return hashMap;
    }
}
