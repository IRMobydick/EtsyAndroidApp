package com.etsy.android.lib.models;

import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.requests.CartsRequest;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class Coupon extends BaseModel {
    public static final String TYPE_FIXED_DISCOUNT = "fixed_discount";
    public static final String TYPE_FREE_SHIPPING = "free_shipping";
    public static final String TYPE_IPP_FIXED_DISCOUNT = "ipp_fixed_discount";
    public static final String TYPE_IPP_PERCENT_DISCOUNT = "ipp_percent_discount";
    public static final String TYPE_PERCENT_DISCOUNT = "pct_discount";
    private static final long serialVersionUID = 7153533561895263275L;
    protected String mCouponCode;
    protected String mCouponDescription;
    protected String mCouponId;
    protected String mCurrencyCode;
    protected long mExpirationDate;
    protected String mFixedDiscount;
    protected boolean mFreeShipping;
    protected boolean mIsIPPEligible;
    protected String mMinimumPurchasePrice;
    protected int mPercentDiscount;
    protected String mType;

    public Coupon() {
        this.mType = StringUtils.EMPTY;
        this.mCouponId = StringUtils.EMPTY;
        this.mCouponCode = StringUtils.EMPTY;
        this.mFixedDiscount = StringUtils.EMPTY;
        this.mCurrencyCode = StringUtils.EMPTY;
        this.mExpirationDate = 0;
        this.mCouponDescription = StringUtils.EMPTY;
        this.mIsIPPEligible = false;
        this.mMinimumPurchasePrice = StringUtils.EMPTY;
    }

    public String getCouponCode() {
        return this.mCouponCode;
    }

    public int getPercentDiscount() {
        return this.mPercentDiscount;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setFixedDiscount(String str) {
        this.mFixedDiscount = str;
    }

    public void setPercentDiscount(int i) {
        this.mPercentDiscount = i;
    }

    public void setMinimumPurchasePrice(String str) {
        this.mMinimumPurchasePrice = str;
    }

    public boolean isPercentDiscount() {
        return TYPE_PERCENT_DISCOUNT.equalsIgnoreCase(this.mType) || this.mPercentDiscount > 0;
    }

    public boolean isFreeShipping() {
        return TYPE_FREE_SHIPPING.equalsIgnoreCase(this.mType) || this.mFreeShipping;
    }

    public boolean isFixedDiscount() {
        return TYPE_FIXED_DISCOUNT.equalsIgnoreCase(this.mType) || bh.m3340a(this.mFixedDiscount);
    }

    public String getFormattedFixedDiscount() {
        return new EtsyMoney(this.mFixedDiscount, this.mCurrencyCode).format();
    }

    public String getMinimumPurchasePrice() {
        return this.mMinimumPurchasePrice;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (CartsRequest.PARAM_COUPON_CODE.equals(currentName)) {
                    this.mCouponCode = BaseModel.parseString(jsonParser);
                } else if (TYPE_PERCENT_DISCOUNT.equals(currentName)) {
                    this.mPercentDiscount = jsonParser.getValueAsInt();
                } else if (TYPE_FREE_SHIPPING.equals(currentName)) {
                    this.mFreeShipping = jsonParser.getValueAsInt() == 1;
                } else if (ResponseConstants.CURRENCY_CODE.equals(currentName)) {
                    this.mCurrencyCode = BaseModel.parseString(jsonParser);
                } else if (TYPE_FIXED_DISCOUNT.equals(currentName)) {
                    this.mFixedDiscount = BaseModel.parseString(jsonParser);
                } else if ("coupon_type".equals(currentName)) {
                    this.mType = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.COUPON_ID.equals(currentName)) {
                    this.mCouponId = BaseModel.parseString(jsonParser);
                } else if ("expiration_date".equals(currentName)) {
                    this.mExpirationDate = jsonParser.getLongValue();
                } else if ("is_ipp_eligible".equals(currentName)) {
                    this.mIsIPPEligible = jsonParser.getBooleanValue();
                } else if ("coupon_description".equals(currentName)) {
                    this.mCouponDescription = BaseModel.parseString(jsonParser);
                } else if ("minimum_purchase_price".equals(currentName)) {
                    this.mMinimumPurchasePrice = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public String getCouponId() {
        return this.mCouponId;
    }

    public long getExpirationDate() {
        return this.mExpirationDate;
    }

    public String getCouponDescription() {
        return this.mCouponDescription;
    }

    public boolean isIsIPPEligible() {
        return this.mIsIPPEligible;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() <= this.mExpirationDate;
    }

    public String getType() {
        return this.mType;
    }

    public String getFixedDiscount() {
        return this.mFixedDiscount;
    }
}
