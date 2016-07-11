package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShortenedUrl;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.AppreciationPhotoLike;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class AppreciationPhotoFeature extends BaseModel implements AppreciationPhotoLike {
    private static final int SIMILAR_LISTINGS_LIMIT = 8;
    private static final long serialVersionUID = -5841643752855431293L;
    protected String mBuyerAvatarUrl;
    protected String mBuyerProfileUrl;
    protected String mBuyerRealName;
    protected EtsyId mBuyerUserId;
    protected String mCreateDate;
    protected String mCreateSource;
    protected boolean mIsPrimaryListingAvailable;
    protected boolean mIsShopOnVacation;
    protected ListingCard mListingCard;
    protected String mPhotoUrl640x640;
    protected String mPhotoUrlFullxFull;
    protected float mShopAverageRating;
    protected String mShopIconUrl;
    protected EtsyId mShopId;
    protected List<ListingCard> mShopListings;
    protected String mShopLocation;
    protected String mShopName;
    protected String mShopTitle;
    protected int mShopTotalRatings;
    protected ShortenedUrl mShortenedShareUrl;
    protected List<ListingCard> mSimilarListings;
    protected EtsyId mTransactionId;

    public AppreciationPhotoFeature() {
        this.mShopListings = new ArrayList(0);
        this.mSimilarListings = new ArrayList(0);
    }

    public EtsyId getTransactionId() {
        return this.mTransactionId;
    }

    public String getPhotoUrl() {
        return this.mPhotoUrlFullxFull;
    }

    public String getBuyerAvatarUrl() {
        return this.mBuyerAvatarUrl;
    }

    public String getBuyerRealName() {
        return this.mBuyerRealName;
    }

    public EtsyId getBuyerUserId() {
        return this.mBuyerUserId;
    }

    public String getBuyerProfileUrl() {
        return this.mBuyerProfileUrl;
    }

    public String getCreateDate() {
        return this.mCreateDate;
    }

    public String getCreateSource() {
        return this.mCreateSource;
    }

    public EtsyId getShopId() {
        return this.mShopId;
    }

    public int getShopTotalRatings() {
        return this.mShopTotalRatings;
    }

    public boolean isShopOnVacation() {
        return this.mIsShopOnVacation;
    }

    public String getShopLocation() {
        return this.mShopLocation;
    }

    public float getShopAverageRating() {
        return this.mShopAverageRating;
    }

    public String getShopTitle() {
        return this.mShopTitle;
    }

    public String getShopIconUrl() {
        return this.mShopIconUrl;
    }

    public ListingCard getListingCard() {
        return this.mListingCard;
    }

    public List<ListingCard> getSimilarListings() {
        return this.mSimilarListings;
    }

    public List<ListingCard> getShopListings() {
        return this.mShopListings;
    }

    public boolean isPrimaryListingAvailable() {
        return this.mIsPrimaryListingAvailable;
    }

    public String getShopName() {
        return this.mShopName;
    }

    public String getListingTitle() {
        return this.mListingCard.getTitle();
    }

    public String getPhotoUrl640x640() {
        return this.mPhotoUrl640x640;
    }

    public String getShareImageUrl() {
        return this.mPhotoUrl640x640;
    }

    public ShortenedUrl getShortenedShareUrl() {
        if (this.mShortenedShareUrl == null) {
            this.mShortenedShareUrl = new ShortenedUrl(this.mTransactionId != null ? AppreciationPhoto.buildShareUrl(this.mTransactionId) : StringUtils.EMPTY);
        }
        return this.mShortenedShareUrl;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.TRANSACTION_ID.equals(currentName)) {
                    this.mTransactionId = new EtsyId();
                    this.mTransactionId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.PHOTO_URL.equals(currentName)) {
                    this.mPhotoUrlFullxFull = BaseModel.parseStringURL(jsonParser);
                    this.mPhotoUrl640x640 = this.mPhotoUrlFullxFull.replace("fullxfull", "640x640");
                } else if (ResponseConstants.BUYER_AVATAR_URL.equals(currentName)) {
                    this.mBuyerAvatarUrl = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.BUYER_REAL_NAME.equals(currentName)) {
                    this.mBuyerRealName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.BUYER_PROFILE_URL.equals(currentName)) {
                    this.mBuyerProfileUrl = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.CREATE_DATE.equals(currentName)) {
                    this.mCreateDate = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.CREATE_SOURCE.equals(currentName)) {
                    this.mCreateSource = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SHOP_NAME.equals(currentName)) {
                    this.mShopName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SHOP_ID.equals(currentName)) {
                    this.mShopId = new EtsyId();
                    this.mShopId.setId(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.SHOP_LOCATION.equals(currentName)) {
                    this.mShopLocation = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SIMILAR_LISTINGS.equals(currentName)) {
                    List parseArray = BaseModel.parseArray(jsonParser, ListingCard.class);
                    this.mSimilarListings = parseArray.size() > SIMILAR_LISTINGS_LIMIT ? new ArrayList(parseArray.subList(0, SIMILAR_LISTINGS_LIMIT)) : parseArray;
                } else if (ResponseConstants.LISTING_CARD.equals(currentName)) {
                    this.mListingCard = (ListingCard) BaseModel.parseObject(jsonParser, ListingCard.class);
                } else if (ResponseConstants.BUYER_USER_ID.equals(currentName)) {
                    this.mBuyerUserId = new EtsyId();
                    this.mBuyerUserId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.LISTING_AVAILABLE.equals(currentName)) {
                    this.mIsPrimaryListingAvailable = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.SHOP_IS_ON_VACATION.equals(currentName)) {
                    this.mIsShopOnVacation = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.SHOP_AVERAGE_RATING.equals(currentName)) {
                    this.mShopAverageRating = (float) jsonParser.getValueAsDouble();
                } else if (ResponseConstants.SHOP_TOTAL_RATINGS.equals(currentName)) {
                    this.mShopTotalRatings = jsonParser.getValueAsInt();
                } else if (ResponseConstants.SHOP_TITLE.equals(currentName)) {
                    this.mShopTitle = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SHOP_LISTINGS.equals(currentName)) {
                    this.mShopListings = BaseModel.parseArray(jsonParser, ListingCard.class);
                } else if (ResponseConstants.SHOP_AVATAR.equals(currentName)) {
                    this.mShopIconUrl = BaseModel.parseStringURL(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
