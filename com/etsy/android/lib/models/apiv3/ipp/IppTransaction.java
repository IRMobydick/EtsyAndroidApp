package com.etsy.android.lib.models.apiv3.ipp;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class IppTransaction extends BaseModel {
    private static final String CREATE_DATE = "create_date";
    private static final String QUICK_LISTING_UNIQUE_ID = "quick_listing_unique_identifier";
    private static final String TAX_AMOUNT = "tax_amount";
    private static final String UPDATE_DATE = "update_date";
    private static final long serialVersionUID = -4373227961535932558L;
    private int mAmount;
    private long mCreateDate;
    private String mCurrencyCode;
    private String mDescription;
    private String mImageListingId;
    private String mImageUrl;
    private EtsyId mListingId;
    private int mQuantity;
    private EtsyId mQuickListingId;
    private EtsyId mReceiptId;
    private EtsyId mShopId;
    private int mTaxAmount;
    private String mTitle;
    private EtsyId mTransactionId;
    private long mUpdateDate;

    public IppTransaction() {
        this.mShopId = new EtsyId();
        this.mReceiptId = new EtsyId();
        this.mTransactionId = new EtsyId();
        this.mListingId = new EtsyId();
        this.mQuickListingId = new EtsyId();
    }

    public String getQuickListingId() {
        return this.mQuickListingId.toString();
    }

    public boolean isQuickListing() {
        return this.mQuickListingId.hasId();
    }

    public int getAmount() {
        return this.mAmount;
    }

    public int getTotalCost() {
        return (this.mQuantity * this.mAmount) + this.mTaxAmount;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public String getImageListingId() {
        return this.mImageListingId;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.SHOP_ID.equals(currentName)) {
                    this.mShopId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.RECEIPT_ID.equals(currentName)) {
                    this.mReceiptId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.TRANSACTION_ID.equals(currentName)) {
                    this.mTransactionId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.LISTING_ID.equals(currentName)) {
                    this.mListingId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.DESCRIPTION.equals(currentName)) {
                    this.mDescription = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.AMOUNT.equals(currentName)) {
                    this.mAmount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.QUANTITY.equals(currentName)) {
                    this.mQuantity = jsonParser.getValueAsInt();
                } else if (TAX_AMOUNT.equals(currentName)) {
                    this.mTaxAmount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.CURRENCY_CODE.equals(currentName)) {
                    this.mCurrencyCode = BaseModel.parseString(jsonParser);
                } else if (QUICK_LISTING_UNIQUE_ID.equals(currentName)) {
                    this.mQuickListingId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.IMAGE.equals(currentName)) {
                    this.mImageUrl = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.IMAGE_LISTING_ID.equals(currentName)) {
                    this.mImageListingId = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else if (CREATE_DATE.equals(currentName)) {
                    this.mCreateDate = jsonParser.getValueAsLong();
                } else if (UPDATE_DATE.equals(currentName)) {
                    this.mUpdateDate = jsonParser.getValueAsLong();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
