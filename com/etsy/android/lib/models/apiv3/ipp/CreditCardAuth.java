package com.etsy.android.lib.models.apiv3.ipp;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.apiv3.ShopListing;
import com.etsy.android.lib.models.apiv3.UserCard;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import java.util.List;

public class CreditCardAuth extends BaseModel {
    private static final String BUYERS = "buyers";
    private static final String ERROR_MESSAGE = "error_msg";
    private static final String LISTINGS = "listings";
    private static final String PROCESS_PAYMENT = "process_payment";
    private static final String RECEIPT = "receipt";
    private static final String TRANSACTIONS = "transactions";
    private static final long serialVersionUID = -4132587089271698818L;
    private AuthStatus mAuthStatus;
    private List<UserCard> mBuyers;
    private String mErrorMessage;
    private List<ShopListing> mListings;
    private IppReceipt mReceipt;
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

    public List<UserCard> getBuyers() {
        return this.mBuyers;
    }

    public AuthStatus getAuthStatus() {
        return this.mAuthStatus;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public int getTotalAmount() {
        if (this.mTransactions == null) {
            return 0;
        }
        int i = 0;
        for (IppTransaction totalCost : this.mTransactions) {
            i = totalCost.getTotalCost() + i;
        }
        return i;
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
                } else if (BUYERS.equals(currentName)) {
                    this.mBuyers = BaseModel.parseArray(jsonParser, UserCard.class);
                } else if (PROCESS_PAYMENT.equals(currentName)) {
                    this.mAuthStatus = (AuthStatus) BaseModel.parseObject(jsonParser, AuthStatus.class);
                } else if (ERROR_MESSAGE.equals(currentName)) {
                    this.mErrorMessage = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        if (this.mReceipt != null) {
            return this.mReceipt.getTrackingParameters();
        }
        return null;
    }
}
