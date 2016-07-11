package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.CurrencyUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Payment extends BaseModel {
    private int mAdjustedGross;
    private List<PaymentAdjustment> mAdjustments;
    private String mCurrency;
    private EtsyId mPaymentId;
    private EtsyId mReceiptId;

    public Payment() {
        this.mCurrency = StringUtils.EMPTY;
        this.mPaymentId = new EtsyId();
        this.mReceiptId = new EtsyId();
        this.mAdjustments = new ArrayList();
    }

    public EtsyId getId() {
        return this.mPaymentId;
    }

    public EtsyId getReceiptId() {
        return this.mReceiptId;
    }

    public List<PaymentAdjustment> getAdjustments() {
        return this.mAdjustments;
    }

    public String getFormattedAdjustedTotal() {
        if (CurrencyUtil.m3072a()) {
            return EtsyMoney.getInstanceWithAmountOfFraction(Currency.getInstance(GiftCard.CURRENCY_USD), this.mAdjustedGross, 100).format();
        }
        return CurrencyUtil.m3057a((long) this.mAdjustedGross);
    }

    public boolean isFullRefund() {
        return hasRefund() && this.mAdjustedGross == 0;
    }

    public boolean hasRefund() {
        for (PaymentAdjustment isRefunded : this.mAdjustments) {
            if (isRefunded.isRefunded()) {
                return true;
            }
        }
        return false;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.PAYMENT_ID.equals(currentName)) {
                    this.mPaymentId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.RECEIPT_ID.equals(currentName)) {
                    this.mReceiptId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.ADJUSTED_GROSS.equals(currentName)) {
                    this.mAdjustedGross = jsonParser.getValueAsInt();
                } else if (EtsyRequest.PARAM_CURRENCY.equals(currentName)) {
                    this.mCurrency = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.ADJUSTMENTS.equals(currentName)) {
                    this.mAdjustments = BaseModel.parseArray(jsonParser, PaymentAdjustment.class);
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
        hashMap.put(AnalyticsLogAttribute.PAYMENT_ID, this.mPaymentId.getId());
        return hashMap;
    }
}
