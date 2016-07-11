package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class GiftCard extends BaseModel {
    public static final String[] ACCEPTED_CURRENCIES;
    public static final String CURRENCY_CAD = "CAD";
    public static final String CURRENCY_EUR = "EUR";
    public static final String CURRENCY_GBP = "GBP";
    public static final String CURRENCY_USD = "USD";
    private int mErrorCode;
    private String mErrorMessage;
    private HashMap<String, Balance> mGiftCards;
    private boolean mSuccess;

    public class Balance extends BaseModel {
        private float mAvailableBalance;
        private String mCurrencyCode;
        private float mPendingBalance;

        public Balance() {
            this.mCurrencyCode = StringUtils.EMPTY;
        }

        public String getCurrencyCode() {
            return this.mCurrencyCode;
        }

        public void setCurrencyCode(String str) {
            this.mCurrencyCode = str;
        }

        public float getAvailableBalance() {
            return this.mAvailableBalance;
        }

        public float getPendingBalance() {
            return this.mPendingBalance;
        }

        public void parseData(JsonParser jsonParser) {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                    if ("available".equals(currentName)) {
                        this.mAvailableBalance = (float) jsonParser.getValueAsDouble();
                    } else if ("pending".equals(currentName)) {
                        this.mPendingBalance = (float) jsonParser.getValueAsDouble();
                    } else {
                        jsonParser.skipChildren();
                    }
                }
            }
        }
    }

    public GiftCard() {
        this.mErrorMessage = StringUtils.EMPTY;
        this.mGiftCards = new HashMap();
    }

    static {
        ACCEPTED_CURRENCIES = new String[]{CURRENCY_CAD, CURRENCY_EUR, CURRENCY_USD, CURRENCY_GBP};
    }

    public boolean isSuccess() {
        return this.mSuccess;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public HashMap<String, Balance> getBalances() {
        return this.mGiftCards;
    }

    public List<Balance> getBalanceList() {
        return new ArrayList(this.mGiftCards.values());
    }

    public Balance getCurrencyBalance(String str) {
        return (Balance) this.mGiftCards.get(str);
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj;
                for (String str : ACCEPTED_CURRENCIES) {
                    if (str.equals(currentName)) {
                        Balance balance = (Balance) BaseModel.parseObject(jsonParser, Balance.class);
                        if (balance != null) {
                            balance.setCurrencyCode(str);
                            this.mGiftCards.put(str, balance);
                            obj = 1;
                            break;
                        }
                    }
                }
                obj = null;
                if (obj == null) {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
