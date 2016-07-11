package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.GiftCard;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.Currency;
import org.apache.commons.lang3.StringUtils;

public class ShippingOption extends BaseModel {
    static final /* synthetic */ boolean $assertionsDisabled;
    private EtsyMoney mCost;
    private String mCurrencyCode;
    private String mName;
    private String mOptionId;
    private int mOptionType;

    static {
        $assertionsDisabled = !ShippingOption.class.desiredAssertionStatus();
    }

    public ShippingOption() {
        this.mName = StringUtils.EMPTY;
        this.mOptionId = StringUtils.EMPTY;
        this.mCost = new EtsyMoney();
        this.mCurrencyCode = StringUtils.EMPTY;
    }

    public void parseData(JsonParser jsonParser) {
        long j = 1;
        Object obj = GiftCard.CURRENCY_USD;
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.NAME.equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.OPTION_ID.equals(currentName)) {
                    this.mOptionId = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else if (ResponseConstants.OPTION_TYPE.equals(currentName)) {
                    this.mOptionType = jsonParser.getValueAsInt();
                } else if (ResponseConstants.COST.equals(currentName)) {
                    j = jsonParser.getValueAsLong();
                } else if (ResponseConstants.CURRENCY_CODE.equals(currentName)) {
                    obj = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
        this.mCost = EtsyMoney.getInstanceWithAmountOfFraction(Currency.getInstance(obj), j, 100);
        if (bh.m3339a((CharSequence) obj, (CharSequence) "JPY")) {
            this.mCost = EtsyMoney.getInstanceWithAmountOfFraction(Currency.getInstance(obj), j, 1);
        }
    }

    public String getName() {
        return this.mName;
    }

    public String getOptionId() {
        return this.mOptionId;
    }

    public int getOptionType() {
        return this.mOptionType;
    }

    public EtsyMoney getCost() {
        return this.mCost;
    }

    public String getCurrencyCode() {
        return this.mCurrencyCode;
    }

    public boolean isLocalDelivery() {
        return this.mOptionType == 5;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ShippingOption)) {
            return false;
        }
        return this.mOptionId.equals(((ShippingOption) obj).getOptionId());
    }

    public int hashCode() {
        if ($assertionsDisabled) {
            return 42;
        }
        throw new AssertionError("hashCode not designed");
    }
}
