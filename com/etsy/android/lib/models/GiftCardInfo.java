package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class GiftCardInfo extends BaseModel {
    private static final long serialVersionUID = -1806616131589913961L;
    private double mAmount;
    private String mDesignImage;
    private boolean mIsEmail;
    private long mListingInventoryId;
    private String mRecipientEmail;
    private String mRecipientName;
    private String mSenderName;

    public GiftCardInfo() {
        this.mRecipientName = StringUtils.EMPTY;
        this.mSenderName = StringUtils.EMPTY;
        this.mRecipientEmail = StringUtils.EMPTY;
        this.mListingInventoryId = 0;
        this.mAmount = 0.0d;
        this.mDesignImage = StringUtils.EMPTY;
    }

    public String getRecipientName() {
        return this.mRecipientName;
    }

    public String getSenderName() {
        return this.mSenderName;
    }

    public boolean isEmail() {
        return this.mIsEmail;
    }

    public String getRecipientEmail() {
        return this.mRecipientEmail;
    }

    public String getDesignImage() {
        return this.mDesignImage;
    }

    public double getAmount() {
        return this.mAmount;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("is_email".equals(currentName)) {
                    this.mIsEmail = jsonParser.getValueAsBoolean();
                } else if ("recipient_name".equals(currentName)) {
                    this.mRecipientName = BaseModel.parseString(jsonParser);
                } else if ("sender_name".equals(currentName)) {
                    this.mSenderName = BaseModel.parseString(jsonParser);
                } else if ("recipient_email".equals(currentName)) {
                    this.mRecipientEmail = BaseModel.parseString(jsonParser);
                } else if ("listing_inventory_id".equals(currentName)) {
                    this.mListingInventoryId = jsonParser.getValueAsLong();
                } else if (ResponseConstants.AMOUNT.equals(currentName)) {
                    this.mAmount = jsonParser.getValueAsDouble();
                } else if ("design_image".equals(currentName)) {
                    this.mDesignImage = BaseModel.parseStringURL(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
