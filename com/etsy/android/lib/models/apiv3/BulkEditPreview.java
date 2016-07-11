package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.EtsyRequest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.Currency;
import java.util.Date;

public class BulkEditPreview extends BaseModel {
    private int cost;
    private int count_billable;
    private int count_credits;
    private int count_selected;
    private String currency;
    private Date ending_date;
    private String formatted_cost;
    private String formatted_ending_date;
    private String formatted_rate;
    private int rate;

    public EtsyMoney getCost() {
        return EtsyMoney.getInstanceWithAmountOfFraction(Currency.getInstance(this.currency), this.cost, 100);
    }

    public EtsyMoney getRate() {
        return EtsyMoney.getInstanceWithAmountOfFraction(Currency.getInstance(this.currency), this.rate, 100);
    }

    public int getCountSelected() {
        return this.count_selected;
    }

    public boolean hasBillable() {
        return this.count_billable > 0;
    }

    public int getCountBillable() {
        return this.count_billable;
    }

    public boolean hasCredits() {
        return this.count_credits > 0;
    }

    public int getCountCredits() {
        return this.count_credits;
    }

    public Date getEndingDate() {
        return this.ending_date;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.COST.equals(currentName)) {
                    this.cost = jsonParser.getValueAsInt();
                } else if (ResponseConstants.RATE.equals(currentName)) {
                    this.rate = jsonParser.getValueAsInt();
                } else if (EtsyRequest.PARAM_CURRENCY.equals(currentName)) {
                    this.currency = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.FORMATTED_COST.equals(currentName)) {
                    this.formatted_cost = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.FORMATTED_RATE.equals(currentName)) {
                    this.formatted_rate = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.COUNT_SELECTED.equals(currentName)) {
                    this.count_selected = jsonParser.getValueAsInt();
                } else if (ResponseConstants.COUNT_BILLABLE.equals(currentName)) {
                    this.count_billable = jsonParser.getValueAsInt();
                } else if (ResponseConstants.COUNT_CREDITS.equals(currentName)) {
                    this.count_credits = jsonParser.getValueAsInt();
                } else if (ResponseConstants.ENDING_DATE.equals(currentName)) {
                    this.ending_date = BaseModel.parseIntoDate(jsonParser);
                } else if (ResponseConstants.FORMATTED_ENDING_DATE.equals(currentName)) {
                    this.formatted_ending_date = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
