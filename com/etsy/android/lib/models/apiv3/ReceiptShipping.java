package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.parceler.Parcel;

@Parcel
public class ReceiptShipping extends BaseModel {
    protected boolean mCanMarkAsShipped;
    protected UserAddressV3 mFromAddress;
    protected boolean mHasProcessing;
    protected boolean mHasUpgrade;
    protected boolean mIsFutureShipment;
    protected UserAddressV3 mToAddress;
    protected String mUpgradeName;
    protected boolean mWasShipped;

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.CAN_MARK_AS_SHIPPED.equals(currentName)) {
                    this.mCanMarkAsShipped = jsonParser.getBooleanValue();
                } else if (ResponseConstants.HAS_PROCESSING.equals(currentName)) {
                    this.mHasProcessing = jsonParser.getBooleanValue();
                } else if (ResponseConstants.HAS_UPGRADE.equals(currentName)) {
                    this.mHasUpgrade = jsonParser.getBooleanValue();
                } else if (ResponseConstants.IS_FUTURE_SHIPMENT.equals(currentName)) {
                    this.mIsFutureShipment = jsonParser.getBooleanValue();
                } else if (ResponseConstants.FROM_ADDRESS.equals(currentName)) {
                    this.mFromAddress = (UserAddressV3) BaseModel.parseObject(jsonParser, UserAddressV3.class);
                } else if (ResponseConstants.TO_ADDRESS.equals(currentName)) {
                    this.mToAddress = (UserAddressV3) BaseModel.parseObject(jsonParser, UserAddressV3.class);
                } else if (ResponseConstants.UPGRADE_NAME.equals(currentName)) {
                    this.mUpgradeName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.WAS_SHIPPED.equals(currentName)) {
                    this.mWasShipped = jsonParser.getBooleanValue();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public boolean canMarkAsShipped() {
        return this.mCanMarkAsShipped;
    }

    public boolean hasProcessing() {
        return this.mHasProcessing;
    }

    public boolean hasUpgrade() {
        return this.mHasUpgrade;
    }

    public boolean isFutureShipment() {
        return this.mIsFutureShipment;
    }

    public UserAddressV3 getFromAddress() {
        return this.mFromAddress;
    }

    public UserAddressV3 getToAddress() {
        return this.mToAddress;
    }

    public String getUpgradeName() {
        return this.mUpgradeName;
    }

    public boolean wasShipped() {
        return this.mWasShipped;
    }
}
