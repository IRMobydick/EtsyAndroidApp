package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class LocalBrowseResponse extends BaseModel {
    private static final long serialVersionUID = 896833550731586676L;
    protected List<LocalMarketCard> mNearbyMarkets;
    protected int mRadius;
    protected List<LocalBrowseModule> mSections;

    public LocalBrowseResponse() {
        this.mSections = new ArrayList();
        this.mNearbyMarkets = new ArrayList();
    }

    public List<LocalBrowseModule> getSections() {
        return this.mSections;
    }

    public List<LocalMarketCard> getNearbyMarkets() {
        return this.mNearbyMarkets;
    }

    public int getRadius() {
        return this.mRadius;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (FindsModule.FIELD_SECTIONS.equals(currentName)) {
                    this.mSections = BaseModel.parseArray(jsonParser, LocalBrowseModule.class);
                } else if (ResponseConstants.NEARBY.equals(currentName)) {
                    this.mNearbyMarkets = BaseModel.parseArray(jsonParser, LocalMarketCard.class);
                } else if (ResponseConstants.RADIUS.equals(currentName)) {
                    this.mRadius = jsonParser.getValueAsInt();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
