package com.etsy.android.lib.models.apiv3;

import android.support.annotation.Nullable;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShopCard extends BaseModel implements ShopLike {
    private static final long serialVersionUID = 9060218421854219479L;
    protected int mActiveListingCount;
    protected List<ListingCard> mDisplayListings;
    protected boolean mHasIcon;
    protected String mHeadline;
    protected Image mIcon;
    protected boolean mIsFavorite;
    protected boolean mIsVacation;
    protected LocalMarket mLocalEvent;
    protected String mLocation;
    protected String mLoginName;
    protected Date mOpenDate;
    protected Rating mRating;
    protected String mSellerAvatar;
    protected EtsyId mShopId;
    protected String mShopName;
    protected String mShopUrl;
    protected EtsyId mUserId;

    public ShopCard() {
        this.mShopId = new EtsyId();
        this.mUserId = new EtsyId();
        this.mShopName = StringUtils.EMPTY;
        this.mHeadline = StringUtils.EMPTY;
        this.mSellerAvatar = StringUtils.EMPTY;
        this.mShopUrl = StringUtils.EMPTY;
        this.mLocation = StringUtils.EMPTY;
        this.mLoginName = StringUtils.EMPTY;
        this.mDisplayListings = new ArrayList();
    }

    public EtsyId getShopId() {
        return this.mShopId;
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public String getShopName() {
        return this.mShopName;
    }

    public List<? extends ListingLike> getCardListings() {
        return this.mDisplayListings;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public String getAvatarUrl() {
        return this.mSellerAvatar;
    }

    public String getIconUrl(int i) {
        if (this.mIcon != null) {
            return ImageBatch.m1557a(i, i, this.mIcon);
        }
        return this.mSellerAvatar;
    }

    public boolean hasRatings() {
        return this.mRating != null && this.mRating.getRatingCount() > 0;
    }

    public double getAverageRating() {
        if (this.mRating != null) {
            return this.mRating.getRating();
        }
        return 0.0d;
    }

    public int getNumRatings() {
        if (this.mRating != null) {
            return this.mRating.getRatingCount();
        }
        return 0;
    }

    public boolean isFavorite() {
        return this.mIsFavorite;
    }

    public void setIsFavorite(boolean z) {
        this.mIsFavorite = z;
    }

    public boolean isVacation() {
        return this.mIsVacation;
    }

    public String getHeadline() {
        return this.mHeadline;
    }

    public String getUrl() {
        return this.mShopUrl;
    }

    public boolean hasUpcomingLocalEvent() {
        return this.mLocalEvent != null;
    }

    public LocalMarket getUpcomingLocalEvent() {
        return this.mLocalEvent;
    }

    public int getActiveListingCount() {
        return this.mActiveListingCount;
    }

    public Date getOpenDate() {
        return this.mOpenDate;
    }

    public boolean hasIcon() {
        return this.mHasIcon;
    }

    public Image getIcon() {
        return this.mIcon;
    }

    public String getLoginName() {
        return this.mLoginName;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.SHOP_ID.equals(currentName)) {
                    this.mShopId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.USER_ID.equals(currentName)) {
                    this.mUserId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.SHOP_NAME.equals(currentName)) {
                    this.mShopName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.HEADLINE.equals(currentName)) {
                    this.mHeadline = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SELLER_AVATAR.equals(currentName)) {
                    this.mSellerAvatar = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.SHOP_URL.equals(currentName)) {
                    this.mShopUrl = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.LOCATION.equals(currentName)) {
                    this.mLocation = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.ACTIVE_LISTING_COUNT.equals(currentName)) {
                    this.mActiveListingCount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.DISPLAY_LISTINGS.equals(currentName)) {
                    this.mDisplayListings = BaseModel.parseArray(jsonParser, ListingCard.class);
                } else if (ResponseConstants.RATING.equals(currentName)) {
                    this.mRating = (Rating) BaseModel.parseObject(jsonParser, Rating.class);
                } else if (Includes.UPCOMING_LOCAL_EVENT.equals(currentName)) {
                    this.mLocalEvent = (LocalMarket) BaseModel.parseObject(jsonParser, LocalMarket.class);
                } else if (ResponseConstants.IS_FAVORITE.equals(currentName)) {
                    this.mIsFavorite = jsonParser.getBooleanValue();
                } else if (ResponseConstants.IS_VACATION.equals(currentName)) {
                    this.mIsVacation = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.HAS_ICON.equals(currentName)) {
                    this.mHasIcon = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.OPEN_DATE.equals(currentName)) {
                    this.mOpenDate = BaseModel.parseIntoDate(jsonParser);
                } else if (ResponseConstants.ICON.equals(currentName)) {
                    this.mIcon = (Image) BaseModel.parseObject(jsonParser, Image.class);
                } else if (ResponseConstants.LOGIN_NAME.equals(currentName)) {
                    this.mLoginName = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.SHOP_ID, this.mShopId.getId());
        return hashMap;
    }
}
