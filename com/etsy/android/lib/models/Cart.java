package com.etsy.android.lib.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.apiv3.CartShippingDetails;
import com.etsy.android.lib.models.apiv3.ShippingOption;
import com.etsy.android.lib.models.apiv3.cart.CartGroupAction;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.homescreen.LandingPageLink;
import com.etsy.android.lib.models.viewstate.CartViewState;
import com.etsy.android.lib.requests.CartsRequest;
import com.etsy.android.lib.util.bh;
import com.etsy.android.ui.cart.CartActivity;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Cart extends BaseModel {
    public static final int SHIPPING_TIME_UNSET = -1;
    private static final String TAG;
    private static final long serialVersionUID = -2656569317409704183L;
    private boolean mApplyGiftCard;
    private boolean mCanUseGiftCard;
    private int mCartId;
    private List<CartListing> mCartListings;
    private CartShippingDetails mCartShippingDetails;
    private boolean mCartTotalCoveredByGiftCard;
    private EtsyMoney mCartTotalMinusGiftCardBalance;
    private EtsyMoney mConvertedDiscountAmount;
    private EtsyMoney mConvertedShippingCost;
    private EtsyMoney mConvertedShippingDiscountAmount;
    private EtsyMoney mConvertedSubtotal;
    private EtsyMoney mConvertedTaxCost;
    private EtsyMoney mConvertedTotal;
    private Coupon mCoupon;
    private String mCouponCode;
    private Country mDestinationCountry;
    private int mDestinationCountryId;
    private EtsyMoney mDiscountAmount;
    private EtsyMoney mGiftCardCreditAmount;
    private GiftCardInfo mGiftCardInfo;
    private boolean mHasDigitalDownload;
    private boolean mHasEditableListings;
    private boolean mHasVat;
    private List<CartListing> mInvalidListings;
    private boolean mIsDownloadOnly;
    private boolean mIsFundOnEtsyCart;
    private boolean mIsGiftCardCart;
    private PaymentMethod mLastPaymentMethod;
    private List<Listing> mListings;
    private int mMaxShippingTime;
    private String mMessageToSeller;
    private int mMinShippingTime;
    private String mNoteToSeller;
    private List<PaymentMethod> mPaymentMethods;
    private boolean mSellerHasActiveCoupons;
    private EtsyMoney mShippingCost;
    private EtsyMoney mShippingDiscountAmount;
    private ShippingOption mShippingOption;
    private Shop mShop;
    private String mShopName;
    private EtsyMoney mSubtotal;
    private EtsyMoney mTaxCost;
    private EtsyMoney mTotal;
    private String mTransparentPriceMessage;
    private List<CartListing> mValidOrEditableListings;
    private CartViewState mViewState;

    static {
        TAG = EtsyDebug.m1891a(Cart.class);
    }

    public Cart(int i) {
        this();
        this.mCartId = i;
    }

    public Cart() {
        this.mShopName = StringUtils.EMPTY;
        this.mMessageToSeller = StringUtils.EMPTY;
        this.mCouponCode = StringUtils.EMPTY;
        this.mTotal = new EtsyMoney();
        this.mSubtotal = new EtsyMoney();
        this.mShippingCost = new EtsyMoney();
        this.mTaxCost = new EtsyMoney();
        this.mDiscountAmount = new EtsyMoney();
        this.mShippingDiscountAmount = new EtsyMoney();
        this.mConvertedTotal = new EtsyMoney();
        this.mConvertedSubtotal = new EtsyMoney();
        this.mConvertedShippingCost = new EtsyMoney();
        this.mConvertedTaxCost = new EtsyMoney();
        this.mConvertedDiscountAmount = new EtsyMoney();
        this.mConvertedShippingDiscountAmount = new EtsyMoney();
        this.mIsGiftCardCart = false;
        this.mHasEditableListings = false;
        this.mApplyGiftCard = false;
        this.mCanUseGiftCard = false;
        this.mCartTotalCoveredByGiftCard = false;
        this.mCartTotalMinusGiftCardBalance = new EtsyMoney();
        this.mGiftCardCreditAmount = new EtsyMoney();
        this.mHasVat = false;
        this.mTransparentPriceMessage = StringUtils.EMPTY;
        this.mGiftCardInfo = null;
        this.mMinShippingTime = SHIPPING_TIME_UNSET;
        this.mMaxShippingTime = SHIPPING_TIME_UNSET;
        this.mIsFundOnEtsyCart = false;
        this.mCartListings = new ArrayList(0);
        this.mListings = new ArrayList(0);
        this.mValidOrEditableListings = new ArrayList(0);
        this.mInvalidListings = new ArrayList(0);
        this.mPaymentMethods = new ArrayList(0);
        this.mViewState = new CartViewState();
    }

    public void setCartShippingDetails(CartShippingDetails cartShippingDetails) {
        this.mCartShippingDetails = cartShippingDetails;
    }

    protected boolean isConvertedPriceSuitableNow() {
        return this.mConvertedTotal.compareTo(0) > 0;
    }

    public CartShippingDetails getCartShippingDetails() {
        return this.mCartShippingDetails;
    }

    public ShippingOption getShippingOption() {
        return this.mShippingOption;
    }

    public void setShippingOption(ShippingOption shippingOption) {
        this.mShippingOption = shippingOption;
    }

    public int getCartId() {
        return this.mCartId;
    }

    public String getShopName() {
        return this.mShopName;
    }

    public String getMessageToSeller() {
        return this.mMessageToSeller;
    }

    public int getDestinationCountryId() {
        return this.mDestinationCountryId;
    }

    public String getCouponCode() {
        return this.mCouponCode;
    }

    public EtsyMoney getTotal() {
        return isConvertedPriceSuitableNow() ? this.mConvertedTotal : this.mTotal;
    }

    public EtsyMoney getSubtotal() {
        return isConvertedPriceSuitableNow() ? this.mConvertedSubtotal : this.mSubtotal;
    }

    public EtsyMoney getShippingCost() {
        return isConvertedPriceSuitableNow() ? this.mConvertedShippingCost : this.mShippingCost;
    }

    public EtsyMoney getTaxCost() {
        return isConvertedPriceSuitableNow() ? this.mConvertedTaxCost : this.mTaxCost;
    }

    public EtsyMoney getDiscountAmount() {
        return isConvertedPriceSuitableNow() ? this.mConvertedDiscountAmount : this.mDiscountAmount;
    }

    public EtsyMoney getShippingDiscountAmount() {
        return isConvertedPriceSuitableNow() ? this.mConvertedShippingDiscountAmount : this.mShippingDiscountAmount;
    }

    public boolean canUseGiftCard() {
        return this.mCanUseGiftCard;
    }

    public boolean hasGiftCardApplied() {
        return this.mApplyGiftCard;
    }

    public boolean isCartTotalCoveredByGiftCard() {
        return this.mCartTotalCoveredByGiftCard;
    }

    public EtsyMoney getCartTotalMinusGiftCardBalance() {
        return this.mCartTotalMinusGiftCardBalance;
    }

    public EtsyMoney getGiftCardCreditAmount() {
        return this.mGiftCardCreditAmount;
    }

    public boolean getSellerHasActiveCoupons() {
        return this.mSellerHasActiveCoupons;
    }

    public List<CartListing> getCartListings() {
        return this.mCartListings;
    }

    public boolean hasInvalidListings() {
        return this.mInvalidListings.size() > 0;
    }

    public boolean hasValidOrEditableListings() {
        return this.mValidOrEditableListings.size() > 0;
    }

    public boolean hasEditableListings() {
        return this.mHasEditableListings;
    }

    public CartListing findListing(EtsyId etsyId, long j) {
        if (this.mCartListings != null) {
            for (CartListing cartListing : this.mCartListings) {
                if (cartListing.getListingId().equals(etsyId) && cartListing.getListingCustomizationId() == j) {
                    return cartListing;
                }
            }
        }
        return null;
    }

    public boolean offersLocalDelivery() {
        if (this.mCartListings != null) {
            for (CartListing offersLocalDelivery : this.mCartListings) {
                if (offersLocalDelivery.offersLocalDelivery()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEarliestLocalDeliveryToday() {
        if (this.mCartListings != null) {
            for (CartListing isEarliestLocalDeliveryToday : this.mCartListings) {
                if (isEarliestLocalDeliveryToday.isEarliestLocalDeliveryToday()) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<CartListing> getValidAndEditableCartListings() {
        return this.mValidOrEditableListings;
    }

    public List<CartListing> getInvalidCartListings() {
        return this.mInvalidListings;
    }

    public Shop getShop() {
        return this.mShop;
    }

    public List<Listing> getListings() {
        return this.mListings;
    }

    public boolean isDownloadOnly() {
        return this.mIsDownloadOnly;
    }

    public boolean hasDigitalDownload() {
        return this.mHasDigitalDownload;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return this.mPaymentMethods;
    }

    public Coupon getCoupon() {
        return this.mCoupon;
    }

    public Country getDestinationCountry() {
        return this.mDestinationCountry;
    }

    public boolean isFundOnEtsyCart() {
        return this.mIsFundOnEtsyCart;
    }

    public void setIsFundOnEtsyCart(boolean z) {
        this.mIsFundOnEtsyCart = z;
    }

    public int getMaxShippingTime() {
        return this.mMaxShippingTime;
    }

    public void setMaxShippingTime(int i) {
        this.mMaxShippingTime = i;
    }

    public int getMinShippingTime() {
        return this.mMinShippingTime;
    }

    public void setMinShippingTime(int i) {
        this.mMinShippingTime = i;
    }

    public boolean isGiftCardCart() {
        return this.mIsGiftCardCart;
    }

    @Nullable
    public PaymentMethod getLastPaymentMethod() {
        return this.mLastPaymentMethod;
    }

    public void setLastPaymentMethod(PaymentMethod paymentMethod) {
        this.mLastPaymentMethod = paymentMethod;
    }

    public boolean hasVat() {
        return this.mHasVat;
    }

    @NonNull
    public String getTransparentPriceMessage() {
        return this.mTransparentPriceMessage;
    }

    public boolean hasTransparentPriceMessage() {
        return bh.m3343b(this.mTransparentPriceMessage);
    }

    public void ensurePaymentMethodSet() {
        if (this.mPaymentMethods.size() <= 0) {
            return;
        }
        if (this.mLastPaymentMethod == null || !this.mPaymentMethods.contains(this.mLastPaymentMethod)) {
            this.mLastPaymentMethod = (PaymentMethod) this.mPaymentMethods.get(0);
        }
    }

    public EtsyMoney calculateDiscountAmount() {
        EtsyMoney discountAmount = getDiscountAmount();
        EtsyMoney shippingDiscountAmount = getShippingDiscountAmount();
        return discountAmount.getAmount().compareTo(shippingDiscountAmount.getAmount()) == 1 ? discountAmount : shippingDiscountAmount;
    }

    public EtsyMoney calculateOrderTotal(boolean z) {
        EtsyMoney discountAmount = getDiscountAmount();
        EtsyMoney shippingDiscountAmount = getShippingDiscountAmount();
        EtsyMoney etsyMoney = new EtsyMoney();
        if (z && canUseGiftCard() && hasGiftCardApplied() && this.mLastPaymentMethod != null && this.mLastPaymentMethod.isDirectCheckout()) {
            try {
                etsyMoney = getTotal().subtract(discountAmount).subtract(shippingDiscountAmount).subtract(getGiftCardCreditAmount());
                EtsyDebug.m1906b(TAG, "total: " + getTotal().format());
                EtsyDebug.m1906b(TAG, "discoumtAmt: " + getDiscountAmount().format());
                EtsyDebug.m1906b(TAG, "shippingDiscountAmt: " + getShippingDiscountAmount().format());
                EtsyDebug.m1906b(TAG, "giftCardCreditAmount: " + getGiftCardCreditAmount().format());
                EtsyDebug.m1906b(TAG, "calculated_total: " + etsyMoney.format());
            } catch (Throwable e) {
                EtsyDebug.m1917d(TAG, "couldn't perform EtsyMoney operation", e);
            }
        } else {
            try {
                etsyMoney = getTotal().subtract(discountAmount).subtract(shippingDiscountAmount);
            } catch (Throwable e2) {
                EtsyDebug.m1917d(TAG, "couldn't perform EtsyMoney operation", e2);
            }
        }
        if (etsyMoney.getAmount().compareTo(new BigDecimal(0)) == SHIPPING_TIME_UNSET) {
            return new EtsyMoney(0);
        }
        return etsyMoney;
    }

    public CartViewState getViewState() {
        return this.mViewState;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Cart)) {
            return false;
        }
        if (((Cart) obj).getCartId() != getCartId()) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return this.mCartId;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (CartActivity.INT_CART_ID.equals(currentName)) {
                    this.mCartId = jsonParser.getValueAsInt();
                } else if ("destination_country_id".equals(currentName)) {
                    this.mDestinationCountryId = jsonParser.getValueAsInt();
                } else if (CartsRequest.PARAM_COUPON_CODE.equals(currentName)) {
                    this.mCouponCode = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.CURRENCY_CODE.equals(currentName)) {
                    this.mTotal = this.mTotal.withCurrency(BaseModel.parseString(jsonParser));
                    this.mSubtotal = this.mSubtotal.withCurrency(BaseModel.parseString(jsonParser));
                    this.mShippingCost = this.mShippingCost.withCurrency(BaseModel.parseString(jsonParser));
                    this.mTaxCost = this.mTaxCost.withCurrency(BaseModel.parseString(jsonParser));
                    this.mDiscountAmount = this.mDiscountAmount.withCurrency(BaseModel.parseString(jsonParser));
                    this.mShippingDiscountAmount = this.mShippingDiscountAmount.withCurrency(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.TOTAL.equals(currentName)) {
                    this.mTotal = this.mTotal.withAmount(BaseModel.parseString(jsonParser));
                    EtsyDebug.m1906b(TAG, "total: " + this.mTotal.format());
                    EtsyDebug.m1906b(TAG, "total: (from method)" + getTotal().format());
                } else if ("subtotal".equals(currentName)) {
                    this.mSubtotal = this.mSubtotal.withAmount(BaseModel.parseString(jsonParser));
                } else if ("shipping_cost".equals(currentName)) {
                    this.mShippingCost = this.mShippingCost.withAmount(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.TAX_COST.equals(currentName)) {
                    this.mTaxCost = this.mTaxCost.withAmount(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.DISCOUNT_AMOUNT.equals(currentName)) {
                    this.mDiscountAmount = this.mDiscountAmount.withAmount(BaseModel.parseString(jsonParser));
                } else if ("shipping_discount_amount".equals(currentName)) {
                    this.mShippingDiscountAmount = this.mShippingDiscountAmount.withAmount(BaseModel.parseString(jsonParser));
                } else if ("seller_has_active_coupons".equals(currentName)) {
                    this.mSellerHasActiveCoupons = jsonParser.getValueAsBoolean();
                } else if (CartsRequest.PARAM_APPLY_GIFTCARD.equals(currentName)) {
                    this.mApplyGiftCard = jsonParser.getValueAsBoolean();
                } else if ("can_use_gift_card_balance".equals(currentName)) {
                    this.mCanUseGiftCard = jsonParser.getValueAsBoolean();
                } else if ("is_cart_total_covered_by_balance".equals(currentName)) {
                    this.mCartTotalCoveredByGiftCard = jsonParser.getValueAsBoolean();
                } else if ("cart_total_with_balance_applied".equals(currentName)) {
                    this.mCartTotalMinusGiftCardBalance = this.mCartTotalMinusGiftCardBalance.withAmount(BaseModel.parseString(jsonParser));
                } else if ("gift_card_credit_amount".equals(currentName)) {
                    this.mGiftCardCreditAmount = this.mGiftCardCreditAmount.withAmount(BaseModel.parseString(jsonParser));
                    EtsyDebug.m1906b(TAG, "gift_card_credit_amount string: " + BaseModel.parseString(jsonParser));
                    EtsyDebug.m1906b(TAG, "gift_card_credit_amount: " + this.mGiftCardCreditAmount.format());
                } else if ("converted_total".equals(currentName)) {
                    this.mConvertedTotal = this.mConvertedTotal.withAmount(BaseModel.parseString(jsonParser));
                    EtsyDebug.m1906b(TAG, "converted_total: " + this.mConvertedTotal.format());
                } else if ("converted_subtotal".equals(currentName)) {
                    this.mConvertedSubtotal = this.mConvertedSubtotal.withAmount(BaseModel.parseString(jsonParser));
                } else if ("converted_shipping_cost".equals(currentName)) {
                    this.mConvertedShippingCost = this.mConvertedShippingCost.withAmount(BaseModel.parseString(jsonParser));
                } else if ("converted_tax_cost".equals(currentName)) {
                    this.mConvertedTaxCost = this.mConvertedTaxCost.withAmount(BaseModel.parseString(jsonParser));
                } else if ("converted_discount_amount".equals(currentName)) {
                    this.mConvertedDiscountAmount = this.mConvertedDiscountAmount.withAmount(BaseModel.parseString(jsonParser));
                } else if ("converted_shipping_discount_amount".equals(currentName)) {
                    this.mConvertedShippingDiscountAmount = this.mConvertedShippingDiscountAmount.withAmount(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.CONVERTED_CURRENCY.equals(currentName)) {
                    this.mCartTotalMinusGiftCardBalance = this.mCartTotalMinusGiftCardBalance.withCurrency(BaseModel.parseString(jsonParser));
                    this.mGiftCardCreditAmount = this.mGiftCardCreditAmount.withCurrency(BaseModel.parseString(jsonParser));
                    this.mConvertedTotal = this.mConvertedTotal.withCurrency(BaseModel.parseString(jsonParser));
                    this.mConvertedSubtotal = this.mConvertedSubtotal.withCurrency(BaseModel.parseString(jsonParser));
                    this.mConvertedShippingCost = this.mConvertedShippingCost.withCurrency(BaseModel.parseString(jsonParser));
                    this.mConvertedTaxCost = this.mConvertedTaxCost.withCurrency(BaseModel.parseString(jsonParser));
                    this.mConvertedDiscountAmount = this.mConvertedDiscountAmount.withCurrency(BaseModel.parseString(jsonParser));
                    this.mConvertedShippingDiscountAmount = this.mConvertedShippingDiscountAmount.withCurrency(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.SHOP_NAME.equals(currentName)) {
                    this.mShopName = BaseModel.parseString(jsonParser);
                } else if (CartsRequest.PARAM_NOTE_TO_SELLER.equals(currentName)) {
                    this.mMessageToSeller = BaseModel.parseString(jsonParser);
                } else if ("payment_method_objects".equals(currentName)) {
                    this.mPaymentMethods = BaseModel.parseArray(jsonParser, PaymentMethod.class);
                } else if (Includes.SHOP.equals(currentName)) {
                    this.mShop = (Shop) BaseModel.parseObject(jsonParser, Shop.class);
                } else if (Includes.COUPON.equals(currentName)) {
                    this.mCoupon = (Coupon) BaseModel.parseObject(jsonParser, Coupon.class);
                } else if ("DestinationCountry".equals(currentName)) {
                    this.mDestinationCountry = (Country) BaseModel.parseObject(jsonParser, Country.class);
                } else if (LandingPageLink.PAGE_TYPE_LISTINGS.equals(currentName)) {
                    this.mCartListings = BaseModel.parseArray(jsonParser, CartListing.class);
                } else if (Includes.LISTINGS.equals(currentName)) {
                    this.mListings = BaseModel.parseArray(jsonParser, Listing.class);
                } else if ("is_download_only".equals(currentName)) {
                    this.mIsDownloadOnly = jsonParser.getValueAsBoolean();
                } else if ("has_digital_download".equals(currentName)) {
                    this.mHasDigitalDownload = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.SHIPPING_DETAILS.equals(currentName)) {
                    this.mCartShippingDetails = (CartShippingDetails) BaseModel.parseObject(jsonParser, CartShippingDetails.class);
                } else if (CartGroupAction.SHIPPING_OPTION.equals(currentName)) {
                    this.mShippingOption = (ShippingOption) BaseModel.parseObject(jsonParser, ShippingOption.class);
                } else if ("has_vat".equals(currentName)) {
                    this.mHasVat = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.TRANSPARENT_PRICE_MESSAGE.equals(currentName)) {
                    this.mTransparentPriceMessage = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
        for (CartListing cartListing : this.mCartListings) {
            if (cartListing.isEditable() || cartListing.isValid()) {
                if (cartListing.isGiftCard()) {
                    this.mIsGiftCardCart = true;
                }
                this.mValidOrEditableListings.add(cartListing);
                if (cartListing.isEditable()) {
                    this.mHasEditableListings = true;
                }
            } else {
                this.mInvalidListings.add(cartListing);
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.CART_ID, Integer.valueOf(this.mCartId));
        return hashMap;
    }
}
