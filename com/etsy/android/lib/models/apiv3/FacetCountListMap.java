package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class FacetCountListMap extends BaseModel {
    protected List<FacetCount> mCategoryFacets;
    protected MarketplaceFacets mMarketplaceFacets;

    @Parcel
    public class MarketplaceFacets {
        protected boolean mHasHandmade;
        protected boolean mHasVintage;

        MarketplaceFacets() {
        }

        public static MarketplaceFacets empty() {
            return new MarketplaceFacets(new ArrayList(0));
        }

        private MarketplaceFacets(List<FacetCount> list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                FacetCount facetCount = (FacetCount) list.get(i);
                if (facetCount.getId().equals(ResponseConstants.HANDMADE)) {
                    this.mHasHandmade = true;
                } else if (facetCount.getId().equals(ResponseConstants.VINTAGE)) {
                    this.mHasVintage = true;
                }
            }
        }

        public boolean hasHandmadeResults() {
            return this.mHasHandmade;
        }

        public boolean hasVintageResults() {
            return this.mHasVintage;
        }
    }

    public List<FacetCount> getCategoryFacetCounts() {
        return this.mCategoryFacets != null ? this.mCategoryFacets : new ArrayList();
    }

    public MarketplaceFacets getMarketplaceFacetCounts() {
        return this.mMarketplaceFacets != null ? this.mMarketplaceFacets : MarketplaceFacets.empty();
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (FindsModule.FIELD_CATEGORIES.equals(currentName)) {
                    this.mCategoryFacets = BaseModel.parseArray(jsonParser, FacetCount.class);
                } else if (ResponseConstants.MARKETPLACES.equals(currentName)) {
                    this.mMarketplaceFacets = new MarketplaceFacets(null);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
