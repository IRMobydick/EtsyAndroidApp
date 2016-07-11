package com.etsy.android.lib.models.apiv3.cart;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Pair;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.fasterxml.jackson.core.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class CartGroupItem extends BaseFieldModel implements IMultiShopCartElement {
    static final String ELEMENT_CART_LISTING = "cartListing";
    static final String ELEMENT_CART_LISTING_UNAVAILABLE = "cartListingUnavailable";
    static final String ELEMENT_MESSAGE_BANNER = "messageBanner";
    static final String ELEMENT_MESSAGE_BUBBLE = "messageBubble";
    static final String ELEMENT_MESSAGE_TO_SELLER = "messageToSeller";
    static final String ELEMENT_PAYMENT_ADD_COUPON = "addCoupon";
    static final String ELEMENT_PAYMENT_APPLY_GIFTCARD = "applyGiftCard";
    static final String ELEMENT_PAYMENT_CHECKOUT = "checkout";
    static final String ELEMENT_PAYMENT_COUPON = "appliedCoupon";
    static final String ELEMENT_PAYMENT_GRAND_TOTAL_LINE_ITEM = "grandTotalLineItem";
    static final String ELEMENT_PAYMENT_HEADER = "paymentHeader";
    static final String ELEMENT_PAYMENT_OPTIONS = "paymentOptions";
    static final String ELEMENT_PAYMENT_OPTIONS_DIVIDER = "paymentOptionsDivider";
    static final String ELEMENT_PAYMENT_SHIPPING_DESTINATION = "shippingDestination";
    static final String ELEMENT_PAYMENT_TOTALS_LINE_ITEM = "totalsLineItem";
    static final String ELEMENT_PAYMENT_TOTALS_NOTE = "totalsNote";
    static final String ELEMENT_SHIPPING = "shipping";
    static final String ELEMENT_SHOP_HEADER = "shopHeader";
    private static final ArrayMap<String, Pair<Integer, Class<? extends BaseModel>>> mTypeToClassMap;
    private static final long serialVersionUID = 8861459722789070266L;
    protected List<CartGroupAction> mActions;
    protected int mCartAdapterPosition;
    protected BaseModel mData;
    protected boolean mTrustSignalsOn;
    protected int mViewType;

    public CartGroupItem() {
        this.mViewType = -1;
        this.mActions = new ArrayList();
    }

    static {
        mTypeToClassMap = new ArrayMap();
        mTypeToClassMap.put(ELEMENT_SHOP_HEADER, new Pair(Integer.valueOf(1), ShopCard.class));
        mTypeToClassMap.put(ELEMENT_CART_LISTING, new Pair(Integer.valueOf(2), CartListing.class));
        mTypeToClassMap.put(ELEMENT_CART_LISTING_UNAVAILABLE, new Pair(Integer.valueOf(19), CartListing.class));
        mTypeToClassMap.put(ELEMENT_SHIPPING, new Pair(Integer.valueOf(3), ShippingDetails.class));
        mTypeToClassMap.put(ELEMENT_MESSAGE_TO_SELLER, new Pair(Integer.valueOf(4), MessageToSeller.class));
        mTypeToClassMap.put(ELEMENT_PAYMENT_ADD_COUPON, new Pair(Integer.valueOf(8), PaymentAddCoupon.class));
        mTypeToClassMap.put(ELEMENT_PAYMENT_COUPON, new Pair(Integer.valueOf(7), PaymentAppliedCoupon.class));
        mTypeToClassMap.put(ELEMENT_PAYMENT_TOTALS_LINE_ITEM, new Pair(Integer.valueOf(6), SimpleKVPCartGroupItem.class));
        mTypeToClassMap.put(ELEMENT_PAYMENT_GRAND_TOTAL_LINE_ITEM, new Pair(Integer.valueOf(11), SimpleKVPCartGroupItem.class));
        mTypeToClassMap.put(ELEMENT_PAYMENT_CHECKOUT, new Pair(Integer.valueOf(9), SimpleKVPCartGroupItem.class));
        mTypeToClassMap.put(ELEMENT_MESSAGE_BUBBLE, new Pair(Integer.valueOf(10), MessageBubble.class));
        mTypeToClassMap.put(ELEMENT_PAYMENT_TOTALS_NOTE, new Pair(Integer.valueOf(12), SimpleKVPCartGroupItem.class));
        mTypeToClassMap.put(ELEMENT_PAYMENT_HEADER, new Pair(Integer.valueOf(15), SimpleKVPCartGroupItem.class));
        mTypeToClassMap.put(ELEMENT_PAYMENT_APPLY_GIFTCARD, new Pair(Integer.valueOf(14), SimpleKVPCartGroupItem.class));
        mTypeToClassMap.put(ELEMENT_PAYMENT_OPTIONS, new Pair(Integer.valueOf(5), PaymentOptions.class));
        mTypeToClassMap.put(ELEMENT_MESSAGE_BANNER, new Pair(Integer.valueOf(13), BaseMessage.class));
        mTypeToClassMap.put(ELEMENT_PAYMENT_OPTIONS_DIVIDER, new Pair(Integer.valueOf(16), SimpleKVPCartGroupItem.class));
        mTypeToClassMap.put(ELEMENT_PAYMENT_SHIPPING_DESTINATION, new Pair(Integer.valueOf(18), SimpleKVPCartGroupItem.class));
    }

    public int getViewType() {
        return this.mViewType;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (mTypeToClassMap.containsKey(str)) {
            Pair pair = (Pair) mTypeToClassMap.get(str);
            this.mData = BaseModel.parseObject(jsonParser, (Class) pair.second);
            this.mViewType = ((Integer) pair.first).intValue();
        } else if (ResponseConstants.ACTIONS.equals(str)) {
            this.mActions = BaseModel.parseArray(jsonParser, CartGroupAction.class);
        } else if (!ResponseConstants.TRUST_SIGNALS_ON.equals(str)) {
            return false;
        } else {
            this.mTrustSignalsOn = jsonParser.getBooleanValue();
        }
        return true;
    }

    public BaseModel getData() {
        return this.mData;
    }

    public boolean isTrustSignalsOn() {
        return this.mTrustSignalsOn;
    }

    public CartGroupAction getAction(String str) {
        for (CartGroupAction cartGroupAction : this.mActions) {
            if (str.equals(cartGroupAction.getType())) {
                return cartGroupAction;
            }
        }
        return null;
    }

    public void setCartAdapterPosition(int i) {
        this.mCartAdapterPosition = i;
    }

    public int getCartAdapterPosition() {
        return this.mCartAdapterPosition;
    }
}
