package com.etsy.android.lib.models.apiv3;

import com.fasterxml.jackson.core.JsonParser;

public class CheckableListingCollection extends ListingCollection {
    private boolean mIsChecked;

    public CheckableListingCollection(ListingCollection listingCollection) {
        super(listingCollection);
        this.mIsChecked = false;
    }

    public boolean getIsChecked() {
        return this.mIsChecked;
    }

    public void setIsChecked(boolean z) {
        this.mIsChecked = z;
    }

    public boolean getWasChanged() {
        return this.mIsChecked != getIncludesListing();
    }

    protected void parseCollectionField(JsonParser jsonParser, String str) {
        if ("includes_listing".equals(str)) {
            this.mIsChecked = jsonParser.getValueAsBoolean();
        }
        super.parseCollectionField(jsonParser, str);
    }
}
