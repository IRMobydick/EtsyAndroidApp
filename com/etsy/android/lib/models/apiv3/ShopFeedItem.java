package com.etsy.android.lib.models.apiv3;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class ShopFeedItem extends BaseModel implements ShopLike {
    private static final long serialVersionUID = 5446744307790834525L;
    private int mActiveListingCount;
    private List<ListingCard> mDisplayListings;
    private String mHeadline;
    private String mLocation;
    private double mRating;
    private int mReviewCount;
    private String mSellerAvatar;
    private ShopIcon mShopIcon;
    private EtsyId mShopId;
    private String mShopName;
    private String mUrl;
    private EtsyId mUserId;

    public ShopFeedItem() {
        this.mShopId = new EtsyId();
        this.mUserId = new EtsyId();
        this.mShopName = StringUtils.EMPTY;
        this.mSellerAvatar = StringUtils.EMPTY;
        this.mLocation = StringUtils.EMPTY;
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

    public boolean hasUpcomingLocalEvent() {
        return false;
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

    public boolean hasRatings() {
        return this.mReviewCount > 0;
    }

    public double getAverageRating() {
        return this.mRating;
    }

    public int getNumRatings() {
        return this.mReviewCount;
    }

    public LocalMarket getUpcomingLocalEvent() {
        return null;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getHeadline() {
        return this.mHeadline;
    }

    public int getActiveListingCount() {
        return this.mActiveListingCount;
    }

    public String getIconUrl(int i) {
        if (this.mShopIcon != null) {
            return this.mShopIcon.getImageUrlForPixelWidth(i);
        }
        return null;
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
                } else if (ResponseConstants.SELLER_AVATAR.equals(currentName)) {
                    this.mSellerAvatar = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.ACTIVE_LISTING_COUNT.equals(currentName)) {
                    this.mActiveListingCount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.LOCATION.equals(currentName)) {
                    this.mLocation = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.DISPLAY_LISTINGS.equals(currentName)) {
                    this.mDisplayListings = BaseModel.parseArray(jsonParser, ListingCard.class);
                } else if (ResponseConstants.RATING.equals(currentName)) {
                    JsonNode jsonNode = (JsonNode) jsonParser.readValueAsTree();
                    this.mRating = jsonNode.get(ResponseConstants.RATING).asDouble();
                    this.mReviewCount = jsonNode.get(ResponseConstants.REVIEW_COUNT).asInt();
                } else if (ResponseConstants.HEADLINE.endsWith(currentName)) {
                    this.mHeadline = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SHOP_ICON_URL_FULL.equals(currentName)) {
                    this.mShopIcon = new ShopIcon(BaseModel.parseStringURL(jsonParser));
                } else if (ResponseConstants.URL.equals(currentName)) {
                    this.mUrl = BaseModel.parseStringURL(jsonParser);
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
