package com.etsy.android.lib.models.apiv3;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class Team extends BaseModel {
    protected String mAvatarUrl;
    protected EtsyId mId;
    protected String mName;
    protected String mShortDescription;

    public Team() {
        this.mName = StringUtils.EMPTY;
        this.mAvatarUrl = StringUtils.EMPTY;
        this.mShortDescription = StringUtils.EMPTY;
        this.mId = new EtsyId();
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.GROUP_ID.equals(currentName)) {
                    this.mId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.NAME.equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.AVATAR_URL.equals(currentName)) {
                    this.mAvatarUrl = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.SHORT_DESCRIPTION.equals(currentName)) {
                    this.mShortDescription = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public EtsyId getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getAvatarUrl() {
        return this.mAvatarUrl;
    }

    public String getShortDescription() {
        return this.mShortDescription;
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.TEAM_ID, this.mId.getId());
        return hashMap;
    }
}
