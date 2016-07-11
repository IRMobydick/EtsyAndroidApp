package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class SuggestedShop extends BaseModel {
    private EtsyId mShopId;
    private String mShopName;
    private int mShopScore;

    public SuggestedShop() {
        this.mShopId = new EtsyId();
    }

    public EtsyId getShopId() {
        return this.mShopId;
    }

    public int getShopScore() {
        return this.mShopScore;
    }

    public String getShopName() {
        return this.mShopName;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.SHOP_ID.equals(currentName)) {
                    this.mShopId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if ("shop_score".equals(currentName)) {
                    this.mShopScore = jsonParser.getValueAsInt();
                } else if (ResponseConstants.SHOP_NAME.equals(currentName)) {
                    this.mShopName = BaseModel.parseString(jsonParser);
                }
            }
        }
    }
}
