package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.interfaces.BasicListingLike;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;

public class CartListing extends BaseModel implements BasicListingLike {
    private static final String PURCHASE_STATE_EDITED = "edited";
    private static final String PURCHASE_STATE_INVALID_CURRENCY = "invalid_currency";
    private static final String PURCHASE_STATE_INVALID_QUANTITY = "invalid_quantity";
    private static final String PURCHASE_STATE_INVALID_SHIPPING = "invalid_shipping";
    private static final String PURCHASE_STATE_INVALID_SHIPPING_CURRENCY = "invalid_shipping_currency";
    private static final String PURCHASE_STATE_INVALID_VARIATIONS = "invalid_variations";
    private static final String PURCHASE_STATE_NOT_ACTIVE = "not_active";
    private static final String PURCHASE_STATE_VALID = "valid";
    private static final long serialVersionUID = 6017063115358062298L;
    private int mAvailableQuantity;
    private EtsyMoney mConvertedPrice;
    private boolean mEarliestLocalDeliveryIsToday;
    private String mEarliestLocalDeliveryTime;
    private GiftCardInfo mGiftCardInfo;
    private boolean mHasVariations;
    private String mImageUrl;
    private boolean mIsDownload;
    private boolean mIsGiftCard;
    private long mListingCustomizationId;
    private EtsyId mListingId;
    private boolean mOffersLocalDelivery;
    private EtsyMoney mPrice;
    private int mPurchaseQuantity;
    private String mPurchaseState;
    private Map<Long, Variation> mSelectedVariations;
    private String mTitle;
    private String mUrl;
    private boolean mVariationsAvailable;

    public CartListing() {
        this.mPurchaseState = StringUtils.EMPTY;
        this.mTitle = StringUtils.EMPTY;
        this.mImageUrl = StringUtils.EMPTY;
        this.mPrice = new EtsyMoney();
        this.mConvertedPrice = new EtsyMoney();
        this.mOffersLocalDelivery = false;
        this.mEarliestLocalDeliveryTime = StringUtils.EMPTY;
        this.mEarliestLocalDeliveryIsToday = false;
        this.mListingId = new EtsyId();
        this.mSelectedVariations = new ConcurrentHashMap();
    }

    public EtsyId getListingId() {
        return this.mListingId;
    }

    public long getListingCustomizationId() {
        return this.mListingCustomizationId;
    }

    public int getPurchaseQuantity() {
        return this.mPurchaseQuantity;
    }

    public void setPurchaseQuantity(int i) {
        this.mPurchaseQuantity = i;
    }

    public int getAvailableQuantity() {
        return this.mAvailableQuantity;
    }

    public void setPrice(String str) {
        this.mPrice = this.mPrice.withAmount(str);
    }

    public String getPurchaseState() {
        return this.mPurchaseState;
    }

    public boolean isEdited() {
        return this.mPurchaseState.equals(PURCHASE_STATE_EDITED);
    }

    public boolean isValid() {
        return this.mPurchaseState.equals(PURCHASE_STATE_VALID);
    }

    public boolean isShippingInvalid() {
        return this.mPurchaseState.equals(PURCHASE_STATE_INVALID_SHIPPING);
    }

    public boolean isNotActive() {
        return this.mPurchaseState.equals(PURCHASE_STATE_NOT_ACTIVE);
    }

    public boolean isCurrencyInvalid() {
        return this.mPurchaseState.equals(PURCHASE_STATE_INVALID_CURRENCY);
    }

    public boolean isQuantityInvalid() {
        return this.mPurchaseState.equals(PURCHASE_STATE_INVALID_QUANTITY);
    }

    public boolean isCurrencyShippingInvalid() {
        return this.mPurchaseState.equals(PURCHASE_STATE_INVALID_SHIPPING_CURRENCY);
    }

    public boolean isInvalidVariations() {
        return this.mPurchaseState.equals(PURCHASE_STATE_INVALID_VARIATIONS);
    }

    public boolean isEditable() {
        return isQuantityInvalid() || (isEdited() && !hasVariations());
    }

    public boolean hasVariations() {
        return this.mHasVariations;
    }

    public boolean hasVariationsAvailable() {
        return this.mVariationsAvailable;
    }

    public boolean isMissingVariations() {
        return this.mVariationsAvailable && !this.mHasVariations;
    }

    public List<Variation> getSelectedVariationsAsList() {
        return new ArrayList(this.mSelectedVariations.values());
    }

    public Map<Long, Variation> getSelectedVariations() {
        return this.mSelectedVariations;
    }

    public Variation getVariation(long j) {
        return (Variation) this.mSelectedVariations.get(Long.valueOf(j));
    }

