package com.etsy.android.lib.models.finds;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Listing;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class FindsSearchCategory extends BaseFieldModel {
    private static final String FIELD_LISTING = "listing";
    private static final String FIELD_SEARCH = "search";
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_URL = "url";
    protected Listing mListing;
    protected FindsUrl mSearchUrl;
    protected String mTitle;
    protected String mUrl;

    public FindsSearchCategory() {
        this.mTitle = StringUtils.EMPTY;
        this.mUrl = StringUtils.EMPTY;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (FIELD_TITLE.equals(str)) {
            this.mTitle = BaseModel.parseString(jsonParser);
        } else if (FIELD_LISTING.equals(str)) {
            this.mListing = (Listing) BaseModel.parseObject(jsonParser, Listing.class);
        } else if (FIELD_SEARCH.equals(str)) {
            this.mSearchUrl = (FindsUrl) BaseModel.parseObject(jsonParser, FindsUrl.class);
        } else if (!FIELD_URL.equals(str)) {
            return false;
        } else {
            this.mUrl = BaseModel.parseStringURL(jsonParser);
        }
        return true;
    }

    public void parseData(JsonParser jsonParser) {
        super.parseData(jsonParser);
        if (this.mListing != null) {
            this.mSearchUrl.setAnchorListingId(this.mListing.getListingId());
        }
    }

    public int getViewType() {
        return 22;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public Listing getListing() {
        return this.mListing;
    }

    public FindsUrl getSearchUrl() {
        return this.mSearchUrl;
    }

    public String getUrl() {
        return this.mUrl;
    }
}
