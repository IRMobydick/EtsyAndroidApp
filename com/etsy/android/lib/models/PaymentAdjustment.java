package com.etsy.android.lib.models;

import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.CurrencyUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.Currency;
import org.apache.commons.lang3.StringUtils;

public class PaymentAdjustment extends BaseModel {
    private static final String STATUS_SUCCESS = "SUCCESS";
    private boolean mIsSuccess;
    private EtsyId mPaymentAdjustmentId;
    private String mReasonCode;
    private String mReasonCodeFormatted;
    private String mStatus;
    private int mTotalAdjustmentAmount;

    public PaymentAdjustment() {
        this.mReasonCode = StringUtils.EMPTY;
        this.mReasonCodeFormatted = StringUtils.EMPTY;
        this.mStatus = StringUtils.EMPTY;
        this.mPaymentAdjustmentId = new EtsyId();
    }

    public boolean isRefunded() {
        return this.mIsSuccess && this.mTotalAdjustmentAmount > 0;
    }

    public String getRefundAmount() {
        if (CurrencyUtil.m3072a()) {
            return EtsyMoney.getInstanceWithAmountOfFraction(Currency.getInstance(GiftCard.CURRENCY_USD), this.mTotalAdjustmentAmount, 100).format();
        }
        return CurrencyUtil.m3057a((long) this.mTotalAdjustmentAmount);
    }

    public String getFormattedReason() {
        return this.mReasonCodeFormatted;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.PAYMENT_ADJUSTMENT_ID.equals(currentName)) {
                    this.mPaymentAdjustmentId.setId(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.REASON_CODE.equals(currentName)) {
                    this.mReasonCode = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.REASON_CODE_TEXT.equals(currentName)) {
                    this.mReasonCodeFormatted = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.TOTAL_ADJUSTMENT_AMOUNT.equals(currentName)) {
                    this.mTotalAdjustmentAmount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.STATUS.equals(currentName)) {
                    this.mStatus = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.IS_SUCCESS.equals(currentName)) {
                    this.mIsSuccess = jsonParser.getBooleanValue();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
