package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class Source extends BaseModel {
    protected static final String SECTION_THUMBNAIL = "/images/storefronts/shop-section-icon.png";
    protected static final String YOUR_SHOP_THUMBNAIL = "/images/storefronts/shop-icon.png";
    protected static final long serialVersionUID = -5759343128525637898L;
    protected int mCount;
    protected String mFormattedType;
    protected EtsyId mListingId;
    protected String mRawType;
    protected String mThumbnail;

    public Source() {
        this.mRawType = StringUtils.EMPTY;
        this.mThumbnail = StringUtils.EMPTY;
        this.mFormattedType = StringUtils.EMPTY;
        this.mListingId = new EtsyId();
    }

    public String getThumbnail() {
        return this.mThumbnail;
    }

    public boolean isShopThumbnail() {
        return YOUR_SHOP_THUMBNAIL.equalsIgnoreCase(this.mThumbnail);
    }

    public boolean isSectionThumbnail() {
        return SECTION_THUMBNAIL.equalsIgnoreCase(this.mThumbnail);
    }

    public int getCount() {
        return this.mCount;
    }

    public String getFormattedType() {
        return this.mFormattedType;
    }

    public EtsyId getListingId() {
        return this.mListingId;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.THUMBNAIL.equals(currentName)) {
                    this.mThumbnail = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.COUNT.equals(currentName)) {
                    this.mCount = jsonParser.getValueAsInt();
                } else if ("formatted_type".equals(currentName)) {
                    this.mFormattedType = BaseModel.parseString(jsonParser);
                } else if (FindsModule.FIELD_TYPE.equals(currentName)) {
                    currentName = BaseModel.parseString(jsonParser);
                    this.mRawType = currentName;
                    String[] split = currentName.split("\\.");
                    if (split.length >= 2 && ActivityFeedEntity.LISTING.equalsIgnoreCase(split[0])) {
                        this.mListingId.setId(split[1]);
                    }
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.STATS_SOURCE_TYPE, this.mRawType);
        return hashMap;
    }
}
