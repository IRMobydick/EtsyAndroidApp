package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class UserCounters extends BaseModel {
    private int mCartCount;
    private double mGiftCardBalance;
    private String mGiftCardCurrency;
    private int mNewShopActivityCount;
    private int mNewUserActivityCount;
    private int mOpenOrdersCount;
    private int mUnreadConvosCount;

    public int getNewUserActivityCount() {
        return this.mNewUserActivityCount;
    }

    public int getmNewShopActivityCount() {
        return this.mNewShopActivityCount;
    }

    public int getCartCount() {
        return this.mCartCount;
    }

    public double getGiftCardBalance() {
        return this.mGiftCardBalance;
    }

    public String getGiftCardCurrency() {
        return this.mGiftCardCurrency;
    }

    public int getUnreadConvosCount() {
        return this.mUnreadConvosCount;
    }

    public int getOpenOrdersCount() {
        return this.mOpenOrdersCount;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("new_activity_count".equals(currentName)) {
                    this.mNewUserActivityCount = jsonParser.getValueAsInt();
                } else if ("shop_activity_count".equals(currentName)) {
                    this.mNewShopActivityCount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.CART_COUNT.equals(currentName)) {
                    this.mCartCount = jsonParser.getValueAsInt();
                } else if ("giftcard_balance".equals(currentName)) {
                    this.mGiftCardBalance = jsonParser.getValueAsDouble();
                } else if ("giftcard_currency".equals(currentName)) {
                    this.mGiftCardCurrency = BaseModel.parseString(jsonParser);
                } else if ("new_convo_count".equals(currentName)) {
                    this.mUnreadConvosCount = jsonParser.getValueAsInt();
                } else if ("open_orders_count".equals(currentName)) {
                    this.mOpenOrdersCount = jsonParser.getValueAsInt();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
