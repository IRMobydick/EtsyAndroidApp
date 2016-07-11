package com.etsy.android.lib.models.apiv3;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class LocalBrowseLandingPage extends BaseModel {
    private static final long serialVersionUID = -7767547258118799963L;
    protected String mAnalyticsEventName;
    protected String mApiPath;
    protected String mLinkTitle;

    public LocalBrowseLandingPage() {
        this.mLinkTitle = StringUtils.EMPTY;
        this.mAnalyticsEventName = StringUtils.EMPTY;
        this.mApiPath = StringUtils.EMPTY;
    }

    public String getLinkTitle() {
        return this.mLinkTitle;
    }

    public String getAnalyticsEventName() {
        return this.mAnalyticsEventName;
    }

    public String getApiPath() {
        return this.mApiPath;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.LINK_TITLE.equals(currentName)) {
                    this.mLinkTitle = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.EVENT_NAME.equals(currentName)) {
                    this.mAnalyticsEventName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.API_PATH.equals(currentName)) {
                    this.mApiPath = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.LOCAL_BROWSE_LANDING_PAGE, this.mAnalyticsEventName);
        return hashMap;
    }
}