    public boolean hasInvalidVariations() {
        for (Variation isValid : this.mSelectedVariations.values()) {
            if (!isValid.isValid()) {
                return true;
            }
        }
        return false;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public BaseModelImage getListingImage() {
        return ListingImage.get75x75(this.mImageUrl);
    }

    public String getUrl() {
        return this.mUrl;
    }

    public boolean isAd() {
        return false;
    }

    public ArrayList<Variation> getInvalidVariations() {
        ArrayList<Variation> arrayList = new ArrayList();
        for (Variation variation : this.mSelectedVariations.values()) {
            if (!variation.isValid()) {
                arrayList.add(variation);
            }
        }
        return arrayList;
    }

    public EtsyMoney getPrice() {
        return this.mConvertedPrice.getAmount().equals(new BigDecimal(0)) ? this.mPrice : this.mConvertedPrice;
    }

    public String getPriceAsString() {
        return String.valueOf(getPrice());
    }

    public EtsyMoney getConvertedPrice() {
        return this.mConvertedPrice;
    }

    public boolean isGiftCard() {
        return this.mIsGiftCard;
    }

    public GiftCardInfo getGiftCardInfo() {
        return this.mGiftCardInfo;
    }

    public boolean isDigitalDownload() {
        return this.mIsDownload;
    }

    public boolean offersLocalDelivery() {
        return this.mOffersLocalDelivery;
    }

    public String getEarliestLocalDeliveryTime() {
        return this.mEarliestLocalDeliveryTime;
    }

    public boolean isEarliestLocalDeliveryToday() {
        return this.mEarliestLocalDeliveryIsToday;
    }

    public boolean isFundOnEtsyCampaign() {
        return false;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.LISTING_ID.equals(currentName)) {
                    this.mListingId.setId(BaseModel.parseString(jsonParser));
                } else if ("listing_customization_id".equals(currentName)) {
                    this.mListingCustomizationId = jsonParser.getValueAsLong();
                } else if (ResponseConstants.PURCHASE_QUANTITY.equals(currentName)) {
                    this.mPurchaseQuantity = jsonParser.getValueAsInt();
                } else if (ResponseConstants.AVAILABLE_QUANTITY.equals(currentName)) {
                    this.mAvailableQuantity = jsonParser.getValueAsInt();
                } else if ("purchase_state".equals(currentName)) {
                    this.mPurchaseState = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.HAS_VARIATIONS.equals(currentName)) {
                    this.mHasVariations = jsonParser.getValueAsBoolean();
                } else if ("variations_available".equals(currentName)) {
                    this.mVariationsAvailable = jsonParser.getValueAsBoolean();
                } else if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser);
                } else if ("image_url_75x75".equals(currentName)) {
                    this.mImageUrl = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.PRICE.equals(currentName)) {
                    this.mPrice = this.mPrice.withAmount(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.CONVERTED_PRICE.equals(currentName)) {
                    this.mConvertedPrice = this.mConvertedPrice.withAmount(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.CURRENCY_CODE.equals(currentName)) {
                    this.mPrice = this.mPrice.withCurrency(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.SELECTED_VARIATIONS.equals(currentName)) {
                    List<Variation> parseArray = BaseModel.parseArray(jsonParser, Variation.class);
                    if (parseArray != null) {
                        for (Variation variation : parseArray) {
                            this.mSelectedVariations.put(Long.valueOf(variation.getPropertyId()), variation);
                        }
                    }
                } else if (ResponseConstants.IS_GIFT_CARD.equals(currentName)) {
                    this.mIsGiftCard = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.GIFT_CARD_INFO.equals(currentName)) {
                    if (jsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
                        this.mGiftCardInfo = (GiftCardInfo) BaseModel.parseObject(jsonParser, GiftCardInfo.class);
                    } else if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        this.mGiftCardInfo = (GiftCardInfo) BaseModel.parseObject(jsonParser, GiftCardInfo.class);
                        return;
                    }
                } else if (ResponseConstants.IS_DOWNLOAD.equals(currentName)) {
                    this.mIsDownload = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.URL.equals(currentName)) {
                    this.mUrl = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.OFFERS_LOCAL_DELIVERY.equals(currentName)) {
                    this.mOffersLocalDelivery = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.EARLIEST_LOCAL_DELIVERY_TIME.equals(currentName)) {
                    this.mEarliestLocalDeliveryTime = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.EARLIEST_LOCAL_DELIVERY_IS_TODAY.equals(currentName)) {
                    this.mEarliestLocalDeliveryIsToday = jsonParser.getValueAsBoolean();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.LISTING_ID, this.mListingId.getId());
        return hashMap;
    }
}
