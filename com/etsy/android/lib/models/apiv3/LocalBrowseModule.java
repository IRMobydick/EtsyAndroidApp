package com.etsy.android.lib.models.apiv3;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class LocalBrowseModule extends BaseModel {
    private static final long serialVersionUID = 8004075899610331269L;
    protected String mItemType;
    protected LocalBrowseLandingPage mLandingPage;
    protected List<LocalMarketCard> mLocalMarkets;
    protected String mTitle;

    public LocalBrowseModule() {
        this.mTitle = StringUtils.EMPTY;
        this.mItemType = StringUtils.EMPTY;
        this.mLocalMarkets = new ArrayList();
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getItemType() {
        return this.mItemType;
    }

    public List<LocalMarketCard> getLocalMarkets() {
        return this.mLocalMarkets;
    }

    public LocalBrowseLandingPage getLandingPage() {
        return this.mLandingPage;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.ITEM_TYPE.equals(currentName)) {
                    this.mItemType = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.LANDING_PAGE.equals(currentName)) {
                    this.mLandingPage = (LocalBrowseLandingPage) BaseModel.parseObject(jsonParser, LocalBrowseLandingPage.class);
                } else if (ResponseConstants.LOCAL_MARKET.equals(currentName)) {
                    this.mLocalMarkets = BaseModel.parseArray(jsonParser, LocalMarketCard.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        if (this.mLandingPage != null) {
            return this.mLandingPage.getTrackingParameters();
        }
        return null;
    }
}
