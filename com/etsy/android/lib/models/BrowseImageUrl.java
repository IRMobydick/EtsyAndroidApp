package com.etsy.android.lib.models;

public class BrowseImageUrl extends ImageUrl {
    private static final long serialVersionUID = 3679844244828447538L;

    public BrowseImageUrl(String str) {
        super(str);
    }

    protected String getLargestDimension() {
        return (String) IMG_SIZE_570.second;
    }
}
