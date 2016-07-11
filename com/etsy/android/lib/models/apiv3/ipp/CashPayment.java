package com.etsy.android.lib.models.apiv3.ipp;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.apiv3.ShopListing;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.List;

public class CashPayment extends BaseModel {
    private static final String LISTINGS = "listings";
    private static final String PROCESS_PAYMENTS = "process_payments";
    private static final String RECEIPT = "receipt";
    private static final String TRANSACTIONS = "transactions";
    private static final long serialVersionUID = -104454122317480379L;
    private List<ShopListing> mListings;
    private IppReceipt mReceipt;
    private AuthStatus mStatus;
    private List<IppTransaction> mTransactions;

    public IppReceipt getReceipt() {
        return this.mReceipt;
    }

    public List<IppTransaction> getTransactions() {
        return this.mTransactions;
    }

    public List<ShopListing> getListings() {
        return this.mListings;
    }

    public AuthStatus getStatus() {
        return this.mStatus;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (RECEIPT.equals(currentName)) {
                    this.mReceipt = (IppReceipt) BaseModel.parseObject(jsonParser, IppReceipt.class);
                } else if (TRANSACTIONS.equals(currentName)) {
                    this.mTransactions = BaseModel.parseArray(jsonParser, IppTransaction.class);
                } else if (LISTINGS.equals(currentName)) {
                    this.mListings = BaseModel.parseArray(jsonParser, ShopListing.class);
                } else if (PROCESS_PAYMENTS.equals(currentName)) {
                    this.mStatus = (AuthStatus) BaseModel.parseObject(jsonParser, AuthStatus.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
