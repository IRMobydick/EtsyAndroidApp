package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class Attendee extends BaseModel {
    private static String UPCOMING_STATUS_PAST;
    protected String mAvatarUrl;
    protected String mComment;
    protected List<ListingImage> mImages;
    protected LocalMarketAttendeeSchedule mSchedule;
    protected EtsyId mShopId;
    protected String mShopLocation;
    protected String mShopName;
    protected String mShopUrl;
    protected String mUpcomingStatus;
    protected String mUpcomingStatusLabel;
    protected EtsyId mUserId;

    static {
        UPCOMING_STATUS_PAST = "past";
    }

    public Attendee() {
        this.mUserId = new EtsyId();
        this.mShopId = new EtsyId();
        this.mShopName = StringUtils.EMPTY;
        this.mShopLocation = StringUtils.EMPTY;
        this.mAvatarUrl = StringUtils.EMPTY;
        this.mShopUrl = StringUtils.EMPTY;
        this.mComment = StringUtils.EMPTY;
        this.mUpcomingStatus = StringUtils.EMPTY;
        this.mUpcomingStatusLabel = StringUtils.EMPTY;
        this.mImages = new ArrayList();
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public EtsyId getShopId() {
        return this.mShopId;
    }

    public String getShopName() {
        return this.mShopName;
    }

    public String getShopLocation() {
        return this.mShopLocation;
    }

    public String getAvatarUrl() {
        return this.mAvatarUrl;
    }

    public String getShopUrl() {
        return this.mShopUrl;
    }

    public String getComment() {
        return this.mComment;
    }

    public List<ListingImage> getListingImages() {
        return this.mImages;
    }

    public LocalMarketAttendeeSchedule getSchedule() {
        return this.mSchedule;
    }

    public boolean hasSchedule() {
        return this.mSchedule != null;
    }

    public String getUpcomingStatusLabel() {
        return this.mUpcomingStatusLabel;
    }

    public boolean attendedInPast() {
        return UPCOMING_STATUS_PAST.equals(this.mUpcomingStatus);
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.USER_ID.equals(currentName)) {
                    this.mUserId.setId(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.SHOP_ID.equals(currentName)) {
                    this.mShopId.setId(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.SHOP_NAME.equals(currentName)) {
                    this.mShopName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SHOP_NAME.equals(currentName)) {
                    this.mShopName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SHOP_LOCATION.equals(currentName)) {
                    this.mShopLocation = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.AVATAR_URL.equals(currentName)) {
                    this.mAvatarUrl = BaseModel.parseStringURL(jsonParser);
                } else if (FindsModule.FIELD_IMAGES.equals(currentName)) {
                    this.mImages = BaseModel.parseArray(jsonParser, ListingImage.class);
                } else if (ResponseConstants.SHOP_URL.equals(currentName)) {
                    this.mShopUrl = BaseModel.parseStringURL(jsonParser);
                } else if ("comment".equals(currentName)) {
                    this.mComment = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.UPCOMING_STATUS.equals(currentName)) {
                    this.mUpcomingStatus = BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser);
                } else if (ResponseConstants.UPCOMING_STATUS_LABEL.equals(currentName)) {
                    this.mUpcomingStatusLabel = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SCHEDULE.equals(currentName)) {
                    this.mSchedule = (LocalMarketAttendeeSchedule) BaseModel.parseObject(jsonParser, LocalMarketAttendeeSchedule.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        if (this.mShopId.hasId()) {
            hashMap.put(AnalyticsLogAttribute.SHOP_ID, this.mShopId.getId());
        } else if (this.mUserId.hasId()) {
            hashMap.put(AnalyticsLogAttribute.TARGET_USER_ID, this.mUserId.getId());
        }
        return hashMap;
    }
}
