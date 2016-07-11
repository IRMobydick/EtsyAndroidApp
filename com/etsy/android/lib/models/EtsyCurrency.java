package com.etsy.android.lib.models;

import com.etsy.android.lib.core.EtsyMoney;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class EtsyCurrency extends BaseModel implements Comparable<EtsyCurrency> {
    private boolean mBrowsingEnabled;
    private boolean mBuyerLocationRestricted;
    private Collator mCollator;
    private int mCurrencyId;
    private boolean mListingEnabled;
    protected String mName;
    protected int mNumberPrecision;
    private boolean mRateUpdatesEnabled;
    protected EtsyMoney mUnit;

    public class Currencies extends BaseModel {
        private List<EtsyCurrency> mCurrencies;
        private Map<String, EtsyCurrency> mCurrencyMap;

        public Currencies() {
            this.mCurrencies = new ArrayList();
            this.mCurrencyMap = new HashMap();
        }

        public List<EtsyCurrency> getCurrencies() {
            return this.mCurrencies;
        }

        public Map<String, EtsyCurrency> getCurrencyMap() {
            return this.mCurrencyMap;
        }

        public void parseData(JsonParser jsonParser) {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if (StringUtils.EMPTY.equals(currentName)) {
                    jsonParser.skipChildren();
                } else {
                    EtsyCurrency etsyCurrency = (EtsyCurrency) BaseModel.parseObject(jsonParser, EtsyCurrency.class);
                    if (etsyCurrency != null) {
                        this.mCurrencies.add(etsyCurrency);
                        this.mCurrencyMap.put(currentName, etsyCurrency);
                    }
                }
            }
        }
    }

    public EtsyCurrency() {
        this.mCollator = Collator.getInstance(Locale.getDefault());
        this.mName = StringUtils.EMPTY;
        this.mUnit = new EtsyMoney();
    }

    public String getCode() {
        return this.mUnit.getCurrencyCode();
    }

    public String getName() {
        return this.mName;
    }

    public EtsyMoney getUnit() {
        return this.mUnit;
    }

    public String toString() {
        return String.format("Name: %s Symbol: %s Code: %s Formatted unit: %s", new Object[]{getName(), getUnit().getCurrencySymbol(), getCode(), getUnit().format()});
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("currency_id".equals(currentName)) {
                    this.mCurrencyId = jsonParser.getValueAsInt();
                } else if (ResponseConstants.CODE.equals(currentName)) {
                    this.mUnit = this.mUnit.withCurrency(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.NAME.equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser);
                } else if ("number_precision".equals(currentName)) {
                    this.mNumberPrecision = jsonParser.getValueAsInt();
                } else if ("listing_enabled".equals(currentName)) {
                    this.mListingEnabled = jsonParser.getValueAsBoolean();
                } else if ("browsing_enabled".equals(currentName)) {
                    this.mBrowsingEnabled = jsonParser.getValueAsBoolean();
                } else if ("buyer_location_restricted".equals(currentName)) {
                    this.mBuyerLocationRestricted = jsonParser.getValueAsBoolean();
                } else if ("rate_updates_enabled".equals(currentName)) {
                    this.mRateUpdatesEnabled = jsonParser.getValueAsBoolean();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public int compareTo(EtsyCurrency etsyCurrency) {
        return this.mCollator.compare(getName(), etsyCurrency.getName());
    }
}
