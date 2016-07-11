package com.etsy.android.lib.requests;

import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class CartsRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    public static final String CART_INCLUDES = "Listings(listing_id,state,title,price,currency_code,quantity,has_variations,is_private,processing_min,processing_max,is_gift_card,gift_card_info,url)/Images(url_75x75,red,green,blue):1,Coupon,DestinationCountry(country_id,name),Shop/User/Profile(image_url_75x75)";
    public static final String PARAM_APPLY_GIFTCARD = "apply_gift_card_balance";
    public static final String PARAM_CART_ID = "cart_id";
    public static final String PARAM_COUPON_CODE = "coupon_code";
    public static final String PARAM_NOTE_TO_SELLER = "message_to_seller";
    private static final String TAG;
    private static final long serialVersionUID = 3827848891992473358L;

    static {
        TAG = EtsyDebug.m1891a(CartsRequest.class);
    }

    public CartsRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public static CartsRequest<Cart> getAllUserCarts() {
        return new CartsRequest(getMethodURLPrefix(), RequestMethod.GET, Cart.class);
    }

    public static CartsRequest<Cart> getUserCart(int i) {
        return new CartsRequest(getMethodURLPrefix() + "/" + i, RequestMethod.GET, Cart.class);
    }

    public static CartsRequest<EmptyResult> addToCart() {
        return new CartsRequest(getMethodURLPrefix(), RequestMethod.POST, EmptyResult.class);
    }

    public static CartsRequest<Cart> addListingOptionToCart() {
        return new CartsRequest(getMethodURLPrefix(), RequestMethod.POST, Cart.class);
    }

    public static CartsRequest<Cart> updateCartListingQuantity() {
        return new CartsRequest(getMethodURLPrefix(), RequestMethod.PUT, Cart.class);
    }

    public static CartsRequest<Cart> applyGiftCardBalance(int i, boolean z) {
        String str = getMethodURLPrefix() + "/" + i;
        RequestMethod requestMethod = RequestMethod.PUT;
        Map hashMap = new HashMap();
        hashMap.put("includes", CART_INCLUDES);
        hashMap.put(PARAM_APPLY_GIFTCARD, String.valueOf(z ? 1 : 0));
        CartsRequest<Cart> cartsRequest = new CartsRequest(str, requestMethod, Cart.class);
        cartsRequest.addParams(hashMap);
        return cartsRequest;
    }

    public static CartsRequest<Cart> removeCartListing() {
        return new CartsRequest(getMethodURLPrefix(), RequestMethod.DELETE, Cart.class);
    }

    public static CartsRequest<Cart> saveCartListing() {
        return new CartsRequest(getMethodURLPrefix() + "/save", RequestMethod.DELETE, Cart.class);
    }

    public static CartsRequest<Cart> updateCart(int i) {
        return new CartsRequest(getMethodURLPrefix() + "/" + i, RequestMethod.PUT, Cart.class);
    }

    public static CartsRequest<Cart> deleteCart(int i) {
        return new CartsRequest(getMethodURLPrefix() + "/" + i, RequestMethod.DELETE, Cart.class);
    }

    private static String getMethodURLPrefix() {
        String str;
        if (aj.m1101a().m1118d()) {
            str = "/users/__SELF__/carts";
        } else {
            str = "/guests/" + InstallInfo.m919a().m928e() + "/carts";
        }
        EtsyDebug.m1906b(TAG, "Cart endpoint: " + str);
        return str;
    }
}
