package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class StructuredShopRefunds extends BaseModel {
    public static final String TYPE_CANCEL_BEFORE_SHIPPED = "until_shipped";
    public static final String TYPE_CANCEL_WITHIN_HOURS = "hours";
    private static final long serialVersionUID = -7966337054426255745L;
    protected String mAcceptedSummaryString;
    protected boolean mAcceptsCancellations;
    protected boolean mAcceptsReturns;
    protected int mCancelWithinHours;
    protected String mCancellationWindowType;
    protected int mContactWithinDays;
    protected boolean mExchanges;
    protected List<NonRefundableItem> mNonRefundableItems;
    protected String mNotAcceptedSummaryString;
    protected int mReturnWithinDays;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CancellationWindowType {
    }

    public StructuredShopRefunds() {
        this.mAcceptedSummaryString = StringUtils.EMPTY;
        this.mNotAcceptedSummaryString = StringUtils.EMPTY;
        this.mNonRefundableItems = new ArrayList();
    }

    public StructuredShopRefunds(StructuredShopRefunds structuredShopRefunds) {
        this.mAcceptedSummaryString = StringUtils.EMPTY;
        this.mNotAcceptedSummaryString = StringUtils.EMPTY;
        this.mNonRefundableItems = new ArrayList();
        this.mAcceptsReturns = structuredShopRefunds.acceptsReturns();
        this.mExchanges = structuredShopRefunds.acceptsExchanges();
        this.mAcceptsCancellations = structuredShopRefunds.acceptsCancellations();
        this.mAcceptedSummaryString = structuredShopRefunds.getAcceptedSummaryString();
        this.mNotAcceptedSummaryString = structuredShopRefunds.getNotAcceptedSummaryString();
        this.mContactWithinDays = structuredShopRefunds.contactSellerWithinDays();
        this.mReturnWithinDays = structuredShopRefunds.returnItemsWithinDays();
        this.mCancelWithinHours = structuredShopRefunds.cancelWithinHours();
        this.mCancellationWindowType = structuredShopRefunds.getCancellationType();
        for (NonRefundableItem nonRefundableItem : structuredShopRefunds.getNonRefundableItems()) {
            this.mNonRefundableItems.add(new NonRefundableItem(nonRefundableItem));
        }
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1404969747:
                        if (currentName.equals(ResponseConstants.CANCELLATION_WINDOW_TYPE)) {
                            obj = 9;
                            break;
                        }
                        break;
                    case -1385634454:
                        if (currentName.equals(ResponseConstants.CONTACT_SELLER_WITHIN_DAYS)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case -1189436448:
                        if (currentName.equals(ResponseConstants.CANCEL_WITHIN_HOURS)) {
                            obj = 7;
                            break;
                        }
                        break;
                    case -761085706:
                        if (currentName.equals(ResponseConstants.NOT_ACCEPTED_SUMMARY_STRING)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case -718081955:
                        if (currentName.equals(ResponseConstants.RETURN_ITEMS_WITHIN_DAYS)) {
                            obj = 6;
                            break;
                        }
                        break;
                    case -389652113:
                        if (currentName.equals(ResponseConstants.ACCEPTS_RETURNS)) {
                            obj = null;
                            break;
                        }
                        break;
                    case -98854302:
                        if (currentName.equals(ResponseConstants.ACCEPTED_SUMMARY_STRING)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case 136691452:
                        if (currentName.equals(ResponseConstants.ACCEPTS_CANCELLATIONS)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 612952389:
                        if (currentName.equals(ResponseConstants.NON_REFUNDABLE_ITEMS)) {
                            obj = 8;
                            break;
                        }
                        break;
                    case 1553479344:
                        if (currentName.equals(ResponseConstants.EXCHANGES)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mAcceptsReturns = jsonParser.getValueAsBoolean();
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mExchanges = jsonParser.getValueAsBoolean();
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mAcceptsCancellations = jsonParser.getValueAsBoolean();
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mAcceptedSummaryString = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mNotAcceptedSummaryString = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mContactWithinDays = jsonParser.getValueAsInt();
                        break;
                    case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                        this.mReturnWithinDays = jsonParser.getValueAsInt();
                        break;
                    case CommonStatusCodes.NETWORK_ERROR /*7*/:
                        this.mCancelWithinHours = jsonParser.getValueAsInt();
                        break;
                    case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                        this.mNonRefundableItems = BaseModel.parseArray(jsonParser, NonRefundableItem.class);
                        break;
                    case CommonStatusCodes.SERVICE_INVALID /*9*/:
                        this.mCancellationWindowType = BaseModel.parseString(jsonParser);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public boolean acceptsReturns() {
        return this.mAcceptsReturns;
    }

    public void setAcceptsReturns(boolean z) {
        this.mAcceptsReturns = z;
    }

    public boolean acceptsExchanges() {
        return this.mExchanges;
    }

    public void setAcceptsExchanges(boolean z) {
        this.mExchanges = z;
    }

    public boolean acceptsCancellations() {
        return this.mAcceptsCancellations && this.mCancellationWindowType != null;
    }

    public void setAcceptsCancellations(boolean z) {
        this.mAcceptsCancellations = z;
    }

    public String getAcceptedSummaryString() {
        return this.mAcceptedSummaryString;
    }

    public String getNotAcceptedSummaryString() {
        return this.mNotAcceptedSummaryString;
    }

    public int contactSellerWithinDays() {
        return this.mContactWithinDays;
    }

    public void setContactWithinDays(int i) {
        this.mContactWithinDays = i;
    }

    public int returnItemsWithinDays() {
        return this.mReturnWithinDays;
    }

    public void setReturnItemsWithinDays(int i) {
        this.mReturnWithinDays = i;
    }

    public int cancelWithinHours() {
        return this.mCancelWithinHours;
    }

    public void setCancelWithinHours(int i) {
        this.mCancelWithinHours = i;
        this.mCancellationWindowType = TYPE_CANCEL_WITHIN_HOURS;
    }

    public void setCancelBeforeShipped() {
        this.mCancelWithinHours = 0;
        this.mCancellationWindowType = TYPE_CANCEL_BEFORE_SHIPPED;
    }

    public String getCancellationType() {
        return this.mCancellationWindowType;
    }

    @NonNull
    public List<NonRefundableItem> getNonRefundableItems() {
        return this.mNonRefundableItems;
    }

    public boolean equals(Object obj) {
        return (obj instanceof StructuredShopRefunds) && this.mAcceptsReturns == ((StructuredShopRefunds) obj).acceptsReturns() && this.mExchanges == ((StructuredShopRefunds) obj).acceptsExchanges() && this.mAcceptsCancellations == ((StructuredShopRefunds) obj).acceptsCancellations() && ObjectUtils.equals(this.mAcceptedSummaryString, ((StructuredShopRefunds) obj).getAcceptedSummaryString()) && ObjectUtils.equals(this.mNotAcceptedSummaryString, ((StructuredShopRefunds) obj).getNotAcceptedSummaryString()) && this.mContactWithinDays == ((StructuredShopRefunds) obj).contactSellerWithinDays() && this.mReturnWithinDays == ((StructuredShopRefunds) obj).returnItemsWithinDays() && this.mCancelWithinHours == ((StructuredShopRefunds) obj).cancelWithinHours() && ObjectUtils.equals(this.mCancellationWindowType, ((StructuredShopRefunds) obj).getCancellationType()) && ObjectUtils.equals(this.mNonRefundableItems, ((StructuredShopRefunds) obj).getNonRefundableItems());
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int i3 = ((this.mAcceptsReturns ? 0 : 1) + 527) * 31;
        if (this.mExchanges) {
            i = 0;
        } else {
            i = 1;
        }
        i = (i + i3) * 31;
        if (!this.mAcceptsCancellations) {
            i2 = 1;
        }
        return ((((((((((((((i + i2) * 31) + ObjectUtils.hashCode(this.mAcceptedSummaryString)) * 31) + ObjectUtils.hashCode(this.mNotAcceptedSummaryString)) * 31) + this.mContactWithinDays) * 31) + this.mReturnWithinDays) * 31) + this.mCancelWithinHours) * 31) + ObjectUtils.hashCode(this.mCancellationWindowType)) * 31) + ObjectUtils.hashCode(this.mNonRefundableItems);
    }
}
