package com.etsy.android.lib.models.apiv3.cart;

import com.etsy.android.uikit.cardview.IBaseRecyclerViewElement;

public interface IMultiShopCartElement extends IBaseRecyclerViewElement {
    public static final int TYPE_CART_GROUP = 17;
    public static final int TYPE_CART_LISTING = 2;
    public static final int TYPE_CART_LISTING_UNAVAILABLE = 19;
    public static final int TYPE_CHECKOUT_SECTION = 9;
    public static final int TYPE_MESSAGE_BANNER = 13;
    public static final int TYPE_MESSAGE_BUBBLE = 10;
    public static final int TYPE_MESSAGE_TO_SELLER = 4;
    public static final int TYPE_PAYMENT_ADD_COUPON = 8;
    public static final int TYPE_PAYMENT_APPLIED_COUPON = 7;
    public static final int TYPE_PAYMENT_APPLY_GIFT_CARD = 14;
    public static final int TYPE_PAYMENT_GRAND_TOTAL_LINE_ITEM = 11;
    public static final int TYPE_PAYMENT_HEADER = 15;
    public static final int TYPE_PAYMENT_OPTIONS = 5;
    public static final int TYPE_PAYMENT_OPTIONS_DIVIDER = 16;
    public static final int TYPE_PAYMENT_SHIP_TO = 18;
    public static final int TYPE_PAYMENT_TOTALS_NOTE = 12;
    public static final int TYPE_PAYMENT_TOTAL_LINE_ITEM = 6;
    public static final int TYPE_SHIPPING = 3;
    public static final int TYPE_SHOP_HEADER = 1;
}
