package com.etsy.android.lib.models;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.interfaces.LocalMarketLike;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class LocalMarketCard extends BaseModel implements LocalMarketLike {
    private static final long serialVersionUID = 8987333212902948869L;
    protected String mDateSubtitle;
    protected String mLat;
    protected List<ListingImage> mListingImages;
    protected EtsyId mLocalMarketId;
    protected String mLocation;
    protected String mLon;
    protected int mSellerCount;
    protected List<LocalStoreImage> mStoreImages;
    protected String mTitle;
    protected String mType;

    public LocalMarketCard() {
        this.mTitle = StringUtils.EMPTY;
        this.mType = StringUtils.EMPTY;
        this.mDateSubtitle = StringUtils.EMPTY;
        this.mLat = StringUtils.EMPTY;
        this.mLon = StringUtils.EMPTY;
        this.mLocation = StringUtils.EMPTY;
        this.mSellerCount = 0;
        this.mLocalMarketId = new EtsyId();
        this.mListingImages = new ArrayList();
        this.mStoreImages = new ArrayList();
    }

    public EtsyId getLocalMarketId() {
        return this.mLocalMarketId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getType() {
        return this.mType;
    }

    public String getDateSubtitle() {
        return this.mDateSubtitle;
    }

    public String getLat() {
        return this.mLat;
    }

    public String getLon() {
        return this.mLon;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public int getSellerCount() {
        return this.mSellerCount;
    }

    public List<ListingImage> getListingImages() {
        return this.mListingImages;
    }

    public List<LocalStoreImage> getStoreImages() {
        return this.mStoreImages;
    }

    public String getMarketTypeLabelString(Resources resources) {
        return LocalMarket.MARKET_TYPE_WHOLESALE_BUYER.equals(getType()) ? resources.getString(R.local_store) : resources.getString(R.local_event);
    }

    public String getAttendanceSummary(Resources resources) {
        String str = StringUtils.EMPTY;
        if (getSellerCount() <= 0) {
            return str;
        }
        return resources.getQuantityString(LocalMarket.MARKET_TYPE_WHOLESALE_BUYER.equals(getType()) ? R.recently_featured_etsy_sellers : R.etsy_shops_attending, getSellerCount(), new Object[]{Integer.valueOf(getSellerCount())});
    }

    public String getMarketType() {
        return this.mType;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.LOCAL_MARKET_ID.equals(currentName)) {
                    this.mLocalMarketId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser);
                } else if (FindsModule.FIELD_TYPE.equals(currentName)) {
                    this.mType = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.DATE_SUBTITLE.equals(currentName)) {
                    this.mDateSubtitle = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.LATITUDE.equals(currentName)) {
                    this.mLat = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else if (ResponseConstants.LONGITUDE.equals(currentName)) {
                    this.mLon = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else if (ResponseConstants.LOCATION.equals(currentName)) {
                    this.mLocation = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SELLER_COUNT.equals(currentName)) {
                    this.mSellerCount = Integer.parseInt(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.LISTING_IMAGES.equals(currentName)) {
                    this.mListingImages = BaseModel.parseArray(jsonParser, ListingImage.class);
                } else if (ResponseConstants.STORE_IMAGES.equals(currentName)) {
                    this.mStoreImages = BaseModel.parseArray(jsonParser, LocalStoreImage.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.LOCAL_MARKET_ID, this.mLocalMarketId.getId());
        hashMap.put(AnalyticsLogAttribute.LOCAL_MARKET_TYPE, getType());
        return hashMap;
    }
}
