package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.SearchAdsMetadata;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.fasterxml.jackson.core.JsonParser;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ListingCard extends BaseFieldModel implements ListingLike {
    private static final String IMAGES = "Images";
    private static final String PROLIST_LOGGING_KEY = "prolist_logging_key";
    private static final String SHOP = "Shop";
    private static final long serialVersionUID = 4083072698296386270L;
    protected FundOnEtsyCampaign mFundOnEtsyCampaign;
    protected boolean mHasCollections;
    protected boolean mHasError;
    protected boolean mIsAd;
    protected boolean mIsFavorite;
    protected boolean mIsFundOnEtsyCampaign;
    protected boolean mIsSoldOut;
    protected EtsyId mListingId;
    protected BaseModelImage mListingImage;
    protected EtsyMoney mPrice;
    protected double mPriceUnformatted;
    protected String mProlistLoggingKey;
    protected int mQuantity;
    protected String mServerFormattedPrice;
    protected float mShopAverageRating;
    protected EtsyId mShopId;
    protected String mShopName;
    protected int mShopTotalRatingCount;
    protected String mTitle;
    protected String mUrl;
    protected int mViewType;

    public ListingCard() {
        this.mListingId = new EtsyId();
        this.mShopId = new EtsyId();
        this.mShopName = StringUtils.EMPTY;
        this.mTitle = StringUtils.EMPTY;
        this.mServerFormattedPrice = StringUtils.EMPTY;
        this.mPrice = new EtsyMoney();
        this.mUrl = StringUtils.EMPTY;
        this.mProlistLoggingKey = StringUtils.EMPTY;
        this.mQuantity = -1;
        this.mShopAverageRating = 0.0f;
        this.mShopTotalRatingCount = -1;
        this.mViewType = 4;
        this.mIsFundOnEtsyCampaign = false;
    }

    public EtsyId getListingId() {
        return this.mListingId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public EtsyMoney getPrice() {
        return this.mPrice;
    }

    public double getPriceUnformatted() {
        return this.mPriceUnformatted;
    }

    public String getPriceAsString() {
        return getPrice().getAmount().toString();
    }

    public String getShopName() {
        return this.mShopName;
    }

    public EtsyId getShopId() {
        return this.mShopId;
    }

    public SearchAdsMetadata getSearchAdsMetadata() {
        return SearchAdsMetadata.fromPromotedLoggingKey(this.mProlistLoggingKey);
    }

    public boolean isAd() {
        return this.mIsAd;
    }

    public boolean isFavorite() {
        return this.mIsFavorite;
    }

    public void setIsFavorite(boolean z) {
        this.mIsFavorite = z;
    }

    public boolean hasCollections() {
        return this.mHasCollections;
    }

    public void setHasCollections(boolean z) {
        this.mHasCollections = z;
    }

    public BaseModelImage getListingImage() {
        return this.mListingImage;
    }

    public String getProlistLoggingKey() {
        return this.mProlistLoggingKey;
    }

    public boolean isSoldOut() {
        return this.mIsSoldOut;
    }

    public boolean isFundOnEtsyCampaign() {
        return this.mIsFundOnEtsyCampaign;
    }

    public FundOnEtsyCampaign getFundOnEtsyCampaign() {
        return this.mFundOnEtsyCampaign;
    }

    public boolean hasError() {
        return this.mHasError;
    }

    public int getQuantity() {
        return this.mQuantity;
    }

    public int getShopTotalRatingCount() {
        return this.mShopTotalRatingCount;
    }

    public float getShopAverageRating() {
        return this.mShopAverageRating;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (ResponseConstants.LISTING_ID.equals(str)) {
            this.mListingId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
        } else if (ResponseConstants.SHOP_ID.equals(str)) {
            this.mShopId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
        } else if (SHOP.equals(str)) {
            Shop shop = (Shop) BaseModel.parseObject(jsonParser, Shop.class);
            this.mShopId = shop.getShopId();
            this.mShopName = shop.getShopName();
        } else if (ResponseConstants.SHOP_NAME.equals(str)) {
            this.mShopName = BaseModel.parseString(jsonParser);
        } else if (FindsModule.FIELD_TITLE.equals(str)) {
            this.mTitle = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.IS_SOLD_OUT.equals(str)) {
            this.mIsSoldOut = jsonParser.getValueAsBoolean();
        } else if (ResponseConstants.SHOP_AVERAGE_RATING.equals(str)) {
            this.mShopAverageRating = (float) jsonParser.getValueAsDouble();
        } else if (ResponseConstants.SHOP_TOTAL_RATING_COUNT.equals(str)) {
            this.mShopTotalRatingCount = jsonParser.getValueAsInt();
        } else if (ResponseConstants.QUANTITY.equals(str)) {
            this.mQuantity = jsonParser.getValueAsInt();
        } else if (ResponseConstants.PRICE.equals(str)) {
            this.mServerFormattedPrice = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.PRICE_UNFORMATTED.equals(str)) {
            this.mPriceUnformatted = jsonParser.getValueAsDouble();
            this.mPrice = this.mPrice.withAmount(BaseModel.parseString(jsonParser));
        } else if (ResponseConstants.CURRENCY_CODE.equals(str)) {
            this.mPrice = this.mPrice.withCurrency(BaseModel.parseString(jsonParser));
        } else if (ResponseConstants.URL.equals(str)) {
            this.mUrl = BaseModel.parseStringURL(jsonParser);
        } else if (ResponseConstants.IMG.equals(str)) {
            this.mListingImage = (BaseModelImage) BaseModel.parseObject(jsonParser, ListingImage.class);
        } else if (PROLIST_LOGGING_KEY.equals(str)) {
            this.mProlistLoggingKey = BaseModel.parseString(jsonParser);
            this.mIsAd = true;
        } else if (IMAGES.equals(str)) {
            List parseArray = BaseModel.parseArray(jsonParser, ListingImage.class);
            if (parseArray != null && parseArray.size() > 0) {
                this.mListingImage = (BaseModelImage) parseArray.get(0);
            }
        } else if (ResponseConstants.IS_FAVORITE.equals(str)) {
            this.mIsFavorite = jsonParser.getValueAsBoolean();
        } else if (ResponseConstants.IS_IN_COLLECTIONS.equals(str)) {
            this.mHasCollections = jsonParser.getValueAsBoolean();
        } else if (ResponseConstants.IS_FUND_ON_ETSY_CAMPAIGN.equals(str)) {
            this.mIsFundOnEtsyCampaign = jsonParser.getValueAsBoolean();
        } else if (ResponseConstants.FUND_ON_ETSY_CAMPAIGN.equals(str)) {
            this.mFundOnEtsyCampaign = (FundOnEtsyCampaign) BaseModel.parseObject(jsonParser, FundOnEtsyCampaign.class);
        } else if (ResponseConstants.FUND_ON_ETSY_CAMPAIGN2.equals(str)) {
            this.mFundOnEtsyCampaign = (FundOnEtsyCampaign) BaseModel.parseObject(jsonParser, FundOnEtsyCampaign.class);
        } else if (!BaseMessage.TYPE_ERROR.equals(str)) {
            return false;
        } else {
            this.mHasError = true;
        }
        return true;
    }

    public String getUrl() {
        return this.mUrl;
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.LISTING_ID, this.mListingId.getId());
        return hashMap;
    }

    public int getViewType() {
        return this.mViewType;
    }

    public void setViewType(int i) {
        this.mViewType = i;
    }
}
