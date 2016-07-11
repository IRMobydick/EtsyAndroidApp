package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.parceler.Parcel;

@Parcel
public class LocalDeliveryDetails extends BaseModel {
    protected EtsyId mBuyerUserId;
    protected EtsyId mDeliveryDetailsId;
    protected int mMarket;
    protected EtsyId mReceiptId;
    protected String mRequestedTimeFormatted;
    protected EtsyId mShopId;

    public LocalDeliveryDetails() {
        this.mBuyerUserId = new EtsyId();
        this.mDeliveryDetailsId = new EtsyId();
        this.mReceiptId = new EtsyId();
        this.mShopId = new EtsyId();
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.MARKET.equals(currentName)) {
                    this.mMarket = jsonParser.getIntValue();
                } else if (ResponseConstants.DELIVERY_DETAILS_ID.equals(currentName)) {
                    this.mDeliveryDetailsId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.RECEIPT_ID.equals(currentName)) {
                    this.mReceiptId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.REQUESTED_TIME_FORMATTED.equals(currentName)) {
                    this.mRequestedTimeFormatted = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SHOP_ID.equals(currentName)) {
                    this.mShopId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.BUYER_USER_ID.equals(currentName)) {
                    this.mBuyerUserId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public EtsyId getBuyerUserId() {
        return this.mBuyerUserId;
    }

    public EtsyId getDeliveryDetailsId() {
        return this.mDeliveryDetailsId;
    }

    public EtsyId getReceiptId() {
        return this.mReceiptId;
    }

    public EtsyId getShopId() {
        return this.mShopId;
    }

    public int getMarket() {
        return this.mMarket;
    }

    public String getRequestedTimeFormatted() {
        return this.mRequestedTimeFormatted;
    }
}
