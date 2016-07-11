package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.ResponseConstants.Includes;
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
public class Treasury extends BaseModel {
    protected static final long serialVersionUID = 6501417274508310869L;
    protected String mDescription;
    protected String mId;
    protected List<Listing> mListings;
    protected String mTitle;
    protected TreasuryCounts mTreasuryCounts;
    protected EtsyId mUserId;
    protected String mUserName;

    public Treasury() {
        this.mId = StringUtils.EMPTY;
        this.mTitle = StringUtils.EMPTY;
        this.mUserName = StringUtils.EMPTY;
        this.mUserId = new EtsyId();
        this.mListings = new ArrayList(0);
    }

    public String getId() {
        return this.mId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public List<Listing> getListings() {
        return this.mListings;
    }

    public int getViews() {
        if (this.mTreasuryCounts != null) {
            return this.mTreasuryCounts.getViews();
        }
        return 0;
    }

    public int getClicks() {
        if (this.mTreasuryCounts != null) {
            return this.mTreasuryCounts.getClicks();
        }
        return 0;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.ID.equals(currentName)) {
                    this.mId = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.DESCRIPTION.equals(currentName)) {
                    this.mDescription = BaseModel.parseString(jsonParser);
                } else if ("user_name".equals(currentName)) {
                    this.mUserName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.USER_ID.equals(currentName)) {
                    this.mUserId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if ("counts".equals(currentName)) {
                    this.mTreasuryCounts = (TreasuryCounts) BaseModel.parseObject(jsonParser, TreasuryCounts.class);
                } else if (Includes.LISTINGS.equals(currentName)) {
                    this.mListings = BaseModel.parseArray(jsonParser, Listing.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.TREASURY_ID, this.mId);
        return hashMap;
    }
}
