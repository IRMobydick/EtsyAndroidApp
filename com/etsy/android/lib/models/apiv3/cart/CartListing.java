package com.etsy.android.lib.models.apiv3.cart;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.apiv3.ListingState;
import com.etsy.android.lib.models.apiv3.Money;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class CartListing extends ListingCard {
    private static final long serialVersionUID = 8525951090074315044L;
    protected EtsyId mCartListingCustomizationId;
    protected String mDescription;
    protected boolean mIsDigital;
    protected boolean mIsGiftCard;
    protected Money mItemPrice;
    protected int mPurchaseQuantity;
    protected List<Variation> mSelectedVariations;
    protected int mState;
    protected Money mTotalPrice;

    public CartListing() {
        this.mDescription = StringUtils.EMPTY;
        this.mSelectedVariations = new ArrayList();
    }

    protected boolean parseField(JsonParser jsonParser, String str) {
        if (ResponseConstants.CART_LISTING_CUSTOMIZATION_ID.equals(str)) {
            this.mCartListingCustomizationId = new EtsyId(BaseModel.parseStringIdOrNumericValue(jsonParser));
        } else if (ResponseConstants.PURCHASE_QUANTITY.equals(str)) {
            this.mPurchaseQuantity = jsonParser.getIntValue();
        } else if (ResponseConstants.PRICE.equals(str)) {
            this.mPrice = this.mPrice.withAmount(jsonParser.getDoubleValue());
        } else if (ResponseConstants.SELECTED_VARIATIONS.equals(str)) {
            this.mSelectedVariations.addAll(BaseModel.parseArray(jsonParser, Variation.class));
        } else if (ResponseConstants.STATE.equals(str)) {
            this.mState = jsonParser.getIntValue();
        } else if (ResponseConstants.IS_DIGITAL.equals(str)) {
            this.mIsDigital = jsonParser.getBooleanValue();
        } else if (ResponseConstants.IS_GIFT_CARD.equals(str)) {
            this.mIsGiftCard = jsonParser.getBooleanValue();
        } else if (ResponseConstants.DESCRIPTION.equals(str)) {
            this.mDescription = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.ITEM_PRICE.equals(str)) {
            this.mItemPrice = (Money) BaseModel.parseObject(jsonParser, Money.class);
        } else if (!ResponseConstants.TOTAL_PRICE.equals(str)) {
            return super.parseField(jsonParser, str);
        } else {
            this.mTotalPrice = (Money) BaseModel.parseObject(jsonParser, Money.class);
        }
        return true;
    }

    public boolean hasVariations() {
        return false;
    }

    public int getPurchaseQuantity() {
        return this.mPurchaseQuantity;
    }

    public List<Variation> getSelectedVariations() {
        return this.mSelectedVariations;
    }

    public int getState() {
        return this.mState;
    }

    public boolean isDigital() {
        return this.mIsDigital;
    }

    public boolean isGiftCard() {
        return this.mIsGiftCard;
    }

    public boolean isAvailable() {
        return ListingState.isActive(getState());
    }

    public String getDescription() {
        return this.mDescription;
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.LISTING_ID, this.mListingId.getId());
        return hashMap;
    }

    public String getTotalPriceString() {
        if (this.mTotalPrice != null) {
            return this.mTotalPrice.toString();
        }
        return StringUtils.EMPTY;
    }

    public String getItemPriceString() {
        if (this.mItemPrice != null) {
            return this.mItemPrice.toString();
        }
        return StringUtils.EMPTY;
    }

    public boolean isCustomOrder() {
        return true;
    }

    public CharSequence getRegistryString() {
        return "From somebody and somebody else's Wedding or Baby shower registry on Etsy.com";
    }
}
