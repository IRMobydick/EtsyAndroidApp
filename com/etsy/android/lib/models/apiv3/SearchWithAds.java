package com.etsy.android.lib.models.apiv3;

import android.text.TextUtils;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class SearchWithAds extends BaseModel {
    protected ListingCard mAnchorListing;
    protected int mCount;
    protected FacetCountListMap mFacetCounts;
    protected List<ListingCard> mListingCardList;
    protected List<SearchGroup> mSearchGroups;
    protected int mShopSearchResultCount;
    protected String mSpellingCorrection;
    protected SuggestedShop mSuggestedShop;
    protected int mUserSearchResultCount;

    public SearchWithAds() {
        this.mFacetCounts = new FacetCountListMap();
        this.mSearchGroups = new ArrayList();
    }

    public int getCount() {
        return this.mCount;
    }

    public List<ListingCard> getListingCardList() {
        return this.mListingCardList;
    }

    public SuggestedShop getSuggestedShop() {
        return this.mSuggestedShop;
    }

    public int getShopSearchResultCount() {
        return this.mShopSearchResultCount;
    }

    public int getUserSearchResultCount() {
        return this.mUserSearchResultCount;
    }

    public String getSpellingCorrection() {
        return this.mSpellingCorrection;
    }

    public boolean hasSpellingCorrection() {
        return !TextUtils.isEmpty(getSpellingCorrection());
    }

    public FacetCountListMap getFacetCountListMap() {
        return this.mFacetCounts;
    }

    public List<SearchGroup> getSearchGroups() {
        return this.mSearchGroups;
    }

    public ListingCard getAnchorListing() {
        return this.mAnchorListing;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.COUNT.equals(currentName)) {
                    this.mCount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.RESULTS.equals(currentName)) {
                    this.mListingCardList = BaseModel.parseArray(jsonParser, ListingCard.class);
                } else if ("suggested_shop".equals(currentName)) {
                    this.mSuggestedShop = (SuggestedShop) BaseModel.parseObject(jsonParser, SuggestedShop.class);
                } else if ("shop_search_result_count".equals(currentName)) {
                    this.mShopSearchResultCount = jsonParser.getValueAsInt();
                } else if ("user_search_result_count".equals(currentName)) {
                    this.mUserSearchResultCount = jsonParser.getValueAsInt();
                } else if ("spelling_correction".equals(currentName)) {
                    this.mSpellingCorrection = BaseModel.parseString(jsonParser);
                } else if ("facet_counts".equals(currentName)) {
                    this.mFacetCounts = (FacetCountListMap) BaseModel.parseObject(jsonParser, FacetCountListMap.class);
                } else if ("groups".equals(currentName)) {
                    this.mSearchGroups = BaseModel.parseArray(jsonParser, SearchGroup.class);
                } else if (ResponseConstants.ANCHOR_LISTING.equals(currentName)) {
                    ListingCard listingCard = (ListingCard) BaseModel.parseObject(jsonParser, ListingCard.class);
                    if (!listingCard.hasError()) {
                        this.mAnchorListing = listingCard;
                    }
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
