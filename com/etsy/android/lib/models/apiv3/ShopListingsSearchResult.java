package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.uikit.ui.shop.BaseShopHomeFragment;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShopListingsSearchResult extends BaseModel {
    protected List<ListingCard> mListings;
    protected String mSortOrderId;

    public ShopListingsSearchResult() {
        this.mListings = new ArrayList(0);
        this.mSortOrderId = StringUtils.EMPTY;
    }

    public List<ListingCard> getListings() {
        return this.mListings;
    }

    public String getSortOrderId() {
        return this.mSortOrderId;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1101458968:
                        if (currentName.equals(ResponseConstants.LISTING_CARDS)) {
                            obj = null;
                            break;
                        }
                        break;
                    case -374296211:
                        if (currentName.equals(BaseShopHomeFragment.SEARCH_PARAM_SORT_ORDER)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mListings = BaseModel.parseArray(jsonParser, ListingCard.class);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mSortOrderId = BaseModel.parseString(jsonParser);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }
}
