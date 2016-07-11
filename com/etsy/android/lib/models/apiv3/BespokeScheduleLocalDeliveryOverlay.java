package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.UserNote;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class BespokeScheduleLocalDeliveryOverlay extends BaseModel {
    protected LocalDeliveryDetails mLocalDeliveryDetails;
    protected LocalDeliveryEstimate mLocalDeliveryEstimate;
    protected ReceiptShipping mReceiptShipping;
    protected ShopLocalDeliveryData mShopLocalDeliveryData;

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.LOCAL_DELIVERY_DETAILS.equals(currentName)) {
                    this.mLocalDeliveryDetails = (LocalDeliveryDetails) BaseModel.parseObject(jsonParser, LocalDeliveryDetails.class);
                } else if (ResponseConstants.SHOP_LOCAL_DELIVERY_DATA.equals(currentName)) {
                    this.mShopLocalDeliveryData = (ShopLocalDeliveryData) BaseModel.parseObject(jsonParser, ShopLocalDeliveryData.class);
                } else if (ResponseConstants.LOCAL_DELIVERY_ESTIMATE.equals(currentName)) {
                    this.mLocalDeliveryEstimate = (LocalDeliveryEstimate) BaseModel.parseObject(jsonParser, LocalDeliveryEstimate.class);
                } else if (UserNote.SUBJECT_TYPE_RECEIPT.equals(currentName)) {
                    this.mReceiptShipping = (ReceiptShipping) BaseModel.parseObject(jsonParser, ReceiptShipping.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public LocalDeliveryDetails getLocalDeliveryDetails() {
        return this.mLocalDeliveryDetails;
    }

    public LocalDeliveryEstimate getLocalDeliveryEstimate() {
        return this.mLocalDeliveryEstimate;
    }

    public ReceiptShipping getReceiptShipping() {
        return this.mReceiptShipping;
    }

    public ShopLocalDeliveryData getShopLocalDeliveryData() {
        return this.mShopLocalDeliveryData;
    }
}
