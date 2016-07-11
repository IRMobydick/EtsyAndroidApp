package com.etsy.android.lib.models.apiv3.cart;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.apiv3.ListingState;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.viewstate.CartViewState;
import com.etsy.android.ui.cart.CartActivity;
import com.fasterxml.jackson.core.JsonParser;
import java.util.HashMap;
import org.parceler.Parcel;

@Parcel
public class SavedCart extends BaseFieldModel {
    private static final long serialVersionUID = -3049154309863884368L;
    protected EtsyId mCartId;
    protected CartListing mCartListing;
    protected CartViewState mCartViewState;
    protected ShopCard mShopCard;

    public SavedCart() {
        this.mCartViewState = new CartViewState();
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (CartActivity.INT_CART_ID.equals(str)) {
            this.mCartId = new EtsyId(BaseModel.parseStringIdOrNumericValue(jsonParser));
        } else if (ActivityFeedEntity.SHOP.equals(str)) {
            this.mShopCard = (ShopCard) BaseModel.parseObject(jsonParser, ShopCard.class);
        } else if (ActivityFeedEntity.LISTING.equals(str)) {
            this.mCartListing = (CartListing) BaseModel.parseObject(jsonParser, CartListing.class);
        } else {
            jsonParser.skipChildren();
            return false;
        }
        return true;
    }

    public ShopCard getShopCard() {
        return this.mShopCard;
    }

    public CartListing getCartListing() {
        return this.mCartListing;
    }

    public int getViewType() {
        return 39;
    }

    public CartViewState getViewState() {
        return this.mCartViewState;
    }

    public EtsyId getId() {
        return this.mCartId;
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.CART_ID, this.mCartId);
        return hashMap;
    }

    public boolean isAvailable() {
        return !this.mShopCard.isVacation() && ListingState.isActive(this.mCartListing.getState());
    }
}
