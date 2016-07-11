package com.etsy.android.lib.models.apiv3;

import com.fasterxml.jackson.core.JsonParser;

public class ListingCollection extends Collection {
    private boolean mIncludesListing;

    public ListingCollection(Collection collection) {
        super(collection);
        this.mIncludesListing = false;
    }

    public ListingCollection(ListingCollection listingCollection) {
        super(listingCollection);
        this.mIncludesListing = listingCollection.getIncludesListing();
    }

    public boolean getIncludesListing() {
        return this.mIncludesListing;
    }

    protected void parseCollectionField(JsonParser jsonParser, String str) {
        if ("includes_listing".equals(str)) {
            this.mIncludesListing = jsonParser.getValueAsBoolean();
        } else {
            super.parseCollectionField(jsonParser, str);
        }
    }
}
