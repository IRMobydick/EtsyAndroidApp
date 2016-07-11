package com.etsy.android.lib.models.homescreen;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.parceler.Parcel;

@Parcel
public class CategoryRecommendationCard extends BaseModel {
    private static final String LANDING_PAGE_NAME = "category_recommendation";
    private static final String RESPONSE_CATEGORY_NAME = "category";
    private static final String RESPONSE_LISTING_CARDS = "listings";
    protected String apiPath;
    protected String categoryName;
    protected int listingCount;
    protected List<ListingCard> listings;

    public class CategoryLandingPage implements LandingPageInfo {
        public int getLayout() {
            return 0;
        }

        public String getEventName() {
            return CategoryRecommendationCard.LANDING_PAGE_NAME;
        }

        public String getPageTitle() {
            return CategoryRecommendationCard.this.getCategoryName();
        }

        public String getPageType() {
            return CategoryRecommendationCard.RESPONSE_LISTING_CARDS;
        }

        public String getAPIPath() {
            return CategoryRecommendationCard.this.getApiPath();
        }

        public int getRequestMethod() {
            return 0;
        }

        public HashMap<String, String> getParams() {
            return null;
        }

        public Map<String, String> getOptions() {
            return null;
        }

        public boolean getBooleanOption(String str) {
            return false;
        }
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            Object obj = -1;
            switch (currentName.hashCode()) {
                case 50511102:
                    if (currentName.equals(RESPONSE_CATEGORY_NAME)) {
                        obj = null;
                        break;
                    }
                    break;
                case 94851343:
                    if (currentName.equals(ResponseConstants.COUNT)) {
                        obj = 3;
                        break;
                    }
                    break;
                case 967303978:
                    if (currentName.equals(ResponseConstants.API_PATH)) {
                        obj = 2;
                        break;
                    }
                    break;
                case 1346279023:
                    if (currentName.equals(RESPONSE_LISTING_CARDS)) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case Task.NETWORK_STATE_CONNECTED /*0*/:
                    this.categoryName = BaseModel.parseString(jsonParser);
                    break;
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    this.listings = BaseModel.parseArray(jsonParser, ListingCard.class);
                    break;
                case Task.NETWORK_STATE_ANY /*2*/:
                    this.apiPath = BaseModel.parseStringURL(jsonParser);
                    break;
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    this.listingCount = jsonParser.getValueAsInt();
                    break;
                default:
                    jsonParser.skipChildren();
                    break;
            }
        }
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public List<ListingCard> getListings() {
        return this.listings;
    }

    public int getListingCount() {
        return this.listingCount;
    }

    public String getApiPath() {
        return this.apiPath;
    }

    public LandingPageInfo getListingLink() {
        if (getApiPath() != null) {
            return new CategoryLandingPage();
        }
        return null;
    }
}
