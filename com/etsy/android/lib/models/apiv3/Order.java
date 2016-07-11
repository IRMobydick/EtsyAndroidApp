package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Transaction;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.enums.EtsyReceiptType;
import com.etsy.android.lib.models.interfaces.ReceiptShippingStatus;
import com.etsy.android.lib.util.bk;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class Order extends BaseModel implements ReceiptShippingStatus {
    private static final long serialVersionUID = -3758862713155497237L;
    protected String mChannelSuffixString;
    protected Date mCreationDate;
    protected boolean mHasRefund;
    protected boolean mIsFullyRefunded;
    protected boolean mIsInPerson;
    protected EtsyId mReceiptId;
    protected EtsyReceiptType mReceiptType;
    protected Date mShippingDate;
    protected EtsyMoney mTotal;
    protected List<Transaction> mTransactions;
    protected boolean mWasPaid;
    protected boolean mWasShipped;

    public Order() {
        this.mTotal = new EtsyMoney();
        this.mReceiptId = new EtsyId();
        this.mTransactions = new ArrayList(0);
    }

    public EtsyId getReceiptId() {
        return this.mReceiptId;
    }

    public Date getCreationDate() {
        return this.mCreationDate;
    }

    public boolean wasPaid() {
        return this.mWasPaid;
    }

    public boolean wasShipped() {
        return this.mWasShipped;
    }

    public boolean isInPerson() {
        return this.mIsInPerson;
    }

    public EtsyMoney getTotal() {
        return this.mTotal;
    }

    @NonNull
    public List<Transaction> getTransactions() {
        return this.mTransactions;
    }

    public EtsyReceiptType getReceiptType() {
        return this.mReceiptType;
    }

    public boolean hasRefund() {
        return this.mHasRefund;
    }

    public boolean isFullyRefunded() {
        return this.mIsFullyRefunded;
    }

    @Nullable
    public Date getShipDate() {
        return this.mShippingDate;
    }

    @Nullable
    public String getChannelSuffixString() {
        return this.mChannelSuffixString;
    }

    public int daysUntilShipped() {
        Date shipDate = getShipDate();
        if (wasShipped() || shipDate == null) {
            return 0;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(shipDate);
        bk.m3351a(instance);
        Calendar instance2 = Calendar.getInstance();
        bk.m3351a(instance2);
        return bk.m3348a(instance.getTimeInMillis() - instance2.getTimeInMillis());
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.RECEIPT_ID.equals(currentName)) {
                    this.mReceiptId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.CREATION_DATE.equals(currentName)) {
                    this.mCreationDate = BaseModel.parseIntoDate(jsonParser);
                } else if (ResponseConstants.RECEIPT_TYPE.equals(currentName)) {
                    this.mReceiptType = EtsyReceiptType.resolveReceiptType(jsonParser.getIntValue());
                } else if (ResponseConstants.WAS_PAID.equals(currentName)) {
                    this.mWasPaid = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.WAS_SHIPPED.equals(currentName)) {
                    this.mWasShipped = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.SHIPPING_DATE.equals(currentName)) {
                    this.mShippingDate = BaseModel.parseIntoDate(jsonParser);
                } else if (ResponseConstants.IS_IN_PERSON.equals(currentName)) {
                    this.mIsInPerson = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.HAS_REFUND.equals(currentName)) {
                    this.mHasRefund = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.IS_FULL_REFUND.equals(currentName)) {
                    this.mIsFullyRefunded = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.CHANNEL_BADGE_SUFFIX_STRING.equals(currentName)) {
                    this.mChannelSuffixString = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.TOTAL.equals(currentName)) {
                    this.mTotal = ((Money) BaseModel.parseObject(jsonParser, Money.class)).asEtsyMoney();
                } else if (ResponseConstants.TRANSACTIONS.equals(currentName)) {
                    this.mTransactions = BaseModel.parseArray(jsonParser, Transaction.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.RECEIPT_ID, this.mReceiptId.getId());
        return hashMap;
    }
}
