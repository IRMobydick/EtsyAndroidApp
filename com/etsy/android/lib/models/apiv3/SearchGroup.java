package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.EtsyArray;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class SearchGroup extends BaseModel {
    public static final int ALWAYS = 1;
    public static final int LANDSCAPE = 2;
    public static final int NEVER = 0;
    protected long mCount;
    protected EtsyArray mFilterValues;
    protected List<ListingImage> mImages;
    protected List<String> mListingIds;
    protected String mName;
    protected int mWide;

    public SearchGroup() {
        this.mWide = 0;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (!(jsonParser.getCurrentToken() == JsonToken.VALUE_NULL || parseField(jsonParser, currentName))) {
                jsonParser.skipChildren();
            }
        }
    }

    protected boolean parseField(JsonParser jsonParser, String str) {
        if (ResponseConstants.COUNT.equals(str)) {
            this.mCount = (long) jsonParser.getIntValue();
            return true;
        } else if (ResponseConstants.NAME.equals(str)) {
            this.mName = BaseModel.parseString(jsonParser);
            return true;
        } else if (FindsModule.FIELD_IMAGES.equals(str)) {
            this.mImages = BaseModel.parseArray(jsonParser, ListingImage.class);
            return true;
        } else if (ResponseConstants.LISTING_IDS.equals(str)) {
            this.mListingIds = BaseModel.parseStringArray(jsonParser);
            return true;
        } else if (!ResponseConstants.FILTER_VALUES.equals(str)) {
            return false;
        } else {
            this.mFilterValues = (EtsyArray) BaseModel.parseObject(jsonParser, EtsyArray.class);
            return true;
        }
    }

    public String getName() {
        return this.mName;
    }

    public List<ListingImage> getImages() {
        return this.mImages;
    }

    public TaxonomyNode buildTaxonomyNode() {
        JSONObject data = this.mFilterValues.getData();
        String str = StringUtils.EMPTY;
        return new TaxonomyNode(data.optString(ResponseConstants.TAXONOMY_ID), this.mName, this.mName);
    }

    public int getViewType() {
        return 14;
    }

    public void setWide(int i) {
        this.mWide = i;
    }

    public int isWide() {
        return this.mWide;
    }
}
