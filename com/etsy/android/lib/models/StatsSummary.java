package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;

public class StatsSummary extends BaseModel {
    private static final long serialVersionUID = 2584698239763238664L;
    private int mFavoritesCount;
    private int mOrdersCount;
    private List<Revenue> mRevenues;
    private int mViewsCount;

    public StatsSummary() {
        this.mRevenues = new ArrayList(0);
    }

    public int getFavoritesCount() {
        return this.mFavoritesCount;
    }

    public int getViewsCount() {
        return this.mViewsCount;
    }

    public int getOrdersCount() {
        return this.mOrdersCount;
    }

    public List<Revenue> getRevenues() {
        return this.mRevenues;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.FAVORITES_COUNT.equals(currentName)) {
                    this.mFavoritesCount = jsonParser.getValueAsInt();
                } else if ("views_count".equals(currentName)) {
                    this.mViewsCount = jsonParser.getValueAsInt();
                } else if ("orders_count".equals(currentName)) {
                    this.mOrdersCount = jsonParser.getValueAsInt();
                } else if ("revenues".equals(currentName)) {
                    this.mRevenues = BaseModel.parseArray(jsonParser, Revenue.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
