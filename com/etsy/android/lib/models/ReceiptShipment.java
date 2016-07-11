package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import android.support.v4.os.EnvironmentCompat;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ReceiptShipment extends BaseModel {
    private static final String SHIPPING_STATE_DELIVERED = "delivered";
    private static final String SHIPPING_STATE_IN_TRANSIT = "in_transit";
    private static final String SHIPPING_STATE_NOT_SHIPPED = "not_shipped";
    private static final String SHIPPING_STATE_OUT_FOR_DELIVERY = "out_for_delivery";
    private static final String SHIPPING_STATE_SHIPPED = "shipped";
    private static final long serialVersionUID = -4554314279402820337L;
    protected String mBuyerNote;
    protected String mCarrierName;
    protected String mMailClass;
    protected Date mMailingDate;
    protected String mMajorTrackingState;
    protected long mReceiptShippingId;
    protected ShippingState mStatus;
    protected Date mStatusDate;
    protected String mTrackingCode;
    protected String mTrackingUrl;

    public enum ShippingState {
        UNKNOWN(R.unknown, EnvironmentCompat.MEDIA_UNKNOWN),
        NOT_YET_SHIPPED(R.paid, ReceiptShipment.SHIPPING_STATE_NOT_SHIPPED),
        SHIPPED(R.shipped, ReceiptShipment.SHIPPING_STATE_SHIPPED),
        IN_TRANSIT(R.in_transit, ReceiptShipment.SHIPPING_STATE_IN_TRANSIT),
        OUT_FOR_DELIVERY(R.out_for_delivery, ReceiptShipment.SHIPPING_STATE_OUT_FOR_DELIVERY),
        DELIVERED(R.delivered, ReceiptShipment.SHIPPING_STATE_DELIVERED);
        
        private String mJsonStatus;
        private int mStringRes;

        private ShippingState(int i, String str) {
            this.mStringRes = i;
            this.mJsonStatus = str;
        }

        public int getStringResource() {
            return this.mStringRes;
        }

        public String getJsonStatus() {
            return this.mJsonStatus;
        }

        public static ShippingState getEnumForJson(String str) {
            ShippingState shippingState = UNKNOWN;
            if (DELIVERED.getJsonStatus().equalsIgnoreCase(str)) {
                return DELIVERED;
            }
            if (OUT_FOR_DELIVERY.getJsonStatus().equalsIgnoreCase(str)) {
                return OUT_FOR_DELIVERY;
            }
            if (IN_TRANSIT.getJsonStatus().equalsIgnoreCase(str)) {
                return IN_TRANSIT;
            }
            if (SHIPPED.getJsonStatus().equalsIgnoreCase(str)) {
                return SHIPPED;
            }
            if (NOT_YET_SHIPPED.getJsonStatus().equalsIgnoreCase(str)) {
                return NOT_YET_SHIPPED;
            }
            return shippingState;
        }
    }

    public ReceiptShipment() {
        this.mTrackingCode = StringUtils.EMPTY;
        this.mTrackingUrl = StringUtils.EMPTY;
        this.mCarrierName = StringUtils.EMPTY;
        this.mMailClass = StringUtils.EMPTY;
        this.mBuyerNote = StringUtils.EMPTY;
        this.mStatus = ShippingState.UNKNOWN;
        this.mMajorTrackingState = StringUtils.EMPTY;
    }

    public ReceiptShipment(Receipt receipt) {
        this.mTrackingCode = StringUtils.EMPTY;
        this.mTrackingUrl = StringUtils.EMPTY;
        this.mCarrierName = StringUtils.EMPTY;
        this.mMailClass = StringUtils.EMPTY;
        this.mBuyerNote = StringUtils.EMPTY;
        this.mStatus = ShippingState.UNKNOWN;
        this.mMajorTrackingState = StringUtils.EMPTY;
        this.mMailingDate = receipt.getShippingNotificationDate();
        this.mCarrierName = receipt.getShippingCarrier();
        this.mStatus = ShippingState.SHIPPED;
        if (bh.m3340a(receipt.getShippingTrackingUrl())) {
            this.mTrackingUrl = receipt.getShippingTrackingUrl();
        }
        this.mStatusDate = this.mMailingDate;
    }

    public long getReceiptShippingId() {
        return this.mReceiptShippingId;
    }

    public String getTrackingCode() {
        return this.mTrackingCode;
    }

    public String getTrackingUrl() {
        return this.mTrackingUrl;
    }

    public String getCarrierName() {
        return this.mCarrierName;
    }

    public String getMailClass() {
        return this.mMailClass;
    }

    public String getBuyerNote() {
        return this.mBuyerNote;
    }

    public Date getStatusDate() {
        return this.mStatusDate;
    }

    public Date getShippedDate() {
        return this.mMailingDate;
    }

    public ShippingState getStatus() {
        return this.mStatus;
    }

    public String getMajorTrackingState() {
        return this.mMajorTrackingState;
    }

    public boolean isShippingInFuture() {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.setTime(this.mMailingDate);
        instance2.setTime(new Date());
        if (instance.get(1) < instance2.get(1) || instance.get(6) <= instance2.get(6)) {
            return false;
        }
        return true;
    }

    public int getShippingString() {
        if (isShippingInFuture()) {
            return R.shipping_on;
        }
        return R.shipped;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.RECEIPT_SHIPPING_ID.equals(currentName)) {
                    this.mReceiptShippingId = jsonParser.getValueAsLong();
                } else if (ResponseConstants.TRACKING_CODE.equals(currentName)) {
                    this.mTrackingCode = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.TRACKING_URL.equals(currentName)) {
                    this.mTrackingUrl = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.CARRIER_NAME.equals(currentName)) {
                    this.mCarrierName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.MAIL_CLASS.equals(currentName)) {
                    this.mMailClass = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.BUYER_NOTE.equals(currentName)) {
                    this.mBuyerNote = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.MAILING_DATE.equals(currentName)) {
                    this.mMailingDate = BaseModel.parseIntoDate(jsonParser);
                } else if (ResponseConstants.CURRENT_STEP.equals(currentName)) {
                    this.mStatus = ShippingState.getEnumForJson(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.CURRENT_STEP_DATE.equals(currentName)) {
                    this.mStatusDate = BaseModel.parseIntoDate(jsonParser);
                } else if (ResponseConstants.MAJOR_TRACKING_STATE.equals(currentName)) {
                    this.mMajorTrackingState = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(2);
        hashMap.put(AnalyticsLogAttribute.RECEIPT_SHIPPING_ID, Long.valueOf(this.mReceiptShippingId));
        hashMap.put(AnalyticsLogAttribute.SHIPPING_STATUS, this.mStatus.getJsonStatus());
        return hashMap;
    }
}
