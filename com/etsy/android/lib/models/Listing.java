package com.etsy.android.lib.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.OfferingResponse;
import com.etsy.android.lib.models.apiv3.TranslatedListing;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.interfaces.BasicListingLike;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Listing extends BaseModel implements BasicListingLike, ListingLike {
    public static final String ACTIVE_STATE = "active";
    protected static final String CLOSED_STATE = "a_closed";
    protected static final String CLOSED_STATE_M = "m_closed";
    public static final String DRAFT_STATE = "draft";
    public static final String EDIT_STATE = "edit";
    public static final String EXPIRED_STATE = "expired";
    public static final String INACTIVE_STATE = "inactive";
    public static final String REMOVED_STATE = "removed";
    public static final String SOLD_OUT_STATE = "sold_out";
    protected static final String TAG;
    protected static final String UNAVAILABLE_STATE = "unavailable";
    public static final String VACATION_STATE = "vacation";
    private static final long serialVersionUID = 1359548269852055547L;
    protected EtsyMoney mBuyerDisplayPrice;
    protected List<Collection> mCollections;
    @Deprecated
    protected String mConvertedCurrencyCode;
    @Deprecated
    protected String mConvertedPrice;
    protected Date mCreationDate;
    protected String mDescription;
    protected FundOnEtsyCampaign mFundOnEtsyCampaign;
    protected boolean mHasCollections;
    protected List<ListingImage> mImages;
    protected boolean mIsDigitalDownload;
    protected boolean mIsFavorite;
    protected Boolean mIsFundOnEtsyCampaign;
    protected boolean mIsPrivate;
    protected boolean mIsVATInclusive;
    protected String mLanguage;
    protected EtsyId mListingId;
    protected ListingVideos mListingVideos;
    protected transient TranslatedListing mMachineTranslation;
    protected List<Manufacturer> mManufacturers;
    protected ListingUpdate mMostRecentUpdate;
    protected int mNumFavorers;
    protected OfferingResponse mOfferings;
    protected List<ListingOption> mOptions;
    protected String mOriginalLanguage;
    protected List<String> mOverview;
    protected PaymentTemplate mPaymentInfo;
    protected EtsyMoney mPrice;
    protected int mProcessingMax;
    protected int mProcessingMin;
    protected int mQuantity;
    protected SearchAdsMetadata mSearchAdsMetadata;
    protected List<ShippingInfo> mShippingInfo;
    protected Shop mShop;
    protected boolean mShouldAutoRenew;
    protected String mState;
    protected String mTitle;
    protected transient List<Listing> mTranslations;
    protected String mTransparentPriceMessage;
    protected String mUrl;
    protected EtsyId mUserId;
    protected List<Variation> mVariations;
    protected int mViews;

    static {
        TAG = EtsyDebug.m1891a(Listing.class);
    }

    public Listing() {
        this.mTitle = StringUtils.EMPTY;
        this.mPrice = new EtsyMoney();
        this.mState = StringUtils.EMPTY;
        this.mShouldAutoRenew = false;
        this.mDescription = StringUtils.EMPTY;
        this.mUrl = StringUtils.EMPTY;
        this.mLanguage = StringUtils.EMPTY;
        this.mOriginalLanguage = StringUtils.EMPTY;
        this.mConvertedPrice = StringUtils.EMPTY;
        this.mConvertedCurrencyCode = StringUtils.EMPTY;
        this.mBuyerDisplayPrice = new EtsyMoney();
        this.mIsPrivate = false;
        this.mIsDigitalDownload = false;
        this.mIsVATInclusive = false;
        this.mTransparentPriceMessage = StringUtils.EMPTY;
        this.mHasCollections = false;
        this.mListingId = new EtsyId();
        this.mUserId = new EtsyId();
        this.mImages = new ArrayList(0);
        this.mShippingInfo = new ArrayList(0);
        this.mVariations = new ArrayList(0);
        this.mManufacturers = new ArrayList(0);
        this.mCollections = new ArrayList(0);
        this.mOptions = new ArrayList();
        this.mOverview = new ArrayList(0);
    }

    public Listing(EtsyId etsyId, String str) {
        this();
        this.mListingId = etsyId;
        this.mImages.add(new ListingImage(str, 0));
    }

    public Listing(EtsyId etsyId, String str, String str2, String str3, String str4, int i) {
        this.mTitle = StringUtils.EMPTY;
        this.mPrice = new EtsyMoney();
        this.mState = StringUtils.EMPTY;
        this.mShouldAutoRenew = false;
        this.mDescription = StringUtils.EMPTY;
        this.mUrl = StringUtils.EMPTY;
        this.mLanguage = StringUtils.EMPTY;
        this.mOriginalLanguage = StringUtils.EMPTY;
        this.mConvertedPrice = StringUtils.EMPTY;
        this.mConvertedCurrencyCode = StringUtils.EMPTY;
        this.mBuyerDisplayPrice = new EtsyMoney();
        this.mIsPrivate = false;
        this.mIsDigitalDownload = false;
        this.mIsVATInclusive = false;
        this.mTransparentPriceMessage = StringUtils.EMPTY;
        this.mHasCollections = false;
        this.mUserId = new EtsyId();
        this.mImages = new ArrayList(0);
        this.mShippingInfo = new ArrayList(0);
        this.mVariations = new ArrayList(0);
        this.mManufacturers = new ArrayList(0);
        this.mCollections = new ArrayList(0);
        this.mOverview = new ArrayList(0);
        this.mListingId = etsyId;
        this.mTitle = str;
        this.mPrice = this.mPrice.withAmount(str2);
        this.mPrice = this.mPrice.withCurrency(str3);
        this.mImages.add(new ListingImage(str4, i));
    }

    public EtsyId getListingId() {
        return this.mListingId;
    }

    public String getState() {
        return this.mState;
    }

    public void setState(String str) {
        this.mState = str;
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getTranslatedTitle(boolean z) {
        if (z) {
            return getOriginalTranslation().getTitle();
        }
        return getMachineTranslation().getTitle();
    }

    public String getDescription() {
        return this.mDescription;
    }

    public EtsyMoney getPrice() {
        return this.mBuyerDisplayPrice.getAmount().equals(new BigDecimal(0)) ? this.mPrice : this.mBuyerDisplayPrice;
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    public String getOriginalLanguage() {
        return StringUtils.EMPTY.equals(this.mOriginalLanguage) ? this.mLanguage : this.mOriginalLanguage;
    }

    public Listing getOriginalTranslation() {
        if (this.mTranslations != null) {
            for (Listing listing : this.mTranslations) {
                if (this.mOriginalLanguage.equals(listing.getLanguage())) {
                    return listing;
                }
            }
        }
        return this;
    }

    public boolean isMachineTranslated() {
        return aj.m3236c(this.mLanguage);
    }

    public boolean isMachineTranslatable() {
        return aj.m3231a(this);
    }

    public void setMachineTranslation(TranslatedListing translatedListing) {
        this.mMachineTranslation = translatedListing;
    }

    public Listing getMachineTranslation() {
        return isMachineTranslated() ? this : this.mMachineTranslation;
    }

    public String getCurrencyCode() {
        return this.mBuyerDisplayPrice.getAmount().equals(new BigDecimal(0)) ? this.mPrice.getCurrency().getCurrencyCode() : this.mBuyerDisplayPrice.getCurrency().getCurrencyCode();
    }

    public String getShopName() {
        return this.mShop != null ? this.mShop.getShopName() : null;
    }

    public EtsyId getShopId() {
        return this.mShop != null ? this.mShop.getShopId() : null;
    }

    public boolean isConverted() {
        return !this.mConvertedPrice.equals(StringUtils.EMPTY);
    }

    public boolean isAd() {
        return this.mSearchAdsMetadata != null;
    }

    public int getQuantity() {
        return this.mQuantity;
    }

    public boolean getShouldAutoRenew() {
        return this.mShouldAutoRenew;
    }

    public int getProcessingDaysMin() {
        return this.mProcessingMin;
    }

    public int getProcessingDaysMax() {
        return this.mProcessingMax;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public boolean isFavorite() {
        return this.mIsFavorite;
    }

    public boolean isPrivate() {
        return this.mIsPrivate;
    }

    public boolean isDigitalDownload() {
        return this.mIsDigitalDownload;
    }

    public boolean isVATInclusive() {
        return this.mIsVATInclusive;
    }

    @NonNull
    public String getTransparentPriceMessage() {
        return this.mTransparentPriceMessage;
    }

    public boolean hasTransparentPriceMessage() {
        return bh.m3343b(this.mTransparentPriceMessage);
    }

    public void setIsFavorite(boolean z) {
        this.mIsFavorite = z;
    }

    @Nullable
    public Shop getShop() {
        return this.mShop;
    }

    public List<ListingImage> getImages() {
        return this.mImages;
    }

    public List<ShippingInfo> getShippingInfo() {
        return this.mShippingInfo;
    }

    public PaymentTemplate getPaymentInfo() {
        return this.mPaymentInfo;
    }

    public List<Variation> getVariations() {
        return this.mVariations;
    }

    public int getViews() {
        return this.mViews;
    }

    public int getNumFavorers() {
        return this.mNumFavorers;
    }

    public Date getCreationDate() {
        return this.mCreationDate;
    }

    public SearchAdsMetadata getSearchAdsMetadata() {
        return this.mSearchAdsMetadata;
    }

    public BaseModelImage getListingImage() {
        return getImage();
    }

    public boolean isFundOnEtsyCampaign() {
        if (this.mIsFundOnEtsyCampaign == null) {
            return false;
        }
        return this.mIsFundOnEtsyCampaign.booleanValue();
    }

    public List<ListingOption> getOptions() {
        return this.mOptions;
    }

    public FundOnEtsyCampaign getFundOnEtsyCampaign() {
        return this.mFundOnEtsyCampaign;
    }

    public ListingUpdate getMostRecentUpdate() {
        return this.mMostRecentUpdate;
    }

    public boolean hasVideos() {
        return (this.mListingVideos == null || this.mListingVideos.getVideos() == null || this.mListingVideos.getVideos().size() == 0) ? false : true;
    }

    public ListingVideos getListingVideos() {
        return this.mListingVideos;
    }

    public boolean hasOptions() {
        return getOptions() != null && getOptions().size() > 0;
    }

    public ListingImage getImage() {
        if (this.mImages.size() > 0) {
            return (ListingImage) this.mImages.get(0);
        }
        return null;
    }

    public boolean hasPriceDiffVariation() {
        for (Variation hasPriceDiff : this.mVariations) {
            if (hasPriceDiff.hasPriceDiff()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasManufacturers() {
        return this.mManufacturers.size() > 0;
    }

    public List<Manufacturer> getManufacturers() {
        return this.mManufacturers;
    }

    public boolean hasCollections() {
        return this.mHasCollections;
    }

    public void setHasCollections(boolean z) {
        this.mHasCollections = z;
    }

    public List<Collection> getCollections() {
        return this.mCollections;
    }

    public Variation getVariation(int i) {
        for (Variation variation : this.mVariations) {
            if (variation.getPropertyId() == ((long) i)) {
                return variation;
            }
        }
        return null;
    }

    public boolean hasVariations() {
        return this.mVariations.size() > 0;
    }

    public int getVariationCount() {
        return this.mVariations.size();
    }

    public int getSelectedVariationCount() {
        if (this.mVariations.size() <= 0) {
            return 0;
        }
        int i = 0;
        for (Variation hasOptionSet : this.mVariations) {
            int i2;
            if (hasOptionSet.hasOptionSet()) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    public String getSelectedVariations() {
        JSONObject jSONObject = new JSONObject();
        for (Variation variation : this.mVariations) {
            if (variation.hasOptionSet()) {
                try {
                    jSONObject.put(variation.getPropertyId() + StringUtils.EMPTY, variation.getValueId());
                } catch (Throwable e) {
                    EtsyDebug.m1917d("Variation", "error parsing variation to JSON", e);
                }
            }
        }
        return jSONObject.toString();
    }

    public List<String> getOverview() {
        return this.mOverview;
    }

    public boolean hasOverview() {
        return this.mOverview != null && this.mOverview.size() > 0;
    }

    @Nullable
    public OfferingResponse getOfferings() {
        return this.mOfferings;
    }

    public boolean isSoldOut() {
        return SOLD_OUT_STATE.equalsIgnoreCase(this.mState);
    }

    public boolean isDraft() {
        return DRAFT_STATE.equalsIgnoreCase(this.mState);
    }

    public boolean isActive() {
        return ACTIVE_STATE.equalsIgnoreCase(this.mState);
    }

    public boolean isInactive() {
        return INACTIVE_STATE.equalsIgnoreCase(this.mState);
    }

    public boolean isEditState() {
        return EDIT_STATE.equalsIgnoreCase(this.mState);
    }

    public boolean isInactiveOrEdit() {
        return isInactive() || isEditState();
    }

    public boolean isExpired() {
        return EXPIRED_STATE.equalsIgnoreCase(this.mState);
    }

    public boolean isUnavailable() {
        return UNAVAILABLE_STATE.equalsIgnoreCase(this.mState);
    }

    public boolean isRemoved() {
        return REMOVED_STATE.equalsIgnoreCase(this.mState);
    }

    public boolean isOnVacation() {
        return VACATION_STATE.equalsIgnoreCase(this.mState);
    }

    public boolean isClosed() {
        return CLOSED_STATE.equalsIgnoreCase(this.mState) || CLOSED_STATE_M.equalsIgnoreCase(this.mState);
    }

    public static boolean isSoldOut(String str) {
        return SOLD_OUT_STATE.equalsIgnoreCase(str);
    }

    public static boolean isDraft(String str) {
        return DRAFT_STATE.equalsIgnoreCase(str);
    }

    public static boolean isActive(String str) {
        return ACTIVE_STATE.equalsIgnoreCase(str);
    }

    public static boolean isInactive(String str) {
        return INACTIVE_STATE.equalsIgnoreCase(str);
    }

    public static boolean isEditState(String str) {
        return EDIT_STATE.equalsIgnoreCase(str);
    }

    public static boolean isInactiveOrEdit(String str) {
        return isInactive(str) || isEditState(str);
    }

    public static boolean isExpired(String str) {
        return EXPIRED_STATE.equalsIgnoreCase(str);
    }

    public static boolean isUnavailable(String str) {
        return UNAVAILABLE_STATE.equalsIgnoreCase(str);
    }

    public static boolean isRemoved(String str) {
        return REMOVED_STATE.equalsIgnoreCase(str);
    }

    public static boolean isOnVacation(String str) {
        return VACATION_STATE.equalsIgnoreCase(str);
    }

    public static boolean isClosed(String str) {
        return CLOSED_STATE.equalsIgnoreCase(str);
    }

    public boolean canAddToCart() {
        return (isSoldOut() || isExpired() || isUnavailable() || isRemoved() || isOnVacation() || isInactiveOrEdit() || isClosed()) ? false : true;
    }

    public boolean canFavorite() {
        return (isExpired() || isUnavailable() || isRemoved() || isInactiveOrEdit() || isClosed()) ? false : true;
    }

    public boolean isVisible() {
        return (isUnavailable() || isRemoved() || isInactiveOrEdit() || isExpired() || isClosed()) ? false : true;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                parseListingField(jsonParser, currentName);
            }
        }
    }

    protected void parseListingField(JsonParser jsonParser, String str) {
        boolean z = false;
        if (ResponseConstants.LISTING_ID.equals(str)) {
            this.mListingId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
        } else if (ResponseConstants.USER_ID.equals(str)) {
            this.mUserId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
        } else if (FindsModule.FIELD_TITLE.equals(str)) {
            this.mTitle = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.DESCRIPTION.equals(str)) {
            this.mDescription = BaseModel.parseString(jsonParser).trim();
        } else if (ResponseConstants.PRICE.equals(str)) {
            this.mPrice = this.mPrice.withAmount(BaseModel.parseString(jsonParser));
        } else if (ResponseConstants.CURRENCY_CODE.equals(str)) {
            this.mPrice = this.mPrice.withCurrency(BaseModel.parseString(jsonParser));
        } else if (ResponseConstants.BUYER_DISPLAY_PRICE.equals(str)) {
            this.mBuyerDisplayPrice = this.mBuyerDisplayPrice.withAmount(BaseModel.parseString(jsonParser));
        } else if (ResponseConstants.BUYER_DISPLAY_CURRENCY_CODE.equals(str)) {
            this.mBuyerDisplayPrice = this.mBuyerDisplayPrice.withCurrency(BaseModel.parseString(jsonParser));
        } else if (ResponseConstants.CONVERTED_PRICE.equals(str)) {
            this.mConvertedPrice = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.CONVERTED_CURRENCY.equals(str)) {
            this.mConvertedCurrencyCode = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.QUANTITY.equals(str)) {
            this.mQuantity = jsonParser.getValueAsInt();
        } else if (ResponseConstants.PROCESSING_MIN.equals(str)) {
            this.mProcessingMin = jsonParser.getValueAsInt();
        } else if (ResponseConstants.PROCESSING_MAX.equals(str)) {
            this.mProcessingMax = jsonParser.getValueAsInt();
        } else if (ResponseConstants.IS_FAVORITE.equals(str)) {
            this.mIsFavorite = jsonParser.getValueAsBoolean();
        } else if (ResponseConstants.IS_PRIVATE.equals(str)) {
            this.mIsPrivate = jsonParser.getValueAsBoolean();
        } else if (ResponseConstants.IS_DIGITAL.equals(str)) {
            this.mIsDigitalDownload = jsonParser.getValueAsBoolean();
        } else if (ResponseConstants.IS_VAT_INCLUSIVE.equals(str)) {
            this.mIsVATInclusive = jsonParser.getValueAsBoolean();
        } else if (ResponseConstants.TRANSPARENT_PRICE_MESSAGE.equals(str)) {
            this.mTransparentPriceMessage = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.URL.equals(str)) {
            this.mUrl = BaseModel.parseStringURL(jsonParser);
        } else if (ResponseConstants.STATE.equals(str)) {
            this.mState = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.VIEWS.equals(str)) {
            this.mViews = jsonParser.getValueAsInt();
        } else if (ResponseConstants.NUM_FAVORERS.equals(str)) {
            this.mNumFavorers = jsonParser.getValueAsInt();
        } else if (ResponseConstants.CREATION_TSZ.equals(str)) {
            this.mCreationDate = BaseModel.parseIntoDate(jsonParser);
        } else if (Includes.SHOP.equals(str)) {
            this.mShop = (Shop) BaseModel.parseObject(jsonParser, Shop.class);
        } else if (Includes.IMAGES.equals(str) || ResponseConstants.IMG.equals(str) || ResponseConstants.LISTING_IMAGES.equals(str)) {
            this.mImages = BaseModel.parseArray(jsonParser, ListingImage.class);
        } else if (Includes.VARIATIONS.equals(str)) {
            this.mVariations = BaseModel.parseArray(jsonParser, Variation.class);
        } else if (Includes.MANUFACTURERS.equals(str)) {
            this.mManufacturers = BaseModel.parseArray(jsonParser, Manufacturer.class);
        } else if (ResponseConstants.IN_COLLECTIONS.equals(str)) {
            this.mCollections = BaseModel.parseArray(jsonParser, Collection.class);
            removeFavoritesCollection();
            if (this.mCollections.size() > 0) {
                z = true;
            }
            setHasCollections(z);
        } else if (Includes.SHIPPING_INFO.equals(str)) {
            this.mShippingInfo = BaseModel.parseArray(jsonParser, ShippingInfo.class);
        } else if (Includes.PAYMENT_INFO.equals(str)) {
            this.mPaymentInfo = (PaymentTemplate) BaseModel.parseObject(jsonParser, PaymentTemplate.class);
        } else if (ResponseConstants.SEARCH_ADS_METADATA.equals(str)) {
            this.mSearchAdsMetadata = (SearchAdsMetadata) BaseModel.parseObject(jsonParser, SearchAdsMetadata.class);
        } else if (ResponseConstants.OVERVIEW.equals(str)) {
            this.mOverview = BaseModel.parseStringArray(jsonParser);
        } else if (ResponseConstants.IS_FUND_ON_ETSY_CAMPAIGN.equals(str)) {
            this.mIsFundOnEtsyCampaign = Boolean.valueOf(jsonParser.getValueAsBoolean());
        } else if (EtsyRequest.PARAM_LANGUAGE.equals(str)) {
            this.mLanguage = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.ALTERNATE_LANGUAGE.equals(str)) {
            this.mOriginalLanguage = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.TRANSLATIONS.equals(str)) {
            this.mTranslations = BaseModel.parseArray(jsonParser, Listing.class);
        } else if (Includes.OPTIONS.equals(str)) {
            this.mOptions = BaseModel.parseArray(jsonParser, ListingOption.class);
        } else if (ResponseConstants.FUND_ON_ETSY_CAMPAIGN.equals(str)) {
            r2 = BaseModel.parseArray(jsonParser, FundOnEtsyCampaign.class);
            FundOnEtsyCampaign fundOnEtsyCampaign = (r2 == null || r2.size() <= 0) ? null : (FundOnEtsyCampaign) r2.get(0);
            this.mFundOnEtsyCampaign = fundOnEtsyCampaign;
        } else if (Includes.MOST_RECENT_UPDATE.equals(str)) {
            r2 = BaseModel.parseArray(jsonParser, ListingUpdate.class);
            ListingUpdate listingUpdate = (r2 == null || r2.size() <= 0) ? null : (ListingUpdate) r2.get(0);
            this.mMostRecentUpdate = listingUpdate;
        } else if (Includes.VIDEOS.equals(str)) {
            r2 = BaseModel.parseArray(jsonParser, ListingVideos.class);
            ListingVideos listingVideos = (r2 == null || r2.size() <= 0) ? null : (ListingVideos) r2.get(0);
            this.mListingVideos = listingVideos;
        } else if (ResponseConstants.OFFERINGS.equals(str)) {
            this.mOfferings = (OfferingResponse) BaseModel.parseObject(jsonParser, OfferingResponse.class);
        } else {
            jsonParser.skipChildren();
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Listing) {
            return ((Listing) obj).getListingId().equals(getListingId());
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return this.mListingId.hashCode();
    }

    protected void removeFavoritesCollection() {
        Iterator it = this.mCollections.iterator();
        while (it.hasNext()) {
            if (Collection.TYPE_FAVORITES.equals(((Collection) it.next()).getType())) {
                it.remove();
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
