package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.text.Collator;
import java.util.HashMap;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class Segment extends BaseModel implements Comparable<Segment> {
    private static final long serialVersionUID = -412269556842274950L;
    protected boolean mHasChildren;
    protected String mImageUrl;
    protected int mListingId;
    protected String mName;
    protected String mPath;
    protected int mShopId;
    protected String mShopName;
    protected String mStandaloneText;
    protected int mWeight;

    public Segment() {
        this.mName = StringUtils.EMPTY;
        this.mStandaloneText = StringUtils.EMPTY;
        this.mPath = StringUtils.EMPTY;
        this.mImageUrl = StringUtils.EMPTY;
        this.mShopName = StringUtils.EMPTY;
    }

    public String getDisplayName() {
        return bh.m3340a(this.mStandaloneText) ? this.mStandaloneText : this.mName;
    }

    public String getPath() {
        return this.mPath;
    }

    public boolean hasChildren() {
        return this.mHasChildren;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public int getShopId() {
        return this.mShopId;
    }

    public int getListingId() {
        return this.mListingId;
    }

    public String getShopName() {
        return this.mShopName;
    }

    public int getWeight() {
        return this.mWeight;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setPath(String str) {
        this.mPath = str;
    }

    public void setWeight(int i) {
        this.mWeight = i;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                parseField(jsonParser, currentName);
            }
        }
    }

    protected void parseField(JsonParser jsonParser, String str) {
        if (ResponseConstants.NAME.equals(str)) {
            this.mName = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.PATH.equals(str)) {
            this.mPath = BaseModel.parseStringURL(jsonParser);
        } else if (ResponseConstants.HAS_CHILDREN.equals(str)) {
            this.mHasChildren = jsonParser.getValueAsBoolean();
        } else if (ResponseConstants.IMAGE_URL.equals(str)) {
            this.mImageUrl = BaseModel.parseStringURL(jsonParser);
        } else if (ResponseConstants.SHOP_ID.equals(str)) {
            this.mShopId = jsonParser.getValueAsInt();
        } else if (ResponseConstants.SHOP_NAME.equals(str)) {
            this.mShopName = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.LISTING_ID.equals(str)) {
            this.mListingId = jsonParser.getValueAsInt();
        } else if (ResponseConstants.WEIGHT.equals(str)) {
            this.mWeight = jsonParser.getValueAsInt();
        } else if (ResponseConstants.STANDALONE_TEXT.equals(str)) {
            this.mStandaloneText = BaseModel.parseString(jsonParser);
        } else {
            jsonParser.skipChildren();
        }
    }

    public int compareTo(Segment segment) {
        return Collator.getInstance(Locale.getDefault()).compare(this.mName, segment.getDisplayName());
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.CATEGORY_SEGMENT, this.mPath);
        return hashMap;
    }
}
