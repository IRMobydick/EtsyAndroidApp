package com.etsy.android.lib.models;

import android.util.Pair;
import com.etsy.android.lib.util.ab;
import com.fasterxml.jackson.core.JsonParser;

public class ImageUrl extends BaseModelImage {
    private static final long serialVersionUID = -457466355359540088L;
    private String mUrl;

    public ImageUrl(String str) {
        this.mUrl = str;
    }

    public String getImageUrl() {
        return this.mUrl;
    }

    public String getImageUrlForPixelWidth(int i) {
        return this.mUrl.replace((CharSequence) IMG_SIZE_FULL.second, getReplaceDimensionForWidth(ab.m3172a(i)));
    }

    protected Pair<Integer, String>[] getImageSizesArray() {
        return IMG_SIZES_ARRAY;
    }

    protected String getLargestDimension() {
        return (String) IMG_SIZE_FULL.second;
    }

    public void parseData(JsonParser jsonParser) {
    }
}
